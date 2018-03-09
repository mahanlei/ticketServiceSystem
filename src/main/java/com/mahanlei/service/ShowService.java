package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;

import java.util.List;

public interface ShowService {
    /**
     * 得到所有的演出的简略信息，用于展示
     * @return
     */
    public List<ShowInfoBrief> getAllShowInfo();

    /**
     * 根据showid获取某场演出的详细信息
     * @param showId
     * @return
     */
    public ShowInfo getShowInfo(int showId);

    /**
     * 场馆发布一场演出
     * @param showInfo
     * @return
     */
    public Message releaseAShow(ShowInfo showInfo);

}
