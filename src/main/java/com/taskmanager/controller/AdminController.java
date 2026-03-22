package com.taskmanager.controller;

import com.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TaskService taskService;

    public AdminController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "admin/dashboard";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Integer id) {
        taskService.deleteTask(id);
        return "redirect:/admin/dashboard";
    }
}
