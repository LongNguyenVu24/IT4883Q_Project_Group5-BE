package com.example.taskcrud.Repository;

import com.example.taskcrud.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    Task findById(int taskId);

    List<Task> findByTaskNameAndStartDateAndEndDate(String taskName, LocalDate startDate, LocalDate endDate);
    List<Task> findByTaskNameAndStartDate(String taskName, LocalDate startDate);
    List<Task> findByTaskNameAndEndDate(String taskName, LocalDate endDate);
    List<Task> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
    List<Task> findByTaskName(String taskName);
    List<Task> findByStartDate(LocalDate startDate);
    List<Task> findByEndDate(LocalDate endDate);
}
