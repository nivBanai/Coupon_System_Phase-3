package com.jb.coupon_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMsg {

    LOGIN_ERROR("Wrong email or password"),
    EMAIL_TAKEN("Email already in use"),
    NAME_TAKEN("Name already in use"),
    COMPANY_NAME_ERROR("Cannot change company's name"),
    COMPANY_NOT_FOUND("Did not find a company with that id"),
    CUSTOMER_NOT_FOUND("Did not find a customer with that id"),
    COUPON_TITLE_TAKEN("Coupon's Title already in use by this company"),
    COUPON_PURCHASE_LIMIT("Cannot purchase one coupon multiple times"),
    OUT_OF_STOCK("Coupon is currently out of stock"),
    COUPON_EXPIRED("Coupon has expired"),
    COUPON_NOT_FOUND("Did not find coupon"),
    ACCESS_DENIED("Access denied"),
    INVALID_CLIENT_TYPE("This client type is invalid");

    private final String MSG;
}
