package com.taskmanager.service;

import com.taskmanager.model.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    // Returns only tasks owned by the given username (USER role)
    @PreAuthorize("hasRole('USER')")
    public List<Task> getTasksByOwner(String username) {
        return tasks.stream()
                .filter(t -> t.getOwnerUsername().equals(username))
                .collect(Collectors.toList());
    }

    // Returns all tasks (ADMIN role only)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    // Create a task owned by the given username (USER role)
    @PreAuthorize("hasRole('USER')")
    public void createTask(String title, String description, String ownerUsername) {
        Task task = new Task(idCounter.getAndIncrement(), title, description, false, ownerUsername);
        tasks.add(task);
    }

    // Mark a task as completed — only the owner can do this
    @PreAuthorize("hasRole('USER')")
    public boolean markCompleted(Integer id, String username) {
        for (Task task : tasks) {
            if (task.getId().equals(id) && task.getOwnerUsername().equals(username)) {
                task.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    // Delete any task (ADMIN role only)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteTask(Integer id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }
}
