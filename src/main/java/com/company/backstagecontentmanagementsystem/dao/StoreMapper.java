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
     *
     * @param store
     * @return affected rows
     * @throws SQLException
     */
    int createStore(@Param("store") Store store) throws SQLException;

    /**
     *
     * @param store
     * @return affected rows
     * @throws SQLException
     */
    int updateStore(@Param("store") Store store) throws SQLException;

    /**
     *
     * @param userId
     * @return store
     */
    Store queryStore(@Param("user_id") int userId);

    /**
     *
     * @param storeId
     * @param logo
     * @return
     * @throws SQLException
     */
    int updateStoreLogo(@Param("store_id") int storeId, @Param("logo") String logo) throws SQLException;
}
