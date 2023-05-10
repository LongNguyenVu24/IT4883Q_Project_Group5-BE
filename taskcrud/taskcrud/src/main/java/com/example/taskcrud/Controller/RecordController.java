package com.example.taskcrud.Controller;


import com.example.taskcrud.Repository.RecordRepo;
import com.example.taskcrud.entity.Recording;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/record")
public class RecordController {


    @Autowired
    private RecordRepo recordRepo;
    @PostMapping
    public ResponseEntity<?>createRecording(@RequestParam("file")MultipartFile file, @Value("{fileDir}") String fileDir) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
            AudioFormat format = audioInputStream.getFormat();
            Long duration = (long) (audioInputStream.getFrameLength() / format.getFrameRate()*1000);
            String recordingName = file.getOriginalFilename();
            String filePath = fileDir +"/" + recordingName;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            Recording recording = new Recording();
            recording.setRecordingName(recordingName);
            recording.setFormat(String.valueOf(format));
            recording.setDuration(duration);

            recordRepo.save(recording);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
