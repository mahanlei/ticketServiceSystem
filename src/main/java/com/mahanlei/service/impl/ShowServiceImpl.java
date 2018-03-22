package com.mahanlei.service.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;
import com.mahanlei.service.ShowService;

import java.util.List;

public class ShowServiceImpl implements ShowService{
    private  static  ShowServiceImpl showService=new ShowServiceImpl();
    public static ShowServiceImpl getInstance(){
        return  showService;
    }

    public List<ShowInfoBrief> getAllShowInfo() {
        return DaoFactory.getShowDao().getAllShowInfo();
    }

    public ShowInfo getShowInfo(int showId) {
       return DaoFactory.getShowDao().getShowInfo(showId);
    }

    public List<ShowInfo> getStaShow(String staId) {
        return DaoFactory.getShowDao().getStaShow(Integer.parseInt(staId));
    }

    public Message releaseAShow(ShowInfo showInfo) {
       return DaoFactory.getShowDao().addAShow(showInfo);
    }
}
