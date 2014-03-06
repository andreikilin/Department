package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.form.DepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by merovingien on 3/6/14.
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentFormValidator departmentFormValidator;

    @RequestMapping(value = "/department/add", method = RequestMethod.GET)
    public String addDepartment(ModelMap model) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.put("title", "Create new department");
        model.put("departmentFormAction", "department/add");
        model.put("editDepartment", departmentForm);
        return "departmentForm";
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public String processDepartment(@ModelAttribute("editDepartment")DepartmentForm departmentForm,BindingResult result, ModelMap model) {
        departmentFormValidator.validate(departmentForm,result);

        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/add");
            model.put("editDepartment", departmentForm);
            return "departmentForm";
        }else {
            Department department = departmentForm.saveDepartment();
            departmentService.add(department);
            return "redirect:/";
        }

    }
}
