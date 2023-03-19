package com.service.cloudtea.service;

import com.service.cloudtea.model.ImageProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.*;

public interface ImageService {
    Page<ImageProduct> findAllImage(Pageable pageable);
    ImageProduct create(ImageProduct image);
    ImageProduct findById(Long id);
    void delete(Long id);
}
