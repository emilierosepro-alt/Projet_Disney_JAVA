package interfacegraphique;

import base.ReservationDAO;
import exception.ReservationException;
import modele.ReservationDisney;

import javax.swing.*;

public class FormulaireReservation extends JFrame {

    private JTextField champNom;
    private JComboBox<String> choixParc;
    private JComboBox<String> choixUnivers;
    private JTextField champAttraction;
    private JComboBox<String> choixPersonnage;
    private JTextField champDate;

    private JRadioButton boutonStandard;
    private JRadioButton boutonPremierAccess;

    private JCheckBox photoSouvenir;

    private JTextArea commentaire;

    private ReservationDisney reservationAModifier;

    public FormulaireReservation() {

        this(null);
    }

    public FormulaireReservation(
            ReservationDisney reservation) {

        this.reservationAModifier = reservation;

        setTitle("Réservation Disney");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panneau = new JPanel();

        JLabel titre =
                new JLabel("Nouvelle aventure Disney");

        champNom = new JTextField(20);

        String[] parcs = {
                "Disneyland Park",
                "Walt Disney Studios"
        };

        choixParc = new JComboBox<>(parcs);

        String[] univers = {
                "Fantasyland",
                "Adventureland",
                "Discoveryland",
                "Main Street",
                "Frontierland"
        };

        choixUnivers = new JComboBox<>(univers);

        champAttraction = new JTextField(20);

        String[] personnages = {
                "Mickey",
                "Minnie",
                "Stitch",
                "Raiponce",
                "Cendrillon",
                "Elsa",
                "Ariel"
        };

        choixPersonnage =
                new JComboBox<>(personnages);

        champDate = new JTextField(10);

        boutonStandard =
                new JRadioButton("Standard", true);

        boutonPremierAccess =
                new JRadioButton("Premier Access");

        ButtonGroup groupeReservation =
                new ButtonGroup();

        groupeReservation.add(boutonStandard);
        groupeReservation.add(boutonPremierAccess);

        photoSouvenir =
                new JCheckBox("Photo souvenir");

        commentaire =
                new JTextArea(5, 25);

        JScrollPane scrollCommentaire =
                new JScrollPane(commentaire);

        JButton boutonCouleur =
                new JButton("Choisir une couleur");

        JButton boutonImage =
                new JButton("Choisir une image");

        JButton boutonEnregistrer =
                new JButton("Enregistrer");

        boutonCouleur.addActionListener(e -> {

            JColorChooser.showDialog(
                    this,
                    "Choisir une couleur",
                    getBackground()
            );
        });

        boutonImage.addActionListener(e -> {

            JFileChooser choixFichier =
                    new JFileChooser();

            choixFichier.showOpenDialog(this);
        });

        boutonEnregistrer.addActionListener(e -> {
            enregistrerReservation();
        });

        panneau.add(titre);

        panneau.add(new JLabel("Nom :"));
        panneau.add(champNom);

        panneau.add(new JLabel("Parc :"));
        panneau.add(choixParc);

        panneau.add(new JLabel("Univers :"));
        panneau.add(choixUnivers);

        panneau.add(new JLabel("Attraction :"));
        panneau.add(champAttraction);

        panneau.add(new JLabel("Personnage préféré :"));
        panneau.add(choixPersonnage);

        panneau.add(new JLabel("Date :"));
        panneau.add(champDate);

        panneau.add(boutonStandard);
        panneau.add(boutonPremierAccess);

        panneau.add(photoSouvenir);

        panneau.add(new JLabel("Commentaire :"));
        panneau.add(scrollCommentaire);

        panneau.add(boutonImage);
        panneau.add(boutonCouleur);

        panneau.add(boutonEnregistrer);

        add(panneau);

        if (reservationAModifier != null) {
            remplirFormulaire();
        }

        setVisible(true);
    }

    private void enregistrerReservation() {

        String typeReservation;

        if (boutonPremierAccess.isSelected()) {
            typeReservation = "Premier Access";
        } else {
            typeReservation = "Standard";
        }

        int id = 0;

        if (reservationAModifier != null) {
            id = reservationAModifier.getId();
        }

        ReservationDisney reservation =
                new ReservationDisney(
                        id,
                        champNom.getText(),
                        choixParc.getSelectedItem().toString(),
                        choixUnivers.getSelectedItem().toString(),
                        champAttraction.getText(),
                        choixPersonnage.getSelectedItem().toString(),
                        champDate.getText(),
                        typeReservation,
                        photoSouvenir.isSelected(),
                        commentaire.getText()
                );

        try {

            reservation.verifierReservation();

            ReservationDAO reservationDAO =
                    new ReservationDAO();

            if (reservationAModifier == null) {

                reservationDAO.ajouterReservation(
                        reservation
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Réservation ajoutée !"
                );

            } else {

                reservationDAO.modifierReservation(
                        reservation
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Réservation modifiée !"
                );
            }

            dispose();

        } catch (ReservationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void remplirFormulaire() {

        champNom.setText(
                reservationAModifier.getNomVisiteur()
        );

        choixParc.setSelectedItem(
                reservationAModifier.getParc()
        );

        choixUnivers.setSelectedItem(
                reservationAModifier.getUnivers()
        );

        champAttraction.setText(
                reservationAModifier.getAttraction()
        );

        choixPersonnage.setSelectedItem(
                reservationAModifier.getPersonnagePrefere()
        );

        champDate.setText(
                reservationAModifier.getDateVisite()
        );

        if (reservationAModifier
                .getTypeReservation()
                .equals("Premier Access")) {

            boutonPremierAccess.setSelected(true);

        } else {

            boutonStandard.setSelected(true);
        }

        photoSouvenir.setSelected(
                reservationAModifier.isPhotoSouvenir()
        );

        commentaire.setText(
                reservationAModifier.getCommentaire()
        );
    }
}