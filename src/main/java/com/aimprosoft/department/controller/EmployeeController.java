package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.EmployeeForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.validator.EmployeeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by merovingien on 3/3/14.
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeFormValidator employeeFormValidator;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Department.class, new PropertyEditorSupport() {

                @Override
                public void setAsText(String id) {
                    Department department = departmentService.getById(Integer.parseInt(id));
                    this.setValue(department);
                    return;
                }

        });

   }

    @RequestMapping(value = "/employee/add", method = RequestMethod.GET)
    public String addEmployee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        model.put("employeeFormAction", "employee/add");
        model.put("editEmployee", employeeForm);
        model.put("departmentList",departmentService.list());

        return "employeeForm";
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.POST)
    public String processEmployee(@ModelAttribute("editEmployee") EmployeeForm employeeForm, BindingResult result, ModelMap model ) {


        employeeFormValidator.validate(employeeForm,result);

        if (result.hasErrors()) {
            model.put("employeeFormAction", "employee/add");
            model.put("editEmployee", employeeForm);
            model.put("departmentList",departmentService.list());
            return "employeeForm";
        } else {
            Employee employee = employeeForm.saveEmployee();
            employeeService.add(employee);
            return "redirect:/";
        }



    }


}
