package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Application;

import java.util.List;

public interface ApplicationDao {
    /**
     * 获得注册/修改未同意申请以及同意的申请
     * @param type
     * @param state
     * @return
     */
    public List<Application> getApplications(int type, int state);

    /**
     * 修改申请状态为1；场馆状态为1
     * @param aid
     * @return
     */
    public Message agreeApplication(int aid,int stadiumId);
 }
