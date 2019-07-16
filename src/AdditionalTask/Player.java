package AdditionalTask;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private String surname;
    private int age;

    public Player(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player: "+ name +
                " " + surname + ", " +
                "age: " + age;
    }
}
