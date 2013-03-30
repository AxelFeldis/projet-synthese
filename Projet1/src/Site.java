import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class Site {
	String nameS;
	float longitudeS;
	float latitudeS;
	Connection conn;

	Site() throws SQLException {
		// Connexion a la base de
		// donnee***************************************************
		try {
			//Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			//String url = "jdbc:postgresql://localhost:5432/Visioscope";
			String url = "jdbc:postgresql://postgresql1.alwaysdata.com/visioscope_visio";
			String user = "visioscope";
			String passwd = "visioscope3000";

			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}
	public String getNameS() {
		return nameS;
	}

	public float getLatitudeS() {
		return latitudeS;
	}

	public float getLongitudeS() {
		return longitudeS;
	}


	public void retrieveGeoSite(String st) throws SQLException {

		// Création d'un objet Statement
		Statement state = conn.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet resultLong = state.executeQuery("SELECT longitude FROM \"Site\" WHERE nom = '" + st + "';");
		// On récupère les MetaData
		ResultSetMetaData resultMetaLong = resultLong.getMetaData();
		// Récupération de la Longitude
		while (resultLong.next()) {
			for (int i = 1; i <= resultMetaLong.getColumnCount(); i++){
				String s = resultLong.getObject(i).toString();
				longitudeS = Float.parseFloat(s);
			}

		}
		resultLong.close();
		
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet resultLat = state.executeQuery("SELECT latitude FROM \"Site\" WHERE nom = '" + st + "';");
		// On récupère les MetaData
		ResultSetMetaData resultMetaLat = resultLat.getMetaData();
		// Récupération de la Latitude
				while (resultLat.next()) {
					for (int i = 1; i <= resultMetaLat.getColumnCount(); i++){
						String s = resultLat.getObject(i).toString();
						latitudeS = Float.parseFloat(s);
					}

				}
		resultLat.close();
		state.close();
	}

}
