package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.repository;

import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity.FilePathEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FilePathEntity,Long> {
    Optional<FilePathEntity> findByName(String fileName);

}
