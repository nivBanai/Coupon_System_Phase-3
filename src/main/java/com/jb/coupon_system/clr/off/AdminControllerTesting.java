package com.jb.coupon_system.clr.off;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(5)
public class AdminControllerTesting implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {

        PrintUtils.printTitle("ADMIN CONTROLLER TESTING");

        PrintUtils.print("Get All Companies");
        Company[] companies = restTemplate.getForObject(url + "/companies", Company[].class);
        List<Company> allCompanies = Arrays.stream(companies).collect(Collectors.toList());
        allCompanies.forEach(System.out::println);

        PrintUtils.print("Get Single Company");
        System.out.println(restTemplate.getForObject(url + "/companies/3", Company.class));

        PrintUtils.print("Add Company");
        Company company = Company.builder()
                .name("New Company")
                .email("new_company@gmail.com")
                .password("12345")
                .build();
        ResponseEntity<String> addCompRes = restTemplate.postForEntity(url + "/companies", company, String.class);
        PrintUtils.printRes(addCompRes.getStatusCodeValue(), 201);

        PrintUtils.print("Update Company");
        Company compToUpdate = restTemplate.getForObject(url + "/companies/6", Company.class);
        compToUpdate.setPassword("New Password");
        HttpEntity<Company> compToUpdateEntity = new HttpEntity<>(compToUpdate);
        ResponseEntity<String> updateCompRes = restTemplate
                .exchange(url + "/companies/6", HttpMethod.PUT, compToUpdateEntity, String.class);
        PrintUtils.printRes(updateCompRes.getStatusCodeValue(), 204);

        PrintUtils.print("Delete Company");
        ResponseEntity<String> deleteCompRes = restTemplate
                .exchange(url + "/companies/6", HttpMethod.DELETE, null, String.class);
        PrintUtils.printRes(deleteCompRes.getStatusCodeValue(), 204);

        PrintUtils.print("Get All Customers");
        Customer[] customers = restTemplate.getForObject(url + "/customers", Customer[].class);
        List<Customer> allCustomers = Arrays.stream(customers).collect(Collectors.toList());
        allCustomers.forEach(System.out::println);

        PrintUtils.print("Get Single Customer");
        System.out.println(restTemplate.getForObject(url + "/customers/5", Customer.class));

        PrintUtils.print("Add Customer");
        Customer customer = Customer.builder()
                .firstName("New")
                .lastName("Customer")
                .email("new_customer@gmail.com")
                .password("12345")
                .build();
        ResponseEntity<String> addCusRes = restTemplate.postForEntity(url + "/customers", customer, String.class);
        PrintUtils.printRes(addCusRes.getStatusCodeValue(), 201);

        PrintUtils.print("Update Customer");
        Customer cusToUpdate = restTemplate.getForObject(url + "/customers/6", Customer.class);
        cusToUpdate.setPassword("New Password");
        HttpEntity<Customer> cusToUpdateEntity = new HttpEntity<>(cusToUpdate);
        ResponseEntity<String> updateCusRes = restTemplate
                .exchange(url + "/customers/6", HttpMethod.PUT, cusToUpdateEntity, String.class);
        PrintUtils.printRes(updateCusRes.getStatusCodeValue(), 204);

        PrintUtils.print("Delete Customer");
        ResponseEntity<String> deleteCusRes = restTemplate
                .exchange(url + "/customers/6", HttpMethod.DELETE, null, String.class);
        PrintUtils.printRes(deleteCusRes.getStatusCodeValue(), 204);
    }
}
