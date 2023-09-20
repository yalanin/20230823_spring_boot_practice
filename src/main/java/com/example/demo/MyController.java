package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
