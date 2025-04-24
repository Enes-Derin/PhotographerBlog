package com.enesderin.photographer.service;

import com.enesderin.photographer.dto.request.ImageCreateRequest;
import com.enesderin.photographer.dto.response.AllImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    List<AllImageResponse> getImages();
    ResponseEntity<String> addImage(ImageCreateRequest imageCreateRequest);
    ResponseEntity<String> deleteImage(int id);
}
