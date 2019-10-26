package com.company.backstagecontentmanagementsystem.controller;

import com.company.backstagecontentmanagementsystem.response.Result;
import com.company.backstagecontentmanagementsystem.service.GoodsService;
import com.company.backstagecontentmanagementsystem.service.StoreService;
import com.company.backstagecontentmanagementsystem.util.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${upload.dir}")
    private String uploadDir;
    @Value("${upload.dir.suffix}")
    private String uploadDirSuffix;

    private GoodsService goodsService;
    private StoreService storeService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/image_store", method = RequestMethod.POST)
    public Result uploadStoreImage(@RequestParam(value = "file") MultipartFile file, @RequestParam("store_id") int storeId) {
        if (file.isEmpty()) {
            return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_EMPTY);
        }

        String randomFileName = getRandomFileName(file);
        File dest = getDestFile(randomFileName, uploadDir);
        logger.info("upload file path：" + dest);
        try {
            file.transferTo(dest);
            storeService.updateStoreLogo(storeId, uploadDirSuffix + randomFileName);
            return Result.createYesResult();
        } catch (IOException e) {
            logger.error("upload fail", e);
        }
        return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_FAILED);
    }

    @RequestMapping(value = "/image_goods", method = RequestMethod.POST)
    public Result uploadGoodsImage(@RequestParam(value = "file") MultipartFile file, @RequestParam("goods_id") int goodsId) {
        if (file.isEmpty()) {
            return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_EMPTY);
        }

        String randomFileName = getRandomFileName(file);
        File dest = getDestFile(randomFileName, uploadDir);
        logger.info("upload file path：{}", dest);
        try {
            file.transferTo(dest);
            goodsService.updateGoodsPicture(goodsId, uploadDirSuffix + randomFileName);
            return Result.createYesResult();
        } catch (IOException e) {
            logger.error("upload fail", e);
        }
        return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_FAILED);
    }

    private File getDestFile(String fileName, String uploadDir) {
        File dest = new File(uploadDir, fileName);
        File parentFile = dest.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return dest;
    }

    private String getRandomFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        return ApiUtils.getUUID() + suffixName;
    }
}
