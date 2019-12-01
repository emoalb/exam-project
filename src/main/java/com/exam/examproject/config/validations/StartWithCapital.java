package com.exam.examproject.config.validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartWithCapitalValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartWithCapital {
    String message() default "Must start with capital letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

