<!DOCTYPE html> 
<html> 
<head> <title>CRIS</title>
 <iframe name="frame2" src="Center.jpg" id="frame2" frameborder=0 align="right" height="800" width="1100" ></iframe>

<style>
.navigation ul 
{ 
list-style-type: none;
} 
.navigation ul a 
{
 text-decoration: none; 
} 
.navigation ul li 
{
 border : 2px solid blue; width:120px; padding:10px; background: lightblue; 
} 
.navigation ul li:hover 
{
 background: Bisque; 
} 
.navigation li ul 
{ 
display:none; position:absolute; 
} 
.navigation li:hover ul 
{ 
display:block; margin-top:-29px; margin-left:90px; 
} 
.navigation li ul li 
{ 
clear:both; display : block; border : 2px solid blue; padding:10px; 
}
 

</style> 
</head>
<body> 
   <%-- <img src="Center.jpg" alt="Image not available" style="width:900px;height:600px;" align="right">--%>
    <div class="navigation">
 
   <ul> 
<li>
<a href=".">Home</a>
</li> 
<li>
<a href=".">About</a> 

</li>
 <li>
 <a href=".">Career</a>
 <ul>
 <li>
<a href="Frame.jsp">Clerical</a>
</li> 
<li>
<a href=".">IT Specialist</a>
</li> 
<li>
<a href=".">Manager</a>
</li> 
</ul> 
</li> 
<li> 
<a href=".">Report</a> 
<ul>
 <li>
<a href="" target="frame2">Delay Based</a>
<ul><li><a href="delay_particularstn.jsp" target="frame2">Delay Station</a></li>
    <li><a href="selected_trains.jsp" target="frame2">Delay Train</a></li></ul>
</li>
<li>
<a href="Statistics.jsp" target="frame2">Statistics</a>

</li> 
<li>
<a href="" target="frame2">SMS Based</a>
<ul><li><a href="particular_station_sms.jsp" target="frame2">SMS Station</a></li>
    <li><a href="selected_train_sms.jsp" target="frame2">SMS Train</a></li></ul>
</li> 
</ul> 
</li>

<li>
<a href=".">Contact Us</a>
</li> 
</ul> 
</div> 
 
</body> 
<div class="footer"><font color="red"><marquee> </marquee></font></div>
</html>