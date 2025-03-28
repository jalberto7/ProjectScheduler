package org.example.model;

import java.time.LocalDate;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private int duration;
    private List<Integer> dependencies;
    private String startDate;
    private String endDate;

    // Additional
    private int projectPlanId;

    public Task() {
    }

    public Task(int id, String name, int duration, List<Integer> dependencies, String startDate) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.dependencies = dependencies;
        this.startDate = startDate;
        this.endDate = calculateEndDate(startDate, duration);
    }

    private String calculateEndDate(String startDate, int duration) {
        return LocalDate.parse(startDate).plusDays(duration).toString();
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

    public int getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(int projectPlanId) {
        this.projectPlanId = projectPlanId;
    }
}
