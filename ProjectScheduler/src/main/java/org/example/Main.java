package org.example;

import org.example.model.ProjectPlan;
import org.example.model.Task;
import org.example.service.Scheduler;
import org.example.util.TaskLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<ProjectPlan> projectPlans = new ArrayList<>();
    private static final Scheduler scheduler = new Scheduler();

    public static void main(String[] args) {
//        System.out.println("Project scheduler is running...");
//        String filePath = "tasks.json";
//        List<Task> tasks = TaskLoader.loadTasks(filePath);
//
//        Scheduler scheduler = new Scheduler();
//        scheduler.calculateSchedule((ProjectPlan) tasks);
        while (true) {
            System.out.println("\n=== Project Scheduler Menu ===");
            System.out.println("1. Add Project Plan");
            System.out.println("2. Add Task to Project Plan");
            System.out.println("3. Calculate Schedule for a Project Plan");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProjectPlan();
                    break;
                case 2:
                    addTaskToProject();
                    break;
                case 3:
                    calculateProjectSchedule();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProjectPlan() {
        System.out.print("Enter Project Plan Name: ");
        String name = scanner.nextLine();

        int id = projectPlans.size() + 1;  // Auto-generate ID
        ProjectPlan projectPlan = new ProjectPlan(id, name);

        projectPlans.add(projectPlan);
        System.out.println("Project Plan added successfully! [ID: " + projectPlan.getId() + ", Name: " + projectPlan.getName() + "]");
    }


    private static void addTaskToProject() {
        if (projectPlans.isEmpty()) {
            System.out.println("No project plans available. Add a project plan first.");
            return;
        }

        listProjectPlans();
        System.out.print("Select a Project Plan ID: ");

        int projectId = getValidIntegerInput(); // Ensures valid integer input
        ProjectPlan selectedProject = getProjectPlanById(projectId);

        if (selectedProject == null) {
            System.out.println("Invalid Project Plan ID.");
            return;
        }

        System.out.print("Enter Task Name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter Task Duration (in days): ");
        int duration = getValidIntegerInput(); // Ensures valid integer input

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        // Task ID auto-generated
        int taskId = selectedProject.getTasks().size() + 1;

        // ✅ Fix: Properly handle dependencies
        System.out.print("Enter Number of Dependencies: ");
        int numDependencies = getValidIntegerInput(); // Ensures valid integer input

        List<Integer> dependencies = new ArrayList<>();
        for (int i = 0; i < numDependencies; i++) {
            System.out.print("Enter Dependency Task ID: ");
            int dependencyId = getValidIntegerInput(); // Ensures valid integer input
            dependencies.add(dependencyId);
        }

        // Create and add task
        Task task = new Task(taskId, taskName, duration, dependencies, startDate);
        task.setProjectPlanId(projectId);
        selectedProject.addTask(task);

        System.out.println("Task '" + taskName + "' added to Project Plan '" + selectedProject.getName() + "'!");
    }

    private static int getValidIntegerInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Clear the invalid input
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static void calculateProjectSchedule() {
        if (projectPlans.isEmpty()) {
            System.out.println("No project plans available. Add a project plan first.");
            return;
        }

        listProjectPlans();
        System.out.print("Select a Project Plan ID: ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ProjectPlan selectedProject = getProjectPlanById(projectId);
        if (selectedProject == null) {
            System.out.println("Invalid Project Plan ID.");
            return;
        }

        Scheduler.calculateSchedule(selectedProject);
    }


    private static void listProjectPlans() {
        System.out.println("\nAvailable Project Plans:");
        for (ProjectPlan plan : projectPlans) {
            System.out.println(plan.getId() + ". " + plan.getName());
        }
    }

    private static ProjectPlan getProjectPlanById(int id) {
        return projectPlans.stream()
                .filter(plan -> plan.getId() == id)
                .findFirst()
                .orElse(null);
    }
}