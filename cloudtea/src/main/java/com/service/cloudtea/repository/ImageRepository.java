package com.service.cloudtea.repository;

import com.service.cloudtea.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface ImageRepository extends JpaRepository<ImageProduct,Long> {
}
