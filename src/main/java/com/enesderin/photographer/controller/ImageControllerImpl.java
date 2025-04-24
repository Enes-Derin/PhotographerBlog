package com.enesderin.photographer.controller;

import com.enesderin.photographer.dto.request.ImageCreateRequest;
import com.enesderin.photographer.dto.response.AllImageResponse;
import com.enesderin.photographer.service.IImageService;
import com.enesderin.photographer.service.ImageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageControllerImpl implements IImageController{

    private IImageService imageService;

    @Override
    @GetMapping()
    public List<AllImageResponse> getImages() {
        return this.imageService.getImages();
    }

    @Override
    @PostMapping()
    public ResponseEntity<String> createImage(@RequestParam("title") String title, @RequestParam("url") MultipartFile  url) {
        try{
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + url.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(url.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "upload/"+fileName;

            ImageCreateRequest imageCreateRequest = new ImageCreateRequest();
            imageCreateRequest.setTitle(title);
            imageCreateRequest.setUrl(imageUrl);

            this.imageService.addImage(imageCreateRequest);

            return ResponseEntity.ok().body("Image created");
        }catch (IOException e){
            return ResponseEntity.internalServerError().body("Failed to create image");
        }
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable int id) {
        this.imageService.deleteImage(id);
        return ResponseEntity.ok().body("Image deleted");
    }
}
