package com.safaricom.microservices.split.payment.service.spring_boot_file_upload;

import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class SpringBootFileUploadApplication {

    private final StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileUploadApplication.class, args);
    }

    //I WILL GET MY MULTIPART IMAGE FROM A PARAM CALLED IMAGE
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
        var downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }
}
