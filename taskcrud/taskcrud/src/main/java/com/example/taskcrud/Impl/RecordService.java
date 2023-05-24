package com.example.taskcrud.Impl;

import com.example.taskcrud.Repository.RecordingRepository;
import com.example.taskcrud.entity.Recordings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.util.Optional;

public class RecordService {

    @Autowired
    private RecordingRepository recordingRepository;

    public Recordings createRecording(MultipartFile file) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
        AudioFormat format = audioInputStream.getFormat();
        long duration = (long) (audioInputStream.getFrameLength() / format.getFrameRate() * 1000);
        String name = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Recordings recordings = new Recordings();
        recordings.setName(name);
        recordings.setFormat(String.valueOf(format));
        recordings.setDuration(duration);
        recordings.setBytes(bytes);
        recordingRepository.save(recordings);
        return  recordings;

    }

    public Optional<Recordings> getRecordingById(Long id) {
        return recordingRepository.findById(id);
    }

    public void deleteRecordingById( Long id) {
        recordingRepository.deleteById(id);

    }

    public byte[] getRecordingBytes(Recordings recordings) throws  Exception {
        return  recordings.getBytes();
    }



}
