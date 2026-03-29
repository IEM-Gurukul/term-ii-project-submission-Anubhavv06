package scheduler.strategy;

import scheduler.model.Task;
import java.time.Duration;
import java.time.LocalDateTime;

/*
Priority based on how close the deadline is.
Closer deadline = higher priority.
*/

public class DeadlinePriorityStrategy implements PriorityStrategy {

    @Override
    public int calculatePriority(Task task) {
        // Calculate time remaining until task deadline in hours
        long hoursLeft = Duration.between(LocalDateTime.now(), task.getDeadline()).toHours();

        // Invert deadline distance to produce higher priority for closer deadlines.
        // This is a simple heuristic: a task due now or overdue gets high score.
        return (int)(100 - hoursLeft);
    }
}
