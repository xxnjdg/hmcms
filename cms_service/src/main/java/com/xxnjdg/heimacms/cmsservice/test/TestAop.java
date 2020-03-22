package com.xxnjdg.heimacms.cmsservice.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAop {

    @GetMapping("/testRuntimeException")
    public void test(){
        throw new RuntimeException("my custom runtime exception");
    }

}
