package com.jb.coupon_system.repos;

import com.jb.coupon_system.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT `id`, `name`, `profile_pic` " +
                    "FROM `coupon_system`.`companies` " +
                    "WHERE (`email` = ?)")
    Map<String, Object> findIdAndNameAndProfilePicByEmail(String email);
}
