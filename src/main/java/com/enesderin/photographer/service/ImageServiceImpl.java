package com.enesderin.photographer.service;

import com.enesderin.photographer.dto.request.ImageCreateRequest;
import com.enesderin.photographer.dto.response.AllImageResponse;
import com.enesderin.photographer.model.Image;
import com.enesderin.photographer.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService{

    private ImageRepository imageRepository;

    @Override
    public List<AllImageResponse> getImages() {
        List<Image> images = this.imageRepository.findAll();
        List<AllImageResponse> allImageResponses = new ArrayList<>();
        for (Image image : images) {
            AllImageResponse allImageResponse = new AllImageResponse();
            BeanUtils.copyProperties(image, allImageResponse);
            allImageResponses.add(allImageResponse);
        }
        return allImageResponses;
    }

    @Override
    public ResponseEntity<String> addImage(ImageCreateRequest imageCreateRequest) {
        Image image = new Image();
        BeanUtils.copyProperties(imageCreateRequest, image);
        this.imageRepository.save(image);
        return ResponseEntity.ok().body("Image added");
    }

    @Override
    public ResponseEntity<String> deleteImage(int id) {
        this.imageRepository.deleteById(id);
        return ResponseEntity.ok().body("Image deleted");
    }
}
