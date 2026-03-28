package scheduler.strategy;

import scheduler.model.Task;

/*
Strategy interface for calculating task priority.
Different strategies will implement this.
*/

public interface PriorityStrategy {
    int calculatePriority(Task task);
}
