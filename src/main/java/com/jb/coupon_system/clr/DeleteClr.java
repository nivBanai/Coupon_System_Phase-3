package com.jb.coupon_system.clr;

import com.jb.coupon_system.services.admin_service.AdminService;
import com.jb.coupon_system.services.company_service.CompanyService;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Order(5)
public class DeleteClr implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;

    private static final int SECONDS_OF_SLEEP = 2;

    @Override
    public void run(String... args) {

        try {
            TimeUnit.SECONDS.sleep(SECONDS_OF_SLEEP); //(Needed To Show Job Works)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        PrintUtils.printTitle("DELETES");

        //delete company
        adminService.deleteCompany(4);
        PrintUtils.print("Companies after delete");
        adminService.getAllCompanies().forEach(System.out::println);

        //delete customer
        adminService.deleteCustomer(2);
        PrintUtils.print("Customers after delete");
        adminService.getAllCustomers().forEach(System.out::println);

        //delete coupon
        companyService.deleteCoupon(5, 10);
        PrintUtils.print("Company coupons after delete");
        companyService.getCompanyCoupons(5).forEach(System.out::println);
    }
}
