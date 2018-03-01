package com.oilseller.oilbrocker.order.param;

import com.oilseller.oilbrocker.history.dto.HistoryType;
import com.oilseller.oilbrocker.order.dto.OrderStatus;

public class OrderHistoryParam {

    private Long historyId;
    private Long orderId;
    private HistoryType historyType;
    private OrderStatus fromStatus;
    private OrderStatus toStatus;
    private String username;
    private String userNote;
    private String historyDate;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public void setHistoryType(HistoryType historyType) {
        this.historyType = historyType;
    }

    public OrderStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(OrderStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public OrderStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrderStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(String historyDate) {
        this.historyDate = historyDate;
    }
}
