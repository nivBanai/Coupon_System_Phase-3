package com.jb.coupon_system.clr;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.login.LoginService;
import com.jb.coupon_system.repos.CouponRepository;
import com.jb.coupon_system.services.customer_service.CustomerService;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CustomerServiceClr implements CommandLineRunner {

    @Autowired
    private LoginService loginManager;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        PrintUtils.printTitle("CUSTOMER SERVICE TESTING");

        PrintUtils.printExceptionTitle("Customer login exception");
        try {
//            loginManager.login("not-a-customer@gmail.com", "12345", ClientType.CUSTOMER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.printLogin("Customer Login");
//        loginManager.login("pooki@gmail.com", "12345", ClientType.CUSTOMER);

        //purchase coupon
//        Coupon couponToPurchase1 = couponRepository.findById(1)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase2 = couponRepository.findById(2)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase3 = couponRepository.findById(3)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase4 = couponRepository.findById(4)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase5 = couponRepository.findById(5)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase6 = couponRepository.findById(6)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase7 = couponRepository.findById(7)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase8 = couponRepository.findById(8)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase9 = couponRepository.findById(9)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        Coupon couponToPurchase10 = couponRepository.findById(10)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));

        customerService.purchaseCoupon(1, couponToPurchase2.getId());
        customerService.purchaseCoupon(1, couponToPurchase5.getId());
        customerService.purchaseCoupon(1, couponToPurchase9.getId());
        customerService.purchaseCoupon(2, couponToPurchase2.getId());
        customerService.purchaseCoupon(2, couponToPurchase10.getId());
        customerService.purchaseCoupon(3, couponToPurchase5.getId());
        customerService.purchaseCoupon(3, couponToPurchase10.getId());
        customerService.purchaseCoupon(4, couponToPurchase4.getId());
        customerService.purchaseCoupon(4, couponToPurchase9.getId());
        customerService.purchaseCoupon(4, couponToPurchase3.getId());
        customerService.purchaseCoupon(4, couponToPurchase5.getId());
        customerService.purchaseCoupon(4, couponToPurchase8.getId());
        customerService.purchaseCoupon(5, couponToPurchase7.getId());
        customerService.purchaseCoupon(5, couponToPurchase2.getId());
        customerService.purchaseCoupon(5, couponToPurchase10.getId());

        PrintUtils.printExceptionTitle("Purchase coupon exception (multiple purchase exception)");
        try {
            customerService.purchaseCoupon(3, couponToPurchase5.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Purchase coupon exception (out of stock exception)");
        try {
            customerService.purchaseCoupon(3, couponToPurchase9.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Purchase coupon exception (expired exception)");
        try {
            customerService.purchaseCoupon(3, couponToPurchase6.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.print("Customer coupons");
        customerService.getCustomerCoupons(4).forEach(System.out::println);
        PrintUtils.print("Customer coupons by category");
        customerService.getCustomerCouponsByCategory(4, Category.FOOD).forEach(System.out::println);
        PrintUtils.print("Customer coupons by price");
        customerService.getCustomerCouponsByPrice(4, 14).forEach(System.out::println);
        PrintUtils.print("Customer details");
        System.out.println(customerService.getCustomerDetails(4));
    }
}
