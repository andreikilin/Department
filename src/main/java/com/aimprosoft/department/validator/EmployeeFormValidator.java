package com.aimprosoft.department.validator;

import com.aimprosoft.department.form.EmployeeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by merovingien on 3/4/14.
 */
@Component("EmployeeFormValidator")
public class EmployeeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String defaultMessage = "Field is required";
        EmployeeForm employeeForm = (EmployeeForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstName", defaultMessage);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastName", defaultMessage);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", defaultMessage);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inn", "required.inn", defaultMessage);
    }
}
