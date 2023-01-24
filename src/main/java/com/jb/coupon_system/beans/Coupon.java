package com.jb.coupon_system.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jb.coupon_system.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Company company;

    @ManyToMany
    @JoinTable(name = "customers_coupons",
    joinColumns = {@JoinColumn(name = "coupon_id", referencedColumnName = "id") },
    inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")})
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customers;
}
