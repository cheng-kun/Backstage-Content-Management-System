package com.company.backstagecontentmanagementsystem.service;

import com.company.backstagecontentmanagementsystem.dao.StoreMapper;
import com.company.backstagecontentmanagementsystem.domain.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StoreService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private StoreMapper storeMapper;

    @Autowired
    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public boolean createStore(Store store) {
        try {
            storeMapper.createStore(store.getName(), store.getLogo(), store.getAddress(), store.getCategory(), store.getDescription(),
                    store.getPhone(), store.getAvgPrice(), store.getSaleFrom(), store.getSaleTo(), store.getUser().getUserId());
            return true;
        } catch (SQLException e) {
            logger.error("create store failed", e);
        }
        return false;
    }

    public boolean updateStore(Store store) {
        try {
            storeMapper.updateStore(store.getName(), store.getLogo(), store.getAddress(), store.getCategory(), store.getDescription(),
                    store.getPhone(), store.getAvgPrice(), store.getSaleFrom(), store.getSaleTo(), store.getUser().getUserId());
            return true;
        } catch (SQLException e) {
            logger.error("update store failed", e);
        }
        return false;
    }

    public Store queryStore(int userId) {
        return storeMapper.queryStore(userId);
    }
}
