<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="org.slf4j.ext.EventData"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.DriverManager"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : selected_trains
    Created on : May 30, 2015, 1:57:11 PM
    Author     : shrey
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="selected_trains.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
             ArrayList<String> stn_code=(ArrayList<String>)request.getAttribute("stn_code");
             ArrayList<String> details=(ArrayList<String>)request.getAttribute("details");
             String train_num=(String)request.getAttribute("train_num");
        %>
        
       <%
       Connection conn=null;
       ResultSet rs_stn_code=null,rs_train_number=null,rs_src_date=null;
       PreparedStatement ps=null;
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       String username="sys as SYSDBA";
       String password="Shreykhanna07";
       String query_stn_code="select DISTINCT STN_CODE from ISL_DATA order by STN_CODE";
       String query_src_date="select DISTINCT to_char(SRC_DATE,'dd-mm-yy') SRC_DATE from ISL_DATA order by SRC_DATE" ;
       String query_train_number="select DISTINCT TRAIN_NUMBER from ISL_DATA order by TRAIN_NUMBER";
       
       %>    
    <%
    try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection(url,username,password);
        } catch (Exception ex) 
        {
         
              System.out.println(ex.getMessage());       
        }
        
        ps=conn.prepareStatement(query_stn_code);
        rs_stn_code=ps.executeQuery();
        ps=conn.prepareStatement(query_src_date);
        rs_src_date=ps.executeQuery();
        ps=conn.prepareStatement(query_train_number);
        rs_train_number=ps.executeQuery();
        
      
   %>
   <script>
       
       function train()
       {    
           
                if(document.myForm.onsubmit && !document.myForm.onsubmit())
                    {
                        return;
                    }
                    document.myForm.submit();       
       }
   </script>
        
        <form name="myForm" method="GET" action="Db_servlet" >
        <input type="hidden" value="2" name="delay_train"></input>
        <%= "Delay Train"%>

            <% if(stn_code!=null)
             {%>
            <table>
                <tr>
                    <td>
                        <label id="title">Train number</label><select  id="train_number" name="train_number" onblur="train()" >
                        <option id="train_number">All</option>

                    <% while(rs_train_number.next())
                    {%>                       
                   <%if(train_num!=null && train_num.trim().equalsIgnoreCase(rs_train_number.getString("TRAIN_NUMBER").trim()))
                                {%>    
                                <option id="train_number" selected="selected"><%= train_num %></option>
                        
                                <%}
                             else
                                {%>
                                    <option id="train_number"><%= rs_train_number.getString("TRAIN_NUMBER") %></option>
                                <%}%>
                 <%}%>
                </select>
                
                <label id="ISL">ISL</label>
                <select  id="station_code" name="station_code">
                   <option id="station_code">All</option>

                  <% for(int i=0;i<stn_code.size();i++)
                    {%>                       
                   {%>                       
                   <option id="station_code"><%= stn_code.get(i) %></option>
                 <%}%>
                </select>
                 
                   <label id="Date">Date</label><select  id="source_date_dropdown" name="source_date_dropdown"> 
                   <option id="source_date_dropdown">All</option>

                 <% while(rs_src_date.next())
                 {%>
                 <option id="source_date_dropdown"><%= rs_src_date.getString("SRC_DATE") %></option>
               <%}%>
                </select>
               <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input  type="submit" id="num_train" value="submit"  name="num_train"></input>
               </td>
                </tr>
            </table>
                <%}
                else{%>
                <table>
                <tr>
                    <td>
                        <label id="title">Train number</label><select  id="train_number" name="train_number" onblur="train()" >
                 <option id="train_number">All</option>

                    <% while(rs_train_number.next())
                   {%>                       
                   <option id="train_number"><%=rs_train_number.getString("TRAIN_NUMBER")%></option>
                 <%}%>
                </select>
                
                <label id="ISL">ISL</label>
                <select  id="station_code" name="station_code">
                <option id="station_code">All</option>
                <% while(rs_stn_code.next())
                   {%>                       
                   <option id="station_code"><%=rs_stn_code.getString("STN_CODE")%></option>
                 <%}%>
                </select>
                 
                   <label id="Date">Date</label><select  id="source_date_dropdown" name="source_date_dropdown"> 
                   <option id="source_date_dropdown">All</option>

                 <% while(rs_src_date.next())
                 {%>
                 <option id="source_date_dropdown"><%= rs_src_date.getString("SRC_DATE") %></option>
               <%}%>
                </select>
               <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input  type="submit" id="num_train" value="submit"  name="num_train"></input>
                </td>
                </tr>
            </table>
            
                <%}        rs_src_date.close();
                           rs_stn_code.close();
                           rs_train_number.close();%>
            
           </form>

    </body>
     <table width="100%" border="1">
      <%
        List<String> result=new ArrayList<String>();
           if(details!=null)
            {%>   
             <%= "Selected Train Delay Report" %>
            
             <%= "Station Code : "+request.getParameter("station_code")%>
             <%= "Train Number : "+request.getParameter("train_number")%>
             <%= "Date : "+request.getParameter("source_date_dropdown")%>
             
             <%if(request.getParameter("orderby_date")!=null)
             {%>
                <%= "Order by Date" %>
             <%}%>     
         
                
                <tr>
                     <td>Station Code</td>
                     <td>Train Number</td>
                     <td>Train Type</td>
                     <td>Arrival Time</td>
                     <td>Source Date</td>
                     <td>Arrival Date</td>
                     <td>ETA</td>
                     <td>DELAY MIN</td>
                </tr>
                 
               <% 
               for(int i=0;i<details.size();i+=8)
                { 
                    result=details.subList(i,i+8);
                 %>
                
                 <tr>
                     <%
                    for(int j=0;j<result.size();j++)
                   {%>
                 
                   <td>
                     <%=result.get(j)%> 
                   </td>
                 
                  
                <%}%>
                 </tr>
          <%}%>
               
           <% }else
               {
                System.out.println("Value of selected_trains attribute"+request.getAttribute("selected_trains"));
                System.out.println("Inside else");
                }%>
        </table>
    
    </html>
