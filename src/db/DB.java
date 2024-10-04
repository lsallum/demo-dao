package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String pass = properties.getProperty("db.pass");

                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
               throw new DBException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeStatement(PreparedStatement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try(FileInputStream file = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
