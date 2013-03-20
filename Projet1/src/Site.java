import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Site {
	String nameS;
	float longitudeS;
	float latitudeS;
	Connection conn;

	Site() {
		// Connexion a la base de
		// donnee***************************************************
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

			String url = "jdbc:postgresql://localhost:5432/Visioscope";
			String user = "postgres";
			String passwd = "postgres";

			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void retrieveGeoSite() throws SQLException {
		// envoi de la
		// requete*************************************************************

		// Création d'un objet Statement
		Statement state = conn.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM \"Site\";");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();

		System.out.println("\n**********************************");
		// On affiche le nom des colonnes
		for (int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase()
					+ "\t *");

		System.out.println("\n**********************************");

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out
						.print("\t" + result.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");

		}

		result.close();
		state.close();
	}

}
