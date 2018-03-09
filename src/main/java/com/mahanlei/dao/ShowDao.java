package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;

import java.util.List;

public interface ShowDao {
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
     * 向showinfo表中插入一条数据
     * @param showInfo
     * @return
     */
    public Message addAShow(ShowInfo showInfo);
}
