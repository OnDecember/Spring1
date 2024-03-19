package org.maxym.spring.controller;

import lombok.RequiredArgsConstructor;
import org.maxym.spring.domain.Task;
import org.maxym.spring.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasks(@RequestParam(name = "page_number", required = false) Integer pageNumber,
                              @RequestParam(name = "page_size", required = false) Integer pageSize,
                              Model model) {
        List<Task> tasks;

        if (nonNull(pageNumber) && nonNull(pageSize))
            tasks = taskService.findAll(pageNumber, pageSize);
        else
            tasks = taskService.findAll();

        model.addAttribute("tasks", tasks);
        return "tasks/all";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PatchMapping
    public String updateTask(@RequestBody Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
