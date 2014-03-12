package by.bsu.ivanyukovich.planner;

import by.bsu.ivanyukovich.planner.io.PersonReader;
import by.bsu.ivanyukovich.planner.io.TaskReader;
import by.bsu.ivanyukovich.planner.model.Person;
import by.bsu.ivanyukovich.planner.model.Plan;
import by.bsu.ivanyukovich.planner.model.Task;

import java.util.List;

/**
 * Created by Hope on 07.03.14.
 */
public class Main {
    public static void main(String[] args) {
        try {
            PersonReader personReader = new PersonReader("src/main/resources/people.csv");
            List<Person> people = personReader.read();
            TaskReader taskReader = new TaskReader("src/main/resources/tasks.csv");
            List<Task> tasks = taskReader.read();
            Planner planner = new Planner(tasks, people);
            Plan plan = planner.plan();
            System.out.println(plan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
