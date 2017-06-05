<%-- 
    Document   : DelayBased
    Created on : Jun 17, 2015, 12:28:37 AM
    Author     : shrey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
     </head>
      <body>
     <%--<img src="Center.jpg" alt="Image not available" style="width:1100px;height:600px;" align="right">--%>
        <form method="GET" action="Db_servlet" >
               <table>   
                <tr>
                    <td>
                        <label id="label_1" >Delay based reports</label></br></input> 
                       
                        <br><label id="label_1_b" >Station Delay </label><input type="submit" id="particular_stations" name="particular_stations"></input></br>
                        <br><label id="label_1_d" >Trains Delay</label><input type="submit"  id="selected_trains" name="selected_trains"></input></br>
                        <br><label id="label_1_e" >Delay units</label><input type="submit" id="delay_units" name="delay_units"></input></br>
                        
                    </td>
                </tr>
        </table>
                          
    </body>
</html>
