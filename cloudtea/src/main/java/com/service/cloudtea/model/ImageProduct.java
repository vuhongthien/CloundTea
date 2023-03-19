package com.service.cloudtea.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_image")
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    @Column(name = "image_path")
    private  String path;
    @Column(name = "image_name")
    private  String name;
    @Column(name = "image_active")
    private int active;
    @ManyToOne(optional=false)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    @ToString.Exclude
    private Product product;
    @Column(name = "product_id", nullable=false)
    private Long productId;
}
