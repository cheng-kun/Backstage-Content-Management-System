package com.company.backstagecontentmanagementsystem.controller;

import com.company.backstagecontentmanagementsystem.config.Constant;
import com.company.backstagecontentmanagementsystem.domain.Member;
import com.company.backstagecontentmanagementsystem.domain.User;
import com.company.backstagecontentmanagementsystem.handler.Result;
import com.company.backstagecontentmanagementsystem.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/create")
    @ResponseBody
    public Result createMember(@RequestBody Member member, @CookieValue(Constant.USER_TOKEN) String token,
                               HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        member.setUser(new User(userId));
        boolean ret = memberService.createMember(member);
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_MEMBER_FAILED);
        }
    }

    @PostMapping("/member/update")
    @ResponseBody
    public Result updateMember(@RequestBody Member member) {
        boolean ret = memberService.updateMember(member);
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_MEMBER_FAILED);
        }
    }

    @PostMapping("/member/delete")
    @ResponseBody
    public Result deleteMember(@RequestBody Member member) {
        boolean ret = memberService.deleteMember(member.getMemberId());
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_MEMBER_FAILED);
        }
    }

    @PostMapping("/member/delete_bundle")
    @ResponseBody
    public Result deleteMembers(@RequestBody List<Member> members) {
        boolean ret = true;
        for (Member member : members) {
            boolean del = memberService.deleteMember(member.getMemberId());
            ret &= del;
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_MEMBER_FAILED);
        }
    }

    @PostMapping("/member/query")
    @ResponseBody
    public ListResult queryAllMembers(@CookieValue(Constant.USER_TOKEN) String token, @RequestParam(value = "page_index",
            defaultValue = "1") int pageIndex, @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "nickname", required = false) String nickname, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("pageIndex:{}, pageSize:{}, nickname:{}", pageIndex, pageSize, nickname);
        List<Member> members = memberService.queryMemberByPage(userId, pageIndex, pageSize, nickname);
        if (members != null) {
            int count;
            if (StringUtils.isEmpty(nickname)) {
                count = memberService.queryMemberCount(userId);
            } else {
                count = members.size();
            }
            return ListResult.createOk(members, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_MEMBER_FAILED.getMessage());
        }
    }

    @GetMapping("/member_edit")
    public ModelAndView editMember(@RequestParam("member_id") int id) {
        ModelAndView modelAndView = new ModelAndView("member_edit");
        Member member = memberService.queryMemberById(id);
        modelAndView.addObject("member", member);
        return modelAndView;
    }
}
