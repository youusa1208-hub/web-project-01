package com.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {

    // リクエスト処理前に呼び出し（Controllerメソッド呼び出し前） - trueを返すと続行（次のインターセプターまたはハンドラーの呼び出し）、
    // falseを返すと処理中断（後続のインターセプターまたはハンドラーは呼び出されない）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle .....");
        return true;
    }

    // リクエスト処理後に呼び出し（Controllerメソッド呼び出し後）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle .....");
    }

    // リクエスト処理完了後のコールバックメソッド、つまりビュー描画完了時にコールバック、パフォーマンス監視ではここで終了時間を記録し消費時間を出力できます、
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion .....");
    }
}

