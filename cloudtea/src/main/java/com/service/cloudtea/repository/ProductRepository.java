package com.service.cloudtea.repository;

import com.service.cloudtea.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
