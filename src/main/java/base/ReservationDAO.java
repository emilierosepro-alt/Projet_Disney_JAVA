package base;

import modele.ReservationDisney;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {

    public void ajouterReservation(ReservationDisney reservation) {

        String requete = "INSERT INTO reservations " +
                "(nom_visiteur, parc, univers, attraction, personnage_prefere, date_visite, type_reservation, photo_souvenir, commentaire) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connexion = ConnexionBase.getConnexion();

            PreparedStatement statement = connexion.prepareStatement(requete);

            statement.setString(1, reservation.getNomVisiteur());
            statement.setString(2, reservation.getParc());
            statement.setString(3, reservation.getUnivers());
            statement.setString(4, reservation.getAttraction());
            statement.setString(5, reservation.getPersonnagePrefere());
            statement.setString(6, reservation.getDateVisite());
            statement.setString(7, reservation.getTypeReservation());
            statement.setBoolean(8, reservation.isPhotoSouvenir());
            statement.setString(9, reservation.getCommentaire());

            statement.executeUpdate();

            connexion.close();

        } catch (SQLException e) {
            System.out.println("Erreur ajout réservation : " + e.getMessage());
        }
    }

    public ArrayList<ReservationDisney> listerReservations() {

        ArrayList<ReservationDisney> reservations = new ArrayList<>();

        String requete = "SELECT * FROM reservations";

        try {
            Connection connexion = ConnexionBase.getConnexion();

            PreparedStatement statement = connexion.prepareStatement(requete);

            ResultSet resultat = statement.executeQuery();

            while (resultat.next()) {

                ReservationDisney reservation = new ReservationDisney(
                        resultat.getInt("id"),
                        resultat.getString("nom_visiteur"),
                        resultat.getString("parc"),
                        resultat.getString("univers"),
                        resultat.getString("attraction"),
                        resultat.getString("personnage_prefere"),
                        resultat.getString("date_visite"),
                        resultat.getString("type_reservation"),
                        resultat.getBoolean("photo_souvenir"),
                        resultat.getString("commentaire")
                );

                reservations.add(reservation);
            }

            connexion.close();

        } catch (SQLException e) {
            System.out.println("Erreur lecture réservations : " + e.getMessage());
        }

        return reservations;
    }

    public void modifierReservation(ReservationDisney reservation) {

        String requete = "UPDATE reservations SET " +
                "nom_visiteur = ?, " +
                "parc = ?, " +
                "univers = ?, " +
                "attraction = ?, " +
                "personnage_prefere = ?, " +
                "date_visite = ?, " +
                "type_reservation = ?, " +
                "photo_souvenir = ?, " +
                "commentaire = ? " +
                "WHERE id = ?";

        try {
            Connection connexion = ConnexionBase.getConnexion();

            PreparedStatement statement = connexion.prepareStatement(requete);

            statement.setString(1, reservation.getNomVisiteur());
            statement.setString(2, reservation.getParc());
            statement.setString(3, reservation.getUnivers());
            statement.setString(4, reservation.getAttraction());
            statement.setString(5, reservation.getPersonnagePrefere());
            statement.setString(6, reservation.getDateVisite());
            statement.setString(7, reservation.getTypeReservation());
            statement.setBoolean(8, reservation.isPhotoSouvenir());
            statement.setString(9, reservation.getCommentaire());
            statement.setInt(10, reservation.getId());

            statement.executeUpdate();

            connexion.close();

        } catch (SQLException e) {
            System.out.println("Erreur modification réservation : " + e.getMessage());
        }
    }

    public void supprimerReservation(int id) {

        String requete = "DELETE FROM reservations WHERE id = ?";

        try {
            Connection connexion = ConnexionBase.getConnexion();

            PreparedStatement statement = connexion.prepareStatement(requete);

            statement.setInt(1, id);

            statement.executeUpdate();

            connexion.close();

        } catch (SQLException e) {
            System.out.println("Erreur suppression réservation : " + e.getMessage());
        }
    }
}