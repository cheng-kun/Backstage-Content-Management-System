package com.company.backstagecontentmanagementsystem.dao;


import com.company.backstagecontentmanagementsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;

@Repository
@Mapper
public interface UserMapper {

    /**
     *
     * @param phone
     * @param password
     * @return
     */
    User login(@Param("phone") String phone, @Param("password") String password);

    /**
     *
     * @param phone
     * @param password
     * @return affected rows
     * @throws SQLException
     */
    int register(@Param("phone") String phone, @Param("password") String password) throws SQLException;

    /**
     *
     * @param phone
     * @return
     */
    int findCountByPhone(@Param("phone") String phone);

    /**
     *
     * @param userId
     * @return
     */
    User findUserById(@Param("user_id") int userId);

    /**
     *
     * @param userId last
     * @return affected rows
     * @throws SQLException
     */
    int updateVisitTime(@Param("user_id") int userId) throws SQLException;

    /**
     *
     * @return
     */
    int findLastUserId();
}
