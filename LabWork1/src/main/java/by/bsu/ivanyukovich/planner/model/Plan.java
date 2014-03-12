package by.bsu.ivanyukovich.planner.model;

import java.util.List;

/**
 * Created by Hope on 3/12/14.
 */
public class Plan {
    private List<Task> assignedTasks;
    private int totalTime;

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return assignedTasks + "\nTotal time: " + totalTime;
    }
}
