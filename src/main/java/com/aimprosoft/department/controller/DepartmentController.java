package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.form.DepartmentForm;
import com.aimprosoft.department.form.ToDepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    EmployeeService employeeService;

    @Autowired
    DepartmentFormValidator departmentFormValidator;

    private final String form = "departmentForm";
    private final String formDepartmentAttribute = "editDepartment";

    @RequestMapping(value = "/department/add", method = RequestMethod.GET)
    public String addDepartment(ModelMap model) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.put("title", "Create new department");
        model.put("departmentFormAction", "department/add");
        model.put(formDepartmentAttribute, departmentForm);
        return form;
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public String processDepartment(@ModelAttribute(formDepartmentAttribute)DepartmentForm departmentForm,
                                    BindingResult result, ModelMap model) {

        departmentFormValidator.validate(departmentForm,result);

        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/add");
            model.put(formDepartmentAttribute, departmentForm);
            return form;
        }else {
            Department department = departmentForm.saveDepartment();
            departmentService.add(department);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/department/list", method = RequestMethod.GET)
    public String listDepartment(ModelMap model) {
        model.put("departmentFormAction", "department/list");
        model.put("departmentList",departmentService.list());
        return "listDepartment";
    }

    @RequestMapping(value = "/department/list", method = RequestMethod.GET, params = "addEmployee")
    public String addEmployeeToDepartment(//@PathVariable("departmentId") Integer departmentId,
                                          ModelMap model) {
        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();

        Department departmentNone = departmentService.getByName("None");
        model.put("departmentFormAction", "department/"+"/add");
        model.put("toDepartmentForm", toDepartmentForm);
        model.put("departmentName", "Department Name");
        model.put("noDepartmentEmployeeList", employeeService.listByDepartment(departmentNone));
        return "employeeToDepartment";
    }
}
