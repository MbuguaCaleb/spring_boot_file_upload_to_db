package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="FILE_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilePathEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String filePath;


}
