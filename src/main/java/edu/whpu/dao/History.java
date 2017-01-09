package edu.whpu.dao;

import edu.whpu.config.InitConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by alan on 17/1/6.
 */
public class History {


    private Statement statement;

    public History(){
        this.statement=InitConfig.getStateMent();
    }

    public void countDailyVisits(Date start, Date end){
        try {
            String sql="SELECT visit_day,count(1) AS  count " +
                "FROM (" +
                "   SELECT round(visit_time/1000000/3600/24)*1000000*3600*24 as visit_day from visits)" +
                ")" +
                " WHERE visits.visit_time between "+start+" and "+end+" group by visit_day" +
                " order by visit_day";
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet findAllVisitUrls(){
        try {
            ResultSet resultSet=statement.executeQuery(" select visits.visit_time,urls.url,urls.title" +
                    " from visits, urls on visits.url = urls.id; ");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //TODO @kameryf 找出所有的表和字段

}
