package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.DiscountCoupon;
import com.mahanlei.model.MemberInfo;

public interface MemberDao {
    /**
     * @param memberInfo
     * @return 在memberInfo表中插入一条会员信息数据
     */
    public Message addMember(MemberInfo memberInfo,String password);

    /**
     *
     * @param code
     * @return 更新memberinfo (state=1)
     */
    public boolean activeMember(String code);
    /**
     * 根据mid获取相应会员的信息
     * @param mid
     * @return
     */
    public MemberInfo getMemberInfo(String mid);
    /**
     * @param mid
     *@param state
     * @param age
     * @return 更新memberinfo表中的 age,state
     */
    public  Message updateProfile(String mid,int age,int state);
    /**
     * 获取该用户的优惠券信息
     * @param mid
     * @return 该用户的优惠券信息
     */
    public DiscountCoupon getDisInfo(String mid);

    /**
     * 向disountcoupon表中优惠券更新数据，加法
     * @param discountCoupon
     * @return
     */

    public Message addDis(DiscountCoupon discountCoupon);

    /**
     * 向disountcoupon表中更新数据，减法
     * @param discountCoupon
     * @return
     */
    public Message removeDis(DiscountCoupon discountCoupon);
}
