package com.system.util.database;

import com.system.controller.util.ExceptionHandlerController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Auther: 李景然
 * @Date: 2018/6/22 20:42
 * @Description:
 */
@Aspect
@Component
public class DataSwitchAop {
    //@Pointcut()
    //@Pointcut("execution(public * com.system.service.impl..*(..))")
    public void execute(){
    }

    @Before("@annotation(com.system.util.database.DataSwitch)")
    public void dataSwitch(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature =(MethodSignature) signature;
        Method method = methodSignature.getMethod();
        DataSwitch data = null;
        if(method!=null){
            data = method.getAnnotation(DataSwitch.class);
        }
        String dataSource = data.dataSource();
        if(dataSource!=null){
            MultipleDataSource.setDataSourceKey(dataSource);
        }
    }
}
