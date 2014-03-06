package com.aimprosoft.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String,String> urlMap= new HashMap<String, String>();
        urlMap.put("employee/add/", "Add employee");
        urlMap.put("department/add/", "Add department");
        model.put("title", title);
        model.addAttribute("urlMap", urlMap);

        return "index";
    }
}
