package fichier;

import modele.ReservationDisney;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GestionFichier {

    public void exporterReservations(
            ArrayList<ReservationDisney> reservations,
            String nomFichier) {

        String contenu = "MES AVENTURES DISNEY\n\n";

        for (ReservationDisney reservation : reservations) {

            contenu += "Nom : " + reservation.getNomVisiteur() + "\n";
            contenu += "Parc : " + reservation.getParc() + "\n";
            contenu += "Univers : " + reservation.getUnivers() + "\n";
            contenu += "Attraction : " + reservation.getAttraction() + "\n";
            contenu += "Personnage préféré : "
                    + reservation.getPersonnagePrefere() + "\n";
            contenu += "Date : " + reservation.getDateVisite() + "\n";
            contenu += "Type : " + reservation.getTypeReservation() + "\n";

            if (reservation.isPhotoSouvenir()) {
                contenu += "Photo souvenir : Oui\n";
            } else {
                contenu += "Photo souvenir : Non\n";
            }

            contenu += "Commentaire : "
                    + reservation.getCommentaire() + "\n";

            contenu += "\n-----------------------------\n\n";
        }

        try {

            Path chemin = Path.of(nomFichier);

            Files.writeString(chemin, contenu);

            System.out.println("Fichier exporté avec succès !");

        } catch (IOException e) {

            System.out.println(
                    "Erreur lors de l'export : " + e.getMessage()
            );
        }
    }
}