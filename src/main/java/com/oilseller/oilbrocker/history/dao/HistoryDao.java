package com.oilseller.oilbrocker.history.dao;

import com.oilseller.oilbrocker.history.model.HistoryItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("historyDao")
public interface HistoryDao extends JpaRepository<HistoryItemModel,Long> {

    List<HistoryItemModel> findAllByOrderId(Long orderId);
}
