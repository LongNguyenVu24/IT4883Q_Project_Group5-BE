package com.example.taskcrud.Repository;

import com.example.taskcrud.entity.Recordings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RecordingRepository extends JpaRepository<Recordings, Long> {

}
