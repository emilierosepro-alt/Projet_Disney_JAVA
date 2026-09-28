package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {

    public boolean verifierConnexion(String login, String motDePasse) {

        String requete = "SELECT * FROM utilisateurs WHERE login = ? AND mot_de_passe = ?";

        try {
            Connection connexion = ConnexionBase.getConnexion();

            PreparedStatement statement = connexion.prepareStatement(requete);

            statement.setString(1, login);
            statement.setString(2, motDePasse);

            ResultSet resultat = statement.executeQuery();

            if (resultat.next()) {
                connexion.close();
                return true;
            }

            connexion.close();

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return false;
    }
}