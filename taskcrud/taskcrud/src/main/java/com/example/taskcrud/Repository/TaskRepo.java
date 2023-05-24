package com.example.taskcrud.Repository;

import com.example.taskcrud.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    Task findById(int taskId);
}
