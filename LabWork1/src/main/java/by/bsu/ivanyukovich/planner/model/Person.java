package by.bsu.ivanyukovich.planner.model;

/**
 * Created by Hope on 07.03.14.
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
