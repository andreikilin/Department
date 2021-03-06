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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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

    private Logger logger = Logger.getLogger(EmployeeController.class);



    @RequestMapping(value = "/department/new", method = RequestMethod.POST)
    public @ResponseBody List jsonNewDepartment(DepartmentForm departmentForm, BindingResult result) {
        departmentFormValidator.validate(departmentForm,result);
        List response = new LinkedList();
        if(result.hasErrors()) {
            response.add("Validation error:");
            for(int i = 0; i<result.getErrorCount(); i++) {
                FieldError error = (FieldError) result.getAllErrors().get(i);
                response.add(error.getField() + ": " + error.getDefaultMessage());
            }
            return response;
        }
        Department department = departmentForm.saveDepartment();
        try{
            departmentService.add(department);
        }catch (Exception e) {
            logger.error(e.toString());
            response.add("Service error:");
            response.add(e.getMessage());
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/department/edit", method = RequestMethod.POST)
    public @ResponseBody List jsonEditDepartment(@ModelAttribute DepartmentForm departmentForm, BindingResult result) {
        List response = new LinkedList();
        Integer id = departmentForm.getId();
        if(id != null && id != 0 && departmentService.getById(id) != null) {
            Department department = departmentService.getById(id);
            departmentFormValidator.validate(departmentForm,result);

            if(result.hasErrors()) {
                response.add("Validation error:");
                for(int i = 0; i<result.getErrorCount(); i++) {
                    FieldError error = (FieldError) result.getAllErrors().get(i);
                    response.add(error.getField() + ": " + error.getDefaultMessage());
                }
                return response;
            }
            department = departmentForm.updateDepartment(department);
            try{
                departmentService.update(department);
            }catch (Exception e) {
                logger.error(e.toString());
                response.add("Service error:");
                response.add(e.getMessage());
                return response;
            }
            return response;
        }
        response.add("Invalid request");
        return response;
    }

    @RequestMapping(value = "/department/delete", method = RequestMethod.POST)
    public @ResponseBody String jsonDeleteDepartment(@RequestParam("departmentId") Department department) {
        if(department == null) {
            return "error";
        }
        try {
            departmentService.delete(department);
        }catch(Exception e) {
            logger.error(e.toString());
            return "service error";
        }
        return "successful";
    }

    @RequestMapping(value = "/department.do", method = RequestMethod.POST)
    public @ResponseBody boolean departmentExist(Integer id, String name) {
        if(id != null && id != 0) {
            Department depEdit = departmentService.getById(id);
            if( depEdit!= null) {
                if(depEdit.equals(departmentService.getByName(name))) {
                    return true;
                }
            }
        }
        return departmentService.getByName(name) == null;
    }

    //    @RequestMapping(value = "/department/new", method = RequestMethod.GET)
//    public String newDepartment(ModelMap model) {
//        DepartmentForm departmentForm = new DepartmentForm();
//        model.put("title", "Create new department");
//        model.put("departmentFormAction", "department/new");
//        model.put("editDepartment", departmentForm);
//        return "departmentForm";
//    }

//    @RequestMapping(value = "/department/newUU", method = RequestMethod.POST)
//    public String processNewDepartment(@ModelAttribute("addDepartment")DepartmentForm departmentForm,
//                                    BindingResult result, ModelMap model) {
//
//        departmentFormValidator.validate(departmentForm,result);
//
//        if(result.hasErrors()) {
//            model.put("title", "Create new department");
//            model.put("departmentFormAction", "department/new");
//            return "departmentForm";
//        }else {
//            Department department = departmentForm.saveDepartment();
//            departmentService.add(department);
//            return "redirect:/";
//        }
//    }

//    @RequestMapping(value = "/department/{departmentId}/add", method = RequestMethod.GET)
//    public String addEmployeesToDepartment(@PathVariable("departmentId") Department department, ModelMap model) {
//
//        if(department == null) {
//            return "redirect:/error404";
//        }
//        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
//        toDepartmentForm.setDepartmentId(department.getId());
//        Department none = departmentService.getByName("None");
//        model.put("title", "Add employees to department");
//        model.put("departmentFormAction", "department/"+department.getId()+"/add");
//        model.put("department", department);
//        model.put("employeeList", employeeService.listByDepartment(none));
//        model.put("toDepartmentForm", toDepartmentForm);
//        return "employeeToDepartment";
//    }

//    @RequestMapping(value = "/department/{departmentId}/add", method = RequestMethod.POST)
//    public String processAddEmployeesToDepartment(@ModelAttribute("toDepartmentForm") ToDepartmentForm toDepartmentForm,
//                                                  @PathVariable("departmentId") Department department) {
//        if(department == null) {
//            return "redirect:/error500";
//        }
//        if(!toDepartmentForm.getDepartmentId().equals(department.getId())) {
//            return "redirect:/error500";
//        }
//        Integer[] employeeId = toDepartmentForm.getEmployeeId();
//        for (Integer anEmployeeId : employeeId) {
//            Employee employee = employeeService.getById(anEmployeeId);
//            employee.setDepartment(department);
//            employeeService.update(employee);
//        }
//        return "redirect:/department/list";
//
//    }

//    @RequestMapping(value = "/department/{departmentId}/edit", method = RequestMethod.GET)
//    public String editDepartment(@PathVariable("departmentId") Department department, ModelMap model) {
//        if(department == null) {
//            return "redirect:/error404";
//        }
//        DepartmentForm departmentForm = new DepartmentForm();
//        departmentForm.loadDepartment(department);
//        model.put("editDepartment", departmentForm);
//        model.put("title", "Edit department");
//        model.put("departmentFormAction", "department/"+department.getId()+"/edit");
//        return "departmentForm";
//    }

//    @RequestMapping(value = "/department/{departmentId}/edit", method = RequestMethod.POST)
//    public String processEditDepartment(@ModelAttribute("editDepartment")DepartmentForm departmentForm,
//                                        @PathVariable("departmentId") Department department, BindingResult result, ModelMap model) {
//        if(department == null) {
//            return "redirect:/error500";
//        }
//        departmentFormValidator.validate(departmentForm,result);
//        if(result.hasErrors()) {
//            model.put("title", "Create new department");
//            model.put("departmentFormAction", "department/add");
//            return "departmentForm";
//        }else {
//            department = departmentForm.updateDepartment(department);
//            departmentService.update(department);
//            return "redirect:/department/list";
//        }
//
//    }

//    @RequestMapping(value = "/department/{departmentId}/delete", method = RequestMethod.GET)
//    public String deleteDepartment(@PathVariable("departmentId") Department department, ModelMap model) {
//        if(department == null) {
//            return "redirect:/error404";
//        }
//        model.put("title", "Delete department");
//        model.put("department", department);
//        model.put("departmentFormAction", "department/"+department.getId()+"/delete");
//        if(!employeeService.listByDepartment(department).isEmpty()) {
//            model.put("employeeList", employeeService.listByDepartment(department));
//        }
//        return "deleteDepartment";
//    }



//    @RequestMapping(value = "/department/{departmentId}/delete", method = RequestMethod.POST)
//    public String processDeleteDepartment(@PathVariable("departmentId") Department department) {
//        if(department == null) {
//            return "redirect:/error500";
//        }
//        if(!employeeService.listByDepartment(department).isEmpty()) {
//            return "redirect:/error500";
//        }
//        departmentService.delete(department);
//        return "redirect:/department/list";
//    }
//
//    @RequestMapping(value = "/department/list", method = RequestMethod.GET)
//    public String listDepartment(ModelMap model) {
//        model.put("title", "List all departments");
//        return "listDepartment";
//    }

    @RequestMapping(value = "/department/getList", method = RequestMethod.GET)
    public @ResponseBody List<Department> getDepartmentList() {
        return  departmentService.list();
    }

//    @RequestMapping(value = "/department/{departmentId}/list", method = RequestMethod.GET)
//    public String listDepartmentById(@PathVariable("departmentId") Department department, ModelMap model) {
//        if (department == null) {
//            return "redirect:/error404";
//        }
//        ToDepartmentForm toDepartmentForm = new ToDepartmentForm();
//        toDepartmentForm.setDepartmentId(department.getId());
//        model.put("title", "List department employees");
//        model.put("department", department);
//        model.put("departmentFormAction", "department/"+department.getId()+"/list");
//        model.put("employeeList", employeeService.listByDepartment(department));
//        model.put("toDepartmentForm", toDepartmentForm);
//        return "listEmployeeByDepartment";
//    }

    @RequestMapping(value = "/department/getListByDepartment", method = RequestMethod.GET)
    public @ResponseBody List getLIstByDepartment(@RequestParam("departmentId") Department department) {
        return employeeService.listJsonByDepartment(department);
    }

//    @RequestMapping(value = "/department/{departmentId}/list", method = RequestMethod.POST)
//    public String processListDepartmentById(@PathVariable("departmentId") Department department, ToDepartmentForm toDepartmentForm) {
//        if(department == null) {
//            return "redirect:/error500";
//        }
//        if(!toDepartmentForm.getDepartmentId().equals(department.getId())) {
//            return "redirect:/error500";
//        }
//        Department none = departmentService.getByName("None");
//        Integer[] employeeId = toDepartmentForm.getEmployeeId();
//        for (Integer anEmployeeId : employeeId) {
//            Employee employee = employeeService.getById(anEmployeeId);
//            employee.setDepartment(none);
//            employeeService.update(employee);
//        }
//        return "redirect:/department/list";
//    }

}
