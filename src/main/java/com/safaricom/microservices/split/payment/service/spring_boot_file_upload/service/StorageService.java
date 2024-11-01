package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.service;

import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity.FilePathEntity;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity.ImageDataEntity;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.exception.ImageNotFoundException;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.repository.FileDataRepository;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.safaricom.microservices.split.payment.service.spring_boot_file_upload.util.ImageUtils.compressImage;
import static com.safaricom.microservices.split.payment.service.spring_boot_file_upload.util.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH = "/Users/cmkaranja/Desktop/saf_projects/MyImages/";

    public String uploadImageToDataBase(MultipartFile file) throws IOException {
        //Every Image has certain Properties.
        //These properties can be found in the MultiPart
        //It can even convert an Image into Bytes Upon receiving the Image
        ImageDataEntity imageData = storageRepository.save(ImageDataEntity
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes()))
                .build());

        return "file name is" + imageData.getName();

    }

    public byte[] downloadImageFromDataBase(String fileName) {
        Optional<ImageDataEntity> getSavedFile = storageRepository.findByName(fileName);

        if (getSavedFile.isPresent()) {
            return decompressImage(getSavedFile.get().getImageData());
        }
        throw new ImageNotFoundException("Image Not Found");
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        //upload a file to path
        file.transferTo(new File(filePath));

        //save a file path in DB
        FilePathEntity uploadedFile = fileDataRepository.save(FilePathEntity
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build());
        //we upload first, and once the Upload is successful, save the image path to DB

        return "file uploaded successfully" + uploadedFile.getFilePath();
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FilePathEntity> fileData = fileDataRepository.findByName(fileName);

        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();

            //converting the path where the image is to Bytes
            return Files.readAllBytes(Path.of(filePath));
        }
        throw new ImageNotFoundException("Image Not Found");
    }
}
