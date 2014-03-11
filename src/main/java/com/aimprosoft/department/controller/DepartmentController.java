package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.DepartmentForm;
import com.aimprosoft.department.form.ToDepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/department/new", method = RequestMethod.GET)
    public String newDepartment(ModelMap model) {
        DepartmentForm departmentForm = new DepartmentForm();
        model.put("title", "Create new department");
        model.put("departmentFormAction", "department/new");
        model.put(formDepartmentAttribute, departmentForm);
        return form;
    }

    @RequestMapping(value = "/department/new", method = RequestMethod.POST)
    public String processNewDepartment(@ModelAttribute(formDepartmentAttribute)DepartmentForm departmentForm,
                                    BindingResult result, ModelMap model) {

        departmentFormValidator.validate(departmentForm,result);

        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/new");
            model.put(formDepartmentAttribute, departmentForm);
            return form;
        }else {
            Department department = departmentForm.saveDepartment();
            departmentService.add(department);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/department/{id}/add", method = RequestMethod.GET)
    public String addEmployeesToDepartment(@PathVariable("id") Integer id, ModelMap model) {

        Department department = departmentService.getById(id);
        if(department == null)
            return "redirect:/error404";
        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
        toDepartmentForm.setDepartmentId(id);
        Department none = departmentService.getByName("None");
        model.put("title", "Add employees to department");
        model.put("departmentFormAction", "department/"+id+"/add");
        model.put("department", department);
        model.put("employeeList", employeeService.listByDepartment(none));
        model.put("toDepartmentForm", toDepartmentForm);

        return "employeeToDepartment";
    }

    @RequestMapping(value = "/department/{id}/add", method = RequestMethod.POST)
    public String processAddEmployeesToDepartment(@ModelAttribute("toDepartmentForm") ToDepartmentForm toDepartmentForm,
                                                  @PathVariable("id") Integer id) {

        if(!toDepartmentForm.getDepartmentId().equals(id))
            return "redirect:/error500";
        Department department = departmentService.getById(id);
        if(department == null)
            return "redirect:/error500";
        Integer[] employeeId = toDepartmentForm.getEmployeeId();
        for (Integer anEmployeeId : employeeId) {
            Employee employee = employeeService.getById(anEmployeeId);
            employee.setDepartment(department);
            employeeService.update(employee);
        }

        return "redirect:/department/list";

    }

    @RequestMapping(value = "/department/{id}/edit", method = RequestMethod.GET)
    public String editDepartment(@PathVariable("id") Integer id, ModelMap model) {
        Department department = departmentService.getById(id);
        if(department == null)
            return "redirect:/error404";
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.loadDepartment(department);
        model.put("title", "Edit department");
        model.put("departmentFormAction", "department/"+id+"/edit");
        return form;
    }

    @RequestMapping(value = "/department/{id}/edit", method = RequestMethod.POST)
    public String processEditDepartment(@ModelAttribute(formDepartmentAttribute)DepartmentForm departmentForm,
                                        @PathVariable("id") Integer id, BindingResult result, ModelMap model) {
        if(departmentService.getById(id) == null)
            return "redirect:/error500";

        departmentFormValidator.validate(departmentForm,result);

        if(result.hasErrors()) {
            model.put("title", "Create new department");
            model.put("departmentFormAction", "department/add");
            model.put(formDepartmentAttribute, departmentForm);
            return form;
        }else {
            Department department = departmentService.getById(id);
            department = departmentForm.updateDepartment(department);
            department.setId(id);
            departmentService.update(department);
            return "redirect:/department/list";
        }

    }

    @RequestMapping(value = "/department/{id}/delete", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable("id") Integer id, ModelMap model) {
        Department department = departmentService.getById(id);
        if(department == null)
            return "redirect:/error404";
        if(!employeeService.listByDepartment(department).isEmpty())
            model.put("title", "Delete department");
            model.put("employeeList", employeeService.listByDepartment(department));
            model.put("departmentFormAction", "department/"+id+"/delete");
            model.put("department", department);
        return "deleteDepartment";
    }

    @RequestMapping(value = "/department/{id}/delete", method = RequestMethod.POST)
    public String processDeleteDepartment(@PathVariable("id") Integer id, ModelMap model) {
        Department department = departmentService.getById(id);
        if(department == null)
            return "redirect:/error500";
        if(!employeeService.listByDepartment(department).isEmpty())
            return "redirect:/error500";
        departmentService.delete(department);
        return "redirect:/department/list";
    }

    @RequestMapping(value = "/department/list", method = RequestMethod.GET)
    public String listDepartment(ModelMap model) {
//        model.put("departmentFormAction", "department/list");
        model.put("title", "List all departments");
        model.put("departmentList",departmentService.list());
        return "listDepartment";
    }

    @RequestMapping(value = "/department/{id}/list", method = RequestMethod.GET)
    public String listDepartmentById(@PathVariable("id") Integer id, ModelMap model) {
        Department department = departmentService.getById(id);

        if (department == null) {
            return "redirect:/error404";
        }

        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
        toDepartmentForm.setDepartmentId(id);
        model.put("title", "List department employees");
        model.put("department", department);
        model.put("departmentFormAction", "department/"+id+"/list");
        model.put("employeeList", employeeService.listByDepartment(department));
        model.put("toDepartmentForm", toDepartmentForm);

        return "listEmployeeByDepartment";
    }

    @RequestMapping(value = "/department/{id}/list", method = RequestMethod.POST)
    public String processListDepartmentById(@PathVariable("id") Integer id,
                                            @ModelAttribute("toDepartmentForm") ToDepartmentForm toDepartmentForm) {
        if(!toDepartmentForm.getDepartmentId().equals(id))
            return "redirect:/error500";
        Department department = departmentService.getById(id);
        Department none = departmentService.getByName("None");
        if(department == null)
            return "redirect:/error500";
        Integer[] employeeId = toDepartmentForm.getEmployeeId();
        for (Integer anEmployeeId : employeeId) {
            Employee employee = employeeService.getById(anEmployeeId);
            employee.setDepartment(none);
            employeeService.update(employee);
        }

        return "redirect:/department/list";
    }

    @RequestMapping(value = "/department/{id}/action", method = RequestMethod.POST, params = {"departmentAction"})
    public String addEmployeeToDepartment(@RequestParam("departmentAction") String departmentAction, @PathVariable("id") Integer id) {
        switch(departmentAction){
            case "Add":
                return "redirect:/department/"+id+"/add";
            case "Edit":
                return "redirect:/department/"+id+"/edit";
            case "Employees":
                return "redirect:/department/"+id+"/list";
            case "Delete":
                return "redirect:/department/"+id+"/delete";
            default:
                return "redirect:/department/list";
        }

    }
}
