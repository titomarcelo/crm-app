package com.tmao.crm.commons.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showWelcomePage(final ModelMap model) {
        return "index";
    }

}
