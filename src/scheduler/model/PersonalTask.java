package scheduler.model;

import java.time.LocalDateTime;

/*
Represents a personal task.
*/

public class PersonalTask extends Task {

    public PersonalTask(String title, int priority, LocalDateTime deadline) {
        super(title, priority, deadline, false);
    }

    @Override
    public String getTaskType() {
        return "Personal Task";
    }
}
