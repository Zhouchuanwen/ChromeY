package edu.whpu.dao;

import edu.whpu.bean.Record;
import edu.whpu.config.InitConfig;
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

    /**
     * 获取
     */
    @Test
    public void countDailyVisits(){
        try {
            String sql="SELECT visit_day,count(1) AS  count FROM ( SELECT round(visit_time/1000000/3600/24)*1000000*3600*24 as visit_day from visits) WHERE visits.visit_time between "+1+" and "+10+" group by visit_day" +
                " order by visit_day";
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1)+rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *  获取用户所有的历史记录
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

    //TODO @kameryf 找出所有的表和字段
    public ResultSet  findAllTable(){
    	try {
    		ResultSet rs =statement.executeQuery("SELECT name FROM sqlite_master "
					+ "WHERE type='table';");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

    public ResultSet findColum(){
    	try {
    		String sql = "select * from sqlite_master where type = 'table';";
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
