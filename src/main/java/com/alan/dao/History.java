package com.alan.dao;

import com.alan.bean.Record;
import com.alan.config.InitConfig;
import com.alan.util.MyDateUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * Created by alan on 17/1/6.
 */
public class History {

    private Statement statement;

    public History(){
        this.statement= InitConfig.getStateMent();
    }

    public Statement getStatement() {
        if(statement==null){
            return InitConfig.getStateMent();
        }
        return statement;
    }


    /**
     * 获取数据库开始与结束时间
     * @return
     */
    public Map<Long,Long> getHistoryRange(){
        try {
            Map<Long,Long> map=new HashMap<>();
            String sql=" select min(visit_time) as min_visit_time, max(visit_time) as max_visit_time from visits";
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                map.put(rs.getLong(1),rs.getLong(2));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取用户所有的历史记录
     * @return
     */
    public List<Record> findAllRecords(){
        try {
            List<Record> list=new ArrayList<>();
            ResultSet rs=statement.executeQuery(" select visits.visit_time,urls.url,urls.title" +
                    " from visits, urls on visits.url = urls.id; ");
            while (rs.next()){
                list.add(Record.builder().visit(rs.getLong(1)).url(rs.getString(2)).title(rs.getString(3)).build());
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取用户每天的访问记录
     */
    public List<Record> countDailyVisits(){
        try {
            long start=MyDateUtils.timeAtStartOfDay();
            long end=MyDateUtils.timeAtEndOfDay();
            return findRecordsByTime(MyDateUtils.unixTime2WebKitTime(start),MyDateUtils.unixTime2WebKitTime(end));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 按照时间查询用户的历史记录
     * @param start
     * @param end
     * @return
     */
    public List<Record> findRecordsByTime(long start, long end){
        try {
            List<Record> list=new ArrayList<>();
            String sql="select visits.visit_time,urls.url,urls.title from visits, urls on visits.url = urls.id where visits.visit_time between "+start+" and "+end+" order by visit_time";
            System.out.println(sql);
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                list.add(Record.builder().visit(rs.getLong(1)).url(rs.getString(2)).title(rs.getString(3)).build());
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 按照关键字查询记录
     * @param content
     * @return
     */
    public List<Record> findRecordByKey(String content){
        try {
            List<Record> list=new ArrayList<>();
            String sql="select visits.visit_time,urls.url,urls.title" +
                    " from visits, urls on visits.url = urls.id WHERE urls.title like '%"+content+"%' ;";
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                list.add(Record.builder().visit(rs.getLong(1)).url(rs.getString(2)).title(rs.getString(3)).build());
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 计算在该时间段内的同一url的频率
     * @return
     */
    public List<Record> countURLsFrequence(long start,long end){
        try {
            List<Record> list=new ArrayList<>();
            String sql="select url,title, sum(urls.visit_count) as visit_count from urls where id in" +
                    " ( select distinct(url) from visits where visit_time BETWEEN "+start+" and "+end+" ) and title!='' " +
                    " group by title order by visit_count desc";
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                list.add(Record.builder().url(rs.getString(1)).title(rs.getString(2)).num(rs.getInt(3)).build());
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
