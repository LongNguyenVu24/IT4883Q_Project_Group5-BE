package com.example.taskcrud.Impl;

import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;
import com.example.taskcrud.Repository.TaskRepo;
import com.example.taskcrud.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

                        a.getTaskId(),
                        a.getTaskName(),
                        a.getTaskDiscription(),
                        a.getStartDate(),
                        a.getEndDate(),
                        a.isTaskStatus(),
                        a.isTaskPriority(),
                        a.isRepeat()

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
            } else {
                    System.out.println("Task Id does not exist");
            }

        return null;
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


}
