package scheduler.strategy;

import scheduler.model.Task;

/*
Priority based mainly on task importance value.
*/

public class ImportancePriorityStrategy implements PriorityStrategy {

    @Override
    public int calculatePriority(Task task) {
        return task.getPriority() * 10;
    }
}
