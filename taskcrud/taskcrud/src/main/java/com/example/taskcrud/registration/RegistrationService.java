package com.example.taskcrud.registration;


import com.example.taskcrud.appuser.AppUSerRole;
import com.example.taskcrud.appuser.AppUser;
import com.example.taskcrud.appuser.AppUserService;
import com.example.taskcrud.registration.token.ConfirmationToken;
import com.example.taskcrud.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not vaild");
        }

        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUSerRole.USER
        ));
    }
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(
                () -> new IllegalStateException("Token is not dounded")
        );
        if(confirmationToken.getConfirmAt()!=null)
        {
            throw  new IllegalStateException("already confirm");
        }
        LocalDateTime expired = confirmationToken.getExpiredAt();
        if(expired.isBefore(LocalDateTime.now()))
        {
            throw new IllegalStateException("the token is expired");
        }
        confirmationTokenService.setConfirmedAt(token);
       appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Cònirmed";
    }
}
