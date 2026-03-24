package com.example.practice1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//new 키워드 없이도 동작하게 함, ctrl + E
@RestController
public class HelloController {

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    // http method + URL, 어떤 리소스에 어떠한 행위를 할 것인지
    @GetMapping("/hello")
    public String hello(){
        String str = helloService.getString();
        return str;
    }


}
