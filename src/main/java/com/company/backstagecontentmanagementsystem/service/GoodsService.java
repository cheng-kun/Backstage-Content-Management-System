package com.company.backstagecontentmanagementsystem.service;

import com.company.backstagecontentmanagementsystem.dao.GoodsMapper;
import com.company.backstagecontentmanagementsystem.domain.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GoodsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private GoodsMapper goodsMapper;

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @CacheEvict(value = "deleteGoods", key = "'goodsPage'")
    public boolean createGoods(Goods goods, int userId) {
        try {
            int key = goodsMapper.createGoods(goods.getGname(), goods.getPicture(), goods.getSpecification(), goods.getPrice(), goods.getStock(),
                    goods.getSaleVolume(), goods.getCost(), goods.getCategory().getCatId(), userId);
            return key > 0;
        } catch (SQLException e) {
            logger.error("create goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "deleteGoods", key = "'goodsPage'")
    public boolean updateGoods(Goods goods) {
        try {
            int affected = goodsMapper.updateGoods(goods.getGname(), goods.getPicture(), goods.getSpecification(), goods.getPrice(), goods.getStock(),
                    goods.getSaleVolume(), goods.getCost(), goods.isOnSale(), goods.getCategory().getCatId(), goods.getGoodsId());
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "deleteGoods", key = "'goodsPage'")
    public boolean deleteGoods(int goodsId) {
        try {
            int affected = goodsMapper.deleteGoods(goodsId);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("delete goods failed", e);
        }
        return false;
    }

    @Cacheable(value = "goodsByPage", key = "'goodsPage'")
    public List<Goods> queryGoodsByPage(int pageIndex, int pageSize, int userId) {
        if (--pageIndex < 0) {
            pageIndex = 0;
        }
        return goodsMapper.queryGoodsByPage(pageIndex * pageSize, pageSize, userId);
    }
}