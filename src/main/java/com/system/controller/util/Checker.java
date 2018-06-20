package com.system.controller.util;

import com.system.util.exception.controller.input.NullArgumentException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * 检查工具，主要用来检查一些Controller层基础的数据检查
 */
@Component
public class Checker {
    /*Spring是基于对象层面上的依赖注入，静态变量不是对象的属性，而是一个类的属性
    * 通过上面的方法可以实现类似静态注入的效果*/
    private static Checker checkerFactory;

    /**
     * 检查Object和Object内部的属性是否合法
     *
     * @param object
     */
    public static void checkObject(Object object) {
        if (null == object) {
            if (null == object.getClass().getAnnotation(Nullable.class)) {
                throw new NullArgumentException("输入的信息不能为空！");
            }
            return;
        }
        if (object.getClass().isPrimitive()) {
            /*原始数据类型不需要检查，直接返回*/
            return;
        }
        if (object instanceof Character || object instanceof Boolean || object instanceof Number) {
            /*包装类型不需要进一步检查*/
            return;
        }
        /*字符串需要单独判断是否为空字符串*/
        if (object instanceof String) {
//            if (object.equals("")) {
//                throw new NullArgumentException("输入的信息不能为空！");
//            }
            return;
        }
        if (object instanceof Collection) {
            /*首先判断输入类型是否为集合数据类型
            若为集合类型，则需要检查长度和内容
            然后递归的检查全部对象
            */
            if (((Collection) object).size() == 0) {
                throw new IllegalArgumentException("输入的信息不能为空！");
            }
            ((Collection) object).forEach(Checker::checkObject);
            return;
        }
        /*最后要判断是否为数组类型,若为数组类型需要逐个检查*/
        try {
            if (Array.getLength(object) == 0) {
                throw new NullArgumentException("输入的信息不能为空！");
            } else {
                for (int i = 0; i < Array.getLength(object); i++) {
                    checkObject(Array.get(object, i));
                }
                return;
            }
        } catch (IllegalArgumentException ignored) {

        }
        checkFields(object);
    }

    /**
     * 检查object中的属性
     *
     * @param object
     */
    private static void checkFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
            /*1.首先判断是否为原始类型，原始类型不需要检查*/
                if (!f.getClass().isPrimitive()) {
                    /*2.判断是否为null，若为null检查是否有Nullable注解，没有则该参数不允许为空*/
                    if (null == getMethod(object, f).invoke(object)) {
                        if (null == f.getAnnotation(Nullable.class)) {
                            throw new NullArgumentException("输入的信息不能为空！" + f.getName());
                        }
                    } else {
                        checkObject(getMethod(object, f).invoke(object));
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static Method getMethod(Object o, Field f) {
        try {

            if(f.getName().length()>=2) {
                char c = f.getName().substring(1, 2).charAt(0);
                if (Character.isUpperCase(c)) {
                    return o.getClass().getMethod("getDTO" + f.getName());
                }
            }
            return o.getClass().getMethod("getDTO" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct
    private void init() {
        checkerFactory = this;
    }
}


