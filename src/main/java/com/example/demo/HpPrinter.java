package com.example.demo;

import org.springframework.stereotype.Component;

//@Component
public class HpPrinter implements Printer{
    @Override
    public void print(String message) {
        System.out.println("HP 印表機：" + message);
    }
}
