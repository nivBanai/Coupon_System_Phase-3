package com.jb.coupon_system.services.company_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.dto.CouponPayload;
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
    public int getId(String email) throws CouponSystemException {
        return companyRepository.findIdByEmail(email);
    }

    @Override
    public Coupon addCoupon(int companyId, CouponPayload couponPayload) throws CouponSystemException {
        if (this.couponRepository.existsByCompanyIdAndTitle(companyId, couponPayload.getTitle())) {
            throw new CouponSystemException(ErrorMsg.COUPON_TITLE_TAKEN);
        }

       return this.couponRepository.save(Coupon.builder()
                .category(couponPayload.getCategory())
                .title(couponPayload.getTitle())
                .description(couponPayload.getDescription())
                .startDate(couponPayload.getStartDate())
                .endDate(couponPayload.getEndDate())
                .amount(couponPayload.getAmount())
                .price(couponPayload.getPrice())
                .image(couponPayload.getImage())
                .company(this.companyRepository.findById(companyId)
                        .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND)))
                .build());
    }

    @Override
    public Coupon updateCoupon(int companyId, int couponId, CouponPayload couponPayload) throws CouponSystemException {
        // TODO: 25/01/2023 check if needed
//        int couponCompanyId = this.couponRepository.findCompanyIdById(couponId);
//        if (companyId != couponCompanyId) {
//            throw new CouponSystemException(ErrorMsg.COUPON_ACCESS_DENIED);
//        }
//        if (couponId != coupon.getId()) {
//            throw new CouponSystemException(ErrorMsg.COUPON_ID_ERROR);
//        }
        // TODO: 25/01/2023 need to set id? 
        Coupon originalCoupon = this.couponRepository.findById(couponId)
                        .orElseThrow(()->new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        originalCoupon.setId(couponId);
        originalCoupon.setCategory(couponPayload.getCategory());
        originalCoupon.setTitle(couponPayload.getTitle());
        originalCoupon.setDescription(couponPayload.getDescription());
        originalCoupon.setStartDate(couponPayload.getStartDate());
        originalCoupon.setEndDate(couponPayload.getEndDate());
        originalCoupon.setAmount(couponPayload.getAmount());
        originalCoupon.setPrice(couponPayload.getPrice());
        originalCoupon.setImage(couponPayload.getImage());
        originalCoupon.setCompany(this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND)));
        return this.couponRepository.saveAndFlush(originalCoupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponId) {
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
