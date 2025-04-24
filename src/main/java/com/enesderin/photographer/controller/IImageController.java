package com.enesderin.photographer.controller;

import com.enesderin.photographer.dto.response.AllImageResponse;
import com.enesderin.photographer.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageController {
    List<AllImageResponse> getImages();
    ResponseEntity<String> createImage(String title, MultipartFile url);
    ResponseEntity<String> deleteImage(int id);
}
