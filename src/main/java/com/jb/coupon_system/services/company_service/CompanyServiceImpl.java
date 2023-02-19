package com.jb.coupon_system.services.company_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.dto.ClientInfoDto;
import com.jb.coupon_system.dto.CouponPayload;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.utils.ConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Override
    public boolean login(String email, String password) {
        return this.companyRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public ClientInfoDto getClientIdAndNameAndProfilePic(String email) {
        return ConvertUtils.convertToClientInfoDto(this.companyRepository.findIdAndNameAndProfilePicByEmail(email));
    }

    @Override
    public Coupon addCoupon(int companyId, CouponPayload couponPayload) throws CouponSystemException {

        if (this.couponRepository.existsByCompanyIdAndTitle(companyId, couponPayload.getTitle())) {
            throw new CouponSystemException(ErrorMsg.COUPON_TITLE_TAKEN);
        }

        return this.couponRepository.save(
                Coupon.builder()
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

        if (this.couponRepository.existsByCompanyIdAndTitleAndId(companyId, couponPayload.getTitle(), couponId)) {
            throw new CouponSystemException(ErrorMsg.COUPON_TITLE_TAKEN);
        }

        Coupon originalCoupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));

        BeanUtils.copyProperties(couponPayload, originalCoupon);

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
