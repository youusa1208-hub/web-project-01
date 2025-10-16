package com.demo.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1;//ページ番号
    private Integer pageSize = 10;//1ページあたりの表示レコード数
    private String name;//氏名
    private Integer gender;//性別
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//入社日時-開始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//入社日時-終了
}

