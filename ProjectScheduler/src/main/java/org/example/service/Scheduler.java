package org.example.service;

import org.example.model.Task;

import java.time.LocalDate;
import java.util.*;

public class Scheduler {
    public void calculateSchedule(List<Task> tasks) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Task> taskMap = new HashMap<>();

        // Initialize graph, inDegree, and taskMap
        for (Task task : tasks) {
            graph.put(task.getId(), new ArrayList<>());
            inDegree.put(task.getId(), 0);
            taskMap.put(task.getId(), task);
        }

        // Build the graph and calculate in-degrees
        for (Task task : tasks) {
            for (Integer dependency : task.getDependencies()) {
                graph.get(dependency).add(task.getId());
                inDegree.put(task.getId(), inDegree.get(task.getId()) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer taskId : inDegree.keySet()) {
            if (inDegree.get(taskId) == 0) {
                queue.add(taskId);
            }
        }

        Map<Integer, LocalDate> endDates = new HashMap<>();
        while (!queue.isEmpty()) {
            int taskId = queue.poll();
            Task task = taskMap.get(taskId);

            // Set start date based on the latest end date of dependencies
            LocalDate startDate = task.getDependencies().stream()
                    .map(dep -> endDates.get(dep)) // Get the end date of dependencies
                    .filter(Objects::nonNull)
                    .max(LocalDate::compareTo) // Take the latest end date
                    .map(date -> date.plusDays(1)) // Start the day after the latest dependency ends
                    .orElse(LocalDate.now()); // Default to today if no dependencies

            LocalDate endDate = startDate.plusDays(task.getDuration());

            // Assign calculated dates to task
            task.setStartDate(startDate.toString());
            task.setEndDate(endDate.toString());

            // Update end dates
            endDates.put(taskId, endDate);

            for (Integer next : graph.get(taskId)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.add(next);
                }
            }
        }

        // Check for cyclic dependency
        if (endDates.size() < tasks.size()) {
            throw new RuntimeException("Cycle detected in task dependencies");
        }

        // Print Results (Optional)
        tasks.forEach(task ->
                System.out.println("Task: " + task.getName() +
                        " → Start Date: " + task.getStartDate() +
                        ", End Date: " + task.getEndDate()));
    }

}
