
import edu.whpu.dao.History;
import edu.whpu.util.MyDateUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by alan on 17/1/8.
 */
public class Test {


	public static void main(String args[]) throws SQLException{
		History history = new History();
		ResultSet rs = history.findAllTable();
		System.out.println("数据库中的表有：");
		try {
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet re = history.findColum();
		ResultSetMetaData data=re.getMetaData();
		System.out.println("数据库的列数为："+data.getColumnCount());
		System.out.println("数据库中列的名称：");
		try {
			for(int i = 1;i <= data.getColumnCount();i++) {
				System.out.println(data.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@org.junit.Test
	public void test(){

		MyDateUtils.getLastYear().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

		MyDateUtils.getThisYear().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

		MyDateUtils.getLastMonth().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

		MyDateUtils.getThisMonth().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

		MyDateUtils.getLastWeek().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

		MyDateUtils.getYesterDay().forEach((i,j)->{
			System.out.println(i+":"+j);
		});

        int num=MyDateUtils.diff(MyDateUtils.String2Date("2016-06-27"),MyDateUtils.String2Date("2016-11-05"));
        System.out.println("days:"+num);


    }

}
