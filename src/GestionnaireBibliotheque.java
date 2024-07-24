import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// Classe abstraite Livre
abstract class Livre {
    private String titre;
    private String auteur;

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public abstract String getCategorie();
}

// Classe LivreRoman
class LivreRoman extends Livre {
    public LivreRoman(String titre, String auteur) {
        super(titre, auteur);
    }

    @Override
    public String getCategorie() {
        return "Roman";
    }
}

// Classe GestionnaireBibliotheque
public class GestionnaireBibliotheque {
    private HashMap<Integer, Livre> bibliotheque = new HashMap<>();
    private int idCounter = 1;

    // Méthode pour ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        bibliotheque.put(idCounter, livre);
        idCounter++;
    }

    // Méthode pour supprimer un livre de la bibliothèque par son identifiant
    public void supprimerLivre(int id) {
        bibliotheque.remove(id);
    }

    // Méthode pour sauvegarder les données dans un fichier texte
    public void sauvegarderDansFichier(String nomFichier) {
        try (FileWriter writer = new FileWriter(nomFichier)) {
            for (Livre livre : bibliotheque.values()) {
                writer.write(livre.getTitre() + ";" + livre.getAuteur() + ";" + livre.getCategorie() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde dans le fichier : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GestionnaireBibliotheque gestionnaire = new GestionnaireBibliotheque();

        LivreRoman livre1 = new LivreRoman("Les Misérables", "Victor Hugo");
        LivreRoman livre2 = new LivreRoman("Germinal", "Émile Zola");
        LivreRoman livre3 = new LivreRoman("L'appel de la foret", "John London");

        gestionnaire.ajouterLivre(livre1);
        gestionnaire.ajouterLivre(livre2);
        gestionnaire.ajouterLivre(livre2);

        // Exemple d'utilisation des méthodes du gestionnaire
        //gestionnaire.supprimerLivre(1);

        // Sauvegarde des données dans un fichier texte
        gestionnaire.sauvegarderDansFichier("bibliotheque.txt");
    }
}