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
     *
     * @param category
     * @return affected rows
     * @throws SQLException
     */
    int createCategory(@Param("category") Category category) throws SQLException;

    /**
     *
     * @param category
     * @return affected rows
     * @throws SQLException
     */
    int updateCategory(@Param("category") Category category) throws SQLException;

    /**
     *
     * @param catId
     * @return affected rows
     * @throws SQLException
     */
    int deleteCategory(@Param("cat_id") int catId) throws SQLException;

    /**
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Category> queryCategoryByPage(@Param("name") String name, @Param("page_index") int pageIndex,
                                       @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     *
     * @param userId
     * @return
     */
    Integer queryMaxSeq(@Param("user_id") int userId);

    /**
     *
     * @param userId
     * @return
     */
    int queryCatCount(@Param("user_id") int userId);

    /**
     *
     * @param name
     * @return
     */
    Integer queryCatIdByName(@Param("name") String name);
}
