package com.jb.coupon_system.exceptions;

import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.utils.PrintUtils;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrorMsg error) {
        super(error.getMSG());
    }
}
