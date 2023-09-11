package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EpsonPrinter implements Printer {
    @Value("${printer.count:999}")
    private int count;
    @Value("${printer.name}")
    private String name;

    @Override
    public void print(String message) {
        count--;
        System.out.println(name + " " + message);
        System.out.println("剩餘使用次數：" + count);
    }
}
