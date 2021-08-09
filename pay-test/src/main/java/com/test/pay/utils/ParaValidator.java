package com.test.pay.utils;

import com.test.pay.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 校验参数合法性工具类
 * @author Liu
 */
@Slf4j
public class ParaValidator {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static void validate(Object object) {
        if (null == object) {
            IllegalArgumentException exception = new IllegalArgumentException(Constants.PARAMETER_NULL_ERROR);
            throw exception;
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() > 0) {
            IllegalArgumentException exception = new IllegalArgumentException(constraintViolations.iterator().next().getMessage());
            throw exception;
        }
    }
}
