package com.jb.coupon_system.repos;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByCompanyIdAndTitle(int companyId, String title);

    List<Coupon> findByCompanyId(int companyId);

    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

    @Query(nativeQuery = true,
            value = "SELECT CASE WHEN EXISTS " +
                    "(SELECT * FROM `coupon_system`.`customers_coupons` " +
                    "WHERE (`customer_id` = ?) AND (`coupon_id` = ?)) " +
                    "THEN 'true' else 'false' END")
    boolean isPurchaseExists(int customerId, int couponId);

    @Query(nativeQuery = true,
            value = "SELECT c.* " +
                    "FROM `coupon_system`.`coupons` c " +
                    "JOIN `coupon_system`.`customers_coupons` ctc " +
                    "ON c.`id` = ctc.`coupon_id` " +
                    "WHERE (`customer_id` = ?)")
    List<Coupon> findByCustomerId(int companyId);

    @Query(nativeQuery = true,
            value = "SELECT c.* " +
                    "FROM `coupon_system`.`coupons` c " +
                    "JOIN `coupon_system`.`customers_coupons` ctc " +
                    "ON c.`id` = ctc.`coupon_id` " +
                    "WHERE (`customer_id` = ?) AND (`category` = ?)")
    List<Coupon> findByCustomerIdAndCategory(int companyId, String category);

    @Query(nativeQuery = true,
            value = "SELECT c.* " +
                    "FROM `coupon_system`.`coupons` c " +
                    "JOIN `coupon_system`.`customers_coupons` ctc " +
                    "ON c.`id` = ctc.`coupon_id` " +
                    "WHERE (`customer_id` = ?) AND (`price` < ?)")
    List<Coupon> findByCustomerIdAndPrice(int companyId, double maxPrice);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM `coupon_system`.`coupons` " +
                    "WHERE (`end_date` < CURRENT_DATE)")
    void deleteExpiredCoupons();

    @Query(nativeQuery = true,
            value = "SELECT CASE WHEN EXISTS " +
                    "(SELECT * FROM `coupon_system`.`coupons`" +
                    "WHERE (`company_id` = ?) AND (`title` = ?) AND (`id` != ?)) " +
                    "THEN 'true' else 'false' END")
    boolean existsByCompanyIdAndTitleAndId(int companyId, String title, int couponId);

    @Query(nativeQuery = true,
            value = "SELECT c.* FROM `coupon_system`.`coupons` c " +
                    "WHERE c.amount > 0 " +
                    "ORDER BY amount ASC " +
                    "LIMIT 3")
    List<Coupon> getThreeWithLowestAmount();
}
