package com.aimprosoft.department.controller;

import com.aimprosoft.department.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by merovingien on 3/3/14.
 */
@Controller
public class IndexApplicationController {

    @Autowired
    DateUtil dateUtil;

    @RequestMapping("/index")
    public String home() {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listDepartments(ModelMap model) {
        model.put("title", "Home page");

        return "index";
    }

    @RequestMapping(value = "/getMainMap", method = RequestMethod.GET)
    public @ResponseBody Map mainMap() {
        Map<String, String> urlMap = new HashMap<String, String>();
        urlMap.put("employee/list", "List employees");
        urlMap.put("employee/new", "Add employee");
        urlMap.put("department/new", "Add department");
        urlMap.put("department/list", "List departments");
        return urlMap;
    }

    @RequestMapping(value = "/getDayList", method = RequestMethod.GET)
    public @ResponseBody List dayList() {
        return dateUtil.getDayList();
    }

    @RequestMapping(value = "/getMonthMap", method = RequestMethod.GET)
    public @ResponseBody Map monthMap() {
        return dateUtil.getMonthMap();
    }

    @RequestMapping(value = "/getYearList", method = RequestMethod.GET)
    public @ResponseBody List yearList() {
        return dateUtil.getYearList();
    }
}
