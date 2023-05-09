package com.example.taskcrud.Impl;

import com.example.taskcrud.Repository.RecordRepo;
import com.example.taskcrud.entity.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class RecordServiceImpl {

    @Autowired
    private RecordRepo recordRepo;

    public Recording createRecording(MultipartFile file) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
        AudioFormat format = audioInputStream.getFormat();
        Long duration = (long) (audioInputStream.getFrameLength() / format.getFrameRate()*1000);
        String recordingName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Recording recording = new Recording();
        recording.setRecordingName(recordingName);
        recording.setFormat(String.valueOf(format));
        recording.setDuration(duration);
        recording.setBytes(bytes);

    }
}
