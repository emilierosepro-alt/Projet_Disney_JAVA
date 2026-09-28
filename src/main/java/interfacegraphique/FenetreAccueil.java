package interfacegraphique;

import base.ReservationDAO;
import fichier.GestionFichier;
import modele.ReservationDisney;

import javax.swing.*;
import java.util.ArrayList;

public class FenetreAccueil extends JFrame {

    public FenetreAccueil() {

        setTitle("Accueil - Disney Dream Planner");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panneau = new JPanel();

        JLabel titre =
                new JLabel("Bienvenue sur Disney Dream Planner");

        JButton boutonReservation =
                new JButton("Nouvelle réservation");

        JButton boutonListe =
                new JButton("Mes réservations");

        JButton boutonExporter =
                new JButton("Exporter mes réservations");

        JButton boutonDeconnexion =
                new JButton("Déconnexion");

        boutonReservation.addActionListener(e -> {
            new FormulaireReservation();
        });

        boutonListe.addActionListener(e -> {
            new FenetreReservations();
        });

        boutonExporter.addActionListener(e -> {
            exporterReservations();
        });

        boutonDeconnexion.addActionListener(e -> {

            dispose();

            new FenetreConnexion();
        });

        panneau.add(titre);
        panneau.add(boutonReservation);
        panneau.add(boutonListe);
        panneau.add(boutonExporter);
        panneau.add(boutonDeconnexion);

        add(panneau);

        setVisible(true);
    }

    private void exporterReservations() {

        ReservationDAO reservationDAO =
                new ReservationDAO();

        ArrayList<ReservationDisney> reservations =
                reservationDAO.listerReservations();

        GestionFichier gestionFichier =
                new GestionFichier();

        gestionFichier.exporterReservations(
                reservations,
                "reservations_disney.txt"
        );

        JOptionPane.showMessageDialog(
                this,
                "Les réservations ont été exportées !"
        );
    }
}