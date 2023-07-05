package com.example.taskcrud.Controller;


import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Impl.TaskExcelExporter;
import com.example.taskcrud.Impl.TaskServiceImpl;
import com.example.taskcrud.entity.Task;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "api/task", produces = "application/json")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @PostMapping(path = "/save")
    public String saveTask(@RequestBody TaskSaveDTO taskSaveDTO) {

        String taskId = taskServiceImpl.addTask(taskSaveDTO);
        return taskId;
    }

    @GetMapping(path = "/getAllTasks")
    public List<TaskDTO> getAllTask() {
        List<TaskDTO>allTasks = taskServiceImpl.getAllTask();
        return allTasks;
    }

    @PutMapping(path = "/update")
    public String updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO) {
        String taskId = taskServiceImpl.updateTask(taskUpdateDTO);
        return taskId;
    }

    @DeleteMapping(path = "/deletetask/{taskId}")
    public String deleteTask(@PathVariable(value = "taskId") int taskId) {
        boolean deleteTask = taskServiceImpl.deleteTask(taskId);
        return "deleted";
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> searchTasks(
            @RequestParam(required = false) String taskName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        List<TaskDTO> tasks = taskServiceImpl.searchTasks(taskName,startDate,endDate);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }   else {
            return ResponseEntity.ok(tasks);
        }
    }

    @GetMapping(path = "/export")
    public void exportsTasks(HttpServletResponse response) throws IOException {
        List<TaskDTO> tasks = taskServiceImpl.getAllTask();
        TaskExcelExporter exporter = new TaskExcelExporter(tasks);
       exporter.export(response);
    }
    @PostMapping(path = "/import")
    public String importTasks(@RequestParam("file")MultipartFile file) throws IOException, InvalidFormatException {

        List<Task> taskList = new ArrayList<>();
return "";
    }
    @GetMapping(path = "/completionRate")
    public double getTaskCompletionRate() {
        List<TaskDTO> allTasks = taskServiceImpl.getAllTask();
        long completedTasks = allTasks.stream()
                .filter(TaskDTO::isTaskStatus)
                .count();
        return (double) completedTasks / allTasks.size();
    }
    @GetMapping(path = "/priorityDistribution")
    public Map<String, Long> getTaskPriorityDistribution() {
        List<TaskDTO> allTasks = taskServiceImpl.getAllTask();
        return allTasks.stream()
                .collect(Collectors.groupingBy(task -> task.isTaskPriority() ? "High" : "Low", Collectors.counting()));
    }
    @GetMapping(path = "/durationAnalysis")
    public Map<String, Double> getTaskDurationAnalysis() {
        List<TaskDTO> allTasks = taskServiceImpl.getAllTask();
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
        List<TaskDTO> allTasks = taskServiceImpl.getAllTask();
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
