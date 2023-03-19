package com.service.cloudtea.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_detail_bill")
public class DetailBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_bill_id")
    private Long detailBillId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_id", insertable = false, updatable = false)
    private Bill bill;
    @Column(name = "bill_id")
    private Long billId;
    @Column(name = "detail_bill_quantity")
    private int quantity;
    @Column(name = "detail_bill_price")
    private double price;
    @Column(name = "detail_bill_total_price")
    private double totalPrice;
    @Column(name = "detail_bill_image")
    private String image;
}
