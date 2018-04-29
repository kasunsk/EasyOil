package com.oilseller.oilbrocker.history.model;

import com.oilseller.oilbrocker.history.dto.HistoryType;
import com.oilseller.oilbrocker.order.dto.OrderStatus;
import com.oilseller.oilbrocker.platform.entity.AbstractTrackableEntity;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY_ITEM")
public class HistoryItemModel extends AbstractTrackableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID", nullable = false)
    private Long historyId;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "SELLING_ITEM_ID")
    private Long sellingItemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "HISTORY_TYPE", nullable = false)
    private HistoryType historyType;

    @Column(name = "FROM_STATUS")
    private String fromStatus;

    @Column(name = "TO_STATUS")
    private String toStatus;

    @Column(name = "NOTE")
    private String historyNote;

    @Column(name = "USER")
    private String username;

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

    public Long getSellingItemId() {
        return sellingItemId;
    }

    public void setSellingItemId(Long sellingItemId) {
        this.sellingItemId = sellingItemId;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public void setHistoryType(HistoryType historyType) {
        this.historyType = historyType;
    }

    public String getHistoryNote() {
        return historyNote;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public void setHistoryNote(String historyNote) {
        this.historyNote = historyNote;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
