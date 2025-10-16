package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; // ID
    private String name; // クラス名
    private String room; // 教室
    private LocalDate beginDate; // 開講日時
    private LocalDate endDate; // 終講日時
    private Integer masterId; // 担任教師
    private Integer subject; // 学科
    private LocalDateTime createTime; // 作成日時
    private LocalDateTime updateTime; // 更新日時

    private String masterName; // 担任教師名
    private String status; // クラス状態 - 未開講 , 在学中 , 終講済
}

