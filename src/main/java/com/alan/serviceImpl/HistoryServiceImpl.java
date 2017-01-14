package com.alan.serviceImpl;

import com.alan.bean.Record;
import com.alan.dao.History;
import com.alan.service.HistoryService;
import com.alan.util.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by alan on 17/1/9.
 */
@Component
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private History history;

    @Override
    public String test() {
        return "hi";
    }

    @Override
    public Map<Long, Long> getHistoryRange() {
        return history.getHistoryRange();
    }

    @Override
    public List<Record> getHistoryByDate(long start,long end) {
        return history.findRecordsByTime(MyDateUtils.unixTime2WebKitTime(start),MyDateUtils.unixTime2WebKitTime(end));
    }

    @Override
    public List<Record> daliyHistory() {
        return history.countDailyVisits();
    }

    @Override
    public List<Record> getHistoryCountByDate(long start, long end) {
        return history.countURLsFrequence(MyDateUtils.unixTime2WebKitTime(start),MyDateUtils.unixTime2WebKitTime(end));
    }

}
