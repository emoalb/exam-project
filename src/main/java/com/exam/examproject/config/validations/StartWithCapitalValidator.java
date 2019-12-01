package com.exam.examproject.config.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class  StartWithCapitalValidator implements ConstraintValidator<StartWithCapital, String> {
    @Override
        public void initialize(StartWithCapital startWithCapital) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length()==0){
            return false;
        }
        return  s.substring(0, 1).equals(s.substring(0, 1).toUpperCase());
    }
}
