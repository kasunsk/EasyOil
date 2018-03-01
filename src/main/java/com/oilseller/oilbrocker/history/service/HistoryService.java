package com.oilseller.oilbrocker.history.service;

import com.oilseller.oilbrocker.history.dto.HistoryItem;

import java.util.List;

public interface HistoryService {

    Boolean addHistoryItem(HistoryItem historyItem);

    List<HistoryItem> loadHistoryItemByOrderId(Long orderId);

    List<HistoryItem> loadStatusChangeActivityByOrderId(Long orderId);
}
