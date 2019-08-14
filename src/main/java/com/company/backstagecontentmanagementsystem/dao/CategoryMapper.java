package com.company.backstagecontentmanagementsystem.dao;

import com.company.backstagecontentmanagementsystem.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    /**
     * @param name
     * @param seq
     * @param userId
     * @return primary key
     * @throws SQLException
     */
    int createCategory(@Param("name") String name, @Param("sequence") int seq, @Param("user_id") int userId) throws SQLException;

    /**
     * @param name
     * @param seq
     * @param catId
     * @return affected rows
     * @throws SQLException
     */
    int updateCategory(@Param("name") String name, @Param("sequence") int seq, @Param("cat_id") int catId) throws SQLException;

    /**
     * @param catId
     * @return affected rows
     * @throws SQLException
     */
    int deleteCategory(@Param("cat_id") int catId) throws SQLException;

    /**
     * @param userId
     * @return
     */
    List<Category> queryAllCategories(@Param("user_id") int userId);

    /**
     * @param userId
     * @return
     */
    Integer queryMaxSeq(@Param("user_id") int userId);
}