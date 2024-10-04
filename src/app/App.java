package app;

import db.DB;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM seller";

        Department obj = new Department(1, "books");
        System.out.println(obj);

        try {
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(query);

            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                DB.closeStatement(preparedStatement);
            }
            DB.closeConnection();
        }
    }
}
