package com.company.backstagecontentmanagementsystem.controller;

import com.company.backstagecontentmanagementsystem.config.Constant;
import com.company.backstagecontentmanagementsystem.domain.Category;
import com.company.backstagecontentmanagementsystem.domain.User;
import com.company.backstagecontentmanagementsystem.response.ListResult;
import com.company.backstagecontentmanagementsystem.response.Result;
import com.company.backstagecontentmanagementsystem.service.CategoryService;
import com.company.backstagecontentmanagementsystem.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Result createCategory(@CookieValue(Constant.USER_TOKEN) String token, @RequestBody Category category,
                                 HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        category.setUser(new User(userId));
        boolean created = categoryService.createCategory(category);
        if (created) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.CREATE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/update")
    public Result updateCategory(@RequestBody Category category) {
        boolean updated = categoryService.updateCategory(category);
        if (updated) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.UPDATE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/delete")
    public Result deleteCategory(@RequestBody Category category) {
        boolean deleted = categoryService.deleteCategory(category.getCatId());
        if (deleted) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/delete_bundle")
    public Result deleteCategories(@RequestBody List<Category> categories) {
        boolean ret = true;
        for (Category category : categories) {
            boolean deleted = categoryService.deleteCategory(category.getCatId());
            ret &= deleted;
        }
        if (ret) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/query")
    public ListResult queryAllCategories(@CookieValue(Constant.USER_TOKEN) String token, @RequestParam(value = "page_index",
            defaultValue = "1") int pageIndex, @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name", required = false) String name, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        logger.info("pageIndex:{}, pageSize:{}, name:{}", pageIndex, pageSize, name);
        List<Category> categories = categoryService.queryAllCategories(pageIndex, pageSize, name, userId);
        if (categories != null) {
            int count;
            if (StringUtils.isEmpty(name)) {
                count = categoryService.queryCatCount(userId);
            } else {
                count = categories.size();
            }
            return ListResult.createOk(categories, count);
        } else {
            return ListResult.createNo(Result.ErrorCode.QUERY_CATEGORY_FAILED.getMessage());
        }
    }
}

