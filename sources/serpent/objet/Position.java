package serpent.objet;

/**
 * Classe Position
 * représentant une Position
 *
 * @author HETRU Mathieu
 * @version 1.0, 11/04/2019
 */
public class Position {
    
    /** attribut x */
    private int x = 0;
    
    /** attribut y */
    private int y = 0;
    
    /** attribut value */
    //private int value = 0;
    
    /**
     * constructeur d'objet par d�faut
     */
    public Position() {}
    
    /**
     * constructeur d'objets de la classe Position
     */
    public Position(int colonne, int ligne) {
    	
        x = colonne;
        y = ligne;
        
    }
    
  

	/**
	 * permet de d�finir une abscisse � une position
	 * @param <code>int</code> valeur
	 */
    public void setX(int val_x) {
		x = val_x;
    }
    
    /**
     * Methode qui retourne la coordonn�e X de la Cell
     * @return <code>int</code>
     */
    public int getX() {
    	
        return x;
        
    }
    
    /**
	 * permet de d�finir une ordonn�e � une position
	 * @param <code>int</code> valeur
	 */
    public void setY(int val_y) {
		y = val_y;
    }
    
    /**
     * Methode qui retourne la coordonn�e y de la Cell
     * @return <code>int</code>
     */
    public int getY() {
    	
        return y;
        
    } 
    
    public boolean equals(Object obj) {
    	boolean retour = false;
    	Position pos_obj = (Position) obj;
    	if ( (pos_obj.getX() == this.x) && (pos_obj.getY() == this.y) ) {
    		retour=true;
    	}
    	return retour;
    }
    
    /**
     * M�thode qui retourne la Cell courante sous forme de cha�ne de caract�res
     * @return <code>String</code>
     */
    public String toString() {
    	
    	String retour = "";
    	
    	retour += "(" + x + "," + y + ")";
    	
    	return retour;
    }
    
    
   
    
}
