package com.demo.interceptor;

import com.demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * トークン検証インターセプター
 **/
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1. リクエストパスを取得
//        String requestURI = request.getRequestURI();//  /login
//
//        //2. リクエストパスがログインリクエストかどうかを判定、パスに/loginが含まれる場合はログイン操作であるため通過
//        if(requestURI.contains("/login")){
//            log.info("ログイン操作、通過...");
//            return true;
//        }

        //3. リクエストヘッダーからtokenを取得
        String token = request.getHeader("token");

        //4. tokenの存在を確認、tokenが存在しない場合はログインしていないことを意味し、エラー結果を返却（401ステータスコードをレスポンス）
        if(token == null || token.isEmpty()){
            log.info("tokenが存在しない、エラー結果を返却（401ステータスコードをレスポンス）...");
            response.setStatus(401);
            return false;
        }

        //5. tokenが存在する場合はトークンを検証、トークン検証に失敗した場合はエラー結果を返却（401ステータスコードをレスポンス）
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("トークン検証に失敗、エラー結果を返却（401ステータスコードをレスポンス）...");
            response.setStatus(401);
            return false;
        }

        //6. トークン検証に成功した場合は通過
        log.info("トークン検証に成功、通過...");
        return true;
    }
}

