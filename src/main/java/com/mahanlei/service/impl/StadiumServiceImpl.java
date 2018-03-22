package com.mahanlei.service.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.service.StadiumService;

public class StadiumServiceImpl  implements StadiumService{
    private static  StadiumServiceImpl stadiumService=new StadiumServiceImpl();

    public static StadiumServiceImpl getStadiumService() {
        return stadiumService;
    }

    public StadiumInfo getStadiumInfo(int stadiumId) {
        return DaoFactory.getStadiumDao().getStadiumInfo(stadiumId);
    }

    public Message addStadium(StadiumInfo stadiumInfo) {
       return DaoFactory.getStadiumDao().addStadium(stadiumInfo);
    }

    public Message activeStadium(int stadiumId) {
        return DaoFactory.getStadiumDao().activeStadium(stadiumId);
    }

    public Message updateStaInfo(int stadiumId, String staName) {
        return DaoFactory.getStadiumDao().updateStaInfo(stadiumId,staName);
    }
}
