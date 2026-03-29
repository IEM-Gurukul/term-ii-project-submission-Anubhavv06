package scheduler;

import scheduler.model.Task;
import scheduler.model.PersonalTask;
import scheduler.model.StudyTask;
import scheduler.model.WorkTask;
import scheduler.service.TaskScheduler;
import scheduler.strategy.DeadlinePriorityStrategy;
import scheduler.strategy.ImportancePriorityStrategy;
import scheduler.strategy.PriorityStrategy;
import scheduler.util.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler(new ImportancePriorityStrategy());
        FileManager fileManager = new FileManager();
        String fileName = "tasks.dat";

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String input;
            try {
                input = scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case "1":
                    addTask(scanner, scheduler);
                    break;
                case "2":
                    viewTasks(scheduler);
                    break;
                case "3":
                    markTaskCompleted(scanner, scheduler);
                    break;
                case "4":
                    changeStrategy(scanner, scheduler);
                    break;
                case "5":
                    saveTasks(fileManager, scheduler, fileName);
                    break;
                case "6":
                    fileManager.saveTasks(scheduler.getTasks(), fileName);
                    System.out.println("Tasks saved and exiting.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Smart Task Scheduler ===");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task Completed");
        System.out.println("4. Change Strategy");
        System.out.println("5. Save Tasks");
        System.out.println("6. Exit");
    }

    private static void addTask(Scanner scanner, TaskScheduler scheduler) {
        System.out.println("Choose task type:");
        System.out.println("1. Study");
        System.out.println("2. Work");
        System.out.println("3. Personal");
        System.out.print("Type: ");

        String typeInput = scanner.nextLine().trim();
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Priority (1-10): ");
        int priority;
        try {
            priority = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid priority. Task not added.");
            return;
        }

        System.out.print("Deadline (yyyy-MM-dd HH:mm): ");
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(scanner.nextLine().trim(), DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Task not added.");
            return;
        }

        Task task;
        switch (typeInput) {
            case "1":
                task = new StudyTask(title, priority, deadline);
                break;
            case "2":
                task = new WorkTask(title, priority, deadline);
                break;
            case "3":
                task = new PersonalTask(title, priority, deadline);
                break;
            default:
                System.out.println("Invalid task type. Task not added.");
                return;
        }

        scheduler.addTask(task);
        System.out.println("Task added: " + task.getTitle());
    }

    private static void viewTasks(TaskScheduler scheduler) {
        List<Task> tasks = scheduler.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("Current tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d. [%s] %s (priority=%d, deadline=%s, completed=%s)\n",
                    i + 1,
                    t.getTaskType(),
                    t.getTitle(),
                    t.getPriority(),
                    t.getDeadline().format(DATE_FORMAT),
                    t.isCompleted());
        }
    }

    private static void markTaskCompleted(Scanner scanner, TaskScheduler scheduler) {
        viewTasks(scheduler);
        System.out.print("Enter task number to mark completed: ");

        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (scheduler.markTaskCompleted(index)) {
                System.out.println("Task marked completed.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number.");
        }
    }

    private static void changeStrategy(Scanner scanner, TaskScheduler scheduler) {
        System.out.println("Choose strategy:");
        System.out.println("1. Importance priority");
        System.out.println("2. Deadline priority");
        System.out.print("Option: ");

        String input = scanner.nextLine().trim();
        PriorityStrategy strategy;
        switch (input) {
            case "1":
                strategy = new ImportancePriorityStrategy();
                scheduler.setPriorityStrategy(strategy);
                System.out.println("Switched to Importance strategy.");
                break;
            case "2":
                strategy = new DeadlinePriorityStrategy();
                scheduler.setPriorityStrategy(strategy);
                System.out.println("Switched to Deadline strategy.");
                break;
            default:
                System.out.println("Invalid strategy option.");
        }
    }

    private static void saveTasks(FileManager fileManager, TaskScheduler scheduler, String filename) {
        fileManager.saveTasks(scheduler.getTasks(), filename);
        System.out.println("Tasks saved to " + filename);
    }
}

