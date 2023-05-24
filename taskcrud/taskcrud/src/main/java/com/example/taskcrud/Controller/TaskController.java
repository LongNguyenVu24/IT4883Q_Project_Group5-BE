package com.example.taskcrud.Controller;


import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Impl.TaskExcelExporter;
import com.example.taskcrud.Impl.TaskServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping(path = "/save")
    public String saveTask(@RequestBody TaskSaveDTO taskSaveDTO) {

        String taskID = taskService.addTask(taskSaveDTO);
        return taskID;
    }

    @GetMapping(path = "/getAllTasks")
    public List<TaskDTO> getAllTask() {
        List<TaskDTO>allTasks = taskService.getAllTask();
        return allTasks;
    }

    @GetMapping(path = "/update")
    public String updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO) {
        String taskId = taskService.updateTask(taskUpdateDTO);
        return taskId;
    }

    @DeleteMapping(path = "/deletetask/{taskId}")
    public String deleteTask(@PathVariable(value = "taskId") int taskId) {
        boolean deleteTask = taskService.deleteTask(taskId);
        return "deleted";
    }

    @GetMapping(path = "/export")
    public void exportsTasks(HttpServletResponse response) throws IOException {
        List<TaskDTO> tasks = taskService.getAllTask();
        TaskExcelExporter exporter = new TaskExcelExporter(tasks);
        exporter.export(response);
    }
    @GetMapping(path = "/completionRate")
    public double getTaskCompletionRate() {
        List<TaskDTO> allTasks = taskService.getAllTask();
        long completedTasks = allTasks.stream()
                .filter(TaskDTO::isTaskStatus)
                .count();
        return (double) completedTasks / allTasks.size();
    }
    @GetMapping(path = "/priorityDistribution")
    public Map<String, Long> getTaskPriorityDistribution() {
        List<TaskDTO> allTasks = taskService.getAllTask();
        return allTasks.stream()
                .collect(Collectors.groupingBy(task -> task.isTaskPriority() ? "High" : "Low", Collectors.counting()));
    }
    @GetMapping(path = "/durationAnalysis")
    public Map<String, Double> getTaskDurationAnalysis() {
        List<TaskDTO> allTasks = taskService.getAllTask();
        double[] durationArray = allTasks.stream()
                .mapToDouble(task -> Duration.between(task.getStartDate(), task.getEndDate()).toMinutes())
                .toArray();
        double averageDuration = Arrays.stream(durationArray).average().orElse(Double.NaN);
        DescriptiveStatistics stats = new DescriptiveStatistics(durationArray);
        Map<String, Double> result = new HashMap<>();
        result.put("averageDuration", averageDuration);
        result.put("maxDuration", stats.getMax());
        result.put("minDuration", stats.getMin());
        result.put("medianDuration", stats.getPercentile(50));
        return result;
    }
    @GetMapping(path = "/throughputAnalysis")
    public Map<String, Integer> getTaskThroughputAnalysis() {
        List<TaskDTO> allTasks = taskService.getAllTask();
        Map<String, Integer> throughputByMonth = new HashMap<>();
        for (TaskDTO task : allTasks) {
            LocalDate startDate = task.getStartDate();
            String monthKey = String.format("%d-%02d", startDate.getYear(), startDate.getMonthValue());
            int currentThroughput = throughputByMonth.getOrDefault(monthKey, 0);
            throughputByMonth.put(monthKey, currentThroughput + 1);
        }
        return throughputByMonth;
    }
}