//package com.example.taskcrud.registration.token;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@AllArgsConstructor
//@Service
//public class ConfirmationTokenService {
//    private  final ConfirmationTokenRepository confirmationTokenRepository;
//    public void saveConfirmationToken(ConfirmationToken token){
//        confirmationTokenRepository.save(token);
//    }
////The Optional type is used here to indicate that the token may or may not be present in the repository. If the token is found, the repository returns an Optional object
//// containing the token, and if it is not found, the repository returns an empty Optional.
////    TOdo:  using Optional instead of null check , it will make code more concise, easier to read, and less error-prone.
////    todo: One of the main problems with null checks is that they can be easily forgotten or overlooked, which can lead to null pointer exceptions at runtime. Even if null checks are present, they can clutter the code and make it harder to read and maintain.
//    public Optional<ConfirmationToken> getToken(String token){
////        If the findByToken method of the confirmationTokenRepository returns null when no token is found, then returning null from the getToken method would require the calling code to perform a null check before using the token.
////        This could result in null pointer exceptions if the calling code forgets to perform the null check.
//      return  confirmationTokenRepository.findByToken(token);
//    }
//
//    public Optional<ConfirmationToken> getAppUser(Long id)
//    {
//        return confirmationTokenRepository.findById(id);
//    }
////    public Optional<AppUser> get
//    public int setConfirmedAt(String token){
//        return confirmationTokenRepository.updateConfirmedAt(token,LocalDateTime.now());
//    }
//
//}
