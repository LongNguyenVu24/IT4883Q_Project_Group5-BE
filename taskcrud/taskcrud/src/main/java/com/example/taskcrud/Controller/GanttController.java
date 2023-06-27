package com.example.taskcrud.Controller;

import com.example.taskcrud.Repository.TaskRepo;
import com.example.taskcrud.entity.GanttResponse;
import com.example.taskcrud.entity.Task;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GanttController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/gantt")
    public GanttResponse gantt() {
        List<Task> tasks = taskRepo.findAll();

//        model.addAttribute("tasks", tasks);
//        model.addAttribute("startDate", tasks.stream().map(Task::getStartDate).min(Comparator.naturalOrder()).orElse(LocalDate.now()));
//        model.addAttribute("endDate", tasks.stream().map(Task::getEndDate).max(Comparator.naturalOrder()).orElse(LocalDate.now().plusDays(30)));
//
//        return "gantt";
        LocalDate startDate = tasks.stream().map(Task::getStartDate).min(Comparator.naturalOrder()).orElse(LocalDate.now());
        LocalDate endDate = tasks.stream().map(Task::getEndDate).min(Comparator.naturalOrder()).orElse(LocalDate.now().plusDays(30));

        GanttResponse response = new GanttResponse(tasks, startDate, endDate);
        return response;

    }

}