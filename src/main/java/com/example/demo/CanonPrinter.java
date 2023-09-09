package com.example.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//@Component
public class CanonPrinter implements Printer, InitializingBean {
    private int count;

    @Override
    public void print(String message) {
        count--;
        System.out.println("Canon 印表機：" + message);
        System.out.println("剩餘使用次數：" + count);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        count = 20;
    }
}
