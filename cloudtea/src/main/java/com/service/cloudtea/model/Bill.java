package com.service.cloudtea.model;

import lombok.*;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;
    @ManyToOne(optional=false)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    @ToString.Exclude
    private User user;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "bill_day_set")
    private LocalDateTime daySet;
    @Column(name = "bill_total_price")
    private Double totalPrice;
}
