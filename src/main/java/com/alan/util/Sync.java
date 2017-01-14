package com.alan.util;

import com.alan.bean.CreateSql;
import com.alan.dao.History;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * 数据到本地mysql
 * Created by alan on 17/1/10.
 */
public class Sync {

    private Statement statement;

    public Sync(){
        statement= new History().getStatement();
    }

    public ResultSet findAllTable(){
        try {
            ResultSet rs =statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table';");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ResultSet findColNameByTable(String table){
        try {
            ResultSet rs=statement.executeQuery("SELECT * FROM "+table);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建sql的建表语句
     * @param tableName
     * @param list
     */
    public static String createSql(String tableName,List<CreateSql> list){
        StringBuilder sb=new StringBuilder();
        sb.append(" CREATE TABLE `"+tableName+"`");
        sb.append(" (");
        list.forEach(createSql -> {
            sb.append("`"+createSql.getColName()+"` "+createSql.getTypeName(createSql.getTypeName())+" ("+createSql.getColsize()+") "+createSql.getAddition(createSql.getAddition())+",");
        });
        sb.append(");");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

}
