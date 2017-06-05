<%-- 
    Document   : particular_sation_sms
    Created on : Jun 2, 2015, 4:44:07 PM
    Author     : shrey
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
       <%     ArrayList<String> train_number=(ArrayList<String>)request.getAttribute("train_number");
              ArrayList<String> Particular_station=(ArrayList<String>)request.getAttribute("Particular_station");
              String station_code=(String)request.getAttribute("station_code");
        %>
             
         <%
       Connection conn=null;
       ResultSet rs_stn_code=null,rs_train_num=null,rs_src_date=null;
       PreparedStatement ps=null;
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       String username="sys as SYSDBA";
       String password="Shreykhanna07";
       String query_stn_code="select DISTINCT STN_CODE from ISL_DATA order by STN_CODE";
       String query_train_num="select DISTINCT TRAIN_NUMBER from ISL_DATA order by TRAIN_NUMBER";
       String query_src_date="select DISTINCT to_char(SRC_DATE,'dd-mm-yy')SRC_DATE from ISL_DATA order by SRC_DATE";
       
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
        
        ps=conn.prepareStatement(query_train_num);
        rs_train_num=ps.executeQuery();
        ps=conn.prepareStatement(query_src_date);
        rs_src_date=ps.executeQuery();
        
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
        <input type="hidden" value="4" name="particular_sms"></input>
        <%= "SMS Station "%>

            <% if(train_number!=null){%>
             <table>
                <tr>
                    <td>
                        <label id="station_code">Station Code</label><select id="station_code" name="station_code" onblur="train()" ></input>
                    <option id="station_code">All</option>
      
                 <% while(rs_stn_code.next())
                 {%>
                 <%if(station_code!=null && station_code.trim().equalsIgnoreCase(rs_stn_code.getString("STN_CODE").trim()))
                                {%>    
                                <option id="station_code" selected="selected"><%= station_code  %></option>
                        
                                <%}
                             else
                                {%>
                                    <option id="station_code"><%= rs_stn_code.getString("STN_CODE") %></option>
                                <%}%>
                      
                 <%}%>
                 </select> 

                 
                 <label id="train_number">Train no.</label><select id="train_number" name="train_number" >
                 <option id="train_number">All</option>

                 <% for(int i=0;i<train_number.size();i++)
                 {%>
                 <option id="train_number"><%= train_number.get(i) %></option>
                 <%}%>
                </select>
                
                <label id="dates">Dates</label><select id="source_date_dropdown" name="source_date_dropdown"></input>
                    <option id="source_date_dropdown">All</option>
                 <% while(rs_src_date.next())
                 {%>
                 <option id="source_date_dropdown"><%=rs_src_date.getString("SRC_DATE")%></option>
                 <%}%>
                </select>

                    
                <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input type="radio" id="orderby_isl" name="orderby_isl" ><label>ISL</label>
                <input type="radio" id="orderby_train" name="orderby_train" ><label>Train</label>
                
                <input type="submit" id="particular_station_submit" value="submit" name="particular_station_submit"></input>
                </td>
                </tr>
            </table>
                
                <%}else{%>
                <table>
                <tr>
                    <td>
                        <label id="station_code">Station Code</label><select id="station_code" name="station_code" onblur="train()" ></input>
                 <option id="station_code">All</option>
                 
                 <% while(rs_stn_code.next())
                 {%>
                 <option id="station_code"><%=rs_stn_code.getString("STN_CODE")%></option>  <%}%>
                </select>

                 
                 <label id="train_number">Train no.</label><select id="train_number" name="train_number" onchange="change()">
                 <option id="train_number">All</option>
                 
                     <% while(rs_train_num.next())
                 {%>
                 <option id="train_number"><%=rs_train_num.getString("TRAIN_NUMBER")%></option>
                 <%}%>
                </select>
                <label id="dates">Dates</label><select id="source_date_dropdown" name="source_date_dropdown"></input>
                    <option id="source_date_dropdown">All</option>
                    
                 <% while(rs_src_date.next())
                 {%>
                 <option id="source_date_dropdown"><%=rs_src_date.getString("SRC_DATE")%></option>
                 <%}%>
                </select>

                    
                <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input type="radio" id="orderby_isl" name="orderby_isl" ><label>ISL</label>
                <input type="radio" id="orderby_train" name="orderby_train" ><label>Train</label>
                
                <input type="submit" id="particular_station_submit" value="submit" name="particular_station_submit"></input>
                </td>
                </tr>
            </table>
                
                
                <%}%>
            
               </form>
       </body>
       
       <table width="100%" border="1">
        <%
        List<String> result=new ArrayList<String>();
           if(Particular_station!=null)
            {%>   
             <%= "Particular Station SMS Report" %>
            
             <%= "Station Code : "+request.getParameter("station_code")%>
             <%= "Train Number : "+request.getParameter("train_number")%>
             <%= "Date : "+request.getParameter("source_date_dropdown")%>
             <%if(request.getParameter("orderby_date")!=null)
             {%>
                <%= "Order by Date" %>
             <%}else if(request.getParameter("orderby_isl")!=null)
             {%>
             <%=  "Order by ISL"%>
             <%}else if(request.getParameter("orderby_train")!=null)
                {%>
                <%= "Order by Train" %>
                <%}%>
                <tr>
                     <td>Station Code</td>
                     <td>Train Number</td>
                     <td>Train Type</td>
                     <td>Arrival Time</td>
                     <td>Source Date</td>
                     <td>Arrival Date</td>
                     <td>SMS Count</td>
                     <td>Departure Date</td>
                </tr>
                 
               <% 
               for(int i=0;i<Particular_station.size();i+=8)
                { 
                    result=Particular_station.subList(i,i+8);
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
                System.out.println("Inside else");
                }%>
        </table>

    
</html>
