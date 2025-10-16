package com.demo.service.impl;

import com.demo.mapper.StudentMapper;
import com.demo.pojo.ClazzCountOption;
import com.demo.pojo.PageResult;
import com.demo.pojo.Student;
import com.demo.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        // 1. ページングパラメータを設定
        PageHelper.startPage(page,pageSize);

        // 2. クエリを実行
        List<Student> dataList = studentMapper.list(name,degree,clazzId);

        // 3. 結果をカプセル化
        Page<Student> d = (Page<Student>) dataList;

        return new PageResult<Student>(d.getTotal(),d.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Student student) {
        // 作成時間と更新時間を設定
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        // 学生情報をデータベースに挿入
        studentMapper.insert(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        // 指定されたIDの学生情報を削除
        studentMapper.delete(ids);
    }

    @Override
    public Student getInfo(Integer id) {
        // 指定されたIDの学生情報を取得
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        // 更新時間を設定
        student.setUpdateTime(LocalDateTime.now());
        // 学生情報を更新
        studentMapper.updateById(student);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
        // 指定された学生の違反処理を実行
        studentMapper.violationHandle(id,score);
    }

    @Override
    public List<Map> getStudentDegreeData() {
        // 学生の学歴データ統計を取得
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        // クラス別の学生数統計データを取得
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            // クラス名リストを抽出
            List<Object> clazzList = countList.stream().map(map -> {
                return map.get("cname");
            }).toList();

            // 学生数リストを抽出
            List<Object> dataList = countList.stream().map(map -> {
                return map.get("scount");
            }).toList();

            // 結果をClazzCountOptionオブジェクトにカプセル化して返却
            return new ClazzCountOption(clazzList, dataList);
        }
        // データが存在しない場合はnullを返却
        return null;
    }
}

