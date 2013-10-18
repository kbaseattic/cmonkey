package us.kbase.kbasecmonkey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Microbesonline {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public Microbesonline() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://pub.microbesonline.org/genomics","guest","guest");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	public String getGenomeForGene(String locusTag) throws SQLException {
		String query = "SELECT DISTINCT Taxonomy.name FROM Taxonomy JOIN Scaffold ON Scaffold.taxonomyId = Taxonomy.taxonomyId JOIN Locus ON Locus.scaffoldId = Scaffold.scaffoldId JOIN Synonym ON Synonym.locusId = Locus.locusId WHERE Synonym.name LIKE \'"+locusTag+"\'";
		resultSet = statement.executeQuery(query);
		if (!resultSet.next()){
			return null;
		} else {
			resultSet.first();
			String name = resultSet.getString("name");
			return name;
		}
	}

	public String getTaxidForGenome(String genomeName) throws Exception {
		String query = "SELECT Taxonomy.taxonomyId FROM Taxonomy WHERE Taxonomy.name LIKE \'"+genomeName+"\'";
		resultSet = statement.executeQuery(query);
		if (resultSet != null) {
			resultSet.first();
			String name = resultSet.getString("taxonomyId");
			return name;
		} else {
			return new String();
		}
	}
	
	// You need to close the resultSet
	public void disconnect() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
