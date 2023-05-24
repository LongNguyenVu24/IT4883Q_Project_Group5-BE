package com.example.taskcrud.Controller;


import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Impl.TaskExcelExporter;
import com.example.taskcrud.Impl.TaskServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

}
