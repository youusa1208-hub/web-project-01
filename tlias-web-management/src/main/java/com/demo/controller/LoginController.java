package com.demo.controller;

import com.demo.pojo.Emp;
import com.demo.pojo.LoginInfo;
import com.demo.pojo.Result;
import com.demo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ログインリクエストController
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * ログインリクエスト
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("従業員ログイン：{}",emp);
        LoginInfo info = empService.login(emp);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("ユーザー名またはパスワードが間違っています");
    }
}
