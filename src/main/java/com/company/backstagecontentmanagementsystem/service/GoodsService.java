package com.company.backstagecontentmanagementsystem.service;

import com.company.backstagecontentmanagementsystem.dao.CategoryMapper;
import com.company.backstagecontentmanagementsystem.dao.GoodsMapper;
import com.company.backstagecontentmanagementsystem.domain.Category;
import com.company.backstagecontentmanagementsystem.domain.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GoodsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private GoodsMapper goodsMapper;
    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @CacheEvict(value = "createGoods", key = "'goods_count'")
    public boolean createGoods(Goods goods) {
        int catId = categoryMapper.queryCatIdByName(goods.getCategory().getName());
        goods.setCategory(new Category(catId));
        try {
            int key = goodsMapper.createGoods(goods);
            return key > 0;
        } catch (SQLException e) {
            logger.error("create goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "updateGoods", key = "'goods_count'")
    public boolean updateGoods(Goods goods) {
        int catId = categoryMapper.queryCatIdByName(goods.getCategory().getName());
        goods.setCategory(new Category(catId));
        try {
            int affected = goodsMapper.updateGoods(goods);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update goods failed", e);
        }
        return false;
    }

    @CacheEvict(value = "deleteGoods", key = "'goods_count'")
    public boolean deleteGoods(int goodsId) {
        try {
            int affected = goodsMapper.deleteGoods(goodsId);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("delete goods failed", e);
        }
        return false;
    }

    //@Cacheable(value = "goodsByPage", key = "'goodsPage'")
    public List<Goods> queryGoodsByPage(int pageIndex, int pageSize, String name, int sale, int userId) {
        if (--pageIndex < 0) {
            pageIndex = 0;
        }
        if (sale == 0) {
            if (name == null) {
                name = "";
            }
            return goodsMapper.queryGoodsByPage(name, pageIndex * pageSize, pageSize, userId);
        } else {
            return goodsMapper.queryGoodsByPageSale(sale == 1 ? 1 : 0, pageIndex * pageSize, pageSize, userId);
        }
    }

    //@Cacheable(value = "queryCount", key = "'goods_count'")
    public int queryCount(int userId, int sale) {
        if (sale == 0) {
            return goodsMapper.queryCount(userId);
        } else {
            return goodsMapper.queryCountBySale(sale == 1 ? 1 : 0, userId);
        }
    }

    public Goods queryGoodsById(int goodsId) {
        return goodsMapper.queryGoodsById(goodsId);
    }

    @CacheEvict(value = "updateGoodsPicture", key = "'goods_count'")
    public boolean updateGoodsPicture(int goodsId, String picture) {
        try {
            int affected = goodsMapper.updateGoodsImage(goodsId, picture);
            return affected > 0;
        } catch (SQLException e) {
            logger.error("update goods failed", e);
        }
        return false;
    }

    public boolean increaseStock(int goodsId, int stock) {
        try {
            int i = goodsMapper.increaseStock(goodsId, stock);
            return i > 0;
        } catch (SQLException e) {
            logger.error("increase stock failed", e);
        }
        return false;
    }

    public boolean changeSale(int goodsId, boolean onSale) {
        try {
            int i = goodsMapper.changeSale(goodsId, onSale);
            return i > 0;
        } catch (SQLException e) {
            logger.error("change sale failed", e);
        }
        return false;
    }
}
