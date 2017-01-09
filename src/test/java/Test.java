import edu.whpu.config.InitConfig;
import edu.whpu.dao.History;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alan on 17/1/8.
 */
public class Test {


//    @org.junit.Test
//    public void test() throws SQLException {
//        History history=new History();
//        ResultSet rs=history.findAll();
//        while (rs.next()){
//            System.out.println(rs.getRow());
//        }
//    }
	public static void main(String args[]){
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("os.name"));
	}
}