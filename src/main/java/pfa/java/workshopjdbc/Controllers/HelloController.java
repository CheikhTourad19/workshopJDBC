package pfa.java.workshopjdbc.Controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pfa.java.workshopjdbc.Models.User;
import pfa.java.workshopjdbc.Models.UserDAO;

public class HelloController {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    private ObservableList<User> userList;

    @FXML

    public void initialize() {
        userList = FXCollections.observableArrayList(UserDAO.loadusers());
        userTable.setItems(userList);

        // Debugging: Print the loaded users
        for (User user : userList) {
            System.out.println("User in List: " + user.getNom() + " - " + user.getEmail());
        }

        // Bind columns to properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        // Debugging: Verify column bindings
        System.out.println("Name column bound to: " + nameColumn.getCellValueFactory());
        System.out.println("Email column bound to: " + emailColumn.getCellValueFactory());

        userTable.setOnMouseClicked(this::handleRowSelect);
    }


    @FXML
    private void handleAddUser() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (!name.isEmpty() && !email.isEmpty()) {
            User newUser = new User(0, name, email);  // L'ID sera généré par la base de données
            UserDAO.addUser(newUser);  // Ajouter à la base de données
            userList.add(newUser);
            userTable.refresh();  // Rafraîchir l'affichage du tableau
// Ajouter à la liste du tableau
        }
        else
            showalert("Veiller remplir tous les champs");
    }

    @FXML
    private void handleUpdateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            selectedUser.setNom(nameField.getText());
            selectedUser.setEmail(emailField.getText());
            UserDAO.updateUser(selectedUser);
            userTable.refresh();  // Rafraîchir l'affichage du tableau
        }else
            showalert("Veiller choisir un utilisateur");
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression d'un utilisateur");
            alert.setHeaderText("Voulez-vous vraiment supprimer cet utilisateur ?");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                UserDAO.deleteUser(selectedUser.getId());
                userList.remove(selectedUser);
            }
              // Supprimer de la liste du tableau
        }else
            showalert("Veiller choisir un utilisateur");
        userTable.refresh();  // Rafraîchir l'affichage du tableau

    }

    // Gérer la sélection de la ligne pour l'édition
    private void handleRowSelect(MouseEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            nameField.setText(selectedUser.getNom());
            emailField.setText(selectedUser.getEmail());
        }
    }

    private void clear(){
        nameField.clear();
        emailField.clear();
    }
    private void showalert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
