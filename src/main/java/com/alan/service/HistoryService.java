package com.alan.service;


import com.alan.bean.Record;
import java.util.List;
import java.util.Map;

/**
 * Created by alan on 17/1/9.
 */
public interface HistoryService {

    String test();

    Map<Long,Long> getHistoryRange();

    List<Record> getHistoryByDate(Long start, Long end);

    List<Record> daliyHistory();

    List<Record> getHistoryCountByDate(Long start,Long end);

}
