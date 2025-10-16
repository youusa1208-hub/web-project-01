package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLoginLog {
    private Integer id; // ID
    private String username; // ログインユーザー名
    private String password; // ログインパスワード
    private LocalDateTime loginTime; // ログイン時間
    private Short isSuccess; // ログイン成功かどうか, 1:成功, 0:失敗
    private String jwt; // 成功後、発行されるJWTトークン
    private Long costTime; // ログイン所要時間, 単位:ms
}
