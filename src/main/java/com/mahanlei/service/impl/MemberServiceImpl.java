package com.mahanlei.service.impl;

        import com.mahanlei.Util.Message;
        import com.mahanlei.factory.DaoFactory;
        import com.mahanlei.model.DiscountCoupon;
        import com.mahanlei.model.MemberInfo;
        import com.mahanlei.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private  static MemberServiceImpl memberService=new MemberServiceImpl();
    public static MemberServiceImpl getInstance(){
        return  memberService;
    }
    public Message doRegister(MemberInfo memberInfo,String password) {


        return DaoFactory.getMemberDao().addMember(memberInfo,password);
    }

    public boolean activeMember(String code) {
        return  DaoFactory.getMemberDao().activeMember(code);
    }

    public MemberInfo getMemberInfo(String mid) {
        return DaoFactory.getMemberDao().getMemberInfo(mid);
    }

    public Message changeProfile(String mid, int age, String state) {
        int stateInt=0;
        if(state.equals("会员")){
            stateInt=1;
        }
        return DaoFactory.getMemberDao().updateProfile(mid,age,stateInt);
    }

    public DiscountCoupon getDisInfo(String mid) {
        return DaoFactory.getMemberDao().getDisInfo(mid);
    }

    public Message exchangeDis(DiscountCoupon discountCoupon) {
       return DaoFactory.getMemberDao().addDis(discountCoupon);
    }

    public Message useDis(DiscountCoupon discountCoupon) {
        return DaoFactory.getMemberDao().removeDis(discountCoupon);
    }
}
