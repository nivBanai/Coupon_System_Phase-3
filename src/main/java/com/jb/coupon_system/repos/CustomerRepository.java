package com.jb.coupon_system.repos;

import com.jb.coupon_system.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO `coupon_system`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?)")
    void purchaseCoupon(int customerId, int couponId);

    @Query(nativeQuery = true,
            value = "Select id from customers where email =? and password=?")
    int findIdByEmailAndPassword(String email, String password);
}
