<%-- 
    Document   : Statistics
    Created on : Jun 16, 2015, 7:20:56 PM
    Author     : shrey
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
               
        <%  
            ArrayList<String> statistics=(ArrayList<String>)request.getAttribute("statistics");
        %>
        <%
       Connection conn=null;
       ResultSet rs_res_upto=null,rs_train_num=null,rs_src_date=null;
       PreparedStatement ps=null;
       String url="jdbc:oracle:thin:@localhost:1521:orcl";
       String username="sys as SYSDBA";
       String password="Shreykhanna07";
       String query_res_upto="select DISTINCT RESUPTO_CODE from CHART_DATA order by RESUPTO_CODE";
       String query_train_num="select DISTINCT TRAIN_NUMBER from CHART_DATA order by TRAIN_NUMBER";
       String query_src_date="select DISTINCT to_char(SRC_DATE,'dd-mm-yy') SRC_DATE from ISL_DATA order by SRC_DATE";
       %>    
    <% 
    try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection(url,username,password);
            
        } catch (Exception ex) 
        {
          
           System.out.println(ex.getMessage());       
        }
        
        ps=conn.prepareStatement(query_res_upto);
        rs_res_upto=ps.executeQuery();
        ps=conn.prepareStatement(query_train_num);
        rs_train_num=ps.executeQuery();
        ps=conn.prepareStatement(query_src_date);
        rs_src_date=ps.executeQuery();
        
        
        %>
        <form method="GET" action="Db_servlet">
            <%= "Statistics "%>
            <table>
                <tr>
                    <td>
                        <label id="train_num">Train No.</label><select id="train_number" name="train_number" onchange="change()">
                            <option id="train_num" value="%">All</option>

                      <% while(rs_train_num.next())
                 {%>
                 <option id="stn_code" value='<%=rs_train_num.getString("TRAIN_NUMBER")%>'><%=rs_train_num.getString("TRAIN_NUMBER")%></option>
                 <%}%>
                 </select>
                     <label id="ISL">ISL</label><select  id="RESUPTO_dropdown" name="RESUPTO_dropdown" onchange="change()"> 
                    <option id="stn_code" value="%">All</option>
                  <% while(rs_res_upto.next())
                 { %>
                 <option id="stn_code" value='<%=rs_res_upto.getString("RESUPTO_CODE")%>'><%=rs_res_upto.getString("RESUPTO_CODE")%></option>
                 
                <%}%>
                </select
                </select>
                 <label id="source_date">Source Date</label><select  id="source_date_dropdown" name="source_date_dropdown" onchange="change()"> 
                 <option id=source_date" value="%">All</option>
                     <% while(rs_src_date.next())
                 { %>
                 <option id=source_date" value='<%=rs_src_date.getString("SRC_DATE")%>'><%=rs_src_date.getString("SRC_DATE")%></option>
                 
                <%}        rs_res_upto.close();
                           rs_train_num.close();
                           rs_src_date.close();
                    %>
                </select>
                <input type="submit" value="submit" name="statistics_submit"></input>
                 </td>
            </tr>
         </table>
     </form>
  </body>
  <table width="100%" border="1" >
    <%
    List result=new ArrayList();
        if(statistics!=null)
      {%>
   <%if("%".equals(request.getParameter("train_number")) && "%".equals(request.getParameter("RESUPTO_dropdown")) && "%".equals(request.getParameter("source_date_dropdown")))
    { %>
        
        <%= "Train Number : All"%>
        <%= "Station Code : All " %>
        <%= "Source Date  : All " %>
        
  <%}else{%> 
  
        <%= "Train Number : "+request.getParameter("train_number") %>
        <%= "Station Code : "+request.getParameter("RESUPTO_dropdown") %>
        <%= "Source Date  : "+request.getParameter("source_date_dropdown") %>
        <%}%>
        <tr>
            <td> Train Number</td>
            <td> Station Code</td>
            <td> SMS Count</td>
            <td> Source Date</td>
            </tr>  
        <% for(int i=0;i<statistics.size();i+=4)
        {
            result=statistics.subList(i,i+4);
           %>
           <tr>
           <% for(int j=0;j<result.size();j++)
           {%>
           <td>
               <%= result.get(j)%>
           </td>   
           
           <%}%>
         
      <%}%>
         </tr>
        
        <%}else
         {
            System.out.println("Inside else");
        }%>
  </table>
    
</html>
