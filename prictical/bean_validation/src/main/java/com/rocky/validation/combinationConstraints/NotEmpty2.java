package com.rocky.validation.combinationConstraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@NotNull(message = "this is mull")
@Size(min = 5, message = "the size less then 5.")
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotEmptyValidator2.class})
public @interface NotEmpty2 {

    String message() default "this age may be not valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        NotEmpty2[] value();
    }
}
