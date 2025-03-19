package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Task;


import java.io.File;
import java.util.List;

public class TaskLoader {
    public static List<Task> loadTasks(String filePath) {
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            TaskList taskList = objectMapper.readValue(new File(filePath), TaskList.class);
            return taskList.getTasks();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load task from file: " + e.getMessage());
        }
    }

    private static class TaskList {
        private List<Task> tasks;

        public List<Task> getTasks() {
            return tasks;
        }
    }
}
