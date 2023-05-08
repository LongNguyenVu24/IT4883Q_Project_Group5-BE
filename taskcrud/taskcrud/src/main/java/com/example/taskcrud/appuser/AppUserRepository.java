package com.example.taskcrud.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
//đảm bảo rằng trong quá trình thực thi phương thức, không có thay đổi nào được thực hiện trên cơ sở dữ liệu.
// Nếu bất kỳ thay đổi nào được thực hiện, Spring sẽ ném ra một ngoại lệ.
@Transactional(readOnly = true)
public interface AppUserRepository  extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

}

