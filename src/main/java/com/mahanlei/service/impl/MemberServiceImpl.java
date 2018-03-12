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

    public Message updateMemRank(String mid) {
        double consumption =DaoFactory.getMemberDao().getConsumption(mid);
        int rank=DaoFactory.getMemberDao().getMemberInfo(mid).getRank();
        int tempRank=0;
        /**
         * rank值1-5为会员级别
         级别1:消费金额0<=cost<100 		可享受95%折扣
         级别2:100<=cost<300		 	    90%
         级别3:300<=cost<600  			80%
         级别4:600<=cost<1000 			75%
         级别5:1000<=cost 				70%
         */
        if(consumption>=0&&consumption<100){
            tempRank=1;
        }else if(consumption>=100&&consumption<300){
            tempRank=2;
        }else if(consumption>=300&&consumption<600) {
            tempRank = 3;
        }else if(consumption>=600&&consumption<1000){
            tempRank=4;
        }else if(consumption>=1000){
            tempRank=5;
        }
        if(rank<tempRank){
            return DaoFactory.getMemberDao().updateMemRank(mid,tempRank);
        }else  return Message.NO_NEED;
    }
}
