package com.company.backstagecontentmanagementsystem.service;

import com.company.backstagecontentmanagementsystem.dao.StoreMapper;
import com.company.backstagecontentmanagementsystem.domain.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
            int key = storeMapper.createStore(store);
            return key > 0;
        } catch (SQLException e) {
            logger.error("create store failed", e);
        }
        return false;
    }

    @CacheEvict(value = "updateStore", key = "'storeInfo'")
    public boolean updateStore(Store store) {
        try {
            int affected = storeMapper.updateStore(store);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update store failed", e);
        }
        return false;
    }

    @CacheEvict(value = "updateStore", key = "'storeInfo'")
    public boolean updateStoreLogo(int storeId, String logo) {
        int affected = 0;
        try {
            affected = storeMapper.updateStoreLogo(storeId, logo);
        } catch (SQLException e) {
            logger.error("update store failed", e);
        }
        return affected > 0;
    }

    @Cacheable(value = "queryStore", key = "'storeInfo'")
    public Store queryStore(int userId) {
        return storeMapper.queryStore(userId);
    }
}
