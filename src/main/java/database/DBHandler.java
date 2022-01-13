package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {




    String connectionUrl = "jdbc:mysql://localhost:3306/library2";
    String user = "";
    String pass = "";

    private static Connection connection;
    public DBHandler(){
        try {
            connection = DriverManager.getConnection(connectionUrl, user, pass);


            System.out.println("Connection to database successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to database");
        }


    }

    public static Connection getConnection() {
        return connection;
    }
}
