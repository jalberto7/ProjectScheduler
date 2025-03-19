package service;

import org.example.model.Task;
import org.example.service.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchedulerTest {

    private Scheduler scheduler;

    @BeforeEach
    void setUp() {
        scheduler = new Scheduler();
    }

    @Test
    void testSuccessfulScheduling() {
        // Arrange
        Task task1 = new Task(1, "Design", 5, Arrays.asList());
        Task task2 = new Task(2, "Development", 10, Arrays.asList(1));
        Task task3 = new Task(3, "Testing", 3, Arrays.asList(2));

        List<Task> tasks = Arrays.asList(task1, task2, task3);

        // Act
        scheduler.calculateSchedule(tasks);

        // Assert
        assertNotNull(task1.getStartDate());
        assertNotNull(task1.getEndDate());
        assertNotNull(task2.getStartDate());
        assertNotNull(task2.getEndDate());
        assertNotNull(task3.getStartDate());
        assertNotNull(task3.getEndDate());
    }

    @Test
    void testIndependentTasksStartImmediately() {
        // Arrange
        Task task1 = new Task(1, "Task A", 5, Arrays.asList());
        Task task2 = new Task(2, "Task B", 3, Arrays.asList());

        List<Task> tasks = Arrays.asList(task1, task2);

        // Act
        scheduler.calculateSchedule(tasks);

        // Assert
        assertEquals(task1.getStartDate(), task2.getStartDate());
    }

    @Test
    void testMultipleDependencies() {
        // Arrange
        Task task1 = new Task(1, "Task A", 2, Arrays.asList());
        Task task2 = new Task(2, "Task B", 3, Arrays.asList(1));
        Task task3 = new Task(3, "Task C", 1, Arrays.asList(1, 2));

        List<Task> tasks = Arrays.asList(task1, task2, task3);

        // Act
        scheduler.calculateSchedule(tasks);

        // Assert
        assertNotNull(task3.getStartDate());
        assertNotNull(task3.getEndDate());
        assertTrue(task3.getStartDate().compareTo(task2.getEndDate()) > 0);
    }

    @Test
    void testCircularDependencyShouldFail() {
        // Arrange
        Task task1 = new Task(1, "Task A", 2, Arrays.asList(2));
        Task task2 = new Task(2, "Task B", 3, Arrays.asList(1));

        List<Task> tasks = Arrays.asList(task1, task2);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            scheduler.calculateSchedule(tasks);
        });

        assertTrue(exception.getMessage().contains("Cycle detected"));
    }
}
