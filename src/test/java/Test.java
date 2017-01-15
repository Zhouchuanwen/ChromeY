import com.alan.bean.CreateSql;
import com.alan.util.MyDateUtils;
import com.alan.util.Sync;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alan on 17/1/8.
 */
public class Test {

	@org.junit.Test
	public void testTime(){

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


    @org.junit.Test
    public void test(){
        try {
            Sync sync=new Sync();
            ResultSet rs=sync.findAllTable();
            List<String> tables=new ArrayList<>();
            while (rs.next()){
                tables.add(rs.getString(1));
            }
            tables.forEach(i->{
                List<CreateSql> list=new ArrayList<>();
                try {
                    ResultSet resultSet=sync.findColNameByTable(i);
                    ResultSetMetaData data = resultSet.getMetaData();
                    for(int j=1;j<=data.getColumnCount();j++){
                        CreateSql createSql=CreateSql.builder().colName(data.getColumnName(j)).typeName(data.getColumnTypeName(j))
                                .colsize(data.getColumnType(j)).addition(data.isNullable(j)).build();
                        list.add(createSql);
                    }
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(sync.createSql(i,list));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @org.junit.Test
    public void print() throws InterruptedException {
        Date start=new Date();
        Thread.sleep(2000);
        Date end=new Date("2017/1/13");
        int num=MyDateUtils.minutes(start,end);
        System.out.println(num);
    }


    @org.junit.Test
    public void timeTest() {
        Long t = 13128948409000000L;
        long unix=MyDateUtils.webkitTime2Unix(t);
        System.out.println(unix);
        System.out.println(MyDateUtils.time2String(new Date(unix)));
    }

}
