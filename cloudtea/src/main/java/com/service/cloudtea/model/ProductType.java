package com.service.cloudtea.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_product_id")
    private Long productTypeId;
    @Column(name = "type_product_name")
    private String typeProductName;
    @Column(name = "type_product_active")
    private int active;
}
