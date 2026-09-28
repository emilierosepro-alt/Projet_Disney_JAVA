package modele;

import exception.ReservationException;

public class ReservationDisney implements Affichable {

    private int id;
    private String nomVisiteur;
    private String parc;
    private String univers;
    private String attraction;
    private String personnagePrefere;
    private String dateVisite;
    private String typeReservation;
    private boolean photoSouvenir;
    private String commentaire;

    public ReservationDisney(int id, String nomVisiteur, String parc,
                             String univers, String attraction,
                             String personnagePrefere, String dateVisite,
                             String typeReservation, boolean photoSouvenir,
                             String commentaire) {

        this.id = id;
        this.nomVisiteur = nomVisiteur;
        this.parc = parc;
        this.univers = univers;
        this.attraction = attraction;
        this.personnagePrefere = personnagePrefere;
        this.dateVisite = dateVisite;
        this.typeReservation = typeReservation;
        this.photoSouvenir = photoSouvenir;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public String getNomVisiteur() {
        return nomVisiteur;
    }

    public String getParc() {
        return parc;
    }

    public String getUnivers() {
        return univers;
    }

    public String getAttraction() {
        return attraction;
    }

    public String getPersonnagePrefere() {
        return personnagePrefere;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public String getTypeReservation() {
        return typeReservation;
    }

    public boolean isPhotoSouvenir() {
        return photoSouvenir;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void verifierReservation() throws ReservationException {

        if (nomVisiteur == null || nomVisiteur.isEmpty()) {
            throw new ReservationException(
                    "Le nom du visiteur est obligatoire."
            );
        }

        if (parc == null || parc.isEmpty()) {
            throw new ReservationException(
                    "Le parc est obligatoire."
            );
        }

        if (dateVisite == null || dateVisite.isEmpty()) {
            throw new ReservationException(
                    "La date de visite est obligatoire."
            );
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNomVisiteur(String nomVisiteur) {
        this.nomVisiteur = nomVisiteur;
    }

    public void setParc(String parc) {
        this.parc = parc;
    }

    public void setUnivers(String univers) {
        this.univers = univers;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public void setPersonnagePrefere(String personnagePrefere) {
        this.personnagePrefere = personnagePrefere;
    }

    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setTypeReservation(String typeReservation) {
        this.typeReservation = typeReservation;
    }

    public void setPhotoSouvenir(boolean photoSouvenir) {
        this.photoSouvenir = photoSouvenir;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    @Override
    public String afficher() {
        return nomVisiteur + " - " + attraction + " - " + dateVisite;
    }
}
