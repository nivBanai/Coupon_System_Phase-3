//package com.jb.coupon_system.clr.off;
//
//import com.jb.coupon_system.beans.Company;
//import com.jb.coupon_system.beans.Coupon;
//import com.jb.coupon_system.beans.Customer;
//import com.jb.coupon_system.enums.Category;
//import com.jb.coupon_system.enums.ErrorMsg;
//import com.jb.coupon_system.exceptions.CouponSystemException;
//import com.jb.coupon_system.login.LoginService;
//import com.jb.coupon_system.repos.CouponRepository;
//import com.jb.coupon_system.services.admin_service.AdminService;
//import com.jb.coupon_system.services.company_service.CompanyService;
//import com.jb.coupon_system.services.customer_service.CustomerService;
//import com.jb.coupon_system.utils.PrintUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//
//@Component
//@Order()
//public class Test implements CommandLineRunner {
//
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private CouponRepository couponRepository;
//    @Autowired
//    LoginService loginManager;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        //Companies
//        Company comp1 = Company.builder()
//                .name("Pepsi")
//                .email("pepsi@yahoo.com")
//                .password("12345")
//                .build();
//        Company comp2 = Company.builder()
//                .name("Konami")
//                .email("konami@gmail.com")
//                .password("12345")
//                .build();
//        Company comp3 = Company.builder()
//                .name("Nintendo")
//                .email("nintendo@gmail.com")
//                .password("12345")
//                .build();
//        Company comp4 = Company.builder()
//                .name("Yes Planet")
//                .email("yes-palnet@walla.com")
//                .password("12345")
//                .build();
//        Company comp5 = Company.builder()
//                .name("McDonald's")
//                .email("mcdonalds@gmail.com")
//                .password("12345")
//                .build();
//
//        //Customers
//        Customer cus1 = Customer.builder()
//                .firstName("Niv")
//                .lastName("Banai")
//                .email("niv@gmail.com")
//                .password("12345")
//                .build();
//        Customer cus2 = Customer.builder()
//                .firstName("Rotem")
//                .lastName("Kabir")
//                .email("rotem@gmail.com")
//                .password("12345")
//                .build();
//        Customer cus3 = Customer.builder()
//                .firstName("Euclides")
//                .lastName("Fibonacci")
//                .email("euclides@gmail.com")
//                .password("12345")
//                .build();
//        Customer cus4 = Customer.builder()
//                .firstName("Pooki")
//                .lastName("S")
//                .email("pooki@gmail.com")
//                .password("12345")
//                .build();
//        Customer cus5 = Customer.builder()
//                .firstName("Yoshi")
//                .lastName("Munchakoopas")
//                .email("yoshi@gmail.com")
//                .password("12345")
//                .build();
//
//        //Coupons
//        Coupon coup1 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 1")
//                .description("Description 1")
//                .startDate(Date.valueOf("2022-08-12"))
//                .endDate(Date.valueOf("2022-11-20"))//*EXPIRED*//
//                .amount(2)
//                .price(12.5)
//                .image("www.coupon1.com")
//                .build();
//        Coupon coup2 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 2")
//                .description("Description 2")
//                .startDate(Date.valueOf("2022-09-01"))
//                .endDate(Date.valueOf("2023-03-22"))
//                .amount(7)
//                .price(15.3)
//                .image("www.coupon2.com")
//                .build();
//        Coupon coup3 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 3")
//                .description("Description 3")
//                .startDate(Date.valueOf("2022-10-08"))
//                .endDate(Date.valueOf("2023-04-09"))
//                .amount(12)
//                .price(18.7)
//                .image("www.coupon3.com")
//                .build();
//        Coupon coup4 = Coupon.builder()
//                .category(Category.TRADING_CARDS)
//                .title("Coupon 4")
//                .description("Description 4")
//                .startDate(Date.valueOf("2022-10-07"))
//                .endDate(Date.valueOf("2023-01-29"))
//                .amount(8)
//                .price(14.4)
//                .image("www.coupon4.com")
//                .build();
//        Coupon coup5 = Coupon.builder()
//                .category(Category.VIDEO_GAMES)
//                .title("Coupon 5")
//                .description("Description 5")
//                .startDate(Date.valueOf("2022-11-11"))
//                .endDate(Date.valueOf("2023-05-19"))
//                .amount(18)
//                .price(10.1)
//                .image("www.coupon5.com")
//                .build();
//        Coupon coup6 = Coupon.builder()
//                .category(Category.CINEMA)
//                .title("Coupon 6")
//                .description("Description 6")
//                .startDate(Date.valueOf("2022-11-01"))
//                .endDate(Date.valueOf("2022-10-30"))//*EXPIRED*//
//                .amount(1)
//                .price(11.2)
//                .image("www.coupon6.com")
//                .build();
//        Coupon coup7 = Coupon.builder()
//                .category(Category.CINEMA)
//                .title("Coupon 7")
//                .description("Description 7")
//                .startDate(Date.valueOf("2022-11-25"))
//                .endDate(Date.valueOf("2023-04-12"))
//                .amount(16)
//                .price(19.5)
//                .image("www.coupon7.com")
//                .build();
//        Coupon coup8 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 8")
//                .description("Description 8")
//                .startDate(Date.valueOf("2022-10-04"))
//                .endDate(Date.valueOf("2023-03-15"))
//                .amount(3)
//                .price(13.7)
//                .image("www.coupon8.com")
//                .build();
//        Coupon coup9 = Coupon.builder()
//                .category(Category.FOOD)
//                .title("Coupon 9")
//                .description("Description 9")
//                .startDate(Date.valueOf("2022-10-04"))
//                .endDate(Date.valueOf("2023-05-16"))
//                .amount(2)
//                .price(12.5)
//                .image("www.coupon9.com")
//                .build();
//        Coupon coup10 = Coupon.builder()
//                .category(Category.CINEMA)
//                .title("Coupon 10")
//                .description("Description 10")
//                .startDate(Date.valueOf("2022-09-27"))
//                .endDate(Date.valueOf("2023-03-06"))
//                .amount(6)
//                .price(18.3)
//                .image("www.coupon10.com")
//                .build();
//
//        PrintUtils.printTitle("ADMIN SERVICE TESTING");
//
//        //add company
////        adminService.addCompany(comp1);
////        adminService.addCompany(comp2);
////        adminService.addCompany(comp3);
////        adminService.addCompany(comp4);
////        adminService.addCompany(comp5);
//
//        //add company exception
//        //email exception
////        adminService.addCompany(Company.builder()
////                .name("Email exception")
////                .email("konami@gmail.com")
////                .password("12345")
////                .build());
//        //name exception
////        adminService.addCompany(Company.builder()
////                .name("Konami")
////                .email("Name exception")
////                .password("12345")
////                .build());
//
//        PrintUtils.print("All companies");
//        adminService.getAllCompanies().forEach(System.out::println);
//        PrintUtils.print("Single company");
//        System.out.println(adminService.getSingleCompany(2));
//
//        //update company
//        Company compToUpdate = adminService.getSingleCompany(2);
//        compToUpdate.setPassword("54321");
////        adminService.updateCompany(2, compToUpdate);
//        PrintUtils.print("Single company after update");
//        System.out.println(adminService.getSingleCompany(2));
//
//        //update company exception
//        //id exception
////        compToUpdate.setId(7);
////        adminService.updateCompany(2, compToUpdate);
//        //name exception
////        compToUpdate.setName("Exception");
////        adminService.updateCompany(2,compToUpdate);
//
//        //add customer
////        adminService.addCustomer(cus1);
////        adminService.addCustomer(cus2);
////        adminService.addCustomer(cus3);
////        adminService.addCustomer(cus4);
////        adminService.addCustomer(cus5);
//
//        //add customer exception
////        adminService.addCustomer(Customer.builder()
////                .firstName("Ex")
////                .lastName("Ception")
////                .email("pooki@gmail.com")
////                .password("12345")
////                .build());
//
//        PrintUtils.print("All customers");
//        adminService.getAllCustomers().forEach(System.out::println);
//        PrintUtils.print("Single customer");
//        System.out.println(adminService.getSingleCustomer(3));
//
//        //update customer
//        Customer cusToUpdate = adminService.getSingleCustomer(3);
//        cusToUpdate.setPassword("54321");
////        adminService.updateCustomer(3, cusToUpdate);
//        PrintUtils.print("Single customer after update");
//        System.out.println(adminService.getSingleCustomer(3));
//
//        //update customer exception
////        cusToUpdate.setId(5);
////        adminService.updateCustomer(3,cusToUpdate);
//
//        PrintUtils.printTitle("COMPANY SERVICE TESTING");
//
//        //add coupon
////        companyService.addCoupon(1, coup1);
////        companyService.addCoupon(1, coup2);
////        companyService.addCoupon(1, coup3);
////        companyService.addCoupon(2, coup4);
////        companyService.addCoupon(3, coup5);
////        companyService.addCoupon(4, coup6);
////        companyService.addCoupon(4, coup7);
////        companyService.addCoupon(5, coup8);
////        companyService.addCoupon(5, coup9);
////        companyService.addCoupon(5, coup10);
//
//        //add coupon exception
////        companyService.addCoupon(5, Coupon.builder()
////                        .title("Coupon 8")
////                .build());
//
//
//        PrintUtils.print("Company coupons");
//        companyService.getCompanyCoupons(4).forEach(System.out::println);
//
//        //update coupon
//        Coupon couponToUpdate = couponRepository.findById(7)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        couponToUpdate.setPrice(200);
////        companyService.updateCoupon(4, 7, couponToUpdate);
//        PrintUtils.print("Company coupons after update");
//        companyService.getCompanyCoupons(4).forEach(System.out::println);
//
//        //update coupon exception
//        //company id exception
////        companyService.updateCoupon(1,7,couponToUpdate);
//        //coupon id exception
////        couponToUpdate.setId(55);
////        companyService.updateCoupon(4,7, couponToUpdate);
//
//        PrintUtils.print("Company coupons by category");
//        companyService.getCompanyCouponsByCategory(5, Category.FOOD).forEach(System.out::println);
//        PrintUtils.print("Company coupons by price");
//        companyService.getCompanyCouponsByPrice(1, 15.5).forEach(System.out::println);
//        PrintUtils.print("Company details");
//        System.out.println(companyService.getCompanyDetails(3));
//
//        PrintUtils.printTitle("CUSTOMER SERVICE TESTING");
//
//        //purchase coupon
//        Coupon couponToPurchase1 = couponRepository.findById(1)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase2 = couponRepository.findById(2)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase3 = couponRepository.findById(3)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase4 = couponRepository.findById(4)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase5 = couponRepository.findById(5)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase6 = couponRepository.findById(6)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase7 = couponRepository.findById(7)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase8 = couponRepository.findById(8)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase9 = couponRepository.findById(9)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//        Coupon couponToPurchase10 = couponRepository.findById(10)
//                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
//
//        customerService.purchaseCoupon(1, couponToPurchase2);
//        customerService.purchaseCoupon(1, couponToPurchase5);
//        customerService.purchaseCoupon(1, couponToPurchase9);
//        customerService.purchaseCoupon(2, couponToPurchase2);
//        customerService.purchaseCoupon(2, couponToPurchase10);
//        customerService.purchaseCoupon(3, couponToPurchase5);
//        customerService.purchaseCoupon(3, couponToPurchase10);
//        customerService.purchaseCoupon(4, couponToPurchase4);
//        customerService.purchaseCoupon(4, couponToPurchase9);
//        customerService.purchaseCoupon(4, couponToPurchase3);
//        customerService.purchaseCoupon(4, couponToPurchase5);
//        customerService.purchaseCoupon(4, couponToPurchase8);
//        customerService.purchaseCoupon(5, couponToPurchase7);
//        customerService.purchaseCoupon(5, couponToPurchase2);
//        customerService.purchaseCoupon(5, couponToPurchase10);
//
//        //purchase coupon exception
//        //multiple purchase exception
////        customerService.purchaseCoupon(3,couponToPurchase5);
//        //coupon out of stock exception
////        customerService.purchaseCoupon(3, couponToPurchase9);
//        //coupon expired exception
////        customerService.purchaseCoupon(3, couponToPurchase6);
//
//        PrintUtils.print("Customer coupons");
//        customerService.getCustomerCoupons(4).forEach(System.out::println);
//        PrintUtils.print("Customer coupons by category");
//        customerService.getCustomerCouponsByCategory(4, Category.FOOD).forEach(System.out::println);
//        PrintUtils.print("Customer coupons by price");
//        customerService.getCustomerCouponsByPrice(4, 14).forEach(System.out::println);
//        PrintUtils.print("Customer details");
//        System.out.println(customerService.getCustomerDetails(4));
//
//        //delete company
////        adminService.deleteCompany(4);
////        PrintUtils.print("Companies after delete");
////        adminService.getAllCompanies().forEach(System.out::println);
//
//        //delete customer
////        adminService.deleteCustomer(2);
////        PrintUtils.print("Customers after delete");
////        adminService.getAllCustomers().forEach(System.out::println);
////
////        //delete coupon
////        companyService.deleteCoupon(5,10);
////        PrintUtils.print("Company coupons after delete");
////        companyService.getCompanyCoupons(5).forEach(System.out::println);
//
//    }
//}