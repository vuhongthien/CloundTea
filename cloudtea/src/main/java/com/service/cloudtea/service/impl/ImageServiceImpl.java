package com.service.cloudtea.service.impl;

import com.service.cloudtea.model.ImageProduct;
import com.service.cloudtea.repository.ImageRepository;
import com.service.cloudtea.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Page<ImageProduct> findAllImage(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public ImageProduct create(ImageProduct image) {
        return imageRepository.save(image);
    }

    @Override
    public ImageProduct findById(Long id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}
