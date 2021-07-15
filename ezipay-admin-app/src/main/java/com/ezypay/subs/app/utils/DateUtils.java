package com.ezypay.subs.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static Date createDateFromDateString(String dateString){
        Date date = null;
        if(null != dateString){
            try{
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
    
    public static String getddMMyyyyDateString(Long longDate){
        Date date = null;
        String result = null;
        SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
        if(null != longDate){
            try{
            	Date dd = new Date(longDate);
            	result = ddMMyyyy.format(dd);
            }catch(Exception pe){
                date = new Date();
                result = ddMMyyyy.format(date);
            }
        }else{
            date = new Date();
            result = ddMMyyyy.format(date);
        }
        
        return result;
    }
    
    public static Long getLongDate(String dateString) {
		// TODO Auto-generated method stub
		//String dateString = "2021-07-01";

		SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
		long milliseconds = 0;
		try {
		    Date d = ddMMyyyy.parse(dateString);
		    //System.out.println(d);
		    milliseconds = d.getTime();
		    //System.out.println(d.getTime());
		    //System.out.println(milliseconds);
		    
		    //Date dd = new Date(Long.valueOf(milliseconds));
		    //System.out.println(dd);
		    //System.out.println(ddMMyyyy.format(dd));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return  Long.valueOf(milliseconds);
    }
}
