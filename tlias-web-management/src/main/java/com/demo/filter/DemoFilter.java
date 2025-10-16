package com.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // すべてのリクエストをフィルタリング
@Slf4j
public class DemoFilter implements Filter {

    /**
     * 初期化メソッド、Webサーバー起動時に実行され、一度だけ実行される
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初期化メソッド ....");
    }

    /**
     * フィルターメソッド、Webサーバーがリクエストをキャッチするたびに実行される
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("フィルターDemoFilterがリクエストをキャッチ...");
        // 通過
        filterChain.doFilter(servletRequest,servletResponse);

        log.info("フィルターDemoFilterが通過...");
    }

    /**
     * 破棄メソッド、Webサーバー終了時に実行され、一度だけ実行される
     */
    @Override
    public void destroy() {
        log.info("destroy 破棄メソッド ....");
    }
}

