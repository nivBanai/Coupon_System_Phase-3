package com.jb.coupon_system.clr.off;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.repos.CouponRepository;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(6)
public class CompanyControllerTesting implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CouponRepository couponRepository;

    @Value("${url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {

        PrintUtils.printTitle("Company Controller Testing");

        PrintUtils.print("Company Details");
        System.out.println(restTemplate.getForObject(url + "/companies/1/details", Company.class));

        PrintUtils.print("Get Company Coupons");
        Coupon[] coupons = restTemplate.getForObject(url + "/companies/1/coupons", Coupon[].class);
        List<Coupon> listOfCoups = Arrays.stream(coupons).collect(Collectors.toList());
        listOfCoups.forEach(System.out::println);

        PrintUtils.print("Get Company Coupons By Category");
        Coupon[] coupsByCategory =
                restTemplate.getForObject(url + "/companies/1/coupons/filter/categories/FOOD", Coupon[].class);
        List<Coupon> listOfCoupByCategory = Arrays.stream(coupsByCategory).collect(Collectors.toList());
        listOfCoupByCategory.forEach(System.out::println);

        PrintUtils.print("Get Company Coupons By Price");
        Coupon[] coupsByPrice =
                restTemplate.getForObject(url + "/companies/1/coupons/filter/price/max-price/?price=16", Coupon[].class);
        List<Coupon> listOfCoupByPrice = Arrays.stream(coupsByPrice).collect(Collectors.toList());
        listOfCoupByPrice.forEach(System.out::println);

        PrintUtils.print("Add Coupon");
        Coupon coup = Coupon.builder()
                .category(Category.SPORTS_GEAR)
                .title("New Coupon")
                .description("New Description")
                .startDate(Date.valueOf("2022-12-18"))
                .endDate(Date.valueOf("2023-08-08"))
                .amount(12)
                .price(9.4)
                .build();

        ResponseEntity<String> addCoupRes = restTemplate.postForEntity(url + "/companies/1/coupons", coup, String.class);
        PrintUtils.printRes(addCoupRes.getStatusCodeValue(), 201);

        PrintUtils.print("Update Coupon");
        Coupon coupToUpdate = couponRepository.findById(2)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        coupToUpdate.setPrice(5000);
        HttpEntity<Coupon> coupToUpdateEntity = new HttpEntity<>(coupToUpdate);
        ResponseEntity<String> updateCoupRes = restTemplate
                .exchange(url + "/companies/1/coupons/2", HttpMethod.PUT, coupToUpdateEntity, String.class);
        PrintUtils.printRes(updateCoupRes.getStatusCodeValue(), 204);

        PrintUtils.print("Delete Coupon");
        ResponseEntity<String> deleteCoupRes = restTemplate
                .exchange(url + "/companies/1/coupons/3", HttpMethod.DELETE, null, String.class);
        PrintUtils.printRes(deleteCoupRes.getStatusCodeValue(), 204);

    }
}
