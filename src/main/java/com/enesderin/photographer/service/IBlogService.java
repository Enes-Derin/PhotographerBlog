package com.enesderin.photographer.service;

import com.enesderin.photographer.dto.request.BlogCreateRequest;
import com.enesderin.photographer.dto.request.BlogUpdateRequest;
import com.enesderin.photographer.dto.response.AllBlogResponse;
import com.enesderin.photographer.dto.response.OneBlogResponse;


import java.util.List;

public interface IBlogService {
    List<AllBlogResponse> listBlogs();
    OneBlogResponse getBlogById(int id);
    void saveBlog(BlogCreateRequest blogCreateRequest);
    void deleteBlog(int id);
    void updateBlog(int id, BlogUpdateRequest blogUpdateRequest);

}
