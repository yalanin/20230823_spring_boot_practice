package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.mapper.StudentRowMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class StudentController {
    @Autowired
    @Qualifier("test1JdbcTemplate")
    private NamedParameterJdbcTemplate test1JdbcTemplate;

    @Autowired
    @Qualifier("test2JdbcTemplate")
    private NamedParameterJdbcTemplate test2JdbcTemplate;

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
//    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String create(@RequestBody @Valid Student student) {
        return "資料庫 create 操作";
    }

    @GetMapping("/students/{studentId}")
    public String read(@PathVariable @Min(20) Integer studentId) {
        return "資料庫 read 操作";
    }

    @PutMapping("/students/{studentId}")
    public String update(@PathVariable Integer studentId,
                         @RequestBody Student student) {
        return "資料庫 update 操作";
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId) {
        return "資料庫 delete 操作";
    }

    @PostMapping("insert_students")
    public String insert_sql(@RequestBody Student student) {
        String sql = "INSERT INTO student(name) VALUES(:studentName)";
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        test1JdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int id = keyHolder.getKey().intValue();
        System.out.println("mysql 自動生成 id: " + id);
        return "執行 insert sql";
    }

    @PostMapping("/students/batch")
    public String insert_list(@RequestBody List<Student> studentList) {
        String sql = "INSERT INTO student(name) VALUES(:studentName)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];
        for(int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
        }
        test1JdbcTemplate.batchUpdate(sql, parameterSources);
        return "執行批次寫入 sql";
    }

    @DeleteMapping("/delete_students/{studentId}")
    public String delete_sql(@PathVariable Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        test1JdbcTemplate.update(sql, map);
        return "執行 delete sql";
    }

    @GetMapping("/students/select")
    public List<Student> select_sql() {
        String sql = "SELECT id, name FROM student";
        Map<String, Object> map = new HashMap<>();
        List<Student> list = test1JdbcTemplate.query(sql, map, new StudentRowMapper());
        return list;
    }

    @GetMapping("/select_students/{studentId}")
    public Student select_student(@PathVariable Integer studentId) {
        return studentService.getById(studentId);
    }
}
