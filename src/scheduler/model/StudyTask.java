package scheduler.model;

import java.time.LocalDateTime;

/*
Represents a study-related task.
Extends Task and defines its own type.
*/

public class StudyTask extends Task {

    public StudyTask(String title, int priority, LocalDateTime deadline) {
        super(title, priority, deadline, false);
    }

    @Override
    public String getTaskType() {
        return "Study Task";
    }
}
