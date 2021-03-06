package com.mycompany.myapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", params = "error", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
