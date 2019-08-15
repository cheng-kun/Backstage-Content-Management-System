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
     * @param name
     * @param picture
     * @param specification
     * @param price
     * @param stock
     * @param saleVolume
     * @param cost
     * @param catId
     * @param userId
     * @return
     * @throws SQLException
     */
    int createGoods(@Param("name") String name, @Param("picture") String picture, @Param("specification") String specification,
                    @Param("price") double price, @Param("stock") int stock, @Param("sale_volume") int saleVolume,
                    @Param("cost") double cost, @Param("cat_id") int catId, @Param("user_id") int userId) throws SQLException;

    /**
     *
     * @param name
     * @param picture
     * @param specification
     * @param price
     * @param stock
     * @param saleVolume
     * @param cost
     * @param onSale
     * @param catId
     * @param goodsId
     * @return
     * @throws SQLException
     */
    int updateGoods(@Param("name") String name, @Param("picture") String picture, @Param("specification") String specification,
                    @Param("price") double price, @Param("stock") int stock, @Param("sale_volume") int saleVolume,
                    @Param("cost") double cost, @Param("on_sale") boolean onSale, @Param("cat_id") int catId,
                    @Param("goods_id") int goodsId) throws SQLException;

    /**
     * 删除商品
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
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Goods> queryGoodsByPage(@Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);
}