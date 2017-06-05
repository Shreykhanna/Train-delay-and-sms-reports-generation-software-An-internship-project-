<%-- 
    Document   : delay_particularstn
    Created on : Jun 2, 2015, 12:08:00 AM
    Author     : shrey
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%      
               ArrayList<String> train_number=(ArrayList<String>)request.getAttribute("train_number");
               ArrayList<String> particular_stations=(ArrayList<String>)request.getAttribute("particular_stations");
               String station_code=(String)request.getAttribute("station_code");
        %>
       
       <%
       Connection conn=null;
       ResultSet rs_train_num=null,rs_stn_code=null,rs_date=null;
       PreparedStatement ps=null;
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       String username="sys as SYSDBA";
       String password="Shreykhanna07";
       String query_date="select DISTINCT to_char(SRC_DATE,'dd-mm-yy')SRC_DATE from ISL_DATA ORDER BY SRC_DATE";
       String query_stn_code="select DISTINCT STN_CODE from ISL_DATA ORDER BY STN_CODE";
       String query_train_num="select DISTINCT TRAIN_NUMBER from ISL_DATA ORDER BY TRAIN_NUMBER ";
        %>
        
        <%
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection(url,username,password);
            
            
        } catch (Exception ex) 
        {
           System.out.println("Inside catch");
           System.out.println(ex.getMessage());       
        }
        
        ps=conn.prepareStatement(query_stn_code);
        rs_stn_code=ps.executeQuery();
        ps=conn.prepareStatement(query_train_num); 
        rs_train_num=ps.executeQuery();
        ps=conn.prepareStatement(query_date);
        rs_date=ps.executeQuery();
        
        %>
        <script>
       
       function station()
       {    
           
                if(document.myForm.onsubmit && !document.myForm.onsubmit())
                {   
                  return;
                   
                }
                    document.myForm.submit();       
       }
   </script>
           
   <form name="myForm" method="GET" action="Db_servlet">
   <input type="hidden" value="1" name="delay_stn"></input>
             <%= "Delay Station "%>
               <%   
               if(train_number!=null)
                {%>
             <table>
                <tr>
                    <td>
                        
                        <label id="title">Station code</label><select id="station_code" name="station_code" onblur="station()">
                        <option id="station_code">All</option>

                       <% while(rs_stn_code.next())
                        {%>
                       <%if(station_code!=null && station_code.trim().equalsIgnoreCase(rs_stn_code.getString("STN_CODE").trim()))
                           
                            { %>    
                                <option id="station_code" selected="selected"><%= station_code %></option>
                        
                                <%}
                             else
                                {%>
                                    <option id="station_code"><%= rs_stn_code.getString("STN_CODE") %></option>
                                <%}%>
                        
                     <%}%>
                        </select>
                    
                  <label id="train_no">Train no.</label><select id="train_number" name="train_number" onchange="change()">
                    <option id="train_number">All</option>

                     <% for(int i=0;i<train_number.size();i++)
                         {%>
                    
                            <option id="train_number"><%= train_number.get(i) %></option>
                    
                        <%}%>
                 </select>
               
                <label id="dates">Dates</label><select id="source_date_dropdown" name="source_date_dropdown">
                   <option id="source_date_dropdown">All</option>
                    <% while(rs_date.next())
                        {%>
                    
                            <option id="source_date_dropdown"><%= rs_date.getString("SRC_DATE") %></option>
                    
                        <%}%>
                </select>
                
                
                <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                 <input  type="submit" id="code_station" value="submit"  name="code_station"></input>
                 </td>
                </tr>
            </table>
                <%}else{%>
                
                <table>
                <tr>
                    <td>
                        <label id="title">Station code</label><select id="station_code" name="station_code" onblur="station()">
               <option id="station_code">All</option>
                    <% while(rs_stn_code.next())
                        {%>
                    
                            <option id="station_code"><%= rs_stn_code.getString("STN_CODE")%></option>
                    
                        <%}%>
                </select>
                    
                  <label id="train_no">Train no.</label><select id="train_number" name="train_number" onchange="change()">
                       <option id="train_number">All</option>
                        <% while(rs_train_num.next())
                            {%>
                    
                                 <option id="train_number"><%= rs_train_num.getString("TRAIN_NUMBER") %></option>
                    
                            <%}%>
                </select>
               
                <label id="dates">Dates</label><select id="source_date_dropdown" name="source_date_dropdown">
                     <option id="source_date_dropdown">All</option>
                <% while(rs_date.next())
                        {%>
                    
                            <option id="source_date_dropdown"><%= rs_date.getString("SRC_DATE") %></option>
                    
                       <%}%>
                </select>
                
                 <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                 <input  type="submit" id="code_station" value="submit"  name="code_station"></input>
                 </td>
                </tr>
            </table>
                              
                <%}        rs_date.close();
                           rs_stn_code.close();
                           rs_train_num.close();    %>
                
            
               </form>

    </body>
    <table width="100%" border="1">
       <%
        List<String> result=new ArrayList<String>();
          if(particular_stations!=null)
            {%>   
             <%= "Station Code : "+request.getParameter("station_code")%>
             <%= "Train Number : "+request.getParameter("train_number")%>
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
                    <td>Delay Min</td>
                </tr>
                 
               <% 
               for(int i=0;i<particular_stations.size();i+=8)
                { 
                    result=particular_stations.subList(i,i+8);
                 %>
                
                 <tr>
                     
                     <%
                    for(int j=0;j<result.size();j++)
                   {%>
                
                   <td>
                     <%=result.get(j)%> 
                   </td>
                 
                  
                   <%}session.removeAttribute(station_code+train_number); %>
                 </tr>
          <%}%>
               
           <% }else
               {
                System.out.println("Inside else");
               }%>
        </table>
            

   
</html>
