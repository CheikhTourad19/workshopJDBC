package pfa.java.workshopjdbc.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String URL = "jdbc:mysql://mysql-1f241da4-elghothvadel-6af2.g.aivencloud.com:13505/defaultdb";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "????";//a remplir

    public static Connection connect() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            throw e;
        }
    }

}
