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

import java.util.List;

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
    @PostMapping("/")
    public ResponseEntity<String> addBlog(@Valid @RequestBody BlogCreateRequest blog) {
        this.blogService.saveBlog(blog);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable int id,@Valid @RequestBody BlogUpdateRequest blog) {
        this.blogService.updateBlog(id, blog);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id) {
        this.blogService.deleteBlog(id);
        return ResponseEntity.ok().body("Deleted blog");
    }
}
