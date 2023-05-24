package com.example.taskcrud.Impl;

import com.example.taskcrud.DTO.TaskDTO;
import com.example.taskcrud.DTO.TaskSaveDTO;
import com.example.taskcrud.DTO.TaskUpdateDTO;

import java.util.List;

public interface TaskService {
String addTask(TaskSaveDTO taskSaveDTO);

List<TaskDTO> getAllTask();

String updateTask(TaskUpdateDTO taskUpdateDTO);

boolean deleteTask(int taskId);
}
