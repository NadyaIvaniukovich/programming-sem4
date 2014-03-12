package by.bsu.ivanyukovich.planner;

import by.bsu.ivanyukovich.planner.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hope on 07.03.14.
 */
public class Planner {
    private List<Task> tasks;
    private List<Person> freePeople;
    private List<Task> unfinishedTasks = new ArrayList<>();
    private int totalTime = 0;
    private int lastIndex = 0;

    public Planner(List<Task> tasks, List<Person> people) {
        this.tasks = tasks;
        this.freePeople = people;
    }

    public Plan plan(){

        int assignedIndex = 0;
        List<Task> assignedTasks = new ArrayList<>();
        Plan plan = new Plan();

        while (assignedTasks.size() < tasks.size()){


            lastIndex = 0;

            for(Person person: freePeople){
                Task task = getUnassignedIndependentTask();
                if(task != null){
                    task.setAssignee(person);
                    task.setStartTime(totalTime);
                    assignedTasks.add(task);
                    unfinishedTasks.add(task);
                } else {
                    break;
                }
            }

            // No free people, still have unassigned independent tasks
            while(lastIndex < tasks.size() - 1){
                Task lastFinishedTask = nextFinishedTask();
                Person freePerson = lastFinishedTask.getAssignee();
                Task task = getUnassignedIndependentTask();
                if(task != null){
                    task.setAssignee(freePerson);
                    task.setStartTime(lastFinishedTask.getEndTime());
                    assignedTasks.add(task);
                    unfinishedTasks.add(task);
                    unfinishedTasks.remove(lastFinishedTask);
                }
            }

            findTotalTime();

            // No unassigned independent tasks left

            for ( ; assignedIndex < assignedTasks.size(); assignedIndex++){
                Task assignedTask = assignedTasks.get(assignedIndex);
                for(Task dependency: assignedTask.getDependencies()){
                    dependency.removeFinishedDependsOn(assignedTask);
                }
            }
        }

        plan.setAssignedTasks(assignedTasks);
        plan.setTotalTime(totalTime);

        return plan;
    }

    private Task nextFinishedTask(){
        Task task = null;
        for(Task unfinished: unfinishedTasks){
            if(task == null || unfinished.getEndTime() < task.getEndTime()){
                task = unfinished;
            }
        }
        return task;
    }
    private Task getUnassignedIndependentTask(){
        while (lastIndex < tasks.size()){
            Task task = tasks.get(lastIndex);
            if(task.getAssignee() == null && task.getUnfinishedDependsOn().size() == 0){
                return task;
            }
            lastIndex++;
        }
        return null;
    }

    private void findTotalTime(){
        for(Task task: unfinishedTasks){
            if(task.getEndTime() > totalTime){
                totalTime = task.getEndTime();
            }
        }
    }
}
