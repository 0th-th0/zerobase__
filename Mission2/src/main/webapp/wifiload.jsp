<%@page import="pack.APIKey"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	APIKey apiKey = new APIKey();
%>
	<h1 style="text-align:center;"> <%=apiKey.insertWifi()%> 개의 WIFI 정보를 정상적으로 저장하였습니다. </h1>
	<div style="text-align: center;"><a href="index.jsp">홈으로 가기</a></div>
	</body>
</html>