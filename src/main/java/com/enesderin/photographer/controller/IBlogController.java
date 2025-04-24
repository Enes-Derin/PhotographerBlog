package com.enesderin.photographer.controller;


import com.enesderin.photographer.dto.request.BlogCreateRequest;
import com.enesderin.photographer.dto.request.BlogUpdateRequest;
import com.enesderin.photographer.dto.response.AllBlogResponse;
import com.enesderin.photographer.dto.response.OneBlogResponse;
import com.enesderin.photographer.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogController {

    List<AllBlogResponse> getBlogs();
    OneBlogResponse getBlogById(int id);
    public ResponseEntity<String> uploadBlog(String title, String summary, String content, MultipartFile coverImage);
    ResponseEntity<String> updateBlog(int id, BlogUpdateRequest blogUpdateRequest);
    ResponseEntity<String> deleteBlog(int id);

}
