package com.rocky.validation.singleConstraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotEmptyValidator.class})
public @interface NotEmpty {

    String message() default "this string may be empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
