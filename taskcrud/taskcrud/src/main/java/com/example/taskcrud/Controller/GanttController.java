package com.example.taskcrud.Controller;

import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.Impl.TaskService;
import com.example.taskcrud.Repository.TaskRepo;
import com.example.taskcrud.entity.Task;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


import java.util.Comparator;

@RestController
@RequestMapping("/api")
public class GanttController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/gantt")
    public String gantt(Model model) {
        List<Task> tasks = taskRepo.findAll();

        model.addAttribute("tasks", tasks);
        model.addAttribute("startDate", tasks.stream().map(Task::getStartDate).min(Comparator.naturalOrder()).orElse(LocalDate.now()));
        model.addAttribute("endDate", tasks.stream().map(Task::getEndDate).max(Comparator.naturalOrder()).orElse(LocalDate.now().plusDays(30)));

        return "gantt";
    }

}