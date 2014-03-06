package com.aimprosoft.department.validator;

import com.aimprosoft.department.form.DepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by merovingien on 3/6/14.
 */
@Component("DepartmentFormValidator")
public class DepartmentFormValidator implements Validator {

    @Autowired
    DepartmentService departmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String defaultMessage = "Field is required";
        DepartmentForm departmentForm = (DepartmentForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", defaultMessage);
        if (!errors.hasFieldErrors("name")) {
            if (((departmentForm.getName().length()) > 16) || (departmentForm.getName().length()) < 3) {
                errors.rejectValue("name", "length.departmentName", "Length must be more 3 and less 16 characters");
            } else {
                if(departmentService.getByName(departmentForm.getName()) != null) {
                    errors.rejectValue("name", "exist.departmentName", "this department already exist");
                }
            }
        }
    }
}
