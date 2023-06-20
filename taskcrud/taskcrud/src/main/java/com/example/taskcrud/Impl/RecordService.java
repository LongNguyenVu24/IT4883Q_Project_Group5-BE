package com.example.taskcrud.Impl;

import com.example.taskcrud.Repository.RecordingRepository;
import com.example.taskcrud.entity.Recordings;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;

@Service
public class RecordService {
    @Autowired
    private RecordingRepository recordingRepository;
    @Value("${fileDir}")
    private String fileDir;

    public Recordings createRecording(MultipartFile file, String fileDir) throws Exception {
        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, false);
        DataLine.Info targetDataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);

        if (!AudioSystem.isLineSupported(targetDataLineInfo)) {
            throw new LineUnavailableException("Microphone is not supported.");
        }

        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetDataLineInfo);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];

        long startTime = System.currentTimeMillis();

        System.out.println("Recording started.");

        while (System.currentTimeMillis() - startTime < 5000) { // Adjust the duration of recording as needed
            int bytesRead = targetDataLine.read(buffer, 0, buffer.length);
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        System.out.println("Recording finished.");

        targetDataLine.stop();
        targetDataLine.close();

        byte[] audioData = byteArrayOutputStream.toByteArray();





        //Create Directory
        File directory = new File(fileDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IllegalStateException("Failed to create the directory: " + fileDir);
            }
        }
        String name = file.getOriginalFilename();

        Recordings recordings = new Recordings();

        recordings.setName(name);
        recordings.setFormat(audioFormat.toString());
        recordings.setDuration(5000L);
        recordings.setBytes(audioData);
        recordings.setFilePath(fileDir + File.separator + name);



        recordingRepository.save(recordings);
        return recordings;


    }

    public Optional<Recordings> getRecordingById(String id) {
        return recordingRepository.findById(id);
    }

    public void deleteRecordingById( String id) {
        recordingRepository.deleteById(id);

    }

    public byte[] getRecordingBytes(Recordings recordings) throws  Exception {
        return  recordings.getBytes();
    }


}
