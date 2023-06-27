package com.example.taskcrud.Controller;//package com.example.taskcrud.Controller;
import com.example.taskcrud.Impl.RecordService;
import com.example.taskcrud.Repository.RecordingRepository;
import com.example.taskcrud.entity.Recordings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/recordings", produces = "application/json")
@Slf4j
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordingRepository recordingRepository;

    @PostMapping("/createRecording")
    public ResponseEntity<String> createRecording(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("fileDir") String fileDir,
                                                  @RequestParam("customName") String customName) {
        try {
            Recordings createdRecording = recordService.createRecording(file, fileDir,customName);
            return ResponseEntity.ok("Recording created successfully. ID: " + createdRecording.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create recording: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/play")
    public ResponseEntity<byte[]> playRecording(@PathVariable("id") Integer id) {
        Optional<Recordings> optionalRecordings = recordService.getRecordingById(id);
        if (optionalRecordings.isPresent()) {
            Recordings recordings = optionalRecordings.get();
            try {
                byte[] bytes = recordService.getRecordingBytes(recordings);
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
    public ResponseEntity<?> deleteRecording(@PathVariable("id") Integer id) {
        try {
            recordService.deleteRecordingById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}