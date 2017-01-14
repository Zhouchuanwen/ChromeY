import com.alan.Main;
import com.alan.dao.History;
import com.alan.util.MyDateUtils;

import java.util.Date;

/**
 * Created by alan on 17/1/14.
 */
public class HistoryTest {

    @org.junit.Test
    public void getHistoryRange(){
        History history=new History();
        history.getHistoryRange().forEach((i,j)->{
            long a=MyDateUtils.webkitTime2Unix(i);
            long b=MyDateUtils.webkitTime2Unix(j);
            String date1=MyDateUtils.date2String(new Date(a));
            String date2=MyDateUtils.date2String(new Date(b));
            System.out.println(date1+":"+date2);
        });
    }


    @org.junit.Test
    public void findAllRecords(){
        History history=new History();
        history.findAllRecords().forEach((i)->{
            Date d=new Date(MyDateUtils.webkitTime2Unix(i.getVisit()));
            System.out.println(MyDateUtils.date2String(d)+"  >   "+i.getUrl()+"   >"+i.getTitle());
        });
    }


    @org.junit.Test
    public void countDailyVisits(){
        History history=new History();
        history.countDailyVisits().forEach((i)->{
            Date d=new Date(MyDateUtils.webkitTime2Unix(i.getVisit()));
            System.out.println(MyDateUtils.date2String(d)+"  >   "+i.getUrl()+"   >"+i.getTitle());
        });
    }



    @org.junit.Test
    public void findRecordsByTime(){
        History history=new History();
        long s=MyDateUtils.unixTime2WebKitTime(MyDateUtils.timeAtStartOfDay());
        long e=MyDateUtils.unixTime2WebKitTime(MyDateUtils.timeAtEndOfDay());
        history.findRecordsByTime(s,e).forEach((i)->{
            System.out.println(MyDateUtils.webkitTime2Unix(i.getVisit())+"  >   "+i.getUrl()+"   >"+i.getTitle());
        });
    }


    @org.junit.Test
    public void findRecordByKey(){
        History history=new History();
        history.findRecordByKey("微博").forEach((i)->{
            System.out.println(i);
        });
    }



    @org.junit.Test
    public void countURLsFrequence(){
        History history=new History();
        long start=13128838677925955L;
        long end=13128838705971332L;
        history.countURLsFrequence(start,end).forEach((i)->{
            System.out.println(i);
        });
    }



}
