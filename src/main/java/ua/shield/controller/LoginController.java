package ua.shield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * Created by sa on 02.09.17.
 */
@Controller
public class LoginController {
    @RequestMapping("/logout")
    public String logOut(){
        return "redirect:/login?logout";
    }

    @RequestMapping("/login")
    public String logIn() {
        return "login";
    }

    @RequestMapping("/unregisteredUser")
    public String unregisteredUser() {
        return "unregisteredUser";
    }
}
