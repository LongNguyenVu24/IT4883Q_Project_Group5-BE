package com.example.taskcrud.Impl;

import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Repository.TaskRepo;
import com.example.taskcrud.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;


    public Task getTaskswithSubTasks(int taskId) {
        return taskRepo.findById(taskId);
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
                taskSaveDTO.isRepeat()
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
                        a.getTaskName(),
                        a.getTaskDiscription(),
                        a.getStartDate(),
                        a.getEndDate(),
                        a.isTaskPriority(),
                        a.isTaskStatus(),
                        a.isRepeat()
                );
                    taskDTOList.add(taskDTO);
            }
            return taskDTOList;

    }

    @Override
    public String updateTask(TaskUpdateDTO taskUpdateDTO) {
        return null;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return false;
    }
}
