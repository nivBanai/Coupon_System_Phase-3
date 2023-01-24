package com.jb.coupon_system.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetails {

    private final String key = "Coupon System";
    private String value;
}
