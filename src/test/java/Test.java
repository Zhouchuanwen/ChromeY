import edu.whpu.bean.CreateSql;
import edu.whpu.util.MyDateUtils;
import edu.whpu.util.Sync;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 17/1/8.
 */
public class Test {

	public static void main(String args[]) throws SQLException{
        //meta downloads downloads_url_chains urls visits visit_source keyword_search_terms segments segment_usage
	}

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

}
