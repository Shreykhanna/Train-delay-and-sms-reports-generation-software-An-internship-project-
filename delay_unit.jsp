<%-- 
    Document   : delay_unit
    Created on : May 30, 2015, 2:00:04 PM
    Author     : shrey
--%>

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
        ArrayList<String> delay_unit=(ArrayList<String>)session.getAttribute("delay_unit");
        %>                       
    </body>
     <table width="100%" border="1">
         
       <%
        List<String> result=new ArrayList<String>();
          if(delay_unit!=null)
            {%>   
            
                <tr>
                     <td>Station Code</td>
                     <td>Train Number</td>
                     <td>Train Type</td>
                     <td>Arrival Time</td>
                     <td>Source Date</td>
                     <td>Arrival Date</td>
                     <td>ETA</td>
                     <td>SMS Count</td>
                     <td>Departure Date</td>
                     <td>Delay Min</td>
                </tr>
                 
               <% 
               for(int i=0;i<delay_unit.size();i+=10)
                { 
                    result=delay_unit.subList(i,i+10);
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
