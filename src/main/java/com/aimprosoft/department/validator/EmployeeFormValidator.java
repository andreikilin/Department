package com.aimprosoft.department.validator;

import com.aimprosoft.department.form.EmployeeForm;
import com.aimprosoft.department.service.EmployeeService;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Date;

/**
 * Created by merovingien on 3/4/14.
 */
@Component("EmployeeFormValidator")
public class EmployeeFormValidator implements Validator {

    @Autowired
    EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String defaultMessage = "Field is required";
        EmployeeForm employeeForm = (EmployeeForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.employeeFirstName", defaultMessage);
        if (!errors.hasFieldErrors("firstName")) {
            if (((employeeForm.getFirstName().length()) > 16) || (employeeForm.getFirstName().length()) < 3) {
                errors.rejectValue("firstName", "length.employeeFirstName", "Length must be more 3 and less 16 characters");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.employeeLastName", defaultMessage);
        if (!errors.hasFieldErrors("lastName")) {
            if (((employeeForm.getFirstName().length()) > 16) || (employeeForm.getFirstName().length()) < 3) {
                errors.rejectValue("firstName", "length.employeeFirstName", "Length must be more 3 and less 16 characters");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.employeeEmail", defaultMessage);
        if(!errors.hasFieldErrors("email")) {
            if(!EmailValidator.getInstance().isValid( employeeForm.getEmail() ) ){
                errors.rejectValue("email", "mismatch.employeeEmail", "");
            }else
            if(employeeService.getByEmail(employeeForm.getEmail()) != null) {
                errors.rejectValue("email", "exist.employeeEmail", "");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inn", "required.employeeInn", defaultMessage);
        if(!errors.hasFieldErrors("inn")) {
            if(employeeForm.getInn().toString().length() != 6) {
                errors.rejectValue("inn", "size.employeeInn", "Inn length must be 6 digits");
            }else {
                if(employeeService.getByInn(employeeForm.getInn()) != null) {
                    errors.rejectValue("inn", "exist.employeeInn", "Employee with this inn already exist");
                }
            }
        }

        if(!DateValidator.getInstance().isValid(employeeForm.getDay()+"/"+employeeForm.getMonth()+"/"+employeeForm.getYear(), "dd/MM/yyyy")) {
            errors.rejectValue("birthday", "incorrect.Date", "Incorrect date");
        }

    }
}
