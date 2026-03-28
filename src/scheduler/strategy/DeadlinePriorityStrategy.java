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
        long hoursLeft = Duration.between(LocalDateTime.now(), task.getDeadline()).toHours();
        return (int)(100 - hoursLeft); // closer deadline → higher value
    }
}
