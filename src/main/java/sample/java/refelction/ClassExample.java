package sample.java.refelction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kopelevi on 01/11/2015.
 */
public class ClassExample {

    public static void main(String[] args) {
        Class testObjClass = TestObject.class;
        System.out.println("Class name: " + testObjClass.getName());
        System.out.println("Simple class name: " + testObjClass.getSimpleName());
        int modifiersInt = testObjClass.getModifiers();
        System.out.println("Modifiers int: " + modifiersInt);
        System.out.println("isAbstract: " + Modifier.isAbstract(modifiersInt));
        System.out.println("isPublic: " + Modifier.isPublic(modifiersInt));
        System.out.println("Package: " + testObjClass.getPackage());
        System.out.println("SuperClass: " + testObjClass.getSuperclass().getName());
        System.out.println("1st level interfaces: " + getFirstLevelInterfaces(testObjClass));
        System.out.println("All interfaces: " + getAllInterfaces(testObjClass));
        System.out.println("Methods: " + Arrays.asList(testObjClass.getMethods()).stream().map(x -> x.getName()).reduce("", (acc, item) -> acc + ", " + item));
        System.out.println("Fields: " + Arrays.asList(testObjClass.getFields()).stream().map(x -> x.getName()).reduce("", (acc, item) -> acc + ", " + item));
        System.out.println("Annotaions: " + Arrays.asList(testObjClass.getAnnotations()).stream().map(x -> x.toString()).reduce("", (acc, item) -> acc + ", " + item));
        System.out.println("Constructors: " + Arrays.asList(testObjClass.getConstructors()).stream()
                .map(x -> Arrays.asList(x.getParameterTypes()).stream()
                        .map(y -> y.getName())
                        .reduce("", (acc, item) -> acc + item + ", "))
                .reduce("", (acc, item) -> acc + item + "|"));

        try {
            Constructor stringCtor = testObjClass.getConstructor(new Class[]{String.class});
            System.out.println(((TestObject) stringCtor.newInstance("bubu")).getStringValue());
            Constructor emptyCtor = testObjClass.getConstructor();
            System.out.println(((TestObject) emptyCtor.newInstance()).getStringValue());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static String getFirstLevelInterfaces(Class c) {
        if (c == null) {
            return "";
        }
        Class[] classInterfaces = c.getInterfaces();
        if (classInterfaces == null) {
            return "";
        }

        List<Class> interfacesList = Arrays.asList(classInterfaces);
        return interfacesList.stream().map(x -> x.getName()).reduce("", (acc, item) -> acc + ", " + item);

    }

    private static String getAllInterfaces(Class myClass) {
        if (myClass == null) {
            return "";
        }
        String interfacesString = "";
        interfacesString += getFirstLevelInterfaces(myClass);
        List<Class> firstLevelInterfaces = Arrays.asList(myClass.getInterfaces());
        interfacesString += firstLevelInterfaces.stream().map(c -> getAllInterfaces(c)).reduce("", (acc, item) -> acc + ", " + item);
        Class superClass = myClass.getSuperclass();
        interfacesString += getAllInterfaces(superClass);

        return interfacesString;
    }
}
