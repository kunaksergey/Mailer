package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.User;
import ua.shield.exeption.UserIsNotRegisteredExeption;
import ua.shield.service.SecurityServiceImpl;

/**
 * Created by sa on 01.09.17.
 */
@Controller
public class MainController {

    @Autowired
    SecurityServiceImpl securityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goIndex() {
        return "redirect:/index.xhtml";
    }
}
