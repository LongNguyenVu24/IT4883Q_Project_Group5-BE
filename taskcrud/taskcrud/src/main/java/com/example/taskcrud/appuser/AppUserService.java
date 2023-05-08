package com.example.taskcrud.appuser;


import com.example.taskcrud.registration.token.ConfirmationToken;
import com.example.taskcrud.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private ConfirmationTokenService confirmationTokenService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService=confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        boolean isConfirmAt = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists)
        {
//

//            todo:when the user exists  but user has not confirm, he must permit the user to confirm email again
            throw  new IllegalStateException("Email is exists ");
        }

        String encodedpw = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedpw);
        appUserRepository.save(appUser);
//        todo; send confirmation token

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;


    }

    public int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
