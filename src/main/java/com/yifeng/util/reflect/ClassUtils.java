package com.yifeng.util.reflect;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-21 15:10
 */
public class ClassUtils {

    private ClassUtils() {}

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        }
        catch (Throwable ex) {
            return false;
        }
    }

    public static Class<?> forName(String name, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }

        if (classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
        }

        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }

        try {
            return classLoader != null ? classLoader.loadClass(name) : Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        forName("com.yifeng.util.reflect.ClassUtils", ClassUtils.class.getClassLoader());
    }
}
