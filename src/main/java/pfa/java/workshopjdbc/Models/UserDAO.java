package pfa.java.workshopjdbc.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDAO {


    public static List<User> loadusers(){

        String sql = "SELECT id, nom, email FROM user";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBconnection.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("email")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addUser(User user) {
        String query = "INSERT INTO user (nom, email,prenom) VALUES (?, ?, ?)";

        try (Connection conn = DBconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour un utilisateur
    public static void updateUser(User user) {
        String query = "UPDATE user SET nom = ?, email = ? WHERE id = ?";

        try (Connection conn = DBconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un utilisateur
    public static void deleteUser(int id) {
        String query = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DBconnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
