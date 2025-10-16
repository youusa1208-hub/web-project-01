package com.demo.controller;

import com.demo.pojo.Result;
import com.demo.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * ローカルディスクストレージ方式
     */
//    @PostMapping("/upload")
//    public Result upload (String name, Integer age, MultipartFile file) throws IOException {
//        log.info("パラメータ受信:{},{},{}",name,age,file);
//        //元のファイル名を取得
//        String originalFilename = file.getOriginalFilename();
//
//        //新しいファイル名
//        String extension =  originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() +  extension;
//
//        //ファイルを保存
//        file.transferTo(new File("D:/back/images/" + newFileName));
//        return Result.success();

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("ファイルアップロード:{}",file.getOriginalFilename());
        //ファイルをOSSストレージ管理に委ねる
        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("ファイルアップロード成功:{}",url);

        return Result.success(url);
    }
}

