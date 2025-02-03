package pfa.java.workshopjdbc.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final StringProperty nom;
    private final StringProperty email;
    private final int id;

    public User(int id, String nom, String email) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.email = new SimpleStringProperty(email);
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return nom.get() + " (" + email.get() + ")";
    }
}
