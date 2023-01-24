package com.jb.coupon_system.clr.off;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.repos.CouponRepository;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(7)
public class CustomerControllerTesting implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CouponRepository couponRepository;

    @Value("${url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {

        PrintUtils.printTitle("Customer Controller Testing");

        PrintUtils.print("Customer Details");
        System.out.println(restTemplate.getForObject(url + "/customers/4", Customer.class));

        PrintUtils.print("Get Customer Coupons");
        Coupon[] cusCoups = restTemplate.getForObject(url + "/customers/4/coupons", Coupon[].class);
        List<Coupon> listOfCoups = Arrays.stream(cusCoups).collect(Collectors.toList());
        listOfCoups.forEach(System.out::println);

        PrintUtils.print("Get Customer Coupons By Category");
        Coupon[] cusCoupsByCategory = restTemplate.getForObject(url + "/customers/4/coupons/filter/categories/FOOD", Coupon[].class);
        List<Coupon> listOfCoupsByCategory = Arrays.stream(cusCoupsByCategory).collect(Collectors.toList());
        listOfCoupsByCategory.forEach(System.out::println);

        PrintUtils.print("Get Customer Coupons By Price");
        Coupon[] cusCoupsByPrice = restTemplate.getForObject(url + "/customers/4/coupons/filter/price/max-price/?price=15", Coupon[].class);
        List<Coupon> listOfCoupsByPrice = Arrays.stream(cusCoupsByPrice).collect(Collectors.toList());
        listOfCoupsByPrice.forEach(System.out::println);

        PrintUtils.print("Purchase Coupon");
        Coupon coup = couponRepository.findById(11)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        ResponseEntity<String> purchaseRes = restTemplate.postForEntity(url + "/customers/3/coupons", coup, String.class);
        PrintUtils.printRes(purchaseRes.getStatusCodeValue(), 201);
    }
}
