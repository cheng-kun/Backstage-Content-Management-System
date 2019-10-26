package com.company.backstagecontentmanagementsystem.dao;


import com.company.backstagecontentmanagementsystem.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@Mapper
public interface MemberMapper {
    /**
     *
     * @param member
     * @return
     * @throws SQLException
     */
    int createMember(@Param("member") Member member) throws SQLException;

    /**
     *
     * @param member
     * @return
     * @throws SQLException
     */
    int updateMember(@Param("member") Member member) throws SQLException;

    /**
     *
     * @param memberId
     * @return
     * @throws SQLException
     */
    int deleteMember(@Param("member_id") int memberId) throws SQLException;

    /**
     *
     * @param nickname
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    List<Member> queryMemberByPage(@Param("nickname") String nickname, @Param("page_index") int pageIndex, @Param("page_size") int pageSize, @Param("user_id") int userId);

    /**
     *
     * @param memberId
     * @return
     */
    Member queryMemberById(@Param("member_id") int memberId);

    /**
     *
     * @param userId
     * @return
     */
    int queryMemberCount(@Param("user_id") int userId);
}
