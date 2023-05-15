package com.example.taskcrud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "recordings")
@NoArgsConstructor
@AllArgsConstructor
public class Recordings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id", length = 255)
    private long id;
    @Column(name = "record_name", length = 255)
    private String name;
    @Column(name = "record_format", length = 255)
    private String format;
    @Column(name = "record_filepath", length = 255)
    private String filePath;
    @Column(name = "record_duration", length = 255)
    private Long duration;
    @Column(name = "record_byte", length = 255)
    private byte[] bytes;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}