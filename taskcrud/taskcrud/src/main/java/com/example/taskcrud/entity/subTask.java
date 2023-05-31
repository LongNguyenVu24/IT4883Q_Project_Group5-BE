package com.example.taskcrud.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name= "subTask")
public class subTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_Id", length  = 50)
    private int subtaskId;
    @Column(name = "subTask_name", length = 255)
    private String subTaskName;

    @Column(name = "step", length = 255)
    private String step;

    @Column(name = "status")
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subTask")
    private List<Task> tasks = new ArrayList<>();

    public subTask(int subtaskId, String subTaskName, String step, boolean status) {
        this.subtaskId = subtaskId;
        this.subTaskName = subTaskName;
        this.step = step;
        this.status = status;
    }

    public subTask() {
    }

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


