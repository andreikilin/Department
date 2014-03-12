package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.DepartmentForm;
import com.aimprosoft.department.form.ToDepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.utils.DepartmentPropertyUtil;
import com.aimprosoft.department.utils.EmployeePropertyUtil;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

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

    @Autowired
    private DepartmentPropertyUtil departmentPropertyUtil;

    @Autowired
    private EmployeePropertyUtil employeePropertyUtil;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, departmentPropertyUtil);
        binder.registerCustomEditor(Employee.class, employeePropertyUtil);
    }


    @RequestMapping(value = "/department/new", method = RequestMethod.GET)
    public String newDepartment(ModelMap model) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.put("title", "Create new department");
        model.put("departmentFormAction", "department/new");
        model.put("editDepartment", departmentForm);
        return "departmentForm";
    }

    @RequestMapping(value = "/department/new", method = RequestMethod.POST)
    public String processNewDepartment(@ModelAttribute("editDepartment")DepartmentForm departmentForm,
                                    BindingResult result, ModelMap model) {

        departmentFormValidator.validate(departmentForm,result);

        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/new");
            return "departmentForm";
        }else {
            Department department = departmentForm.saveDepartment();
            departmentService.add(department);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/department/{departmentId}/add", method = RequestMethod.GET)
    public String addEmployeesToDepartment(@PathVariable("departmentId") Department department, ModelMap model) {

        if(department == null) {
            return "redirect:/error404";
        }
        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
        toDepartmentForm.setDepartmentId(department.getId());
        Department none = departmentService.getByName("None");
        model.put("title", "Add employees to department");
        model.put("departmentFormAction", "department/"+department.getId()+"/add");
        model.put("department", department);
        model.put("employeeList", employeeService.listByDepartment(none));
        model.put("toDepartmentForm", toDepartmentForm);
        return "employeeToDepartment";
    }

    @RequestMapping(value = "/department/{departmentId}/add", method = RequestMethod.POST)
    public String processAddEmployeesToDepartment(@ModelAttribute("toDepartmentForm") ToDepartmentForm toDepartmentForm,
                                                  @PathVariable("departmentId") Department department) {
        if(department == null) {
            return "redirect:/error500";
        }
        if(!toDepartmentForm.getDepartmentId().equals(department.getId())) {
            return "redirect:/error500";
        }
        Integer[] employeeId = toDepartmentForm.getEmployeeId();
        for (Integer anEmployeeId : employeeId) {
            Employee employee = employeeService.getById(anEmployeeId);
            employee.setDepartment(department);
            employeeService.update(employee);
        }
        return "redirect:/department/list";

    }

    @RequestMapping(value = "/department/{departmentId}/edit", method = RequestMethod.GET)
    public String editDepartment(@PathVariable("departmentId") Department department, ModelMap model) {
        if(department == null) {
            return "redirect:/error404";
        }
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.loadDepartment(department);
        model.put("editDepartment", departmentForm);
        model.put("title", "Edit department");
        model.put("departmentFormAction", "department/"+department.getId()+"/edit");
        return "departmentForm";
    }

    @RequestMapping(value = "/department/{departmentId}/edit", method = RequestMethod.POST)
    public String processEditDepartment(@ModelAttribute("editDepartment")DepartmentForm departmentForm,
                                        @PathVariable("departmentId") Department department, BindingResult result, ModelMap model) {
        if(department == null) {
            return "redirect:/error500";
        }
        departmentFormValidator.validate(departmentForm,result);
        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/add");
            return "departmentForm";
        }else {
            department = departmentForm.updateDepartment(department);
            departmentService.update(department);
            return "redirect:/department/list";
        }

    }

    @RequestMapping(value = "/department/{departmentId}/delete", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable("departmentId") Department department, ModelMap model) {
        if(department == null) {
            return "redirect:/error404";
        }
        model.put("title", "Delete department");
        model.put("department", department);
        if(!employeeService.listByDepartment(department).isEmpty()) {
            model.put("employeeList", employeeService.listByDepartment(department));
            model.put("departmentFormAction", "department/"+department.getId()+"/delete");
        }
        return "deleteDepartment";
    }

    @RequestMapping(value = "/department/{departmentId}/delete", method = RequestMethod.POST)
    public String processDeleteDepartment(@PathVariable("departmentId") Department department) {
        if(department == null) {
            return "redirect:/error500";
        }
        if(!employeeService.listByDepartment(department).isEmpty()) {
            return "redirect:/error500";
        }
        departmentService.delete(department);
        return "redirect:/department/list";
    }

    @RequestMapping(value = "/department/list", method = RequestMethod.GET)
    public String listDepartment(ModelMap model) {
        model.put("title", "List all departments");
        model.put("departmentList",departmentService.list());
        return "listDepartment";
    }

    @RequestMapping(value = "/department/{departmentId}/list", method = RequestMethod.GET)
    public String listDepartmentById(@PathVariable("departmentId") Department department, ModelMap model) {
        if (department == null) {
            return "redirect:/error404";
        }
        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
        toDepartmentForm.setDepartmentId(department.getId());
        model.put("title", "List department employees");
        model.put("department", department);
        model.put("departmentFormAction", "department/"+department.getId()+"/list");
        model.put("employeeList", employeeService.listByDepartment(department));
        model.put("toDepartmentForm", toDepartmentForm);
        return "listEmployeeByDepartment";
    }

    @RequestMapping(value = "/department/{departmentId}/list", method = RequestMethod.POST)
    public String processListDepartmentById(@PathVariable("departmentId") Department department, ToDepartmentForm toDepartmentForm) {
        if(department == null) {
            return "redirect:/error500";
        }
        if(!toDepartmentForm.getDepartmentId().equals(department.getId())) {
            return "redirect:/error500";
        }
        Department none = departmentService.getByName("None");
        Integer[] employeeId = toDepartmentForm.getEmployeeId();
        for (Integer anEmployeeId : employeeId) {
            Employee employee = employeeService.getById(anEmployeeId);
            employee.setDepartment(none);
            employeeService.update(employee);
        }
        return "redirect:/department/list";
    }

}
