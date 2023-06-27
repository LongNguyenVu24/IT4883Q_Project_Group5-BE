package com.example.taskcrud.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDiscription;

    @Column(name = "start_date")

    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status_task")
    private boolean taskStatus;

    @Column(name = "priority_task")
    private boolean taskPriority;

    @Column(name = "repeat_task")
    private boolean repeat;

    @OneToMany(mappedBy = "task")
    private List<subTask> subTasks;




    public Task(int taskId, String taskName, String taskDiscription, LocalDate startDate, LocalDate endDate, boolean taskStatus, boolean taskPriority, boolean repeat) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDiscription = taskDiscription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.repeat = repeat;
    }

    public Task(String taskName, String taskDiscription, LocalDate startDate, LocalDate endDate, boolean taskPriority, boolean taskStatus, boolean repeat) {
        this.taskName = taskName;
        this.taskDiscription = taskDiscription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.repeat = repeat;
    }

    public Task() {

    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDiscription() {
        return taskDiscription;
    }

    public void setTaskDiscription(String taskDiscription) {
        this.taskDiscription = taskDiscription;
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

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(boolean taskPriority) {
        this.taskPriority = taskPriority;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
    @Override
    public String toString(){
        return "Task{" +
                "   task_id='" + taskId + '\'' +
                "   task_name='" + taskName + '\'' +
                ", task_description='" + taskDiscription + '\'' +
                ", start_date=" + startDate + '\'' +
                ", end_date=" + endDate + '\'' +
                ", status_task=" + taskStatus + '\'' +
                ", priority_task=" + taskPriority + '\'' +
                ", repeat=" + repeat + '\'' +
                '}';
    }
}
