package com.company.backstagecontentmanagementsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.company.backstagecontentmanagementsystem.domain.Store;
import java.sql.SQLException;

@Mapper
@Repository
public interface StoreMapper {

    /**
     * 创建店铺
     *
     * @param name
     * @param logo
     * @param address
     * @param category
     * @param description
     * @param phone
     * @param avgPrice
     * @param saleFrom
     * @param saleTo
     * @param userId
     * @throws SQLException
     */
    void createStore(@Param("name") String name, @Param("logo") String logo, @Param("address") String address,
                     @Param("category") String category, @Param("description") String description, @Param("phone") String phone,
                     @Param("avg_price") String avgPrice, @Param("sale_from") String saleFrom, @Param("sale_to") String saleTo,
                     @Param("user_id") int userId) throws SQLException;

    /**
     *
     * @param name
     * @param logo
     * @param address
     * @param category
     * @param description
     * @param phone
     * @param avgPrice
     * @param saleFrom
     * @param saleTo
     * @param userId
     * @throws SQLException
     */
    void updateStore(@Param("name") String name, @Param("logo") String logo, @Param("address") String address,
                     @Param("category") String category, @Param("description") String description, @Param("phone") String phone,
                     @Param("avg_price") String avgPrice, @Param("sale_from") String saleFrom, @Param("sale_to") String saleTo,
                     @Param("user_id") int userId) throws SQLException;

    /**
     *
     * @param userId
     * @return store
     */
    Store queryStore(@Param("user_id") int userId);
}