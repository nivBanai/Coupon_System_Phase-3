package com.jb.coupon_system.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "customers_coupons",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id") },
            inverseJoinColumns = {@JoinColumn(name = "coupon_id", referencedColumnName = "id")})
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;
}
