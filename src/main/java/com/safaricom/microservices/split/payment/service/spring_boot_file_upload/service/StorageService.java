package com.safaricom.microservices.split.payment.service.spring_boot_file_upload.service;

import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.entity.ImageDataEntity;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.exception.ImageNotFoundException;
import com.safaricom.microservices.split.payment.service.spring_boot_file_upload.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.safaricom.microservices.split.payment.service.spring_boot_file_upload.util.ImageUtils.compressImage;
import static com.safaricom.microservices.split.payment.service.spring_boot_file_upload.util.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        //Every Image has certain Properties.
        //These properties can be found in the MultiPart
        //It can even convert an Image into Bytes Upon receiving the Image
        ImageDataEntity imageData = storageRepository.save(ImageDataEntity
                .builder()
                .name(file.getName())
                .type(file.getContentType())
                .imageData(compressImage(file.getBytes()))
                .build());

        return "file name is" + imageData.getName();

    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageDataEntity> getSavedFile = storageRepository.findByName(fileName);

        if (getSavedFile.isPresent()) {
            return decompressImage(getSavedFile.get().getImageData());
        }
        throw new ImageNotFoundException("Image Not Found");
    }
}
