package com.jb.coupon_system.clr;

import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.services.admin_service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class AdminServiceClr implements CommandLineRunner {

    @Autowired
    private final AdminService adminService;

    @Override
    public void run(String... args) throws Exception {

        //Companies
        CompanyPayload comp1 = CompanyPayload.builder()
                .name("Pepsi")
                .email("pepsi@yahoo.com")
                .password("12345")
                .profilePic("https://styles.redditmedia.com/t5_2r60c/styles/communityIcon_mbk7jktnrgk81.png?width=256&s=40abe708a0bc6813daf4abc8c17c0241e99b0b41")
                .build();

        CompanyPayload comp2 = CompanyPayload.builder()
                .name("Konami")
                .email("konami@gmail.com")
                .password("12345")
                .profilePic("https://upload.wikimedia.org/wikipedia/commons/3/33/KONAMI_LOGO_RED.png")
                .build();

        CompanyPayload comp3 = CompanyPayload.builder()
                .name("Nintendo")
                .email("nintendo@gmail.com")
                .password("12345")
                .profilePic("https://www.nintendo.co.il/wp-content/uploads/2021/06/NLogo_StandardVersionA_01-1.png")
                .build();

        CompanyPayload comp4 = CompanyPayload.builder()
                .name("Yes Planet")
                .email("yes-planet@walla.com")
                .password("12345")
                .profilePic("https://www.icoupons.co.il/wp-content/uploads/2021/08/%D7%99%D7%A1-%D7%A4%D7%9C%D7%A0%D7%98.jpeg")
                .build();

        CompanyPayload comp5 = CompanyPayload.builder()
                .name("McDonald's")
                .email("mcdonalds@gmail.com")
                .password("12345")
                .profilePic("https://rmhdurhamwake.org/wp-content/uploads/2016/09/mcdonalds-logo.jpg")
                .build();

        //Customers
        CustomerPayload cus1 = CustomerPayload.builder()
                .firstName("Niv")
                .lastName("Banai")
                .email("niv@gmail.com")
                .password("12345")
                .profilePic("https://live.staticflickr.com/65535/52674823407_28a18ebd14_c.jpg")
                .build();

        CustomerPayload cus2 = CustomerPayload.builder()
                .firstName("Rotem")
                .lastName("Kabir")
                .email("rotem@gmail.com")
                .password("12345")
                .profilePic("https://media.licdn.com/dms/image/C4D03AQH9ZPp_Giolbw/profile-displayphoto-shrink_800_800/0/1661498033673?e=1681344000&v=beta&t=cZwKkyvlDjdd-QRqzB343nqiy4p_nuohEg3OHHU975w")
                .build();

        CustomerPayload cus3 = CustomerPayload.builder()
                .firstName("Euclides")
                .lastName("Fibonacci")
                .email("euclides@gmail.com")
                .password("12345")
                .profilePic("https://live.staticflickr.com/65535/52675333371_14821753b8_c.jpg")
                .build();

        CustomerPayload cus4 = CustomerPayload.builder()
                .firstName("Pooki")
                .lastName("S")
                .email("pooki@gmail.com")
                .profilePic("https://live.staticflickr.com/65535/52675766425_4383dc1395_c.jpg")
                .password("12345")
                .build();

        CustomerPayload cus5 = CustomerPayload.builder()
                .firstName("Yoshi")
                .lastName("Munchakoopas")
                .email("yoshi@gmail.com")
                .password("12345")
                .profilePic("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq7Syi661-rn9WBZEdkSTUZWAv_AoFvbaJ5Q&usqp=CAU")
                .build();


        //add company
        adminService.addCompany(comp1);
        adminService.addCompany(comp2);
        adminService.addCompany(comp3);
        adminService.addCompany(comp4);
        adminService.addCompany(comp5);

        //add customer
        adminService.addCustomer(cus1);
        adminService.addCustomer(cus2);
        adminService.addCustomer(cus3);
        adminService.addCustomer(cus4);
        adminService.addCustomer(cus5);
    }
}
