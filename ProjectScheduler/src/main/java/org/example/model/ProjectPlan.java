package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlan {
    private int id;
    private String name;
    private List<Task> tasks;

    public ProjectPlan(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) { this.tasks.add(task); }
}
