package com.alan.controller;

import com.alan.core.Wrapper;
import com.alan.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;

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
    public Wrapper getHistoryByDate(@QueryParam("start") long start,@QueryParam("end")long end) {
        return Wrapper.builder().data(historyService.getHistoryByDate(start,end)).build();
    }

    @RequestMapping("/count")
    @ResponseBody
    public Wrapper getHistoryCountByDate(@QueryParam("start") long start,@QueryParam("end")long end) {
        return Wrapper.builder().data(historyService.getHistoryCountByDate(start,end)).build();
    }


}
