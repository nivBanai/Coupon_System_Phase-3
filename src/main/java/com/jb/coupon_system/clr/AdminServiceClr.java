package com.jb.coupon_system.clr;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.login.LoginManager;
import com.jb.coupon_system.services.admin_service.AdminService;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AdminServiceClr implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {

        //Companies
        CompanyPayload comp1 = CompanyPayload.builder()
                .name("Pepsi")
                .email("pepsi@yahoo.com")
                .password("12345")
                .build();
        CompanyPayload comp2 = CompanyPayload.builder()
                .name("Konami")
                .email("konami@gmail.com")
                .password("12345")
                .build();
        CompanyPayload comp3 = CompanyPayload.builder()
                .name("Nintendo")
                .email("nintendo@gmail.com")
                .password("12345")
                .build();
        CompanyPayload comp4 = CompanyPayload.builder()
                .name("Yes Planet")
                .email("yes-palnet@walla.com")
                .password("12345")
                .build();
        CompanyPayload comp5 = CompanyPayload.builder()
                .name("McDonald's")
                .email("mcdonalds@gmail.com")
                .password("12345")
                .build();

        //Customers
        CustomerPayload cus1 = CustomerPayload.builder()
                .firstName("Niv")
                .lastName("Banai")
                .email("niv@gmail.com")
                .password("12345")
                .build();
        CustomerPayload cus2 = CustomerPayload.builder()
                .firstName("Rotem")
                .lastName("Kabir")
                .email("rotem@gmail.com")
                .password("12345")
                .build();
        CustomerPayload cus3 = CustomerPayload.builder()
                .firstName("Euclides")
                .lastName("Fibonacci")
                .email("euclides@gmail.com")
                .password("12345")
                .build();
        CustomerPayload cus4 = CustomerPayload.builder()
                .firstName("Pooki")
                .lastName("S")
                .email("pooki@gmail.com")
                .password("12345")
                .build();
        CustomerPayload cus5 = CustomerPayload.builder()
                .firstName("Yoshi")
                .lastName("Munchakoopas")
                .email("yoshi@gmail.com")
                .password("12345")
                .build();

        PrintUtils.printTitle("ADMIN SERVICE TESTING");

        PrintUtils.printExceptionTitle("Admin login exception");
        try {
//            loginManager.login("not-admin@admin.com", "not-admin", ClientType.ADMINISTRATOR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.printLogin("Admin Login");
//        loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

        //add company
        adminService.addCompany(comp1);
        adminService.addCompany(comp2);
        adminService.addCompany(comp3);
        adminService.addCompany(comp4);
        adminService.addCompany(comp5);

        PrintUtils.printExceptionTitle("Add company exception (email exception)");
        try {
            adminService.addCompany(CompanyPayload.builder()
                    .name("Email exception")
                    .email("konami@gmail.com")
                    .password("67890")
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Add company exception (name exception)");
        try {
            adminService.addCompany(CompanyPayload.builder()
                    .name("Konami")
                    .email("Name exception")
                    .password("12345")
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.print("All companies");
        adminService.getAllCompanies().forEach(System.out::println);
        PrintUtils.print("Single company");
        System.out.println(adminService.getSingleCompany(2));

        //update company
        Company compToUpdate = adminService.getSingleCompany(2);
        compToUpdate.setPassword("54321");
//        adminService.updateCompany(2, compToUpdate);
        PrintUtils.print("Single company after update");
        System.out.println(adminService.getSingleCompany(2));

        PrintUtils.printExceptionTitle("Update company exception (id exception)");
        try {
            compToUpdate.setId(7);
//            adminService.updateCompany(2, compToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        PrintUtils.printExceptionTitle("Update company exception (name exception)");
        try {
            compToUpdate.setId(2);
            compToUpdate.setName("Exception");
//            adminService.updateCompany(2, compToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //add customer
        adminService.addCustomer(cus1);
        adminService.addCustomer(cus2);
        adminService.addCustomer(cus3);
        adminService.addCustomer(cus4);
        adminService.addCustomer(cus5);

        PrintUtils.printExceptionTitle("Add customer exception");
        try {
            adminService.addCustomer(CustomerPayload.builder()
                    .firstName("Ex")
                    .lastName("Ception")
                    .email("pooki@gmail.com")
                    .password("12345")
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        PrintUtils.print("All customers");
        adminService.getAllCustomers().forEach(System.out::println);
        PrintUtils.print("Single customer");
        System.out.println(adminService.getSingleCustomer(3));

        //update customer
        Customer cusToUpdate = adminService.getSingleCustomer(3);
        cusToUpdate.setPassword("54321");
//        adminService.updateCustomer(3, cusToUpdate);
        PrintUtils.print("Single customer after update");
        System.out.println(adminService.getSingleCustomer(3));

        PrintUtils.printExceptionTitle("Update customer exception");
        try {
            cusToUpdate.setId(5);
//            adminService.updateCustomer(3, cusToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
