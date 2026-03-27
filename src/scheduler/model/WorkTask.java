package scheduler.model;

import java.time.LocalDateTime;

/*
Represents a work-related task.
*/

public class WorkTask extends Task {

    public WorkTask(String title, int priority, LocalDateTime deadline) {
        super(title, priority, deadline, false);
    }

    @Override
    public String getTaskType() {
        return "Work Task";
    }
}
