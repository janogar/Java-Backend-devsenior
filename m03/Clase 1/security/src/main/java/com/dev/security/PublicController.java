package com.dev.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/products/public")
public class PublicController {

    @GetMapping 
    public String publicEndPoint(){
        return "Este endpoint es publico";
    }
}
