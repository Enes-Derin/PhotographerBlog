package com.enesderin.photographer.controller;

import com.enesderin.photographer.dto.request.BlogCreateRequest;
import com.enesderin.photographer.dto.request.BlogUpdateRequest;
import com.enesderin.photographer.dto.response.AllBlogResponse;
import com.enesderin.photographer.dto.response.OneBlogResponse;
import com.enesderin.photographer.service.IBlogService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/blog")
@AllArgsConstructor
public class BlogControllerImpl implements IBlogController {

    private IBlogService blogService;

    @Override
    @GetMapping("/")
    public List<AllBlogResponse> getBlogs() {
        return this.blogService.listBlogs();
    }

    @Override
    @GetMapping("/{id}")
    public OneBlogResponse getBlogById(@PathVariable int id) {
        return this.blogService.getBlogById(id);
    }


    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable int id, @Valid @RequestBody BlogUpdateRequest blog) {
        this.blogService.updateBlog(id, blog);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id) {
        this.blogService.deleteBlog(id);
        return ResponseEntity.ok().body("Deleted blog");
    }

    // 🎯 Yeni endpoint - Dosya yüklemeli blog oluşturma
    @PostMapping("/")
    public ResponseEntity<String> uploadBlog(@RequestParam("title") String title,
                                             @RequestParam("summary") String summary,
                                             @RequestParam("content") String content,
                                             @RequestParam("coverImage") MultipartFile coverImage) {
        try {
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + coverImage.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(coverImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/uploads/" + fileName;

            BlogCreateRequest request = new BlogCreateRequest();
            request.setTitle(title);
            request.setSummary(summary);
            request.setContent(content);
            request.setCoverImageUrl(imageUrl); // ✅ DTO'da bu alan tanımlı olmalı

            blogService.saveBlog(request);

            return ResponseEntity.ok("Blog başarıyla oluşturuldu.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Yükleme hatası: " + e.getMessage());
        }
    }
}
