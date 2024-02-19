package com.placement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepDAO {
	
	/**
	 * Inserts a representative into the database
	 * @param rep : the Representative to insert
	 * @return
	 */
	public int registerRepresentative(Representative rep) {
		// Create the MtSQL statement for inserting the rep
		String INSERT_USER_SQL = "INSERT INTO representative "
				+ "(username, password_) VALUES"
				+ "(?, ?);";
		
		int result = 0;
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rep_app", "root", "root");
			
			// Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
			preparedStatement.setString(1, rep.getUsername());
			preparedStatement.setString(2, rep.getPassword());
			
			// Execute the query
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// Process sql exception
			printSQLException(e);
		}
		
		return result;
	}

	/**
	 * Print sql exception
	 * @param ex
	 */
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
	}
}
