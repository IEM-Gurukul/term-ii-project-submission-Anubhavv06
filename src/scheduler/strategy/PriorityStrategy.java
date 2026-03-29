package scheduler.strategy;

import scheduler.model.Task;

/*
Strategy interface for calculating task priority.
Different strategies will implement this.
*/

public interface PriorityStrategy {
    // Calculate a priority score for a task.
    // Implementations determine how importance/deadline contributes.
    int calculatePriority(Task task);
}
