package com.example.taskcrud.Impl;

import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Repository.TaskRepo;
import com.example.taskcrud.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;


    public Task getTaskswithSubTasks(int taskId) {
        return taskRepo.findById(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public String addTask(TaskSaveDTO taskSaveDTO) {
        Task task = new Task (
               taskSaveDTO.getTaskName(),
                taskSaveDTO.getTaskDiscription(),
                taskSaveDTO.getStartDate(),
                taskSaveDTO.getEndDate(),
                taskSaveDTO.isTaskPriority(),
                taskSaveDTO.isTaskStatus(),
                taskSaveDTO.isRepeat(),
                taskSaveDTO.getParent()
        );
        taskRepo.save(task);
        return task.getTaskName();
    }

    @Override
    public List<TaskDTO> getAllTask() {
            List<Task> getTasks = taskRepo.findAll();
            List<TaskDTO> taskDTOList = new ArrayList<>();
            for (Task a:getTasks) {
                TaskDTO taskDTO = new TaskDTO(

                        a.getTaskId(),
                        a.getTaskName(),
                        a.getTaskDiscription(),
                        a.getStartDate(),
                        a.getEndDate(),
                        a.isTaskStatus(),
                        a.isTaskPriority(),
                        a.isRepeat(),
                        a.getParent()

                );
                    taskDTOList.add(taskDTO);
            }
            return taskDTOList;


    }

    @Override
    public String updateTask(TaskUpdateDTO taskUpdateDTO) {
        if (taskRepo.existsById(taskUpdateDTO.getTaskId())) {
            Task task = taskRepo.getById(taskUpdateDTO.getTaskId());
            task.setTaskName(taskUpdateDTO.getTaskName());
            task.setTaskDiscription(taskUpdateDTO.getTaskName());
            task.setStartDate(taskUpdateDTO.getStartDate());
            task.setEndDate(taskUpdateDTO.getEndDate());
            task.setTaskPriority(taskUpdateDTO.isTaskPriority());
            task.setTaskStatus(taskUpdateDTO.isTaskStatus());
            task.setRepeat(taskUpdateDTO.isRepeat());
            task.setParent(taskUpdateDTO.getParent());
            return "Success";
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteTask(int taskId) {
        if (taskRepo.existsById(taskId)) {
            taskRepo.deleteById(taskId);
        } else {
            System.out.println("Task Id does not exist");
        }

        return true;
    }

    @Override
    public List<TaskDTO> searchTasks(String taskName, LocalDate startDate, LocalDate endDate) {
        List<Task> foundTasks;

        if (taskName != null && startDate != null && endDate != null) {
            // Search by taskName, startDate, and endDate
            foundTasks = taskRepo.findByTaskNameAndStartDateAndEndDate(taskName, startDate, endDate);
        } else if (taskName != null && startDate != null) {
            // Search by taskName and startDate
            foundTasks = taskRepo.findByTaskNameAndStartDate(taskName, startDate);
        } else if (taskName != null && endDate != null) {
            // Search by taskName and endDate
            foundTasks = taskRepo.findByTaskNameAndEndDate(taskName, endDate);
        } else if (startDate != null && endDate != null) {
            // Search by startDate and endDate
            foundTasks = taskRepo.findByStartDateAndEndDate(startDate, endDate);
        } else if (taskName != null) {
            // Search by taskName
            foundTasks = taskRepo.findByTaskName(taskName);
        } else if (startDate != null) {
            // Search by startDate
            foundTasks = taskRepo.findByStartDate(startDate);
        } else if (endDate != null) {
            // Search by endDate
            foundTasks = taskRepo.findByEndDate(endDate);
        } else {
            // No search parameters provided
            return Collections.emptyList();
        }

        return convertToTaskDTOList(foundTasks);
    }

    private List<TaskDTO> convertToTaskDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO(
                    task.getTaskId(),
                    task.getTaskName(),
                    task.getTaskDiscription(),
                    task.getStartDate(),
                    task.getEndDate(),
                    task.isTaskStatus(),
                    task.isTaskPriority(),
                    task.isRepeat(),
                    task.getParent()
            );

            taskDTOList.add(taskDTO);
        }

        return taskDTOList;
    }
}



