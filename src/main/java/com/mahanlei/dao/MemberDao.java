package com.mahanlei.dao;

import com.mahanlei.Util.Message;
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
}
