package edu.whpu.util;

import edu.whpu.bean.CreateSql;
import edu.whpu.dao.History;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 *
 * 自动同步数据到本地mysql
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
    public void createSql(String tableName,List<CreateSql> list){
        StringBuilder sb=new StringBuilder(" CREATE TABLE "+tableName);
        sb.append("(");
        list.forEach(createSql -> {
            sb.append(createSql.getColName()+" "+createSql.getTypeName()+"("+createSql.getColsize()+")"+createSql.getAddition(createSql.getAddition())+",");
        });
        sb.append(")");
        sb.deleteCharAt(sb.lastIndexOf(","));
        System.out.println(sb.toString());
    }

}
