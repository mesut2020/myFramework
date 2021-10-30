package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    // MMMM dd, yyy  --> February 17, 2019
    public static String todaysDate1(){
        String today = new SimpleDateFormat("MMMM dd, yyy").format(new Date());
        return today;
    }
    // MM-dd-yy     --> 02-17-19
    public static String todaysDate2(){
        String today = new SimpleDateFormat("MM-dd-yy").format(new Date());
        return today;
    }
    // dd-MM-yy    --> 17-02-19
    public static String todaysDate3(){
        String today = new SimpleDateFormat("dd-MM-yy").format(new Date());
        return today;
    }
    // yyyy-MM-dd HH.mm.ss   --> 2019-02-17 23.45.11
    public static String todaysDateAndTime(){
        String today = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
        return today;
    }




}
