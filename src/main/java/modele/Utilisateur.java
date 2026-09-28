package modele;

public class Utilisateur extends Personne {

    private String login;
    private String motDePasse;

    public Utilisateur(String nom, String login, String motDePasse) {
        super(nom);
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String afficherInfos() {
        return "Utilisateur : " + getNom() + " - " + login;
    }
}