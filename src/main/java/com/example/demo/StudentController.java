package com.example.demo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class StudentController {

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
}
