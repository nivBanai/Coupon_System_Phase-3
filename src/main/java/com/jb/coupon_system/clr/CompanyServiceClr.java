package com.jb.coupon_system.clr;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.login.LoginService;
import com.jb.coupon_system.repos.CouponRepository;
import com.jb.coupon_system.services.company_service.CompanyService;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(3)
public class CompanyServiceClr implements CommandLineRunner {

    @Autowired
    private LoginService loginManager;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        //Coupons
        Coupon coup1 = Coupon.builder()
                .category(Category.FOOD)
                .title("Coupon 1")
                .description("Description 1")
                .startDate(Date.valueOf("2022-08-12"))
                .endDate(Date.valueOf("2022-11-20"))//*EXPIRED*//
                .amount(2)
                .price(12.5)
                .image("www.coupon1.com")
                .build();
        Coupon coup2 = Coupon.builder()
                .category(Category.FOOD)
                .title("Coupon 2")
                .description("Description 2")
                .startDate(Date.valueOf("2022-09-01"))
                .endDate(Date.valueOf("2023-03-22"))
                .amount(7)
                .price(15.3)
                .image("www.coupon2.com")
                .build();
        Coupon coup3 = Coupon.builder()
                .category(Category.FOOD)
                .title("Coupon 3")
                .description("Description 3")
                .startDate(Date.valueOf("2022-10-08"))
                .endDate(Date.valueOf("2023-04-09"))
                .amount(12)
                .price(18.7)
                .image("www.coupon3.com")
                .build();
        Coupon coup4 = Coupon.builder()
                .category(Category.TRADING_CARDS)
                .title("Coupon 4")
                .description("Description 4")
                .startDate(Date.valueOf("2022-10-07"))
                .endDate(Date.valueOf("2023-01-29"))
                .amount(8)
                .price(14.4)
                .image("www.coupon4.com")
                .build();
        Coupon coup5 = Coupon.builder()
                .category(Category.VIDEO_GAMES)
                .title("Coupon 5")
                .description("Description 5")
                .startDate(Date.valueOf("2022-11-11"))
                .endDate(Date.valueOf("2023-05-19"))
                .amount(18)
                .price(10.1)
                .image("www.coupon5.com")
                .build();
        Coupon coup6 = Coupon.builder()
                .category(Category.CINEMA)
                .title("Coupon 6")
                .description("Description 6")
                .startDate(Date.valueOf("2022-11-01"))
                .endDate(Date.valueOf("2022-10-30"))//*EXPIRED*//
                .amount(1)
                .price(11.2)
                .image("www.coupon6.com")
                .build();
        Coupon coup7 = Coupon.builder()
                .category(Category.CINEMA)
                .title("Coupon 7")
                .description("Description 7")
                .startDate(Date.valueOf("2022-11-25"))
                .endDate(Date.valueOf("2023-04-12"))
                .amount(16)
                .price(19.5)
                .image("www.coupon7.com")
                .build();
        Coupon coup8 = Coupon.builder()
                .category(Category.FOOD)
                .title("Coupon 8")
                .description("Description 8")
                .startDate(Date.valueOf("2022-10-04"))
                .endDate(Date.valueOf("2023-03-15"))
                .amount(3)
                .price(13.7)
                .image("www.coupon8.com")
                .build();
        Coupon coup9 = Coupon.builder()
                .category(Category.FOOD)
                .title("Coupon 9")
                .description("Description 9")
                .startDate(Date.valueOf("2022-10-04"))
                .endDate(Date.valueOf("2023-05-16"))
                .amount(2)
                .price(12.5)
                .image("www.coupon9.com")
                .build();
        Coupon coup10 = Coupon.builder()
                .category(Category.CINEMA)
                .title("Coupon 10")
                .description("Description 10")
                .startDate(Date.valueOf("2022-09-27"))
                .endDate(Date.valueOf("2023-03-06"))
                .amount(6)
                .price(18.3)
                .image("www.coupon10.com")
                .build();

        PrintUtils.printTitle("COMPANY SERVICE TESTING");

        PrintUtils.printExceptionTitle("Company login exception");
        try {
//            loginManager.login("not-a-company@gmail.com", "12345", ClientType.COMPANY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.printLogin("Company Login");
//        loginManager.login("nintendo@gmail.com", "12345", ClientType.COMPANY);

        //add coupon
        companyService.addCoupon(1, coup1);
        companyService.addCoupon(1, coup2);
        companyService.addCoupon(1, coup3);
        companyService.addCoupon(2, coup4);
        companyService.addCoupon(3, coup5);
        companyService.addCoupon(4, coup6);
        companyService.addCoupon(4, coup7);
        companyService.addCoupon(5, coup8);
        companyService.addCoupon(5, coup9);
        companyService.addCoupon(5, coup10);

        PrintUtils.printExceptionTitle("Add coupon exception");
        try {
            companyService.addCoupon(5, Coupon.builder()
                    .title("Coupon 8")
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        PrintUtils.print("Company coupons");
        companyService.getCompanyCoupons(5).forEach(System.out::println);

        //update coupon
        Coupon couponToUpdate = couponRepository.findById(8)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        couponToUpdate.setPrice(200);
        companyService.updateCoupon(5, 8, couponToUpdate);
        PrintUtils.print("Company coupons after update");
        companyService.getCompanyCoupons(5).forEach(System.out::println);

        PrintUtils.printExceptionTitle("Update coupon exception (company id exception)");
        try {
            companyService.updateCoupon(1, 8, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Update coupon exception (coupon id exception)");
        try {
            couponToUpdate.setId(55);
            companyService.updateCoupon(5, 8, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.print("Company coupons by category");
        companyService.getCompanyCouponsByCategory(5, Category.FOOD).forEach(System.out::println);
        PrintUtils.print("Company coupons by price");
        companyService.getCompanyCouponsByPrice(5, 20).forEach(System.out::println);
        PrintUtils.print("Company details");
        System.out.println(companyService.getCompanyDetails(5));
    }
}
