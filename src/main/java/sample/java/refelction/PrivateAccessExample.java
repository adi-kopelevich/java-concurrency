package sample.java.refelction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by kopelevi on 01/11/2015.
 */
public class PrivateAccessExample {

    public static void main(String[] args) {
        Class testObjectClass = TestObject.class;
        printPrivateFields(testObjectClass);
        printPrivateMethods(testObjectClass);

        TestObject instance = new TestObject();
        Field privateField = null;
        try {
            privateField = testObjectClass.getDeclaredField("privateStaticString");
            privateField.setAccessible(true);
            String fieldValue = (String) privateField.get(instance);
            System.out.println("Private field (privateStaticString) value is: " + fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Method privateMethod = testObjectClass.getDeclaredMethod("getIntValuePlusOne");
            privateMethod.setAccessible(true);
            int methodRetValue = (int) privateMethod.invoke(instance);
            System.out.println("Private method (getIntValuePlusOne) return value is: " + methodRetValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void printPrivateFields(Class testObjectClass) {
        System.out.println("Private Fields: ");
        String privateMethodString = Arrays.asList(testObjectClass.getDeclaredFields()).stream()
                .filter(PrivateAccessExample::isPrivateField)
                .map(f -> f.toString())
                .reduce("", (acc, item) -> acc + item + "\n");
        System.out.println(privateMethodString);
    }

    private static void printPrivateMethods(Class testObjectClass) {
        System.out.println("Private Methods: ");
        String privateMethodString = Arrays.asList(testObjectClass.getDeclaredMethods()).stream()
                .filter(PrivateAccessExample::isPrivateMethod)
                .map(m -> m.toString())
                .reduce("", (acc, item) -> acc + item + "\n");
        System.out.println(privateMethodString);
    }

    private static boolean isPrivateField(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }

    private static boolean isPrivateMethod(Method method) {
        return Modifier.isPrivate(method.getModifiers());
    }
}
