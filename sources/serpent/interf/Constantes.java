package serpent.interf;

/**
 * Interface Constantes
 * représentant les variables constantes
 *
 * @author HETRU Mathieu
 * @version 1.0, 11/04/2019
 */
public interface Constantes {
	final static int LT = 480;  // largeur totale
	final static int HT = 400;  // hauteur totale
	
	final static int LENGTH = 10; // taille du serpent
	
	final static int RAYON = 10; // taille du rayon de chaque rond du serpent
	
	final static int LIMITE_MIN_X = RAYON; // bord gauche
	
	final static int LIMITE_MAX_X = LT - RAYON; // bord droit
	
	final static int LIMITE_MIN_Y = RAYON; // bord haut
	
	final static int LIMITE_MAX_Y = HT - RAYON; // bord bas
	
	final static int DELAI = 1000; // délai en millisecondes (1000ms = 1 seconde)
	
	final static double FREQUENCE = 1.0; // fréquence (1.0 = 1 seconde)
	//final static double FREQUENCE = 0.5; // fréquence (1.0 = 1 seconde)
}
