package com.example.taskcrud.Controller;


import com.example.taskcrud.Repository.RecordingRepository;
import com.example.taskcrud.entity.Recordings;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@RestController
@RequestMapping("/recording")
@Slf4j
public class RecordController {

    @Autowired
    private RecordingRepository recordingRepository;

    @GetMapping("/testData")
    public String testData(HttpServletRequest request){
        log.info("[testData] da truy cap thanh cong {}", request.getHeader("thuoctinha"));

        return "";
    }

    @PostMapping("/createRecording")
    public ResponseEntity<?> createRecording(@RequestParam("file") MultipartFile file, @Value("${fileDir}") String fileDir) {
        log.info("[createRecording] fileDir {}", fileDir);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getInputStream());
            AudioFormat format = audioInputStream.getFormat();
            long duration = (long) (audioInputStream.getFrameLength() / format.getFrameRate() * 1000);
            String name = file.getOriginalFilename();
            String filePath = fileDir + "/" + name;
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            Recordings recordings = new Recordings();
            recordings.setName(name);
            recordings.setFormat(String.valueOf(format));
            recordings.setFilePath(filePath);
            recordings.setDuration(duration);

            recordingRepository.save(recordings);

            return ResponseEntity.ok(). build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/{id}/play")
    public ResponseEntity<byte[]> playRecording(@PathVariable("id") Long id) {
        Optional<Recordings> optionalRecordings = recordingRepository.findById(id);
        if (optionalRecordings.isPresent()) {
            Recordings recordings = optionalRecordings.get();
            try {
                Path filePath = Paths.get(getClass().getResource("/recordings" + recordings.getName()).toURI());
                byte[] bytes = Files.readAllBytes(filePath);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDisposition(ContentDisposition.builder("attachment").filename(recordings.getName()).build());
                return  new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteRecording(@PathVariable("id") Long id) {
        try {
            recordingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
