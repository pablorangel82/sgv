package br.com.sgv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 13/05/2021
 * @brief  class LoginController
 */
@Controller
public class LoginController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
 
}
