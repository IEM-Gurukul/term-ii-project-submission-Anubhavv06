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
        // Rebuild queue with new comparator so existing tasks are ordered by new strategy.
        List<Task> tasks = new ArrayList<>(taskQueue);
        this.priorityStrategy = strategy;
        this.taskQueue = new PriorityQueue<>((task1, task2) -> 
            Integer.compare(priorityStrategy.calculatePriority(task2), 
                          priorityStrategy.calculatePriority(task1))
        );
        this.taskQueue.addAll(tasks);
    }

    public int getPriority(Task task) {
        return priorityStrategy.calculatePriority(task);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(taskQueue);
    }

    public boolean markTaskCompleted(int index) {
        List<Task> tasks = new ArrayList<>(taskQueue);
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        Task task = tasks.get(index);
        task.setCompleted(true);

        // To keep queue order and state updated, rebuild the priority queue from modified list.
        taskQueue.clear();
        taskQueue.addAll(tasks);
        return true;
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }
}
