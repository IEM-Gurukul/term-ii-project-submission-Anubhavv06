package scheduler.strategy;

import scheduler.model.Task;

/*
Priority based mainly on task importance value.
*/

public class ImportancePriorityStrategy implements PriorityStrategy {

    @Override
    public int calculatePriority(Task task) {
        // Convert task importance (1-10) into a priority score
        // Higher importance means larger computed priority.
        // Multiplying by 10 keeps scores on a similar scale as deadline strategy.
        return task.getPriority() * 10;
    }
}
