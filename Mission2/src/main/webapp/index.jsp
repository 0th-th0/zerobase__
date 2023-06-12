<%@page import="pack.WifiService"%>
<%@page import="pack.Wifi"%>
<%@ page import="java.util.List"%>
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

<script type="text/javascript">
	function showPosition() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				var latitude = position.coords.latitude;
				var longitude = position.coords.longitude;

				document.getElementById("LAT").value = latitude;
				document.getElementById("LNT").value = longitude;

				document.getElementById("myForm").submit();
			});
		} else {
			alert("해당 브라우저에서 지원하지 않습니다.");
		}
	}
</script>

</head>
<body>
	<%
	WifiService wifiService = new WifiService();
	%>
	<jsp:include page="header.jsp"></jsp:include>

	<form action="./index.jsp" method="get">
		<label>LAT: <input type="text" name="lat" id="LAT" value="0.0"></label>
		, <label>LNT: <input type="text" name="lnt" id="LNT"
			value="0.0"></label>
		<button type="button" onclick="showPosition()">내 위치 가져오기</button>
		<button type="submit" onclick="this.form.submit()">근처 WIFI 정보 보기</button>
		<br>
	</form>

	<table id="wifiTable">
		<thead>
			<tr>
				<th style="text-align: center;">거리(Km)</th>
				<th style="text-align: center;">관리번호</th>
				<th style="text-align: center;">자치구</th>
				<th style="text-align: center;">와이파이명</th>
				<th style="text-align: center;">도로명주소</th>
				<th style="text-align: center;">상세주소</th>
				<th style="text-align: center;">설치위치(층)</th>
				<th style="text-align: center;">설치유형</th>
				<th style="text-align: center;">설치기관</th>
				<th style="text-align: center;">서비스구분</th>
				<th style="text-align: center;">망종류</th>
				<th style="text-align: center;">설치년도</th>
				<th style="text-align: center;">실내외구분</th>
				<th style="text-align: center;">WIFI접속환경</th>
				<th style="text-align: center;">X좌표</th>
				<th style="text-align: center;">Y좌표</th>
				<th style="text-align: center;">작업일자</th>
			</tr>
		</thead>


		<%
		String lat = request.getParameter("lat");
		String lnt = request.getParameter("lnt");

		if (lat != null && lnt != null) {
			List<Wifi> wifi20 = wifiService.selectWifiList(lat, lnt);
			wifiService.insertHistory(lat, lnt);
			for (Wifi nearWifi : wifi20) {
		%>
		<tr>
			<td><%=nearWifi.getDistance()%></td>
			<td><%=nearWifi.getX_SWIFI_MGR_NO()%></td>
			<td><%=nearWifi.getX_SWIFI_WRDOFC()%></td>
			<td><a
				href="detail_wifi.jsp?mgrNo=<%=nearWifi.getX_SWIFI_MGR_NO()%>&distance=<%=nearWifi.getDistance()%>"><%=nearWifi.getX_SWIFI_MAIN_NM()%></a></td>
			<td><%=nearWifi.getX_SWIFI_ADRES1()%></td>
			<td><%=nearWifi.getX_SWIFI_ADRES2()%></td>
			<td><%=nearWifi.getX_SWIFI_INSTL_FLOOR()%></td>
			<td><%=nearWifi.getX_SWIFI_INSTL_TY()%></td>
			<td><%=nearWifi.getX_SWIFI_INSTL_MBY()%></td>
			<td><%=nearWifi.getX_SWIFI_SVC_SE()%></td>
			<td><%=nearWifi.getX_SWIFI_CMCWR()%></td>
			<td><%=nearWifi.getX_SWIFI_CNSTC_YEAR()%></td>
			<td><%=nearWifi.getX_SWIFI_INOUT_DOOR()%></td>
			<td><%=nearWifi.getX_SWIFI_REMARS3()%></td>
			<td><%=nearWifi.getLAT()%></td>
			<td><%=nearWifi.getLNT()%></td>
			<td><%=nearWifi.getWORK_DTTM()%></td>
		</tr>

		<%
		}
		} else {
		%>
		<tr>
			<td style="text-align: center;" colspan="17">위치 정보를 입력한 후에 조회해
				주세요.</td>
		</tr>
		<%}%>
	</table>
</body>
</html>




