package com.example.taskcrud.Impl;

import com.example.taskcrud.DTO.TaskDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TaskExcelExporter {
    private List<TaskDTO> tasks;

    public TaskExcelExporter(List<TaskDTO> tasks) {
        this.tasks = tasks;

    }

    public void export(HttpServletResponse response) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");


        //Create header rows

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Task ID");
        headerRow.createCell(1).setCellValue("Task Name");
        headerRow.createCell(2).setCellValue("Task Description");
        headerRow.createCell(3).setCellValue("Start Date");
        headerRow.createCell(4).setCellValue("End Date");
        headerRow.createCell(5).setCellValue("Task Status");
        headerRow.createCell(6).setCellValue("Task Priority");
        headerRow.createCell(7).setCellValue("Repeat");

        //Add Data rows
        int rowIdx = 1;
        for (TaskDTO task : tasks) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(task.getTaskID());
            row.createCell(1).setCellValue(task.getTaskName());
            row.createCell(2).setCellValue(task.getTaskDiscription());
            row.createCell(3).setCellValue(task.getStartDate().toString());
            row.createCell(4).setCellValue(task.getEndDate().toString());
            row.createCell(5).setCellValue(task.isTaskStatus() ? "Complete" : "Incomplete");
            row.createCell(6).setCellValue(task.isTaskPriority() ? "High" : "Normal");
            row.createCell(7).setCellValue(task.isRepeat() ? "Yes" : "No");

        }

        //Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition","attachment; filename=\"tasks.xlsx\"");

        //Write to output stream
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }
}
