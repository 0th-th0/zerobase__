<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#wifiTable {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#wifiTable td, #wifiTable th {
	border: 1px solid #ddd;
	padding: 8px;
}

#wifiTable tr:nth-child(even) {
	background-color: #f2f2f2;
}

#wifiTable tr:hover {
	background-color: #ddd;
}

#wifiTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #04AA6D;
	color: white;
}
</style>


</head>
<body>
	<h1>위치 히스토리 목록</h1>


	<a href="index.jsp">홈</a> |
	<a href="history.jsp">위치 히스토리 목록</a> |
	<a href="wifiload.jsp">Open API 와이파이 정보 가져오기</a> |
	<a href="his.jsp">즐겨 찾기 보기</a> |
	<a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
	<br>

		<br>


	<table id="wifiTable">
		<thead>	
			<tr>
				<th style="text-align: center;">북마크 이름</th><td><label> <input type="text" name="NAME" ></label></td><tr>
				<th style="text-align: center;">순서</th><td><label> <input type="text" name="ORDER" ></label></td>
			</tr>
			<tr>
			<td style="text-align: center;" colspan="17"><button onclick="location.href='history.jsp'">추가</button></td>
		</tr>
		</thead>
	</table>

</body>
</html>