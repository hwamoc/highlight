package com.liner.example.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneOfThemValidator.class)
public @interface AtLeastOneOfThem {
    String[] value();

    String message() default "{AtLeastOneOfThem.message}";
    Class[] groups() default {};
    Class[] payload() default {};
}
