<%-- 
    Document   : selected_train_sms
    Created on : Jun 2, 2015, 1:17:07 PM
    Author     : shrey
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
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
        ArrayList<String> stn_code=(ArrayList<String>)request.getAttribute("stn_code");
        ArrayList<String> selected_train=(ArrayList<String>)request.getAttribute("selected_train");
        String train_num=(String)request.getAttribute("train_num");
        %>
         <%
       Connection conn=null;
       ResultSet rs_stn_code=null,rs_train_num=null,rs_src_date=null;
       PreparedStatement ps=null;
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       String username="sys as SYSDBA";
       String password="Shreykhanna07";
       String query_train_num="select DISTINCT TRAIN_NUMBER from ISL_DATA order by TRAIN_NUMBER";
       String query_stn_code="select DISTINCT STN_CODE from ISL_DATA order by STN_CODE";
       String query_src_date="select DISTINCT to_char(SRC_DATE,'dd-mm-yy') SRC_DATE from ISL_DATA order by SRC_DATE" ;

       %>    
    <% 
    try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection(url,username,password);
            
        } catch (Exception ex) 
        {
          
           System.out.println(ex.getMessage());       
        }
        ps=conn.prepareStatement(query_train_num);
        rs_train_num=ps.executeQuery();  
        ps=conn.prepareStatement(query_stn_code);
        rs_stn_code=ps.executeQuery();
        ps=conn.prepareStatement(query_src_date);
        rs_src_date=ps.executeQuery(); 
                
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
  
       <form name="myForm" method="GET" action="Db_servlet" >
       <input type="hidden" value="3" name="train_sms"></input>

            <%= "SMS Train "%>
           <% 
            if(stn_code!=null)
           {%>
             <table>
                <tr>
                    <td>
                        <label id="train_number">Train No.</label><select id="train_number" name="train_number" onblur="station()"></input>
                       <option id="train_number">All</option>
                 
                    <% while(rs_train_num.next())
                 { %>
                 
                  <%if(train_num!=null && train_num.trim().equalsIgnoreCase(rs_train_num.getString("TRAIN_NUMBER").trim()))
                                {%>    
                                <option id="train_number" selected="selected"><%= train_num %></option>
                        
                                <%}
                             else
                                {%>
                                    <option id="train_number"><%= rs_train_num.getString("TRAIN_NUMBER") %></option>
                                <%}%>
                 <%}%>
                </select>
                 
                <label id="station_code">Station code</label><select id="station_code" name="station_code" onchange="change()"> 
                  <option id="station_code">All</option>
           
                 <% for(int i=0;i<stn_code.size();i++)
                 { %>
                 <option id="station_code"><%= stn_code.get(i) %></option>
                 <%}%>
                </select>
                
                <label id="source_date_dropdown">Dates</label><select id="source_date_dropdown" name="source_date_dropdown">
                  <option id="source_date_dropdown">All</option>
                 <% while(rs_src_date.next())
                 { %>
                 <option id="source_date_dropdown"><%=rs_src_date.getString("SRC_DATE") %></option>
                <%}%>
                </select>
                <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input type="radio" id="orderby_isl" name="orderby_isl" ><label>ISL</label>
                <input type="radio" id="orderby_train" name="orderby_train" ><label>Train</label>
                <input  type="submit" id="num_train_sms" value="submit" name="num_train_sms"></input>
                </td>
                </tr>
            </table>
                <%} else{%>
                <table>
                <tr>
                    <td>
                        <label id="train_number">Train No.</label><select id="train_number" name="train_number" onblur="station()"></input>
                     <option id="train_number">All</option>
                 
                    <% while(rs_train_num.next())
                 { %>
                 <option id="train_number"><%=rs_train_num.getString("TRAIN_NUMBER")%></option>
                 
                <%}%>
                </select> 
                 
                <label id="ISL">Station code</label><select  id="station_code" name="station_code" onchange="change()"> 
                     <option id="station_code">All</option>
                 
                 <% while(rs_stn_code.next())
                 { %>
                 <option id="station_code"><%=rs_stn_code.getString("STN_CODE")%></option>
                 
                <%}%>
                </select>
                
                <label id="source_date_dropdown">Dates</label><select id="source_date_dropdown" name="source_date_dropdown">
                  <option id="source_date_dropdown">All</option>
                 <% while(rs_src_date.next())
                 { %>
                 <option id="source_date_dropdown"><%=rs_src_date.getString("SRC_DATE") %></option>
                <%}%>
                </select>
                <label>Order by</label><input type="radio" id="orderby_date" name="orderby_date" ><label>Date</label>
                <input type="radio" id="orderby_isl" name="orderby_isl" ><label>ISL</label>
                <input type="radio" id="orderby_train" name="orderby_train" ><label>Train</label>
                <input  type="submit" id="num_train_sms" value="submit" name="num_train_sms"></input>
               
                   </td>
                </tr>
            </table>
                
               <%}rs_src_date.close();
                  rs_stn_code.close();
                  rs_train_num.close(); %>
            
              
   </form>
   </body>

         <table width="100%" border="1">
       <%
        List<String> result=new ArrayList<String>();
        if(selected_train!=null)
         {%>  
         
             <%= "Station Code : "+request.getParameter("station_code")%>
             <%= "Train Number : "+request.getParameter("train_number")%>
             <%= "DATE : "+request.getParameter("source_date_dropdown")%>
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
                     <td>Train Number</td>
                     <td>Station Code</td>
                     <td>Train Type</td>
                     <td>Arrival Time</td>
                     <td>Source Date</td>
                     <td>Arrival Date</td>
                     <td>SMS Count</td>
                     <td>Departure Date</td>
                </tr>
                 
               <% 
               for(int i=0;i<selected_train.size();i+=8)
                { 
                    result=selected_train.subList(i,i+8);
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
                System.out.println("Inside else else");
                }%>
        </table>

</html>
