package com.example.taskcrud.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        var authenticationResponse = authenticationService.register(request);

        return ResponseEntity.ok(authenticationResponse);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest  request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/demo")
    public ResponseEntity<String> authenticate(){
        return ResponseEntity.ok("Sucess");
    }

}
