package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.EmployeeForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.utils.DateUtil;
import com.aimprosoft.department.validator.EmployeeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

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

    @Autowired
    private DateUtil dateUtil;

    private final String form = "employeeForm";
    private final String formEmployeeAttribute = "editEmployee";

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

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public String listEmployee(ModelMap model) {

        model.put("employeeList", employeeService.list());
        return "listEmployee";
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.GET)
    public String addEmployee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();

        model.put("title", "Add new employee");
        model.put("employeeFormAction", "employee/add");
        model.put(formEmployeeAttribute, employeeForm);
        model.put("departmentList",departmentService.list());
        model.put("dayList", dateUtil.getDayList());
        model.put("monthMap", dateUtil.getMonthMap());
        model.put("yearList", dateUtil.getYearList());

        return form;
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.POST)
    public String processAddEmployee(@ModelAttribute(formEmployeeAttribute) EmployeeForm employeeForm,
                                     BindingResult result, ModelMap model ) {

       employeeFormValidator.validate(employeeForm,result);

       if (result.hasErrors()) {
            model.put("title", "Add new employee");
            model.put("employeeFormAction", "employee/add");
            model.put(formEmployeeAttribute, employeeForm);
            model.put("departmentList",departmentService.list());
            model.put("dayList", dateUtil.getDayList());
            model.put("monthMap", dateUtil.getMonthMap());
            model.put("yearList", dateUtil.getYearList());
            return form;
        } else {
            Employee employee = employeeForm.saveEmployee();
            employeeService.add(employee);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/employee/{id}/edit", method = RequestMethod.GET)
    public String editEmployee(@PathVariable("id") Integer id, ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        Employee employee = employeeService.getById(id);
        employeeForm.loadEmployee(employee);

        model.put("title", "Edit employee");
        model.put("employeeFormAction", "employee/"+id+"/edit");
        model.put(formEmployeeAttribute, employeeForm);
        model.put("departmentList",departmentService.list());
        model.put("dayList", dateUtil.getDayList());
        model.put("monthMap", dateUtil.getMonthMap());
        model.put("yearList", dateUtil.getYearList());

        return form;
    }

    @RequestMapping(value = "/employee/{id}/edit", method = RequestMethod.POST)
    public String processEditEmployee(@PathVariable("id") Integer id,
                                      @ModelAttribute(formEmployeeAttribute) EmployeeForm employeeForm,
                                      BindingResult result, ModelMap model) {

        employeeFormValidator.validate(employeeForm,result);

        if (result.hasErrors()) {
            model.put("title", "Edit employee");
            model.put("employeeFormAction", "employee/"+id+"/edit");
            model.put(formEmployeeAttribute, employeeForm);
            model.put("departmentList",departmentService.list());
            model.put("dayList", dateUtil.getDayList());
            model.put("monthMap", dateUtil.getMonthMap());
            model.put("yearList", dateUtil.getYearList());
            return form;
        }else {
            Employee employee = employeeForm.saveEmployee();
            employee.setId(id);
            employeeService.update(employee);
            return "redirect:/";
        }

    }

    /**
    *  Soon
    */
     @RequestMapping(value = "/employee/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") Integer id, ModelMap model) {

         return form;
    }
}
