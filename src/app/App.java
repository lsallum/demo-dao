package app;

import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM seller";

        Department booksDepartment = new Department(1, "books");
        Seller seller = new Seller(21, "Lucas", "ola@lsallum.com", LocalDate.of(1988, 7, 20), 20000.00, booksDepartment);
        System.out.println(seller);

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
