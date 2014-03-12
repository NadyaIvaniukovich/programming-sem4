package by.bsu.ivanyukovich.planner.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hope on 07.03.14.
 */
public class Task {
    private int index;
    private String name;
    private int estimatedTime;
    private Person assignee;
    private int startTime;
    private List<Task> dependencies;
    private List<Task> unfinishedDependsOn;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public void setUnfinishedDependsOnDependsOn(List<Task> dependsOn) {
        unfinishedDependsOn = dependsOn;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return startTime + estimatedTime;
    }

    public List<Task> getDependencies() {
        if(dependencies == null){
            dependencies = new ArrayList<>();
        }
        return dependencies;
    }

    public void setDependencies(List<Task> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Task> getUnfinishedDependsOn() {
        if(unfinishedDependsOn == null) {
            unfinishedDependsOn = new ArrayList<>();
        }
        return unfinishedDependsOn;
    }

    public void removeFinishedDependsOn(Task finished){
        unfinishedDependsOn.remove(finished);
    }

    @Override
    public String toString() {
        return "\nTask:" + name + " by " + assignee + ", time: " + startTime + "-" + getEndTime();
    }
}
