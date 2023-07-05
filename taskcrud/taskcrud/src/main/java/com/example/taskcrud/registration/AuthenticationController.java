package com.example.taskcrud.registration;

import com.example.taskcrud.Repository.AppUserRepository;
import com.example.taskcrud.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PutMapping("/updatePassword")
    public ResponseEntity<String> changeUserPassword(  @RequestParam String email, @RequestParam String newPassword, String newFullName) {

      return  ResponseEntity.ok(authenticationService.changeUserPassword(email, newPassword,newFullName));
    }
}
