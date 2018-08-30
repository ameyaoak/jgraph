package com;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataProvider
 */
public class DataProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DataProvider() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		PrintWriter out = response.getWriter(); 
		StringBuilder json= new StringBuilder();
		String ft,ot,mv,timeFrom,timeTo,hoursFrom,hoursTo,monitor;
		ft=request.getParameter("ft");
		ot=request.getParameter("ot");
		mv=request.getParameter("mv"); 
		timeFrom=request.getParameter("timeFrom"); 
		timeTo=request.getParameter("timeTo");
		hoursFrom=request.getParameter("hoursFrom"); 
		hoursTo=request.getParameter("hoursTo");  
		monitor = request.getParameter("monitor");
		 
		String timeFromArray[]= timeFrom.split("/");
		String timeToArray[]=timeTo.split("/");
		String hoursFromArray[]=hoursFrom.split(":");
		String hoursToArray[] = hoursTo.split(":");
		
		Calendar cal = new GregorianCalendar();  
	    cal.set(cal.YEAR, Integer.parseInt(timeFromArray[2]));
	    cal.set(cal.MONTH, Integer.parseInt(timeFromArray[0])-1);  
	    cal.set(cal.DATE, Integer.parseInt(timeFromArray[1]));
	    cal.set(cal.HOUR_OF_DAY, Integer.parseInt(hoursFromArray[0]));
	    cal.set(cal.MINUTE, Integer.parseInt(hoursFromArray[1]));
	    cal.set(cal.SECOND, Integer.parseInt("00")); 
	    cal.set(cal.MILLISECOND, 0); 
	    
	    Calendar cal2 = new GregorianCalendar();  
	    cal2.set(cal2.YEAR, Integer.parseInt(timeToArray[2]));
	    cal2.set(cal2.MONTH, Integer.parseInt(timeToArray[0])-1); 
	    cal2.set(cal2.DATE, Integer.parseInt(timeToArray[1]));
	    cal2.set(cal2.HOUR_OF_DAY, Integer.parseInt(hoursToArray[0]));
	    cal2.set(cal2.MINUTE, Integer.parseInt(hoursToArray[1]));
	    cal2.set(cal2.SECOND, Integer.parseInt("00")); 
	    cal2.set(cal2.MILLISECOND, 0); 
	     
		 
		long dateFrom = cal.getTimeInMillis();  
		System.out.println(cal.getTime());
		System.out.println(cal2.getTime());
		long dateTo = cal2.getTimeInMillis();  
		 
		if(ft.isEmpty()&&ot.isEmpty()&&mv.isEmpty())
		{
			
		}
		else 
		{ 
			Map listMap = getData(ft, mv, ot,dateFrom,dateTo,monitor);
			
			List<PlottingData> lista= (ArrayList<PlottingData>) listMap.get("ft"); 
			json.append("[");
			for (PlottingData ashol : lista) { 
				json.append("["+ashol.getDate()+", "+ashol.getValue()+"],"); 
			} 
			List<PlottingData> listb= (ArrayList<PlottingData>) listMap.get("mv");
			json=json.deleteCharAt(json.lastIndexOf(","));
			json.append("];");		
			
			json.append("[");
			for (PlottingData ashol : listb) { 
				json.append("["+ashol.getDate()+", "+ashol.getValue()+"],"); 
			} 
			List<PlottingData> listc= (ArrayList<PlottingData>) listMap.get("ot");
			json=json.deleteCharAt(json.lastIndexOf(","));
			json.append("];");		
			json.append("[");
			for (PlottingData ashol : listc) { 
				json.append("["+ashol.getDate()+", "+ashol.getValue()+"],"); 
			} 
			 
			json=json.deleteCharAt(json.lastIndexOf(","));
			json.append("];");		
		 
			 
		}
		
		
		/*json.append("133409520000, 133409540000, 133409560000, 133409570000, 133409580000, 133409590000, 133409600000, 133409610000, 133409620000, 133409630000, 133409640000 "+";"
				+ "7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6"+";"
				+ "-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5"+";"
				+ "-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0"+";"
				+ "3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8");*/
		/*json.append("[{\"date\" : \"Date.UTC(2010, 0, 1,08,11)\",\"val\":\"22.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,21)\",\"val\":\"512.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,31)\",\"val\":\"121.9\"}];");
		json.append("[{\"date\" : \"Date.UTC(2010, 0, 1,08,11)\",\"val\":\"12.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,21)\",\"val\":\"152.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,31)\",\"val\":\"112.9\"}];");
		json.append("[{\"date\" : \"Date.UTC(2010, 0, 1,08,11)\",\"val\":\"212.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,21)\",\"val\":\"521.9\"},{\"date\" : \"Date.UTC(2010, 0, 1,08,31)\",\"val\":\"212.9\"}]");
				*/ 
		StringBuilder json2= new StringBuilder();
		json2.append("[[1367260200000, 800],[1367260500000, 1000],[1367260900000,22]];[[1367260200000, 852],[1367260500000, 850],[1367260900000,90]];[[1367260200000, 755],[1367260500000, 600],[1367260900000,567]]"); 
		
		System.out.println(json);
		 if(json.toString().trim().isEmpty())
		out.print(json2); 
		 else
			 out.print(json); 
		 
		//
		} 
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	}
	
	
	public Map getData(String ft,String mv,String ot, long dateFrom, long dateTo,String monitor) {  
		DBHealper db = new DBHealper();
		Connection connection = db.getConnection();  
		String qury="select time,"+ft+","+mv+","+ot+",monitor from timetempdata.data where time between "+dateFrom+" and "+dateTo+" and monitor = '"+monitor+"';"; 
		List<PlottingData> plotListA= new ArrayList<PlottingData>();
		List<PlottingData> plotListB= new ArrayList<PlottingData>();
		List<PlottingData> plotListC= new ArrayList<PlottingData>();
		Map<String, List<PlottingData>> plotMap = new HashMap<String, List<PlottingData>>();
		try{  
			PreparedStatement  stmat = connection.prepareStatement(qury);  
			System.out.println(stmat);
			ResultSet rs = stmat.executeQuery(); 
			PlottingData pointA,pointB,pointC;  
			while(rs.next()) 
			{
				pointA = new PlottingData(); 
				pointB = new PlottingData(); 
				pointC = new PlottingData(); 
				pointA.setDate(rs.getString(1));
				pointA.setValue(rs.getString(2));
				pointB.setDate(rs.getString(1)); 
				pointB.setValue(rs.getString(3));
				pointC.setDate(rs.getString(1)); 
				pointC.setValue(rs.getString(4));
				plotListA.add(pointA); 
				plotListB.add(pointB);
				plotListC.add(pointC);
			}
			
			 
			plotMap.put("ft", plotListA);
			plotMap.put("mv", plotListB);
			plotMap.put("ot", plotListC);
			
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	finally
	{
		db.closeConnection(connection);

	}
		return plotMap;
		 
		
	}
	  
}
