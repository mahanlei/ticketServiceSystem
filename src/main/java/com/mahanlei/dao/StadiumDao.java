package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.StadiumInfo;

public interface StadiumDao {
    /**
     * 获取某场馆的全部信息
     * @param stadiumId
     * @return
     */
    public StadiumInfo getStadiumInfo(int stadiumId);

    /**
     * 场馆注册，插入信息，但是状态置为0
     * @param stadiumInfo
     * @return
     */
    public Message addStadium(StadiumInfo stadiumInfo);

    /**
     * 场馆注册申请/修改信息得到审批，状态置为1
     * @param stadiumId
     * @return
     */
    public Message activeStadium(int stadiumId);

    /**
     * 场馆申请修改信息（场馆名称），先改掉然后将状态置为2
     * @param stadiumId
     * @param staName
     * @return
     */
    public Message updateStaInfo(int stadiumId,String staName);


}
