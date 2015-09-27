package sample.java.io.serilaizer;

import java.io.Serializable;

/**
 * Created by kopelevi on 27/09/2015.
 */
public class Person implements Serializable {

    // serialized object version
    private static final long serialVersionUID = 1234L;

    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return "First Name: " + getFirstName() + ", Last Name: " + getLastName();
    }
}
