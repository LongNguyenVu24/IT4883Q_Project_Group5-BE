package com.example.taskcrud.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class demoController {
    @PostMapping("demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("Done");

    }
}
