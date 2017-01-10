import edu.whpu.config.InitConfig;
import edu.whpu.dao.History;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	//@kameryf 测试History库中的表及列
	public static void main(String args[]) throws SQLException{
//		System.out.println(System.getProperty("user.home"));
//		System.out.println(System.getProperty("os.name"));
		History history = new History();
		ResultSet rs = history.findAllTable();
		System.out.println("数据库中的表有：");
		try { 
		while(rs.next()){
			System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet re = history.findColum();
		ResultSetMetaData data=re.getMetaData();
		System.out.println("数据库的列数为："+data.getColumnCount());
		System.out.println("数据库中列的名称：");
		try {
			
			for(int i = 1;i <= data.getColumnCount();i++){
				System.out.println(data.getColumnName(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
}