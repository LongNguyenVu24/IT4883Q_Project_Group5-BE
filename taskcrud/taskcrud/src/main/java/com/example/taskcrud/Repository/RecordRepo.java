package com.example.taskcrud.Repository;

import jdk.jfr.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepo extends JpaRepository<Recording,Integer> {
}
