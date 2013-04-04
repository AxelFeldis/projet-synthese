import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Site {
	private String nameS;
	private float longitudeMonument;
	private float latitudeMonument;
	private float longitudeZone;
	private float latitudeZone;
	private float hauteurMonument;
	private float largeurMonument;
	private float hauteurZone;
	private float largeurZone;
	private Connection conn;

	Site() throws SQLException {
		// Connexion a la base de
		// donnee***************************************************
		try {
			//connection avec la base de données
			String url = "jdbc:postgresql://postgresql1.alwaysdata.com/visioscope_visio";
			String user = "visioscope";
			String passwd = "visioscope3000";
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//------------------------------------------------------------GETTER--------------------------------------------------------\\
	public Connection getConnection() {
		return conn;
	}

	public String getNameS() {
		return nameS;
	}

	public float getLatitudeMonument() {
		return latitudeMonument;
	}

	public float getLongitudeMonument() {
		return longitudeMonument;
	}

	public float getLatitudeZone() {
		return latitudeZone;
	}

	public float getLongitudeZone() {
		return longitudeZone;
	}

	public float getHauteurMonument() {
		return hauteurMonument;
	}

	public float getLargeurMonument() {
		return largeurMonument;
	}

	public float getHauteurZone() {
		return hauteurZone;
	}

	public float getLargeurZone() {
		return largeurZone;
	}

	//Methode qui récupère les attributs dans la base de données et qui les
	public void retrieveGeoSite(String st) throws SQLException {

		// Récupération du nom du Monument
		// Création d'un objet Statement
		Statement state = conn.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet resultNom = state.executeQuery("SELECT \"Site\".\"nom\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		// On récupère les MetaData
		ResultSetMetaData resultMetaNom = resultNom.getMetaData();
		while (resultNom.next()) {
			for (int i = 1; i <= resultMetaNom.getColumnCount(); i++) {
				nameS = resultNom.getObject(i).toString();
			}
		}
		resultNom.close();

		// Récupération de la Longitude du Monument
		ResultSet resultLongM = state.executeQuery("SELECT \"Site\".\"longitudeMonument\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLongM = resultLongM.getMetaData();
		while (resultLongM.next()) {
			for (int i = 1; i <= resultMetaLongM.getColumnCount(); i++) {
				String s = resultLongM.getObject(i).toString();
				longitudeMonument = Float.parseFloat(s);
			}
		}
		resultLongM.close();

		// Récupération de la Latitude du Monument
		ResultSet resultLatM = state.executeQuery("SELECT \"Site\".\"latitudeMonument\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLatM = resultLatM.getMetaData();
		while (resultLatM.next()) {
			for (int i = 1; i <= resultMetaLatM.getColumnCount(); i++) {
				String s = resultLatM.getObject(i).toString();
				latitudeMonument = Float.parseFloat(s);
			}
		}
		resultLatM.close();

		// Récupération de la Longitude de la Zone
		ResultSet resultLongZ = state.executeQuery("SELECT \"Site\".\"longitudeZone\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLongZ = resultLongZ.getMetaData();
		while (resultLongZ.next()) {
			for (int i = 1; i <= resultMetaLongZ.getColumnCount(); i++) {
				String s = resultLongZ.getObject(i).toString();
				longitudeZone = Float.parseFloat(s);
			}
		}
		resultLongZ.close();

		// Récupération de la Latitude de la Zone
		ResultSet resultLatZ = state.executeQuery("SELECT \"Site\".\"latitudeZone\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLatZ = resultLatZ.getMetaData();
		while (resultLatZ.next()) {
			for (int i = 1; i <= resultMetaLatZ.getColumnCount(); i++) {
				String s = resultLatZ.getObject(i).toString();
				latitudeZone = Float.parseFloat(s);
			}
		}
		resultLatZ.close();

		// Récupération de la Largeur de la Zone
		ResultSet resultLargZ = state.executeQuery("SELECT \"Site\".\"largeurZone\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLargZ = resultLargZ.getMetaData();
		while (resultLargZ.next()) {
			for (int i = 1; i <= resultMetaLargZ.getColumnCount(); i++) {
				String s = resultLargZ.getObject(i).toString();
				largeurZone = Float.parseFloat(s);
			}
		}
		resultLargZ.close();

		// Récupération de la hauteur de la Zone
		ResultSet resultHautZ = state.executeQuery("SELECT \"Site\".\"hauteurZone\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaHautZ = resultHautZ.getMetaData();
		while (resultHautZ.next()) {
			for (int i = 1; i <= resultMetaHautZ.getColumnCount(); i++) {
				String s = resultHautZ.getObject(i).toString();
				hauteurZone = Float.parseFloat(s);
			}
		}
		resultHautZ.close();

		// Récupération de la Largeur du Monument
		ResultSet resultLargM = state.executeQuery("SELECT \"Site\".\"largeurMonument\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaLargM = resultLargM.getMetaData();
		while (resultLargM.next()) {
			for (int i = 1; i <= resultMetaLargM.getColumnCount(); i++) {
				String s = resultLargM.getObject(i).toString();
				largeurMonument = Float.parseFloat(s);
			}
		}
		resultLargM.close();

		// Récupération de la hauteur du Monument
		ResultSet resultHautM = state.executeQuery("SELECT \"Site\".\"hauteurMonument\" FROM public.\"Site\" WHERE \"Site\".nom ='" + st + "';");
		ResultSetMetaData resultMetaHautM = resultHautM.getMetaData();
		while (resultHautM.next()) {
			for (int i = 1; i <= resultMetaHautM.getColumnCount(); i++) {
				String s = resultHautM.getObject(i).toString();
				hauteurMonument = Float.parseFloat(s);
			}
		}
		resultHautM.close();
		state.close();
		

	}

}
