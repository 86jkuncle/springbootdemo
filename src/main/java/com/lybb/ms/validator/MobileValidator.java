package com.lybb.ms.validator;

import com.lybb.ms.utils.ValidatorUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/25 23:27 $
*/
public class MobileValidator implements ConstraintValidator<ValidateMobile,String> {
    private boolean required  = false;
    @Override
    public void initialize(ValidateMobile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtils.isMobile(s);
        }else{
            if(StringUtils.isBlank(s)){
                return true;
            }else{
                return ValidatorUtils.isMobile(s);
            }
        }
    }
}
