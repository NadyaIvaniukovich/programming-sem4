package by.bsu.ivanyukovich.planner.io;

import by.bsu.ivanyukovich.planner.model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hope on 07.03.14.
 */
public class TaskReader {
    private String fileName;

    public TaskReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Task> read() throws IOException {

        try(FileInputStream in = new FileInputStream(fileName);
            Scanner scanner = new Scanner(in)
        ){
            List<Task> tasks = new ArrayList<>();
            List<int[]> dependencies = new ArrayList<>();
            while(scanner.hasNext()){
                String[] row = scanner.nextLine().split(",");
                Task task = new Task();
                task.setIndex(tasks.size());
                task.setName(row[0]);
                task.setEstimatedTime(Integer.parseInt(row[1]));
                tasks.add(task);

                int[] dependsOn = new int[row.length - 2];
                for(int i = 0; i < dependsOn.length; i++){
                    dependsOn[i] = Integer.parseInt(row[i + 2]) - 1;
                }
                dependencies.add(dependsOn);
            }

            for (int i = 0; i < dependencies.size(); i++){
                Task task = tasks.get(i);
                for (int dependsOnIndex: dependencies.get(i)){
                    task.getUnfinishedDependsOn().add(tasks.get(dependsOnIndex));
                    tasks.get(dependsOnIndex).getDependencies().add(task);
                }
            }

            return tasks;
        }
    }
}
