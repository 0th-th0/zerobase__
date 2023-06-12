import java.io.IOException;
import java.sql.SQLException;
import pack.APIKey;


public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		APIKey apiKey = new APIKey();
		apiKey.insertWifi();
	

	}

}
