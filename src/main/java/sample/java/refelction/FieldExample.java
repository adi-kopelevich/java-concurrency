package sample.java.refelction;

import java.lang.reflect.Field;

/**
 * Created by kopelevi on 01/11/2015.
 */
public class FieldExample {

    public static void main(String[] args) {
        Class aClass = TestObject.class;
        Field memberField = null;
        try {
            memberField = aClass.getField("memberField");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        TestObject objectInstance = new TestObject();

        Object value = null;
        try {
            value = memberField.get(objectInstance);
            System.out.println("Member field value: " + value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            memberField.set(objectInstance, value + "UpdatedMember!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        value = null;
        try {
            value = memberField.get(objectInstance);
            System.out.println("Member Field updated value: " + value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field staticField = null;
        try {
            staticField = aClass.getField("staticField");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }



        Object staticValue = null;
        try {
            staticValue = staticField.get(null);
            System.out.println("Static field value: " + staticValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            staticField.set(null, staticValue + "UpdatedStatic!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        staticValue = null;
        try {
            staticValue = staticField.get(null);
            System.out.println("Static Field updated value: " + staticValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
