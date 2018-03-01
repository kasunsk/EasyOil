package com.oilseller.oilbrocker.history.dao;

import com.oilseller.oilbrocker.history.model.HistoryItemModel;

import java.util.List;

public interface HistoryDao {

    Long addHistoryItem(HistoryItemModel historyItemModel);

    List<HistoryItemModel> loadHistoryByOrderId(Long orderId);
}
