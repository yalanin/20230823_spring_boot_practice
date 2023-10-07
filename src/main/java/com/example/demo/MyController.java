package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
public class MyController {
    @Autowired
    @Qualifier("epsonPrinter")
    private Printer printer;

//  @ResponseBody
    @RequestMapping("/user")
    public Student user() {
        Student student = new Student();
        student.setName("Tom");
        return student;
    }

    @RequestMapping("/product")
    public Store product() {
        Store store = new Store();
        List<String> list = new ArrayList<>();
        list.add("蘋果");
        list.add("橘子");
        store.setProductList(list);
        return store;
    }

    @RequestMapping("/test1")
    public String test1(@RequestParam(defaultValue = "12") Integer id,
                       @RequestParam(required = false, name="nameTest") String name) {
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        return "Hello test1";
    }

    @RequestMapping("/test2")
    public String test2(@RequestBody Student student) {
        System.out.println("student id: " + student.getId());
        System.out.println("student name:" + student.getName());
        return "Hello test2";
    }

    @RequestMapping("/test3")
    public String test3(@RequestHeader String info) {
        System.out.println("info: " + info);
        return "Hello test3";
    }

    @RequestMapping("/test4/{id}")
    public String test4(@PathVariable Integer id) {
        System.out.println("id: " + id);
        return "Hello test4";
    }
}
