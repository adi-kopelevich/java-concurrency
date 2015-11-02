package sample.java.refelction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by kopelevi on 01/11/2015.
 */
public class MethodExample {
    public static void main(String[] args) {
        Class testObjClass = TestObject.class;
        Arrays.asList(testObjClass.getMethods()).stream().map(MethodExample::getMethodDescription).count();

        printGettersSetters(testObjClass);

        //get static method that takes a String as argument and return a String
        Method method = null;
        try {
            method = TestObject.class.getMethod("setStringValueAndReturnOldValue", String.class);
            String returnValue = (String) method.invoke(null, "parameter-value1");
            System.out.println(returnValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            TestObject testObjectInstance = (TestObject) testObjClass.getConstructor().newInstance();
            Method getIntMethod = TestObject.class.getMethod("getIntValue", null);
            int returnValue = (int) getIntMethod.invoke(testObjectInstance);
            System.out.println(returnValue);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private static String getMethodDescription(Method method) {
        System.out.println("-----------");
        System.out.println("Method Name: " + method.getName());
        System.out.println("Params Types: " + Arrays.asList(method.getParameterTypes()).stream().map(p -> p.getName()).reduce("", (acc, item) -> acc + item + ", "));
        System.out.println("Return Type: " + method.getReturnType().getName());
        System.out.println("-----------");
        return "";
    }

    public static void printGettersSetters(Class aClass) {
        System.out.println("---- Getters and Setters ---");
        Method[] methods = aClass.getMethods();

        String gettersString = Arrays.asList(methods).stream()
                .filter(MethodExample::isGetter)
                .map(m -> m.toString())
                .reduce("", (acc, item) -> acc + "\nGetter: " + item);
        System.out.println(gettersString);

        String settersString = Arrays.asList(methods).stream()
                .filter(MethodExample::isSetter)
                .map(m -> m.toString())
                .reduce("", (acc, item) -> acc + "\nSetter: " + item);
        System.out.println(settersString);
        System.out.println("----------------------------");
    }

    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }
}
