package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiService {
	final static String WifiDb = "/Users/Y/eclipse-workspace/Mission2/Mission_db.db";
	final static String url = "jdbc:sqlite:" + WifiDb;

	public void insertWifi(List<Wifi> wifiList) throws ClassNotFoundException, SQLException {
		Connection conn = null;

		if (conn == null) {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		}

		String sql = "INSERT INTO Wifi"
				+ "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
				+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		conn.setAutoCommit(false);
		PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement(sql);
		int count = 0;

		for (Wifi wifi : wifiList) {
			count++;
			preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
			preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
			preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
			preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
			preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
			preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
			preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
			preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
			preparedStatement.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
			preparedStatement.setDouble(14, wifi.getLAT());
			preparedStatement.setDouble(15, wifi.getLNT());
			preparedStatement.setString(16, wifi.getWORK_DTTM());

			preparedStatement.addBatch();

			if (count % 1000 == 0) {
				preparedStatement.executeBatch();
				preparedStatement.clearBatch();
				conn.commit();
			}
		}

		preparedStatement.executeBatch();
		conn.commit();
		conn.rollback();
	}

	public List<Wifi> selectWifiList(String lat, String lnt) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		if (conn == null) {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		}
		List<Wifi> wifiList = new ArrayList<>();

		String sql = " SELECT *, 6371 * 2 * "
				+ " ASIN(SQRT(POWER(SIN(((LAT - ? ) * PI() / 180) / 2), 2) + COS( ? * PI() / 180) * "
				+ " COS((LAT * PI() / 180))" + " * POWER(SIN(((LNT - ? ) * PI() / 180) / 2), 2))) "
				+ " AS distance FROM Wifi ORDER BY distance LIMIT 0, 20";

		PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, lnt);
		preparedStatement.setString(2, lnt);
		preparedStatement.setString(3, lat);

		ResultSet rs = null;
		rs = preparedStatement.executeQuery();

		while (rs.next()) {
			Wifi wifi = new Wifi();
			wifi.setDistance(Math.round(Double.parseDouble(rs.getString("distance")) * 10000.0) / 10000.0);
			wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
			wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
			wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
			wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
			wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
			wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
			wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
			wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
			wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
			wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
			wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
			wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
			wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
			wifi.setLAT(rs.getDouble("LAT"));
			wifi.setLNT(rs.getDouble("LNT"));
			wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
			wifiList.add(wifi);
		}

		if (!conn.isClosed()) {
			conn.close();
		}
		return wifiList;
	}

	public void insertHistory(String lat, String lnt) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		if (conn == null) {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		}
		
		String sql = "INSERT INTO History" + " (LAT, LNT, SEARCH_DTTM)" + " values (?, ?, ?)";

		conn.setAutoCommit(false);
		PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement(sql);

		preparedStatement.setString(1, lat);
		preparedStatement.setString(2, lnt);
		
		
		

		int affectedRows = preparedStatement.executeUpdate();

		if (affectedRows > 0) {
			System.out.println("저장되었사옵니다.");
		} else {
			System.out.println("저장 실패!");
		}

		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}
