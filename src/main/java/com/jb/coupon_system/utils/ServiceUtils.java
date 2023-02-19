package com.jb.coupon_system.utils;

import com.jb.coupon_system.beans.Coupon;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceUtils {

    public static boolean isCouponOutOfStock(int amount) {
        return amount <= 0;
    }

    public static boolean isCouponExpired(Date endDate) {
        return endDate.before(Date.valueOf(LocalDate.now()));
    }

    public static void reduceCouponAmount(Coupon coupon) {
        coupon.setAmount(coupon.getAmount() - 1);
    }

    public static boolean isTokenExpired(LocalDateTime time) {
        return time.isBefore(LocalDateTime.now().minusMinutes(30));
    }
}
