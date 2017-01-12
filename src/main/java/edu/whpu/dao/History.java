package edu.whpu.dao;

import edu.whpu.bean.Record;
import edu.whpu.config.InitConfig;
import edu.whpu.util.MyDateUtils;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by alan on 17/1/6.
 */
public class History {

    private Statement statement;

    public History(){
        this.statement=InitConfig.getStateMent();
    }

    public Statement getStatement() {
        if(statement==null){
            return InitConfig.getStateMent();
        }
        return statement;
    }

    /**
     * 获取用户每天的访问数量
     */
    public List<Record> countDailyVisits(){
        try {
            //TODO 修改sql
            ResultSet rs=statement.executeQuery(" select visits.visit_time,urls.url,urls.title" +
                    " from visits, urls on visits.url = urls.id; ");
            while (rs.next()){
            }
            return null;
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
     * 按照时间查询用户的历史记录
     * @param start
     * @param end
     * @return
     */
    public List<Record> findRecordsByTime(Date start, Date end){
        try {
            List<Record> list=new ArrayList<>();
            ResultSet rs=statement.executeQuery(" select visits.visit_time,urls.url,urls.title" +
                    " from visits, urls on visits.url = urls.id WHERE visits.visit_time BETWEEN "+start+" AND "+end);
            while (rs.next()){
                list.add(Record.builder().visit(rs.getLong(1)).url(rs.getString(2)).title(rs.getString(3)).build());
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
