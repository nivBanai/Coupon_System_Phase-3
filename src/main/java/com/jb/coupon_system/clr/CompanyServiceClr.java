package com.jb.coupon_system.clr;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.dto.CouponPayload;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.login.LoginManager;
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
    private LoginManager loginManager;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        //Coupons
        CouponPayload coup1 = CouponPayload.builder()
                .category(Category.FOOD)
                .title("5 + 1")
                .description("Purchase 5 pepsi cans - get the 6th for free!")
                .startDate(Date.valueOf("2023-02-18"))
                .endDate(Date.valueOf("2023-11-20"))
                .amount(7)
                .price(12.99)
                .image("https://beercastleny.com/wp-content/uploads/2017/11/Pepsi-Bottle-Cans-12-fl-oz-6-ct.jpg")
                .build();

        CouponPayload coup2 = CouponPayload.builder()
                .category(Category.FOOD)
                .title("10% Off of 24 pack")
                .description("Get 10% off of your next 24 pack purchase!")
                .startDate(Date.valueOf("2022-09-01"))
                .endDate(Date.valueOf("2023-05-22"))
                .amount(4)
                .price(18.49)
                .image("https://i5.walmartimages.com/asr/d95d7098-6f69-4c53-8ca6-1884f9d4f931.6948722d416c7de1f5ee17dc8c8a2a6b.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF")
                .build();

        CouponPayload coup3 = CouponPayload.builder()
                .category(Category.CINEMA)
                .title("25% Off of a movie")
                .description("Get 25% off of a selection of movies on a 36 pack purchase!")
                .startDate(Date.valueOf("2022-10-08"))
                .endDate(Date.valueOf("2023-04-09"))
                .amount(12)
                .price(29.99)
                .image("https://m.media-amazon.com/images/I/51wALfYYY4L._AC_.jpg")
                .build();

        CouponPayload coup4 = CouponPayload.builder()
                .category(Category.TRADING_CARDS)
                .title("3% Off of a booster pack")
                .description("Get a 3% discount on your next booster pack purchase!")
                .startDate(Date.valueOf("2022-10-07"))
                .endDate(Date.valueOf("2023-04-29"))
                .amount(17)
                .price(30)
                .image("https://www.yugioh-card.com/en/wp-content/uploads/2022/12/PSV_25th_550.png")
                .build();

        CouponPayload coup5 = CouponPayload.builder()
                .category(Category.VIDEO_GAMES)
                .title("30% Off of second game")
                .description("Purchase a game and get a second game with 30% discount out of a selection of games!")
                .startDate(Date.valueOf("2022-11-11"))
                .endDate(Date.valueOf("2023-05-19"))
                .amount(5)
                .price(22.19)
                .image("https://media.karousell.com/media/photos/products/2021/5/17/14_free_games__4_nintendo_swit_1621216624_e3834afe_progressive")
                .build();

        CouponPayload coup6 = CouponPayload.builder()
                .category(Category.FOOD)
                .title("Free popcorn and 2 drinks")
                .description("Get a free popcorn and a pair of drinks of your choice when purchasing a pair of movie tickets!")
                .startDate(Date.valueOf("2022-11-01"))
                .endDate(Date.valueOf("2023-10-30"))
                .amount(3)
                .price(24.49)
                .image("https://fox5theatre.com/wp-content/uploads/2020/03/PopcornCombo.jpg")
                .build();

        CouponPayload coup7 = CouponPayload.builder()
                .category(Category.CINEMA)
                .title("1 + 1")
                .description("Get 2 movie tickets with the price of 1!")
                .startDate(Date.valueOf("2022-11-25"))
                .endDate(Date.valueOf("2023-04-12"))
                .amount(12)
                .price(19.99)
                .image("https://media.dolcehost.co.il/prod_files/20221214142356.jpg")
                .build();

        CouponPayload coup8 = CouponPayload.builder()
                .category(Category.FOOD)
                .title("Free fries")
                .description("Get free fries on your next 50\u20AA or over order!")
                .startDate(Date.valueOf("2022-10-04"))
                .endDate(Date.valueOf("2023-06-15"))
                .amount(8)
                .price(13.75)
                .image("https://www.wired.com/wp-content/uploads/2014/06/qq_whatsinside_fries_f.jpg")
                .build();

        CouponPayload coup9 = CouponPayload.builder()
                .category(Category.FOOD)
                .title("Free donut")
                .description("Get a free donut with your next meal!")
                .startDate(Date.valueOf("2022-10-04"))
                .endDate(Date.valueOf("2023-05-16"))
                .amount(3)
                .price(9.99)
                .image("https://qph.cf2.quoracdn.net/main-qimg-722e76aa081e187a0e81984749e6ce22-lq")
                .build();

        CouponPayload coup10 = CouponPayload.builder()
                .category(Category.TRADING_CARDS)
                .title("Free Pokémon pack")
                .description("Get a Pokémon pack with your next meal!")
                .startDate(Date.valueOf("2022-09-27"))
                .endDate(Date.valueOf("2023-03-06"))
                .amount(11)
                .price(11.49)
                .image("https://imageio.forbes.com/specials-images/imageserve/6135fd17488c82343149f144/0x0.jpg?format=jpg&width=1200")
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
//            companyService.addCoupon(5, CouponPayload.builder()
//                    .title("Coupon 8")
//                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        PrintUtils.print("Company coupons");
        companyService.getCompanyCoupons(5).forEach(System.out::println);

        //update coupon
        Coupon couponToUpdate = couponRepository.findById(8)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        couponToUpdate.setPrice(200);
//        companyService.updateCoupon(5, 8, couponToUpdate);
        PrintUtils.print("Company coupons after update");
        companyService.getCompanyCoupons(5).forEach(System.out::println);

        PrintUtils.printExceptionTitle("Update coupon exception (company id exception)");
        try {
//            companyService.updateCoupon(1, 8, couponToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Update coupon exception (coupon id exception)");
        try {
            couponToUpdate.setId(55);
//            companyService.updateCoupon(5, 8, couponToUpdate);
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
