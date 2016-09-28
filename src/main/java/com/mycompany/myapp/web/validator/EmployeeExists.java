package com.mycompany.myapp.web.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EmployeeExistsValidator.class})
@ReportAsSingleViolation
public @interface EmployeeExists {

    String message() default "{employee.notexists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String companyCode();
    String employeeCode();
    
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        EmployeeExists[] value();
    }

}
