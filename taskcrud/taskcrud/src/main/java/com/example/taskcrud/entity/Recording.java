package com.example.taskcrud.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "recordings")
public class Recording  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id", length = 50)
    private int recordingId;

    @Column(name = "record_name", length = 255)
    private String recordingName;

    @Column(name = "format", length = 255)
    private String format;

    @Column(name = "filepath", length = 255)
    private String filePath;

    @Column(name = "duration", length = 255)
    private Long duration;

    @Column(name = "bytes", length = 255)
    private byte[] bytes;

    public int getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(int recordingId) {
        this.recordingId = recordingId;
    }

    public String getRecordingName() {
        return recordingName;
    }

    public void setRecordingName(String recordingName) {
        this.recordingName = recordingName;
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
