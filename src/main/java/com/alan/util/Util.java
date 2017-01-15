package com.alan.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 时间处理类
 * Created by alan on 17/1/12.
 */
public class Util {

    public static Map<Long,Long> state2time(String time){
        Map<String,String> map=new HashMap<>();
        switch(time){
            case "1":
                map.put(MyDateUtils.timeAtStartOfDayStr(),MyDateUtils.timeAtEndOfDayStr());
                break;
            case "2":
                map=MyDateUtils.getYesterDay();
                break;
            case "3":
                map=MyDateUtils.getLastWeek();
                break;
            case "4":
                map=MyDateUtils.getThisMonth();
                break;
            case "5":
                map=MyDateUtils.getLastMonth();
                break;
            default:
                break;
        }
        Map<Long,Long> transfer=new HashMap<>();
        if(map.size()>0){
            map.forEach((i,j)->{
                transfer.put(MyDateUtils.String2Datetime(i).getTime(),MyDateUtils.String2Datetime(j).getTime());
            });
        }
        return transfer;
    }


}
