package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id; // ID
    private String name; // 氏名
    private String no; // 学籍番号
    private Integer gender; // 性別 , 1: 男 , 2 : 女
    private String phone; // 携帯電話番号
    private String idCard; // 身分証明書番号
    private Integer isCollege; // 院校出身者かどうか, 1: はい, 0: いいえ
    private String address; // 連絡先住所
    private Integer degree; // 最終学歴, 1: 中学校, 2: 高等学校 , 3: 専門学校 , 4: 大学 , 5: 修士 , 6: 博士
    private LocalDate graduationDate; // 卒業日時
    private Integer clazzId; // クラスID
    private Short violationCount; // 違紀回数
    private Short violationScore; // 違紀減点
    private LocalDateTime createTime; // 作成日時
    private LocalDateTime updateTime; // 更新日時

    private String clazzName;// クラス名
}

