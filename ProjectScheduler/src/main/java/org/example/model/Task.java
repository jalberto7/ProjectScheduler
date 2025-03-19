package org.example.model;

import java.util.List;

public class Task {
    private int id;
    private String name;
    private int duration;
    private List<Integer> dependencies;
    private String startDate;
    private String endDate;

    public Task() {
    }

    public Task(int id, String name, int duration, List<Integer> dependencies) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.dependencies = dependencies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Integer> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Integer> dependencies) {
        this.dependencies = dependencies;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
