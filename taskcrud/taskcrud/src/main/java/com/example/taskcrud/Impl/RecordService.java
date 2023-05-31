package com.example.taskcrud.Impl;

import com.example.taskcrud.Repository.RecordingRepository;
import com.example.taskcrud.entity.Recordings;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;

@Service
public class RecordService {
    @Autowired
    private RecordingRepository recordingRepository;
    @Value("${fileDir}")
    private String fileDir;

    public Recordings createRecording(MultipartFile file) throws Exception {
        InputStream bufferedStream = new BufferedInputStream(file.getInputStream());
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);
        AudioFormat format = audioInputStream.getFormat();
        long duration = (long) (audioInputStream.getFrameLength() / format.getFrameRate() * 1000);
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        File directory = new File(fileDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IllegalStateException("Failed to create the directory: " + fileDir);
            }
        }
        String name = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Recordings recordings = new Recordings();
        recordings.setName(name);
        recordings.setFormat(String.valueOf(format));
        recordings.setDuration(duration);
        recordings.setBytes(bytes);
        recordings.setFilePath(fileDir + File.separator + name);

        Tika tika = new Tika();
        String fileType = tika.detect(bytes);
        if (fileType.startsWith("audio/")) {
            recordings.setFileFormat(fileType.substring("audio/".length()));
        } else {
            throw new IllegalArgumentException("Not an audio file: " + fileType);
        }


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
