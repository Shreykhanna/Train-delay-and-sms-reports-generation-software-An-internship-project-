<%-- 
    Document   : Home_page
    Created on : May 29, 2015, 7:24:38 PM
    Author     : shrey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link src="css.css" type="text/css"></link>
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            
            function Display(submit)
            {
                if(submit.value=="Reports")
                {       
                        document.getElementById("label_1").hidden=false;
                        document.getElementById("delay_based_reports").hidden=false;
                        document.getElementById("label_2").hidden=false;
                        document.getElementById("sms based reports").hidden=false;
                    }
            }
            
            
            
        </script>
        <script>
            
            function options_func(radio)
            {
                
                if(radio.value=="delay based reports")
                    {
                        document.getElementById("label_1_a").hidden=false;
                        document.getElementById("all_trains").hidden=false;
                        document.getElementById("label_1_b").hidden=false;
                        document.getElementById("particular_stations").hidden=false;
                        document.getElementById("label_1_c").hidden=false;
                        document.getElementById("all_stations").hidden=false;
                        document.getElementById("label_1_d").hidden=false;
                        document.getElementById("selected_trains").hidden=false;
                        document.getElementById("label_1_e").hidden=false;
                        document.getElementById("delay_units").hidden=false
                    }
                else if(radio.value=="SMS based reports")
                {       document.getElementById("label_2_a").hidden=false;
                        document.getElementById("all_train").hidden=false;
                        document.getElementById("label_2_b").hidden=false;
                        document.getElementById("selected_train").hidden=false;
                        document.getElementById("label_2_c").hidden=false;
                        document.getElementById("particular_station").hidden=false;
                        document.getElementById("all_station").hidden=false;
                        document.getElementById("label_2_d").hidden=false;
                        document.getElementById("delay_unit").hidden=false
                        document.getElementById("label_2_e").hidden=false;
                        document.getElementById("first_sms").hidden=false;
                        document.getElementById("label_2_f").hidden=false;
                        document.getElementById("second_sms").hidden=false;
                        document.getElementById("label_2_g").hidden=false;
                        document.getElementById("label_2_h").hidden=false;
                        document.getElementById("any_sms").hidden=false;
                        
                     
                    }
            }
            
         </script>
        
        <form method="GET" action="Db_servlet">
            <table>   
                <tr>
                    <td>
                        <input type="radio" value="Reports" name="report" size="15" max="20" onclick="Display(this);">Reports</input>   
                    </td>
                </tr>
                <tr>
                    <td>
                        <br><input type="radio" value="delay based reports" name="delay based report" hidden="true" id="delay_based_reports" onclick="options_func(this);" size="15" max="20"><label id="label_1" hidden="true">Delay based reports</label></br></input> 
                        <br><label id="label_1_a" hidden="true">All Trains</label><input type="submit" hidden="true" id="all_trains" name="all_trains"></input></br>
                        <br><label id="label_1_b" hidden="true">Particular Station</label><input type="submit" hidden="true" id="particular_stations" name="particular_stations"></input></br>
                        <br><label id="label_1_c" hidden="true">All Stations</label><input type="submit" hidden="true" id="all_stations" name="all_stations"></input></br>
                        <br><label id="label_1_d" hidden="true">Selected Trains</label><input type="submit" hidden="true" id="selected_trains" name="selected_trains"></input></br>
                        <br><label id="label_1_e" hidden="true">Delay units</label><input type="submit" hidden="true" id="delay_units" name="delay_units"></input></br>
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        <br><input type="radio" value="SMS based reports" id="sms based reports" onclick="options_func(this);" hidden="true" size="15" max="20"><label id="label_2" hidden="true">SMS based reports</label></br></input>
                        <br><label id="label_2_a" hidden="true">All trains</label><input type="submit" hidden="true" id="all_train" name="all_train"></input></br>
                        <br><label id="label_2_b" hidden="true">Selected Trains</label><input type="submit" hidden="true" id="selected_train" name="selected_train"></input></br>
                        <br><label id="label_2_c" hidden="true">Particular Station</label><input type="submit" hidden="true" id="particular_station" name="particular_station"></input></br>
                        <br><label id="label_2_d" hidden="true">All Station</label><input type="submit" hidden="true" id="all_station" name="all_station"></input></br>
                        <br><label id="label_2_e" hidden="true">Delay unit</label><input type="submit" hidden="true" id="delay_unit" name="delay_unit"></input></br>
                        <br><label id="label_2_f" hidden="true">1st Sms</label><input type="submit" hidden="true" id="first_sms" name="first_sms"></input></br>
                        <br><label id="label_2_g" hidden="true">2nd Sms</label><input type="submit" hidden="true" id="second_sms" name="second_sms"></input></br>
                        <br><label id="label_2_h" hidden="true">Any Sms</label><input type="submit" hidden="true" id="any_sms" name="any_sms"></input></br>
                       
                    </td>
                </tr>
            </table>
        </form>
    </body>
   
</html>
