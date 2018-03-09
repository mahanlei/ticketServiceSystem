package com.mahanlei.Util;

public class TransDataType {
    public static ShowType intToShowType (int type){
        ShowType showType=null;
        switch (type){
            case 0:
                showType=ShowType.CONCERT;
                break;
            case 1:
                showType=ShowType.DANCE;
                break;
            case 2:
                showType=ShowType.DRAMA;
                break;
            case 3:
                showType=ShowType.SPORTS_EVENT;
                break;
            case 4:
                showType=ShowType.MOVIE;
                break;
            case 5:
                showType=ShowType.VOCAL_CONCERT;
                break;

        }
        return showType;
    }
    public static int showTypeToInt(ShowType showType){
        int i=-1;
        switch (showType){
            case CONCERT:
                i=0;
                break;
            case DANCE:
                i=1;
                break;
            case DRAMA:
                i=2;
                break;
            case SPORTS_EVENT:
                i=3;
                break;
            case MOVIE:
                i=4;
                break;
            case VOCAL_CONCERT:
                i=5;
                break;

        }
        return  i;
    }
    public static String showTypeToString(ShowType showType){
        String result=null;
        switch (showType){
            case CONCERT:
             result="音乐会";
                break;
            case DANCE:
                result="舞蹈";
                break;
            case DRAMA:
                result="话剧";
                break;
            case SPORTS_EVENT:
              result="体育比赛";
                break;
            case MOVIE:
                result="电影";
                break;
            case VOCAL_CONCERT:
               result="演唱会";
                break;

        }
        return result;
    }
}
