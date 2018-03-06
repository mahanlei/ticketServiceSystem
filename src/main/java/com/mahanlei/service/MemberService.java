package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.MemberInfo;

public interface MemberService {
    /**
     *
     * @param memberInfo

     * @return 注册是否成功的信息
     */
    public Message doRegister(MemberInfo memberInfo,String password);

    /**
     *
     * @param code
     * @return 激活用户状态为会员
     */
    public boolean activeMember(String code);
}
