package com.taskmanager.controller;

import com.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final TaskService taskService;

    public UserController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("tasks", taskService.getTasksByOwner(username));
        model.addAttribute("username", username);
        return "user/dashboard";
    }

    @GetMapping("/create")
    public String createTaskForm() {
        return "user/create-task";
    }

    @PostMapping("/create")
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             Principal principal) {
        taskService.createTask(title, description, principal.getName());
        return "redirect:/user/dashboard";
    }

    @PostMapping("/complete")
    public String completeTask(@RequestParam Integer id, Principal principal) {
        taskService.markCompleted(id, principal.getName());
        return "redirect:/user/dashboard";
    }
}
