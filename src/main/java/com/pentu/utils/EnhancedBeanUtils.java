package com.pentu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;


public class EnhancedBeanUtils {

    private final static Logger logger = LoggerFactory.getLogger(EnhancedBeanUtils.class);

    private EnhancedBeanUtils() {

    }

    /**
     * copy not null properties from source object to target object.
     *
     * @param source
     * @param target
     */
    public static void copyNotNullProperties(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) {
            return;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            //bug fix: when the method return type is String, they doesn't work
                            if (value == null || (readMethod.getReturnType() != String.class && (value.equals("") ||
                                    value.equals("0") || value.equals("0.0")))) {
                                continue;
                            } else {
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                            logger.error("error, {}");
                        }
                    }
                }
            }
        }
    }


    /**
     * 判断是否除了指定的属性外其余属性都为空
     *
     * @param target
     * @param ignoreProperties
     * @return
     */
    public static boolean isAllPropertiesNull(Object target, String... ignoreProperties) {
        if (target == null) {
            return true;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        boolean result = true;
        for (PropertyDescriptor targetPd : targetPds) {
            Method readMethod = targetPd.getReadMethod();
            String name = readMethod.getName();
            if (name.equals("getClass")) continue;
            if (readMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                try {
                    Object value = readMethod.invoke(target);
                    if (null != value) {
                        result = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    /**
     * 初始化 target class, 拷贝非null 属性，并返回对应Object
     * @param source
     * @param targetClass
     * @param ignoreProperties
     * @return
     */
    public static Object copyNotNullProperties(Object source, Class targetClass,String... ignoreProperties){

        Object objTarget = null;
        try {
            objTarget = targetClass.newInstance();
            copyNotNullProperties(source, objTarget, ignoreProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objTarget;
    }


}
