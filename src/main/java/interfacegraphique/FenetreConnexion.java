package interfacegraphique;

import base.UtilisateurDAO;

import javax.swing.*;

public class FenetreConnexion extends JFrame {

    private JTextField champLogin;
    private JPasswordField champMotDePasse;

    public FenetreConnexion() {

        setTitle("Disney Dream Planner");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panneau = new JPanel();

        JLabel titre = new JLabel("Disney Dream Planner");

        JLabel labelLogin = new JLabel("Login :");
        champLogin = new JTextField(15);

        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        champMotDePasse = new JPasswordField(15);

        JButton boutonConnexion = new JButton("Se connecter");

        boutonConnexion.addActionListener(e -> connexion());

        panneau.add(titre);
        panneau.add(labelLogin);
        panneau.add(champLogin);
        panneau.add(labelMotDePasse);
        panneau.add(champMotDePasse);
        panneau.add(boutonConnexion);

        add(panneau);

        setVisible(true);
    }

    private void connexion() {

        String login = champLogin.getText();
        String motDePasse = new String(champMotDePasse.getPassword());

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        boolean connexion =
                utilisateurDAO.verifierConnexion(login, motDePasse);

        if (connexion) {

            JOptionPane.showMessageDialog(
                    this,
                    "Connexion réussie !"
            );

            dispose();

            new FenetreAccueil();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Identifiants incorrects."
            );
        }
    }
}