package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.EmployeeForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.utils.DateUtil;
import com.aimprosoft.department.utils.DepartmentPropertyUtil;
import com.aimprosoft.department.utils.EmployeePropertyUtil;
import com.aimprosoft.department.validator.EmployeeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private DepartmentPropertyUtil departmentPropertyUtil;

    @Autowired
    private EmployeePropertyUtil employeePropertyUtil;

//    private Logger logger = Logger.getLogger(EmployeeController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, departmentPropertyUtil);
        binder.registerCustomEditor(Employee.class, employeePropertyUtil);
   }

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public String listEmployee(ModelMap model) {
//        logger.info("Visit info");
//        logger.debug("Debugging message");
//        logger.error("Error usage");
        model.put("title", "List all employees");
        return "listAllEmployees";
    }

    @RequestMapping(value = "/employee/getList", method = RequestMethod.GET)
    public @ResponseBody List getEmployeeList() {
        return employeeService.listJson();
    }

    @RequestMapping(value = "/employee/new", method = RequestMethod.GET)
    public String addEmployee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        model.put("title", "Add new employee");
        model.put("employeeFormAction", "employee/add");
        model.put("editEmployee", employeeForm);
        model.put("departmentList",departmentService.list());
        model.put("dayList", dateUtil.getDayList());
        model.put("monthMap", dateUtil.getMonthMap());
        model.put("yearList", dateUtil.getYearList());
        return "employeeForm";
    }

    @RequestMapping(value = "/employee/new", method = RequestMethod.POST)
    public String processAddEmployee(@ModelAttribute("editEmployee") EmployeeForm employeeForm,
                                     BindingResult result, ModelMap model ) {
       employeeFormValidator.validate(employeeForm,result);
       if (result.hasErrors()) {
            model.put("title", "Add new employee");
            model.put("employeeFormAction", "employee/add");
            model.put("departmentList",departmentService.list());
            model.put("dayList", dateUtil.getDayList());
            model.put("monthMap", dateUtil.getMonthMap());
            model.put("yearList", dateUtil.getYearList());
           return "employeeForm";
       } else {
          Employee employee = employeeForm.saveEmployee();
          employeeService.add(employee);
          return "redirect:/";
       }
    }

    @RequestMapping(value = "/employee/{employeeId}/edit", method = RequestMethod.GET)
    public String editEmployee(@PathVariable("employeeId") Employee employee, ModelMap model) {
        if(employee == null) {
            return "redirect:/error404";
        }
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.loadEmployee(employee);
        model.put("title", "Edit employee");
        model.put("employeeFormAction", "employee/"+employee.getId()+"/edit");
        model.put("editEmployee", employeeForm);
        model.put("departmentList",departmentService.list());
        model.put("dayList", dateUtil.getDayList());
        model.put("monthMap", dateUtil.getMonthMap());
        model.put("yearList", dateUtil.getYearList());
        return "employeeForm";
    }

    @RequestMapping(value = "/employee/{employeeId}/edit", method = RequestMethod.POST)
    public String processEditEmployee(@PathVariable("employeeId") Employee employee, @ModelAttribute("editEmployee") EmployeeForm employeeForm,
                                      BindingResult result, ModelMap model) {
        if(employee == null) {
            return "redirect:/error500";
        }
        employeeFormValidator.validate(employeeForm,result);
        if (result.hasErrors()) {
            model.put("title", "Edit employee");
            model.put("employeeFormAction", "employee/"+employee.getId()+"/edit");
            model.put("departmentList",departmentService.list());
            model.put("dayList", dateUtil.getDayList());
            model.put("monthMap", dateUtil.getMonthMap());
            model.put("yearList", dateUtil.getYearList());
            return "employeeForm";
        }else {
            employee = employeeForm.saveEmployee();
            employeeService.update(employee);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/employee/{employeeId}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("employeeId") Employee employee, ModelMap model) {
        if(employee == null) {
            return "redirect:/error404";
        }
        model.put("title", "Delete employee");
        model.put("employeeFormAction", "employee/"+employee.getId()+"/delete");
        model.put("employee", employee);
        return "deleteEmployee";
    }

    @RequestMapping(value = "/employee/{employeeId}/delete", method = RequestMethod.POST)
    public String processDeleteEmployee(@PathVariable("employeeId") Employee employee) {
        if(employee == null) {
            return "redirect:/error500";
        }
        employeeService.delete(employee);
        return "redirect:/employee/list";
    }
}
