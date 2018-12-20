package com.lin.comm.xmparser.demodata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author meilin
 * @Date 2018/10/3117:37
 * @Version 1.0
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "你好";
    }
}
