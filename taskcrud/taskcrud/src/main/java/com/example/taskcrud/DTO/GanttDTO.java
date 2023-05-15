package com.example.taskcrud.DTO;

import java.time.LocalDate;

public class GanttDTO {
    private int ganttId;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;

    public GanttDTO(int ganttId, String taskName, LocalDate startDate, LocalDate endDate) {
        this.ganttId = ganttId;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
