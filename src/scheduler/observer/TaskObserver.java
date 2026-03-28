package scheduler.observer;

import scheduler.model.Task;

/*
Observer interface for task updates.
Used to notify when a task becomes urgent.
*/

public interface TaskObserver {
    void update(Task task);
}
