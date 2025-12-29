<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="css/custom.css"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
</head>
<body>
<%! 
String greeting = "마켓에 오신걸 환영합니다";
String tagline = "Welcome to Market!";
%>
<%@ include file="include/menu.jsp" %>
<div class="container">
   <div class="row">
      <div class="col-md-12">
		<h3 class="text-center"><%=greeting%></h3>
      </div>
   </div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4 class="text-center"><%=tagline%></h4>
			<br>
			<h5 class="text-center">
			<%
response.setIntHeader("Refresh", 5);
Date day = new java.util.Date();
String am_pm;
int hour = day.getHours();
int minute = day.getMinutes();
int second = day.getSeconds();
if(hour / 12 == 0){
	am_pm = "AM";
}else{
	am_pm = "PM";
	hour = hour -12;
}
String CT = hour + ":" + minute + ":"+second + "" + am_pm;
out.println("현재 접속 시각 :" + CT + "\n");
%>
			</h5>
		</div>
	</div>
</div>



<%@ include file="include/footer.jsp" %>
</body>
</html>