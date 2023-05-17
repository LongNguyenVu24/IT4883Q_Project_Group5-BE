package com.example.taskcrud.DTO;


import java.time.LocalDate;


public class TaskUpdateDTO {

    private int taskId;
    private String taskName;

    private String taskDiscription;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean taskStatus;

    private boolean taskPriority;

    private boolean repeat;


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
        return "TaskUpdateDTO{" +
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
