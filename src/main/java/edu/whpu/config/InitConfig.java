package edu.whpu.config;


import com.google.common.base.Strings;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alan on 17/1/8.
 */
public class InitConfig {

    public static String getOsInfo(){
    	String uri;
        switch (System.getProperty("os.name")){
            case "Mac OS X":
                uri="/Library/Application Support/Google/Chrome/Default/History";
                break;
            case "Mac OS":
                uri="/Library/Application Support/Google/Chrome/Default/History";
                break;
            case "Linux":
                uri="/.config/google-chrome/Default/History";
                break;
            case "Windows":
                uri="/AppData/Local/Google/Chrome/User Data/Default/History";
                break;
            default:
                return null;
        }
        return System.getProperty("user.home")+uri;
    }

    public static boolean findTarget(String uri){
        if(Strings.isNullOrEmpty(uri))
            return false;
        File file=new File(uri);
        if(file.exists())
            return file.canRead();
        return false;
    }


    public static Statement initDataBase(String local) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+local);
            return conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Statement getStateMent(){
        try {
        	String uri =getOsInfo();
            if(findTarget(uri)) {
            	String local=System.getProperty("user.dir") + "/src/main/resources/History";
                File db = new File(local);
                Files.copy(new File(uri), db);
                return initDataBase(local);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
