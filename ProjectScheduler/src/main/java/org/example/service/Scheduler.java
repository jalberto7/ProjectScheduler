package org.example.service;

import org.example.model.ProjectPlan;
import org.example.model.Task;

import java.time.LocalDate;

public class Scheduler {
    public static void calculateSchedule(ProjectPlan projectPlan) {
        if (projectPlan.getTasks().isEmpty()) {
            System.out.println("No tasks found for this project plan.");
            return;
        }

        System.out.println("\nCalculating schedule for " + projectPlan.getName() + "...");

        LocalDate projectStartDate = null;
        LocalDate projectEndDate = null;
        int totalDuration = 0;

        for (Task task : projectPlan.getTasks()) {
            LocalDate taskStartDate = LocalDate.parse(task.getStartDate());
            LocalDate taskEndDate = taskStartDate.plusDays(task.getDuration());

            System.out.println("Task " + task.getId() + ": " + task.getName());
            System.out.println("  Start Date: " + taskStartDate);
            System.out.println("  Duration: " + task.getDuration() + " days");
            System.out.println("  End Date: " + taskEndDate);

            if (!task.getDependencies().isEmpty()) {
                System.out.println("  Dependencies: " + task.getDependencies());
            }
            System.out.println();

            // Set project start and end date
            if (projectStartDate == null || taskStartDate.isBefore(projectStartDate)) {
                projectStartDate = taskStartDate;
            }
            if (projectEndDate == null || taskEndDate.isAfter(projectEndDate)) {
                projectEndDate = taskEndDate;
            }
        }

        totalDuration = (int) (projectEndDate.toEpochDay() - projectStartDate.toEpochDay());

        System.out.println("Project Plan: " + projectPlan.getName());
        System.out.println("Start Date: " + projectStartDate);
        System.out.println("End Date: " + projectEndDate);
        System.out.println("Total Duration: " + totalDuration + " days");
    }


}
