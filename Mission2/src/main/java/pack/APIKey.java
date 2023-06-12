package pack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class APIKey {

	final String WIFI = "TbPublicWifiInfo";
	final String APIKey = "624666426275756f3339586d695272";
	WifiService wifiService = new WifiService();

	public String Wifi(int start, int end) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");/* URL */
		urlBuilder.append("/" + URLEncoder.encode(APIKey, "UTF-8")); /* 인증키(sample사용시에는 호출시 제한됩니다.) */
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입(xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode(WIFI, "UTF-8"));/* 서비스명 (대소문자 구분 필수입니다.) */
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start), "UTF-8")); /* 요청시작위치(sample인증키 사용시 5이내 숫자) */
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end), "UTF-8"));
		/* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에자세히 나와 있습니다.

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");

		System.out.println("Response code: " + conn.getResponseCode()); /* 연결자체에 대한 확인이 필요하므로 추가합니다. */

		BufferedReader rd;
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		return sb.toString();
	}
	public int insertWifi() throws ClassNotFoundException, SQLException, IOException {
		int totalCount = 0;
		try {
			JSONObject jsonObject = new JSONObject(Wifi(1, 1));
			totalCount = ((JSONObject) jsonObject.get(WIFI)).getInt("list_total_count");
			Gson gson = new Gson();
			int start = 1;
			int end = 1000;
			
			while (end <= totalCount) {
				if (start > end) break;
				
				jsonObject = new JSONObject(Wifi(start, end));
				JSONArray jsonArray = ((JSONObject) jsonObject.get(WIFI)).getJSONArray("row");
				
				List<Wifi> wifiList = new ArrayList<>();
				
				for (int i=0; i<=end-start; i++) {
					Wifi wifi = gson.fromJson(jsonArray.get(i).toString(), Wifi.class);
					wifiList.add(wifi);
				}
				
				wifiService.insertWifi(wifiList);
				
				if (end + 1000 > totalCount) {
					end = totalCount;
					} else {
					end += 1000;
					}
					start += 1000;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return totalCount;
	}
}
 