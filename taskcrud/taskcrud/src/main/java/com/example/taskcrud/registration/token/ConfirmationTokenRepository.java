package com.example.taskcrud.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
    //   ?1", "?2" đại diện cho tham số thứ 1 ,2 được truyền vào phương thức thực thi câu truy vấn.
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token , LocalDateTime confirmedAt) ;
//    int updateByTokenAndConfirmAt(String token,LocalDateTime confirmedAt);


}
