package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.security.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String getLoginPage(){
        logger.info("Open login page");
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam String login,
                         @RequestParam String password,
                         HttpServletResponse response){
        Cookie cookie = loginService.login(login, password);
        if(cookie != null){
            logger.info("Login: " + login + " authorized successfully");
            response.addCookie(cookie);
            return "products";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String signOut(@CookieValue("user-token") Cookie cookie){
        loginService.logout(cookie.getValue());
        return "login";
    }

}
