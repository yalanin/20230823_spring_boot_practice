package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class StudentController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        String sql = "INSERT INTO student(id, name) VALUES(:studentId, :studentName)";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", student.getId());
        map.put("studentName", student.getName());
        namedParameterJdbcTemplate.update(sql, map);
        return "執行 insert sql";
    }

    @DeleteMapping("delete_students/{studentId}")
    public String delete_sql(@PathVariable Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        namedParameterJdbcTemplate.update(sql, map);
        return "執行 delete sql";
    }
}
