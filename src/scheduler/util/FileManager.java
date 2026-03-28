package scheduler.util;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import scheduler.model.Task;

public class FileManager {
    
    public void saveTasks(List<Task> tasks, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
    
    public List<Task> loadTasks(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object loaded = ois.readObject();
            if (loaded instanceof List) {
                @SuppressWarnings("unchecked")
                List<Task> raw = (List<Task>) loaded;
                tasks = raw;
            } else {
                System.err.println("Loaded object is not a List<Task>: " + loaded.getClass());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }
    
}
