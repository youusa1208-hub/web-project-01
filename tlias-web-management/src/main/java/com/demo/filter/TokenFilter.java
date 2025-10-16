package com.demo.filter;


import com.demo.utils.CurrentHolder;
import com.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") // すべてのリクエストをフィルタリング
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =  (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. リクエストパスを取得
        String requestURI = request.getRequestURI();//  /login

        //2. リクエストパスがログインリクエストかどうかを判断、パスに/loginが含まれている場合はログイン操作であるため通過
        if(requestURI.contains("/login")){
            log.info("ログイン操作、通過...");
            filterChain.doFilter(request,response);
            return;
        }

        //3. リクエストヘッダーからtokenを取得
        String token = request.getHeader("token");

        //4. tokenの存在を確認、tokenが存在しない場合はログインしていないことを意味し、エラー結果を返却（401ステータスコードをレスポンス）
        if(token == null || token.isEmpty()){
            log.info("tokenが存在しない、エラー結果を返却（401ステータスコードをレスポンス）...");
            response.setStatus(401);
            return;
        }

        //5. tokenが存在する場合はトークンを検証、トークン検証に失敗した場合はエラー結果を返却（401ステータスコードをレスポンス）
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);//格納
            log.info("現在のユーザーid:{}、これをThreadLocalに格納",empId);

        } catch (Exception e) {
            log.info("トークン検証に失敗、エラー結果を返却（401ステータスコードをレスポンス）...");
            response.setStatus(401);
            return;
        }

        //6. トークン検証に成功した場合は通過
        log.info("トークン検証に成功、通過...");
        filterChain.doFilter(request,response);

        //7. フィルター実行完了後、ThreadLocalからデータを削除
        CurrentHolder.remove();
    }
}

