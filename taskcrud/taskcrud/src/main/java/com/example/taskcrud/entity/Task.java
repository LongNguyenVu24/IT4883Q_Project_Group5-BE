package com.example.taskcrud.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;

    @Column(name = "task_name", length = 255)
    private String taskName;

    @Column(name = "task_description", length = 255)
    private String taskDiscription;

    @Column(name = "start_date", length = 255)
    private Date startDate;

    @Column(name = "end_date", length = 255)
    private Date endDate;

    @Column(name = "status_task")
    private boolean taskStatus;

    @Column(name = "priority_task")
    private boolean taskPriority;

    @Column(name = "repeat")
    private boolean repeat;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "task_dependency",
                joinColumns = @JoinColumn(name = "task_id"),
                    inverseJoinColumns = @JoinColumn(name = "dependency_id"))
            private Set<Task> dependencies;
    @ManyToMany(fetch =  FetchType.LAZY, mappedBy = "dependencies")
    private Set<Task> dependentTasks;



    public Task(String taskName, String taskDiscription, Date startDate, Date endDate, boolean taskStatus, boolean taskPriority, boolean repeat) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDiscription = taskDiscription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.repeat = repeat;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
