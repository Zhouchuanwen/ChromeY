package com.alan.controller;

import com.alan.bean.Record;
import com.alan.core.Wrapper;
import com.alan.service.HistoryService;
import com.alan.util.MyDateUtils;
import com.alan.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alan on 17/1/9.
 */
@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/greeting")
    @ResponseBody
    public Wrapper greeting() {
        return Wrapper.builder().data(historyService.test()).build();
    }

    @RequestMapping("/range")
    @ResponseBody
    public Wrapper getHistoryRange() {
        return Wrapper.builder().data(historyService.getHistoryRange()).build();
    }

    @RequestMapping("/today")
    @ResponseBody
    public Wrapper getTodays() {
        return Wrapper.builder().data(historyService.daliyHistory()).build();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Wrapper getHistoryByDate(@QueryParam("state") String state) {
        Map<Long,Long> map=Util.state2time(state);
        for (Long start:map.keySet()){
            List<Record> list=historyService.getHistoryByDate(start,map.get(start));
            return Wrapper.builder().data(list).build();
        }
        return Wrapper.ERROR;
    }

    @RequestMapping("/count")
    @ResponseBody
    public Wrapper getHistoryCountByDate(@QueryParam("state") String state) {
        Map<Long,Long> map=Util.state2time(state);
        for (Long start:map.keySet()){
            List<Record> list=historyService.getHistoryCountByDate(start,map.get(start));
            return Wrapper.builder().data(list).build();
        }
        return Wrapper.ERROR;
    }

}
