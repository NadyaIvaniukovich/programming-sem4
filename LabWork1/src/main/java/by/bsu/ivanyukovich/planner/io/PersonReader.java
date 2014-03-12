package by.bsu.ivanyukovich.planner.io;

import by.bsu.ivanyukovich.planner.model.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hope on 07.03.14.
 */
public class PersonReader {
    private String fileName;

    public PersonReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Person> read() throws IOException {
        try(FileInputStream in = new FileInputStream(fileName);
            Scanner scanner = new Scanner(in)
        ){
            List<Person> people = new ArrayList<>();
            while(scanner.hasNext()){
                String name = scanner.nextLine();
                Person person = new Person();
                person.setName(name);
                people.add(person);
            }
            return people;
        }
    }
}
