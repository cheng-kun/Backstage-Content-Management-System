package com.company.backstagecontentmanagementsystem.dao;

import com.company.backstagecontentmanagementsystem.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    /**
     *
     * @param goods
     * @return
     * @throws SQLException
     */
    int createGoods(@Param("goods") Goods goods) throws SQLException;

    /**
     *
     * @param goods
     * @return
     * @throws SQLException
     */
    int updateGoods(@Param("goods") Goods goods) throws SQLException;

    /**
     *
     * @param goodsId
     * @return
     * @throws SQLException
     */
    int deleteGoods(@Param("goods_id") int goodsId) throws SQLException;

    /**
     *
     * @param goodsId
     * @return
     */
    Goods queryGoodsById(@Param("goods_id") int goodsId);

    /**
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Goods> queryGoodsByPage(@Param("name") String name, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     *
     * @param sale
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Goods> queryGoodsByPageSale(@Param("sale") int sale, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     *
     * @param userId
     * @return
     */
    int queryCount(@Param("user_id") int userId);

    /**
     *
     * @param sale
     * @param userId
     * @return
     */
    int queryCountBySale(@Param("sale") int sale, @Param("user_id") int userId);

    /**
     *
     * @param goodsId
     * @param picture
     * @return
     * @throws SQLException
     */
    int updateGoodsImage(@Param("goods_id") int goodsId, @Param("picture") String picture) throws SQLException;

    /**
     *
     * @param goodsId
     * @param stock
     * @return
     * @throws SQLException
     */
    int increaseStock(@Param("goods_id") int goodsId, @Param("stock") int stock) throws SQLException;

    /**
     *
     * @param goodsId
     * @param sale
     * @return
     * @throws SQLException
     */
    int changeSale(@Param("goods_id") int goodsId, @Param("sale") boolean sale) throws SQLException;
}
