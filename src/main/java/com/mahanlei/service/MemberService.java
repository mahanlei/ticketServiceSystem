package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.DiscountCoupon;
import com.mahanlei.model.MemberInfo;

public interface MemberService {
    /**
     * @param memberInfo
     * @return 注册是否成功的信息
     */
    public Message doRegister(MemberInfo memberInfo, String password);

    /**
     * @param code
     * @return 激活用户状态为会员
     */
    public boolean activeMember(String code);

    /**
     * 根据mid获取相应会员的信息
     *
     * @param mid
     * @return
     */
    public MemberInfo getMemberInfo(String mid);

    /**
     * @param mid
     * @param age   年龄
     * @param state 是否是会员
     * @return 会员可以修改自己的个人资料
     */
    public Message changeProfile(String mid, int age, String state);

    /**
     * 获取该用户的优惠券信息
     * @param mid
     * @return 该用户的优惠券信息
     */
    public DiscountCoupon getDisInfo(String mid);

    /**
     * 兑换优惠券，可同时兑换多张
     * @param discountCoupon
     * @return
     */

    public Message exchangeDis(DiscountCoupon discountCoupon);

    /**
     * 使用优惠券,同时只能使用一张优惠券
     * @param discountCoupon
     * @return
     */
    public Message useDis(DiscountCoupon discountCoupon);

}