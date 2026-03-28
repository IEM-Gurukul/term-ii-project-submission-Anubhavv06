package scheduler.service;

import scheduler.model.Task;
import scheduler.observer.TaskObserver;
import scheduler.strategy.PriorityStrategy;

import java.util.*;

/*
Main scheduler that manages tasks with priority-based ordering.
Uses observer pattern to notify when tasks become urgent.
*/

public class TaskScheduler {
    private PriorityQueue<Task> taskQueue;
    private List<TaskObserver> observers;
    private PriorityStrategy priorityStrategy;

    public TaskScheduler(PriorityStrategy priorityStrategy) {
        this.priorityStrategy = priorityStrategy;
        this.observers = new ArrayList<>();
        // Create PriorityQueue with comparator based on priority strategy
        this.taskQueue = new PriorityQueue<>((task1, task2) -> 
            Integer.compare(priorityStrategy.calculatePriority(task2), 
                          priorityStrategy.calculatePriority(task1))
        );
    }

    public void addTask(Task task) {
        taskQueue.add(task);
        if (task.isUrgent()) {
            notifyObservers(task);
        }
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void setPriorityStrategy(PriorityStrategy strategy) {
        this.priorityStrategy = strategy;
    }

    public int getPriority(Task task) {
        return priorityStrategy.calculatePriority(task);
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }
}
