package com.enesderin.photographer.service;

import com.enesderin.photographer.dto.request.BlogCreateRequest;
import com.enesderin.photographer.dto.request.BlogUpdateRequest;
import com.enesderin.photographer.dto.response.AllBlogResponse;
import com.enesderin.photographer.dto.response.OneBlogResponse;
import com.enesderin.photographer.model.Blog;
import com.enesderin.photographer.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements IBlogService{

    private BlogRepository blogRepository;

    @Override
    public List<AllBlogResponse> listBlogs() {
        List<Blog> blogs = this.blogRepository.findAll();
        List<AllBlogResponse> allBlogResponses = new ArrayList<>();
        for (Blog blog : blogs) {
            AllBlogResponse allBlogResponse = new AllBlogResponse();
            BeanUtils.copyProperties(blog, allBlogResponse);
            allBlogResponses.add(allBlogResponse);
        }
        return allBlogResponses;
    }

    @Override
    public OneBlogResponse getBlogById(int id) {
        Optional<Blog> blog = this.blogRepository.findById(id);
        if (blog.isPresent()) {
            OneBlogResponse oneBlogResponse = new OneBlogResponse();
            BeanUtils.copyProperties(blog.get(), oneBlogResponse);
            return oneBlogResponse;
        }
        return null;
    }

    @Override
    public void saveBlog(BlogCreateRequest blogCreateRequest) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogCreateRequest, blog);
        this.blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(int id) {
        this.blogRepository.deleteById(id);
    }

    @Override
    public void updateBlog(int id, BlogUpdateRequest blog) {
        Optional<Blog> optional= this.blogRepository.findById(id);
        if (optional.isPresent()) {
            Blog blog1 = optional.get();
            BeanUtils.copyProperties(blog, blog1);
            blogRepository.save(blog1);
        }
    }
}
