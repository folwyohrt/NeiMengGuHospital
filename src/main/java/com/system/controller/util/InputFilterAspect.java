package com.system.controller.util;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.EnumSet;


/**
 * 该切面的目的是对整个Controller的输入参数做一些基础的合法性检查
 * 附带有两个例外注解：InputCheckException和VersionCheckException
 */
@Component
@Aspect
public class InputFilterAspect {

    @Before(value = "(execution(* com.system.controller.*Controller.*(..)) && args(..))")
    public void InputFilter(JoinPoint point) throws Exception {
        String[] paramNames = ((CodeSignature) point.getSignature()).getParameterNames();
        Object[] args = point.getArgs();
        /*上面这两个数组是长度相同，一一对应的，一个代表变量名称，一个代表变量本身*/
        Class targetClass = point.getSignature().getDeclaringType();
        if (null == ((MethodSignature) point.getSignature()).getMethod().getAnnotation(InputCheckException.class)) {
            for (int i = 0; i < paramNames.length; i++) {
                Checker.checkObject(args[i]);
            }
        }
    }

}


