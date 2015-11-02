package sample.java.refelction;

import java.io.Serializable;

/**
 * Created by kopelevi on 01/11/2015.
 */
@MyAnnotaion(name = "someName", value = "Hello World")
public class TestObject extends TestObjectAbstract implements Serializable {

    private static String privateStaticString = "deafult";
    private static final int PRIVATE_STATIC_FINAL_INT = 7;

    public static String staticField = "staticField";
    public String memberField = "memberField";

    public TestObject() {
        this.privateStaticString = "defaultString";
    }

    public TestObject(String str) {
        this.privateStaticString = str;
    }

    @Override
    public String getStringValue() {
        return privateStaticString;
    }

    public static String setStringValueAndReturnOldValue(String str) {
        String oldStr = privateStaticString;
        privateStaticString = str;
        return oldStr;
    }

    @Override
    public int getIntValue() {
        return PRIVATE_STATIC_FINAL_INT;
    }

    private int getIntValuePlusOne() {
        return getIntValue() + 1;
    }
}
