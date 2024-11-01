package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    //When we want to store any binary formats in our DB
    @Lob
    @Column(name = "imagedata", columnDefinition = "LONGBLOB")
    private byte[] imageData;


}
