package com.jb.coupon_system.services.company_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Override
    public boolean login(String email, String password) {
        return this.companyRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public int getId(String email, String password) throws CouponSystemException {
        return companyRepository.findIdByEmailAndPassword(email, password);
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws CouponSystemException {
        if (this.couponRepository.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
            throw new CouponSystemException(ErrorMsg.COUPON_TITLE_TAKEN);
        }
        coupon.setCompany(this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND)));
        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int companyId, int couponId, Coupon coupon) throws CouponSystemException {
        int couponCompanyId = this.couponRepository.findCompanyIdById(couponId);
        if (companyId != couponCompanyId) {
            throw new CouponSystemException(ErrorMsg.COUPON_ACCESS_DENIED);
        }
        if (couponId != coupon.getId()) {
            throw new CouponSystemException(ErrorMsg.COUPON_ID_ERROR);
        }
        coupon.setCompany(this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND)));
        coupon.setId(couponId);
        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponId) {
//        this.couponRepository.deletePurchaseHistoryByCouponId(couponId);
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return this.couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(int companyId, Category category) {
        return this.couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCouponsByPrice(int companyId, double maxPrice) {
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws CouponSystemException {
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND));
    }
}
