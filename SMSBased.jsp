<%-- 
    Document   : SMSBased
    Created on : Jun 17, 2015, 12:36:13 AM
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
             <form method="GET" action="Db_servlet">
        <table>
                <tr>
                    <td>
                        <label id="label_2">SMS based reports</label></br></input>
                       
                        <br><label id="label_2_b"> Trains Report</label><input type="submit" id="selected_train" name="selected_train"></input></br>
                        <br><label id="label_2_c">Station Report</label><input type="submit" id="particular_station" name="particular_station"></input></br>
                                               
                    </td>
                </tr>    
        </table>
        </form>
        
        </body>
</html>
