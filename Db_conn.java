/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shrey
 */
public class Db_conn 
{
       static Connection conn=null;
       static ResultSet rs=null;
       static PreparedStatement ps=null;
   public static String url="jdbc:oracle:thin:@localhost:1521:orcl";
   public static String username="sys as SYSDBA";
   public static String password="Shreykhanna07";
   
 
       public static void conn()
{
     try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection(url,username,password);
            System.out.println("in try catch");
            
        } catch (Exception ex) 
        {
           System.out.println("Inside catch");
           System.out.println(ex.getMessage());       
        } 
}   
 ///////////////////////////DELAY BASED REPORT FUNCTIONS/////////////////////////////////////////////////
   
 
  public static ArrayList<String> selected_trains(String train_num,String ISL,String date,String orderby_date,String orderby_ISL,String orderby_train )
  {
      Db_conn.conn();
      ArrayList<String> details=new ArrayList<String>();
      String query=null;
      if( "on".equals(orderby_date) && orderby_date!=null )
      {
      query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where ARRV_TIME <> to_char(ETA,'HH24:MI') order by SRC_DATE";
      } 
      else if("All".equals(train_num) && "All".equals(ISL) && "All".equals(date) )
        {
         query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where ARRV_TIME <> to_char(ETA,'HH24:MI')";
        }
    else if(!"All".equals(train_num) && "All".equals(ISL) && "All".equals(date))
        {
        query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
        }
    else if(!"All".equals(train_num) && !"All".equals(ISL) && "All".equals(date))
    {
       query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND TRIM(STN_CODE)='"+ISL+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
    }
    else if(!"All".equals(train_num) && !"All".equals(ISL) && !"All".equals(date))
    {
       query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND TRIM(STN_CODE)='"+ISL+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";

    }
    else if("All".equals(train_num) && "All".equals(ISL) && !"All".equals(date))
    {
      query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
 
    }
    else if("All".equals(train_num) && !"All".equals(ISL) && "All".equals(date))
    {
      query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+ISL+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
  
    }
    else if("All".equals(train_num) && !"All".equals(ISL) && !"All".equals(date))
    {
     query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+ISL+"'  AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
  
    }
      else
      {
       query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND TRIM(STN_CODE)='"+ISL+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
 
      }
     System.out.println(query);
      try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
               details.add(rs.getString("STN_CODE"));
               details.add(rs.getString("TRAIN_NUMBER"));
               details.add(rs.getString("TRAIN_TYPE"));
               details.add(rs.getString("ARRV_TIME"));
               details.add(rs.getString("SRC_DATE"));
               details.add(rs.getString("ARRV_DATE"));
               details.add(rs.getString("ETA"));
               details.add(rs.getString("DELAY_MIN"));
            }
           rs.close();
           ps.close();
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
       return details;
      
  }
  
  public static ArrayList<String> particular_stations(String station_code,String train_num,String date,String orderby_date,String orderby_ISL,String orderby_train)
{
    Db_conn.conn();
    ArrayList<String> particular_stations=new ArrayList<String>();
    String query=null;

    if( "on".equals(orderby_date) && orderby_date!=null)
    {
    query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where ARRV_TIME <> to_char(ETA,'HH24:MI') order by SRC_DATE";
    }
    else if("All".equals(station_code) && "All".equals(train_num) && "All".equals(date))
    {
     query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where ARRV_TIME <> to_char(ETA,'HH24:MI')";
    }
    else if(!"All".equals(station_code) && "All".equals(train_num) && "All".equals(date))
    {
    query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy  hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI') ";
    }
    else if(!"All".equals(station_code) && !"All".equals(train_num) && "All".equals(date))
    {
    query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND TRAIN_NUMBER='"+train_num+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
   
    }
    else if(!"All".equals(station_code) && !"All".equals(train_num) && !"All".equals(date))
    {
    query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND TRAIN_NUMBER='"+train_num+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
   
    }
    else if("All".equals(station_code) && "All".equals(train_num) && !"All".equals(date))
    {
        
     query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";  
    }
    else if("All".equals(station_code) && !"All".equals(train_num) && "All".equals(date))
    {
      query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";  

    }
    else if("All".equals(station_code) && !"All".equals(train_num) && !"All".equals(date))
    {
      query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";  

    }
    else
    {
    query="select STN_CODE,TRAIN_NUMBER,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,to_char(ETA,'dd-mm-yy hh24:mi:ss')ETA,DELAY_MIN from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND TRAIN_NUMBER='"+train_num+"'AND SRC_DATE='"+date+"' AND ARRV_TIME <> to_char(ETA,'HH24:MI')";
    }
    
    System.out.println(query);
        try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {  
               particular_stations.add(rs.getString("STN_CODE"));
               particular_stations.add(rs.getString("TRAIN_NUMBER"));
               particular_stations.add(rs.getString("TRAIN_TYPE"));
               particular_stations.add(rs.getString("ARRV_TIME"));
               particular_stations.add(rs.getString("SRC_DATE"));
               particular_stations.add(rs.getString("ARRV_DATE"));
               particular_stations.add(rs.getString("ETA"));
               particular_stations.add(rs.getString("DELAY_MIN"));
               System.out.println(particular_stations);
            }
           rs.close();
           ps.close();
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
return  particular_stations;    
}
public static ArrayList<String> fetch_delay_unit()
{
    Db_conn.conn();
    ArrayList<String> delay_unit=new ArrayList<String>();
    String query="select *from ISL_DATA where ARRV_TIME <> to_char(ETA,'HH24:MI')";

        try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
               {
               delay_unit.add(rs.getString("STN_CODE"));
               delay_unit.add(rs.getString("TRAIN_NUMBER"));
               delay_unit.add(rs.getString("TRAIN_TYPE"));
               delay_unit.add(rs.getString("ARRV_TIME"));
               delay_unit.add(rs.getString("SRC_DATE"));
               delay_unit.add(rs.getString("ARRV_DATE"));
               delay_unit.add(rs.getString("ETA"));
               delay_unit.add(rs.getString("SMS_COUNT"));
               delay_unit.add(rs.getString("DEPT_DATE"));
               delay_unit.add(rs.getString("DELAY_MIN"));
                 }
           rs.close();
           ps.close();
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    return delay_unit;
}
/////////////////////////////SMS BASED REPORTS FUNCTIONS////////////////////////////////////
public static ArrayList<String> selected_train_sms(String train_num,String stn_code,String date,String orderby_date,String orderby_ISL,String orderby_train)
{
 Db_conn.conn();
ArrayList<String> selected_train=new ArrayList<String>();
String query=null;

if("on".equals(orderby_date) && orderby_date!=null)
{
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE from ISL_DATA where SMS_COUNT <> 0 order by SRC_DATE";
}
else if("on".equals(orderby_ISL) && orderby_ISL!=null)
{
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0 order by STN_CODE";
   
}
else if("on".equals(orderby_train) && orderby_train!=null)
{
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0 order by TRAIN_NUMBER";

}
else if("All".equals(train_num) && "All".equals(stn_code) && "All".equals(date))
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0";   
}
else if(!"All".equals(train_num) && "All".equals(stn_code) && "All".equals(date))
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRAIN_NUMBER  = '"+train_num+"' AND SMS_COUNT <> 0";   
}
else if("All".equals(train_num) && !"All".equals(stn_code) && "All".equals(date))
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRIM(STN_CODE)='"+stn_code+"' AND SMS_COUNT <> 0";   
}
else if("All".equals(train_num) && "All".equals(stn_code) && !"All".equals(date))
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND SMS_COUNT <> 0";   
}
else if(!"All".equals(train_num) && !"All".equals(stn_code) && "All".equals(date) )
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0 AND TRIM(STN_CODE)='"+stn_code+"' AND TRAIN_NUMBER='"+train_num+"'";   
}
else if(!"All".equals(train_num) && !"All".equals(stn_code) && !"All".equals(date) )
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0 AND TRIM(STN_CODE)='"+stn_code+"' AND TRAIN_NUMBER='"+train_num+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"'";   
}
else
{
 query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRAIN_NUMBER  = '"+train_num+"' AND TRIM(STN_CODE)='"+stn_code+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND SMS_COUNT <> 0";
}
System.out.println(query);
try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
           while(rs.next())
           {   
               selected_train.add(rs.getString("TRAIN_NUMBER"));
               selected_train.add(rs.getString("STN_CODE"));
               selected_train.add(rs.getString("TRAIN_TYPE"));
               selected_train.add(rs.getString("ARRV_TIME"));
               selected_train.add(rs.getString("SRC_DATE"));
               selected_train.add(rs.getString("ARRV_DATE"));
               selected_train.add(rs.getString("SMS_COUNT"));
               selected_train.add(rs.getString("DEPT_DATE"));
          }
           rs.close();
           ps.close();
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
return selected_train;

}

public static ArrayList<String> particular_station_sms(String station_code,String train_num,String date,String orderby_date,String orderby_ISL,String orderby_train)
{
    Db_conn.conn();

    ArrayList<String> Particular_station=new ArrayList<String>();
    String query=null;
    System.out.println("train_number"+train_num);
    if( "on".equals(orderby_date) && orderby_date!=null)
    {
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE from ISL_DATA where SMS_COUNT <> 0 order by SRC_DATE"; 
    System.out.println("Query inside order by date loop : "+query);
    }
    else if("on".equals(orderby_ISL) && orderby_ISL!=null)
    {
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE from ISL_DATA where SMS_COUNT <> 0 order by STN_CODE"; 
    }
    else if("on".equals(orderby_date) && orderby_train!=null)
    {
    query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE from ISL_DATA where SMS_COUNT <> 0 order by TRAIN_NUMBER"; 
    }
    else if("All".equals(station_code) && "All".equals(train_num) && "All".equals(date))
        {
           query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where SMS_COUNT <> 0";
        }
    else if(!"All".equals(station_code) && "All".equals(train_num) && "All".equals(date))
    {
       query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND SMS_COUNT <> 0";
    }
    else if("All".equals(station_code) && !"All".equals(train_num) && "All".equals(date))
    {
       query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRAIN_NUMBER='"+train_num+"' AND SMS_COUNT <> 0";
    }
    else if("All".equals(station_code) && "All".equals(train_num) && !"All".equals(date))
    {
       query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND SMS_COUNT <> 0";
    }
    
    else if(!"All".equals(station_code) &&!"All".equals(train_num) && "All".equals(date) )
    {
      query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND TRAIN_NUMBER='"+train_num+"' AND SMS_COUNT <> 0";
    } 
    else if(!"All".equals(station_code) && !"All".equals(train_num) && !"All".equals(date))
        {
           query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE  from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND TRAIN_NUMBER='"+train_num+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND SMS_COUNT <> 0";
        }
    else
    {
        query="select TRAIN_NUMBER,STN_CODE,TRAIN_TYPE,ARRV_TIME,to_char(SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(ARRV_DATE,'dd-mm-yy')ARRV_DATE,SMS_COUNT,to_char(DEPT_DATE,'dd-mm-yy')DEPT_DATE from ISL_DATA where TRIM(STN_CODE)='"+station_code+"' AND to_char(SRC_DATE,'dd-mm-yy')='"+date+"' AND SMS_COUNT <> 0";
    }
    System.out.println(query);
    try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
           while(rs.next())
           {   
               
               Particular_station.add(rs.getString("STN_CODE"));
               Particular_station.add(rs.getString("TRAIN_NUMBER"));
               Particular_station.add(rs.getString("TRAIN_TYPE"));
               Particular_station.add(rs.getString("ARRV_TIME"));
               Particular_station.add(rs.getString("SRC_DATE"));
               Particular_station.add(rs.getString("ARRV_DATE"));
               Particular_station.add(rs.getString("SMS_COUNT"));
               Particular_station.add(rs.getString("DEPT_DATE"));
          }
           rs.close();
           ps.close();
           conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }

   return Particular_station;
       
}
/////////////////////////////////////////////////////STATISTICS//////////////////////////////////////////////////////////////
public static ArrayList<String> statistics(String train_num,String resupto_code,String source_date)
{
    Db_conn.conn();
    ArrayList<String> statistics=new ArrayList<String>();
    String query=null;
    System.out.println(train_num+" "+resupto_code+" "+source_date);
/*    if("All".equals(train_num) && "All".equals(resupto_code) && "All".equals(source_date))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA where ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";
     }
    else if(!"All".equals(train_num) && !"All".equals(resupto_code) && !"All".equals(source_date))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE CHART_DATA.train_number='"+train_num+"' AND TRIM(CHART_DATA.RESUPTO_CODE)='"+resupto_code+"' AND to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE order by TRAIN_NUMBER";
    }   
    else if(!"All".equals(train_num) && "All".equals(resupto_code) && "All".equals(source_date))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE CHART_DATA.train_number='"+train_num+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";
    }
    else if(!"All".equals(train_num) && !"All".equals(resupto_code) && "All".equals(source_date))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE CHART_DATA.train_number='"+train_num+"' AND TRIM(CHART_DATA.RESUPTO_CODE)='"+resupto_code+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";
    }    
    else if("All".equals(train_num) && "All".equals(resupto_code) && !"All".equals(source_date))
    {
      query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";  
    }
    else if(!"All".equals(train_num) && !"All".equals(source_date) && "All".equals(resupto_code))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE CHART_DATA.TRAIN_NUMBER='"+train_num+"' AND to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";     
    }
    else if(!"All".equals(resupto_code) && "All".equals(train_num) && "All".equals(source_date))
    {
    query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE TRIM(CHART_DATA.RESUPTO_CODE)='"+resupto_code+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";     
  
    }
    else if(!"All".equals(resupto_code) && "All".equals(train_num) && !"All".equals(source_date))
    {
     query="select CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,SUM(ISL_DATA.SMS_COUNT),to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE,to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')SRC_DATE FROM CHART_DATA,ISL_DATA WHERE TRIM(CHART_DATA.RESUPTO_CODE)='"+resupto_code+"' AND to_char(CHART_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND to_char(ISL_DATA.SRC_DATE,'dd-mm-yy')='"+source_date+"' AND ISL_DATA.SMS_COUNT > 0 group by CHART_DATA.TRAIN_NUMBER,CHART_DATA.RESUPTO_CODE,ISL_DATA.SRC_DATE,CHART_DATA.SRC_DATE ORDER BY TRAIN_NUMBER";     

    }*/
    query="select chart.TRAIN_NUMBER,CHART.RESUPTO_CODE,SUM(isl.SMS_COUNT)SMS_count,to_char(isl.SRC_DATE,'dd-mm-yy') SRC_DATE,"
            + "to_char(chart.SRC_DATE,'dd-mm-yy') SRC_DATE FROM CHART_DATA chart,ISL_DATA isl"
            + " where chart.RESUPTO_CODE=isl.STN_CODE AND chart.TRAIN_NUMBER=isl.TRAIN_NUMBER AND chart.SRC_DATE=isl.SRC_DATE"
            + " AND TRIM(chart.RESUPTO_CODE) like ? AND to_char(chart.SRC_DATE,'dd-mm-yy') like ? AND chart.TRAIN_NUMBER LIKE ?" 
            + " AND isl.SMS_COUNT > 0"
            + " group by chart.TRAIN_NUMBER,chart.RESUPTO_CODE,isl.SRC_DATE,chart.SRC_DATE"
            + " ORDER BY chart.TRAIN_NUMBER";     
System.out.println(resupto_code+source_date+train_num);
    //System.out.println(query);
        try {
            ps=conn.prepareStatement(query);
            ps.setString(1, resupto_code);
            ps.setString(2, source_date);
            ps.setString(3, train_num);
            
            
            System.out.println(query);
            rs=ps.executeQuery();
             while(rs.next())
            {   
                statistics.add(rs.getString("TRAIN_NUMBER"));
                statistics.add(rs.getString("RESUPTO_CODE"));
                statistics.add(rs.getString("SMS_COUNT"));
                statistics.add(rs.getString("SRC_DATE"));
                
            }
            rs.close();
            ps.close();
            conn.close();
           } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statistics;
    
}
///////////////////////////////////STATION CODE AND TRAIN NUMBER GET FUNCTION//////////////////////////////////////////////
public static ArrayList<String> ISL_detail(String train_num)
  {
      Db_conn.conn();
      ArrayList<String> stn_code=new ArrayList<String>();
      String query="select DISTINCT STN_CODE from ISL_DATA where TRAIN_NUMBER = '"+train_num+"' ORDER BY STN_CODE";
        try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                stn_code.add(rs.getString("STN_CODE"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
       return stn_code;
  }
 
 public static ArrayList<String> Train_number(String Station_code)
{
      Db_conn.conn();
      ArrayList<String> train_number=new ArrayList<String>();
      String query="select DISTINCT TRAIN_NUMBER from ISL_DATA where TRIM(STN_CODE) ='"+Station_code+"' ORDER BY TRAIN_NUMBER";
      
        try {
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                train_number.add(rs.getString("TRAIN_NUMBER"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db_conn.class.getName()).log(Level.SEVERE, null, ex);
        }
       return train_number;
  
}


}