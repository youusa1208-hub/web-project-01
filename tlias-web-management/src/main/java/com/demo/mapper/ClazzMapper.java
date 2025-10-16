package com.demo.mapper;

import com.demo.pojo.Clazz;
import com.demo.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

import static net.sf.jsqlparser.parser.feature.Feature.insert;

@Mapper
public interface ClazzMapper {

    /**
     * 動的クエリによるクラスリスト検索
     */
    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    /**
     * クラス情報の追加
     */
    @Insert("insert into clazz values(null,#{name},#{room},#{beginDate}," +
            "#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /**
     * クラス情報の一括削除
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteByIds(Integer id);

    /**
     * IDによるクラス情報検索
     */
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 動的クエリによるクラス情報更新
     */
    void update(Clazz clazz);

    /**
     * すべてのクラス情報検索
     */
    @Select("select id, name, begin_date, end_date, master_id, subject, create_time, update_time  from clazz")
    List<Clazz> findAll();

}

