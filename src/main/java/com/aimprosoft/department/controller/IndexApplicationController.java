package com.aimprosoft.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by merovingien on 3/3/14.
 */
@Controller
public class IndexApplicationController {

    @RequestMapping("/index")
    public String home() {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listDepartments(ModelMap model){
        String title = "Home page";
        model.addAttribute("title", title);
        return "index";
    }
}
