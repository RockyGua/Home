package com.rocky.validation.combinationConstraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator2 implements ConstraintValidator<NotEmpty2, String> {

    public void initialize(NotEmpty2 notEmpty2) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return true;
    }
}
