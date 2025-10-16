package com.demo.service.impl;

import com.demo.mapper.EmpMapper;
import com.demo.mapper.StudentMapper;
import com.demo.pojo.ClazzCountOption;
import com.demo.pojo.JobOption;
import com.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1. Mapperインターフェースのメソッドを呼び出して統計データを取得
        List<Map<String, Object>> list = empMapper.countEmpJobData();//map : pos=教研主管，num=1

        // 2. 結果を組み立てて返却
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        // 従業員の性別別人数統計データを取得
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        // 学生の学歴別統計データを取得
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        // クラス別学生数統計データを取得
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

