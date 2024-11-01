package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.repository;

import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity.ImageDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository  extends JpaRepository<ImageDataEntity,Long> {

   Optional<ImageDataEntity> findByName(String fileName);
}
