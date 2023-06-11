package com.example.taskcrud.entity;

import java.time.LocalDate;
import java.util.List;

public class GanttResponse {
    private List<Task> tasks;
    private LocalDate startDate;
    private LocalDate endDate;


    public GanttResponse(List<Task> tasks, LocalDate startDate, LocalDate endDate) {
        this.tasks = tasks;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public GanttResponse() {
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
