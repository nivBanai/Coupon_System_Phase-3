package com.jb.coupon_system.repos;

import com.jb.coupon_system.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

@Query(nativeQuery = true,
        value = "SELECT `id` FROM `coupon_system`.`companies` WHERE (`email` = ?)")
    int findIdByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT `name` FROM `coupon_system`.`companies` WHERE (`email` = ?)")
    String findNameByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT `profile_pic` FROM `coupon_system`.`companies` WHERE (`email` = ?)")
    String findProfilePicByEmail(String email);
}
