package com.aimprosoft.department.validator;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.EmployeeForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * Created by merovingien on 3/4/14.
 */
@Component("EmployeeFormValidator")
public class EmployeeFormValidator implements Validator {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String defaultMessage = "Field is required";
        EmployeeForm employeeForm = (EmployeeForm) target;

        /**
         * First Name validation
        */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.employeeFirstName", defaultMessage);
        if (!errors.hasFieldErrors("firstName")) {
            if (((employeeForm.getFirstName().length()) > 16) || (employeeForm.getFirstName().length()) < 3) {
                errors.rejectValue("firstName", "length.employeeFirstName", "Length must be more 3 and less 16 characters");
            }
        }

        /**
         *  Last Name validation
        */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.employeeLastName", defaultMessage);
        if (!errors.hasFieldErrors("lastName")) {
            if (((employeeForm.getFirstName().length()) > 16) || (employeeForm.getFirstName().length()) < 3) {
                errors.rejectValue("firstName", "length.employeeFirstName", "Length must be more 3 and less 16 characters");
            }
        }

        /**
         *  Email validation
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.employeeEmail", defaultMessage);
        if(!errors.hasFieldErrors("email")) {
            if(!EmailValidator.getInstance().isValid( employeeForm.getEmail() ) ){
                errors.rejectValue("email", "mismatch.employeeEmail", "Email is invalid");
            }else {
                if(employeeForm.getId() != null && employeeForm.getId() !=0) {
                    // Edit employee validation
                    Employee employeeCurrent = employeeService.getById(employeeForm.getId());
                    Employee employeeFound = employeeService.getByEmail(employeeForm.getEmail());
                    if(employeeFound != null)
                        if(employeeCurrent.getId() != employeeFound.getId())
                            errors.rejectValue("email", "exist.employeeEmail", "Email already in use");
                } else {
                    // Add employee validation
                    if(employeeService.getByEmail(employeeForm.getEmail()) != null) {
                        errors.rejectValue("email", "exist.employeeEmail", "Email already in use");
                    }
                }

            }
        }

        /**
         *  Inn validation
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inn", "required.employeeInn", defaultMessage);
        if(!errors.hasFieldErrors("inn")) {
            if(employeeForm.getInn().toString().length() != 6) {
                errors.rejectValue("inn", "size.employeeInn", "Inn length must be 6 digits");
            }else {
                if(employeeForm.getId() != null && employeeForm.getId() !=0) {
                    /**
                     *  Edit employee validation
                     */
                    Employee employeeCurrent = employeeService.getById(employeeForm.getId());
                    Employee employeeFound = employeeService.getByInn(employeeForm.getInn());
                    if(employeeFound != null && !employeeCurrent.getId().equals(employeeFound.getId()))
                        errors.rejectValue("inn", "exist.employeeInn", "Employee with this inn already exist");
                }else {
                    /**
                     *  Add employee validation
                     */
                    if(employeeService.getByInn(employeeForm.getInn()) != null) {
                        errors.rejectValue("inn", "exist.employeeInn", "Employee with this inn already exist");
                    }
                }

            }
        }

        /**
         *  Birthday validation
         */
        if(!DateValidator.getInstance().isValid(employeeForm.getDay()+"/"+employeeForm.getMonth()+"/"+employeeForm.getYear(), "dd/MM/yyyy")) {
            errors.rejectValue("birthday", "incorrect.Date", "Incorrect date");
        }

        /**
         * Department validation
         */
        Department departmentCurrent = employeeForm.getDepartment();
        if (departmentCurrent != null) {
            Department departmentFound = departmentService.getById(departmentCurrent.getId());
            if(departmentFound != null) {
                if (!departmentFound.equals(departmentCurrent)) {
                    errors.rejectValue("department", "incorrect.Department", "Choose department");
                }
            }else {
                errors.rejectValue("department", "incorrect.Department", "Choose department");
            }
        }else {
            errors.rejectValue("department", "incorrect.Department", "Choose department");
        }


    }
}
