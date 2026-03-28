package scheduler.observer;

import scheduler.model.Task;

/*
Concrete observer that handles notifications.
Prints message when task becomes urgent.
*/

public class NotificationService implements TaskObserver {

    @Override
    public void update(Task task) {
        System.out.println("⚠ Task \"" + task.getTitle() + "\" is now URGENT!");
    }
}
