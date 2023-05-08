package com.example.taskcrud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "subTask")
public class subTask extends Task {
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

    @ManyToOne
    @JoinColumn(name = "task_id")
    private List<subTask> subTasks;

}


