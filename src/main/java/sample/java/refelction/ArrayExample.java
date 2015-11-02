package sample.java.refelction;

import java.lang.reflect.Array;

/**
 * Created by kopelevi on 01/11/2015.
 */
public class ArrayExample {
    public static void main(String[] args) throws ClassNotFoundException {
        int[] intArray = (int[]) Array.newInstance(int.class, 7);

        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));

        Class intArr = Class.forName("[I");
        Class StringArr = Class.forName("[Ljava.lang.String;");


        Class stringArrayClass = Array.newInstance(String.class, 0).getClass();
        System.out.println("is array: " + stringArrayClass.isArray());

        String[] strings = new String[3];
        Class stringArrayClass2 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass2.getComponentType();
        System.out.println(stringArrayComponentType);
    }
}
