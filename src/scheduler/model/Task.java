package scheduler.model;

import java.time.LocalDateTime;

public abstract class Task {
    private String title;
    private int priority;
    private LocalDateTime deadline;
    private boolean completed;
    
    public Task(String title, int priority, LocalDateTime deadline, boolean completed) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = completed;
    }
    
    public boolean isUrgent() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusHours(24);
        
        boolean withinOneDay = deadline.isAfter(now) && deadline.isBefore(tomorrow);
        boolean veryHighPriority = priority >= 8;
        boolean isPastDue = deadline.isBefore(now);
        
        return withinOneDay || veryHighPriority || isPastDue;
    }
    
    public abstract String getTaskType();
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }
    
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
