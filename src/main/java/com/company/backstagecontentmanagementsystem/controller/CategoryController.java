package com.company.backstagecontentmanagementsystem.controller;

import com.company.backstagecontentmanagementsystem.config.Constant;
import com.company.backstagecontentmanagementsystem.domain.Category;
import com.company.backstagecontentmanagementsystem.handler.Result;
import com.company.backstagecontentmanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Result createCategory(@CookieValue(Constant.USER_TOKEN) String token, @RequestBody Category category,
                                 HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        boolean created = categoryService.createCategory(category.getName(), userId);
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
    public Result deleteCategory(@RequestParam("cat_id") int catId) {
        boolean deleted = categoryService.deleteCategory(catId);
        if (deleted) {
            return Result.createYesResult();
        } else {
            return Result.createNoResult(Result.ErrorCode.DELETE_CATEGORY_FAILED);
        }
    }

    @PostMapping("/query_all")
    public Result queryAllCategories(@CookieValue(Constant.USER_TOKEN) String token, HttpServletRequest request) {
        int userId = (int) WebUtils.getSessionAttribute(request, token);
        List<Category> categories = categoryService.queryAllCategories(userId);
        if (categories != null) {
            return Result.createYesResult(categories);
        } else {
            return Result.createNoResult(Result.ErrorCode.QUERY_CATEGORY_FAILED);
        }
    }
}
