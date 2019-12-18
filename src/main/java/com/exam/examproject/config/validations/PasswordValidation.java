package com.exam.examproject.config.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Password should contain at least 4 characters capital letter lowercase letter and a number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
