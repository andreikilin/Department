package com.aimprosoft.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by merovingien on 3/11/14.
 */
@Controller
public class ErrorPageController {

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public String errorPage403(ModelMap model){
        model.put("title", "Access denied");
        return "error403";
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String errorPage404(ModelMap model){
        model.put("title", "Page not found");
        return "error404";
    }

    @RequestMapping(value = "/error500", method = RequestMethod.GET)
    public String errorPage500(ModelMap model){
        model.put("title", "Internal server error");
        return "error500";
    }
}
