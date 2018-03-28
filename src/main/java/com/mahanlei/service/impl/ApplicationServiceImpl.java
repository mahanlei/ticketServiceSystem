package com.mahanlei.service.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.model.Application;
import com.mahanlei.service.ApplicationService;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private static ApplicationServiceImpl applicationService=new ApplicationServiceImpl();

    public static ApplicationServiceImpl getApplicationService() {
        return applicationService;
    }

    @Override
    public List<Application> getApplications(int type, int state) {
        return DaoFactory.getApplicationDao().getApplications(type,state);
    }

    @Override
    public Message agreeApplication(int aid, int stadiumId) {
        return DaoFactory.getApplicationDao().agreeApplication(aid,stadiumId);
    }
}
