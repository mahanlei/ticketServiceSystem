package com.mahanlei.service.impl;

        import com.mahanlei.Util.CodeUtil;
        import com.mahanlei.Util.Message;
        import com.mahanlei.dao.DaoHelper;
        import com.mahanlei.factory.DaoFactory;
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
}
