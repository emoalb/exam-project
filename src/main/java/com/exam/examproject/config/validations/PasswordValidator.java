package com.exam.examproject.config.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    @Override
    public void initialize(PasswordValidation passwordValidation) {
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()==0){
            return false;
        }
        return  s.matches("^.*(?=.{4,})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$");
    }
}
