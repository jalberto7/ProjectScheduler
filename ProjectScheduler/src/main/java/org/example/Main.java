package org.example;

import org.example.model.Task;
import org.example.service.Scheduler;
import org.example.util.TaskLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Project scheduler is running...");
        String filePath = "tasks.json";
        List<Task> tasks = TaskLoader.loadTasks(filePath);

        Scheduler scheduler = new Scheduler();
        scheduler.calculateSchedule(tasks);
    }
}