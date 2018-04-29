package com.oilseller.oilbrocker.order.service.impl;

import com.oilseller.oilbrocker.email.dto.EmailParam;
import com.oilseller.oilbrocker.email.service.EmailService;
import com.oilseller.oilbrocker.history.dto.HistoryItem;
import com.oilseller.oilbrocker.history.dto.HistoryType;
import com.oilseller.oilbrocker.history.service.HistoryService;
import com.oilseller.oilbrocker.order.adaptor.model.CustomerModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderDetailModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderPlacementEntityAdaptor;
import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dao.OrderDao;
import com.oilseller.oilbrocker.order.dto.*;
import com.oilseller.oilbrocker.order.entity.CustomerEntity;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.order.service.OrderService;
import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import com.oilseller.oilbrocker.product.dto.Product;
import com.oilseller.oilbrocker.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.oilseller.oilbrocker.platform.util.ValidationUtil.validate;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private CustomerDao customerDao;
    private OrderDao orderDao;
    private EmailService emailService;
    private HistoryService historyService;
    private ProductService productService;
    private CustomerModelAdaptor customerModelAdaptor = new CustomerModelAdaptor();
    private OrderPlacementEntityAdaptor orderPlacementModelAdaptor = new OrderPlacementEntityAdaptor();
    private OrderDetailModelAdaptor orderModelAdaptor = new OrderDetailModelAdaptor();

    @Autowired
    public OrderServiceImpl(CustomerDao customerDao, OrderDao orderDao, ProductService productService,
                            EmailService emailService, HistoryService historyService) {
        this.customerDao = customerDao;
        this.productService = productService;
        this.orderDao = orderDao;
        this.emailService = emailService;
        this.historyService = historyService;
    }

    @Transactional
    @Override
    public Long addCustomerDetails(Customer customer) {
        validateCustomer(customer);
        return customerDao.save(customerModelAdaptor.fromDto(customer)).getCustomerId();
    }

    @Transactional
    @Override
    public Customer getCustomerDetails(Long customerId) {
        return customerModelAdaptor.fromModel(customerDao.findOne(customerId));
    }

    @Transactional
    @Override
    public List<OrderDetail> viewOrders() {
        return orderModelAdaptor.fromModelList((Collection<OrderPlacementEntity>) orderDao.findAll());
    }

    @Transactional
    @Override
    public Boolean updateOrderStatus(Long orderId, OrderStatus toOrderStatus) {
        OrderPlacementEntity order = orderDao.findOne(orderId);

        if (order == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Order not found");
        }
        OrderStatus currentOrderStatus = order.getOrderStatus();

        if (currentOrderStatus.equals(toOrderStatus)) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "Order status are same");
        }
        order.setOrderStatus(toOrderStatus);
        orderDao.save(order);
        addHistoryItem(order, HistoryType.ORDER_SC,  currentOrderStatus.name(), toOrderStatus.name());
        sendOrderStatusUpdateEmail(toOrderStatus, order);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Order update(Order order) {

        OrderPlacementEntity orderEntity = orderDao.findOne(order.getOrderId());

        if (orderEntity == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Order not found");
        }

        OrderStatus currentOrderStatus = orderEntity.getOrderStatus();
        orderEntity.setOrderStatus(order.getOrderStatus());

        Long currentPrice = orderEntity.getPrice();
        orderEntity.setPrice(order.getPrice());

        PaymentStatus currentPaymentStatus = orderEntity.getPaymentStatus();
        orderEntity.setPaymentStatus(order.getPaymentStatus());

        PaymentType paymentType = orderEntity.getPaymentType();
        orderEntity.setPaymentType(order.getPaymentType());

        orderDao.save(orderEntity);

        if (currentOrderStatus != order.getOrderStatus()) {
            addHistoryItem(orderEntity, HistoryType.ORDER_SC, currentOrderStatus.name(), order.getOrderStatus().name());
            sendOrderStatusUpdateEmail(order.getOrderStatus(), orderEntity);
        }

        if (!Objects.equals(currentPrice, order.getPrice())) {
            addHistoryItem(orderEntity, HistoryType.PAYMENT_AC, currentPrice.toString(), order.getPrice().toString());
        }

        if (currentPaymentStatus != order.getPaymentStatus()) {
            addHistoryItem(orderEntity, HistoryType.PAYMENT_SC, currentPaymentStatus.name(), order.getPaymentStatus().name());
        }

        if (paymentType != order.getPaymentType()) {
            addHistoryItem(orderEntity, HistoryType.PAYMENT_TC, paymentType.name(), order.getPaymentType().name());
        }
        return order;
    }

    private void addHistoryItem(OrderPlacementEntity order, HistoryType historyType, String currentStatus, String toPaymentType) {

        HistoryItem historyItem = new HistoryItem();
        historyItem.setHistoryType(historyType);
        historyItem.setFromStatus(currentStatus);
        historyItem.setToStatus(toPaymentType);
        historyItem.setOrderId(order.getOrderId());
        historyItem.setUsername(ThreadLocalContext.getUser());
        historyItem.setUserNote(order.getOrderItem());
        historyService.addHistoryItem(historyItem);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String placeOrder(OrderPlacementRequest orderPlacementRequest) {
        validateOrderPlacement(orderPlacementRequest);
        Product product = productService.loadProduct(orderPlacementRequest.getOrderItemId());
        validateSubmittedPrice(orderPlacementRequest, product);
        OrderPlacementEntity orderEntity = orderPlacementModelAdaptor.fromDto(orderPlacementRequest);
        String orderReference = generateOrderReference(orderPlacementRequest);
        orderEntity.setOrderReference(orderReference);
        productService.reduceProductAmount(product.getId(), orderPlacementRequest.getAmount());

        orderEntity.setOrderItem(product.getSellingItem());
        Long customerId = customerDao.save(customerModelAdaptor.fromDto(orderPlacementRequest.getCustomer())).getCustomerId();
        orderEntity.setCustomerId(customerId);
        orderEntity.setPaymentReference("COD");
        orderEntity.setOrderStatus(OrderStatus.PLACED);
        orderDao.save(orderEntity);
        addHistoryItem(orderPlacementRequest, orderEntity);
        sendOrderCreateEmail(orderPlacementRequest, orderEntity, orderReference);
        return orderEntity.getOrderReference();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order loadOrder(Long orderId) {

        OrderPlacementEntity orderEntity = orderDao.findOne(orderId);
        if (orderEntity == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Order not found");
        }
        Order order = getOrder(orderEntity);
        return order;
    }

    private Order getOrder(OrderPlacementEntity orderEntity) {
        CustomerEntity customerEntity = customerDao.findOne(orderEntity.getCustomerId());
        if (customerEntity == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Customer details not found");
        }
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setOrderItem(orderEntity.getOrderItem());
        order.setPrice(orderEntity.getPrice());
        order.setPaymentStatus(orderEntity.getPaymentStatus());
        order.setPaymentType(orderEntity.getPaymentType());
        order.setCustomerAddress(customerEntity.getCustomerAddress());
        order.setOrderReference(orderEntity.getOrderReference());
        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setCustomerEmail(customerEntity.getEmail());
        order.setCustomerMobile(customerEntity.getMobileNumber());
        order.setCustomerName(customerEntity.getCustomerName());
        order.setCreatedDate(DateUtil.toSimpleDate(customerEntity.getCreatedDate()));
        return order;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order loadOrderByReference(String reference) {
        OrderPlacementEntity orderEntity = orderDao.findByOrderReference(reference);
        if (orderEntity == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Order not found");
        }
        Order order = getOrder(orderEntity);
        return order;

    }

    private void validateSubmittedPrice(OrderPlacementRequest orderPlacementRequest, Product product) {
        Long requestedAmount = orderPlacementRequest.getAmount();
        Long pricePerOneItem = product.getPrice();
        Long submittedPrice = orderPlacementRequest.getPrice();
        Long requiredAMount = requestedAmount * pricePerOneItem;

        if (requiredAMount > submittedPrice) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "Entered price is wrong");
        }
        if (requestedAmount < submittedPrice) {
            log.info("User entered more than required price");
        }
    }

    private void sendOrderStatusUpdateEmail(OrderStatus toOrderStatus, OrderPlacementEntity order) {
        EmailParam emailParam = new EmailParam();
        emailParam.setSubject("Order Status Changed");
        emailParam.setReceiverAddress(customerDao.findOne(order.getCustomerId()).getEmail());
        emailParam.setContent(" Your order status changed to " + toOrderStatus);
        emailService.sendEmail(emailParam);
    }

    private void addHistoryItem(OrderPlacementRequest orderPlacementRequest, OrderPlacementEntity orderEntity) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setHistoryType(HistoryType.ORDER_CREATED);
        historyItem.setOrderId(orderEntity.getOrderId());
        historyItem.setSellingItemId(orderPlacementRequest.getOrderItemId());
        historyItem.setUsername("CUSTOMER");
        historyItem.setUserNote("Order Created");
        historyService.addHistoryItem(historyItem);
    }

    private void sendOrderCreateEmail(OrderPlacementRequest orderPlacementRequest, OrderPlacementEntity orderEntity, String orderReference) {
        EmailParam emailParam = new EmailParam();
        emailParam.setSubject("Order Placed");
        emailParam.setReceiverAddress(orderPlacementRequest.getCustomer().getEmail());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order Reference : " + orderReference);
        stringBuilder.append(" Order Status : " + orderEntity.getOrderStatus());
        emailParam.setContent(stringBuilder.toString());
        emailService.sendEmail(emailParam);
    }


    private String generateOrderReference(OrderPlacementRequest orderPlacementRequest) {
        String name = orderPlacementRequest.getCustomer().getCustomerName();
        String partOne = name.substring(0, 2);
        String currentTime = new Date().getTime() + "";
        String partThree = currentTime.substring(currentTime.length() - 2, currentTime.length());
        String address = orderPlacementRequest.getCustomer().getCustomerAddress();
        String partTwo = address.substring(0, 2);
        String partFour = orderPlacementRequest.getOrderItemId() + "";
        String mobile = orderPlacementRequest.getCustomer().getMobileNumber();
        String partFive = mobile.substring(mobile.length() - 3, mobile.length());
        String reference = partOne + partTwo + partThree + partFour + partFive;
        return reference.toLowerCase().trim();
    }

    private void validateOrderPlacement(OrderPlacementRequest orderPlacementRequest) {

        validate(orderPlacementRequest, "Invalid order request");
        validateCustomer(orderPlacementRequest.getCustomer());
        validate(orderPlacementRequest.getAmount(), "Amount is empty");
        validate(orderPlacementRequest.getOrderItemId(), "Order Item id is null");
        validate(orderPlacementRequest.getCurrency(), "Currency is empty");
        validate(orderPlacementRequest.getPrice(), "Price is empty");
//        validate(orderPlacementRequest.getPaymentStatus(), "Payment status can not be empty");

        if (orderPlacementRequest.getPaymentStatus() == null) {
            orderPlacementRequest.setPaymentStatus(PaymentStatus.WAITING);
        }
    }

    private void validateCustomer(Customer customer) {
        validate(customer, "Customer data can not be null");
        validate(customer.getCustomerName(), "Customer name is empty");
        validate(customer.getCustomerAddress(), "Customer address is empty");
        validate(customer.getMobileNumber(), "Customer mobile number is empty");
//        validate(customer.getEmail(), "Customer email is empty");
    }
}
