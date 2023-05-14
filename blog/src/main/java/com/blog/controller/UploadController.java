package com.blog.controller;

import com.blog.pojo.Result;
import com.blog.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    //上传图片
    @PostMapping("/api/blog/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //调用阿里云oss工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功，文件访问的url:{}",url);
        return Result.success(url);
    }
}
