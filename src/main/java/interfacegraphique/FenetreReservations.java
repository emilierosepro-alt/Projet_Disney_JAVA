package interfacegraphique;

import base.ReservationDAO;
import modele.ReservationDisney;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FenetreReservations extends JFrame {

    private JTable tableau;
    private DefaultTableModel modeleTableau;

    private ArrayList<ReservationDisney> reservations;

    public FenetreReservations() {

        setTitle("Mes réservations");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panneau = new JPanel();

        String[] colonnes = {
                "ID",
                "Nom",
                "Parc",
                "Univers",
                "Attraction",
                "Personnage",
                "Date",
                "Type"
        };

        modeleTableau =
                new DefaultTableModel(colonnes, 0);

        tableau =
                new JTable(modeleTableau);

        JScrollPane scroll =
                new JScrollPane(tableau);

        JButton boutonAjouter =
                new JButton("Ajouter");

        JButton boutonModifier =
                new JButton("Modifier");

        JButton boutonSupprimer =
                new JButton("Supprimer");

        JButton boutonActualiser =
                new JButton("Actualiser");

        boutonAjouter.addActionListener(e -> {

            new FormulaireReservation();
        });

        boutonModifier.addActionListener(e -> {

            modifierReservation();
        });

        boutonSupprimer.addActionListener(e -> {

            supprimerReservation();
        });

        boutonActualiser.addActionListener(e -> {

            chargerReservations();
        });

        panneau.add(scroll);
        panneau.add(boutonAjouter);
        panneau.add(boutonModifier);
        panneau.add(boutonSupprimer);
        panneau.add(boutonActualiser);

        add(panneau);

        chargerReservations();

        setVisible(true);
    }

    private void chargerReservations() {

        modeleTableau.setRowCount(0);

        ReservationDAO reservationDAO =
                new ReservationDAO();

        reservations =
                reservationDAO.listerReservations();

        for (ReservationDisney reservation : reservations) {

            Object[] ligne = {
                    reservation.getId(),
                    reservation.getNomVisiteur(),
                    reservation.getParc(),
                    reservation.getUnivers(),
                    reservation.getAttraction(),
                    reservation.getPersonnagePrefere(),
                    reservation.getDateVisite(),
                    reservation.getTypeReservation()
            };

            modeleTableau.addRow(ligne);
        }
    }

    private void modifierReservation() {

        int ligne =
                tableau.getSelectedRow();

        if (ligne == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Sélectionnez une réservation."
            );

            return;
        }

        ReservationDisney reservation =
                reservations.get(ligne);

        new FormulaireReservation(
                reservation
        );
    }

    private void supprimerReservation() {

        int ligne =
                tableau.getSelectedRow();

        if (ligne == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Sélectionnez une réservation."
            );

            return;
        }

        int choix =
                JOptionPane.showConfirmDialog(
                        this,
                        "Voulez-vous vraiment supprimer cette réservation ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

        if (choix == JOptionPane.YES_OPTION) {

            ReservationDisney reservation =
                    reservations.get(ligne);

            ReservationDAO reservationDAO =
                    new ReservationDAO();

            reservationDAO.supprimerReservation(
                    reservation.getId()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Réservation supprimée !"
            );

            chargerReservations();
        }
    }
}