package com.enesderin.photographer.repository;

import com.enesderin.photographer.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
