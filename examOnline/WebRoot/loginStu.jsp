<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="model.LoginModel"%> 
<jsp:useBean id="login" class="model.LoginModel" scope="session"/>
<html>
<head><%@ include file="head.txt" %></head>
<title>Ê×Ò³</title>
<script type="text/javascript" src="js/check.js" charset="utf-8"></script>
<body>
<div align="center"><br><br>
<form action="handleStuLogin" method="post">
	<table border=2 cellpadding="8">
		<tr align="center"><th>
			<p>¿¼ÉúµÇÂ½</p>
		</th></tr>
		<tr><td><input type=text name="account" placeholder="ÇëÊäÈëÕËºÅ"  size=35></td></tr>
		<tr><td><input type=password name="password" placeholder="ÇëÊäÈëÃÜÂë" size=37></td></tr>
	</table>
	<br>
	<input type="submit" name="login" value="µÇÂ½" onclick=login_Check()>
</form>
<a href="registerStu.jsp" >Ã»ÓÐÕËºÅ£¿µã»÷×¢²á</a>
</div>

<div align="center">
 <Font size=4 color=red>
    <BR>µÇÂ½·´À¡:
    <jsp:getProperty name="login"  property="backNews"/>
 </Font>
 </div>
</body></html>
