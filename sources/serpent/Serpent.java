package serpent;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.*;

import serpent.interf.*;
import serpent.objet.*;

public class Serpent implements Constantes {
	// attributs
    private Timer timer;
	private int delai = DELAI;
	private double frequence = FREQUENCE;
	private JFrame frame = new JFrame("Serpent ((c) Mathieu HETRU)");
	private Dessin d;
	private JMenuBar barreMenu;
    private JMenu menuNewPartie;
    private JMenu menuAide;
    private JMenuItem menuNouvellePartie;
    private JMenuItem menuQuitter;
    private JMenuItem menuAPropos;
    private Container pane;
	private JTextField etat;
	private String direction = "";
	//public Position[] serpent;
	//public Position[] fleurs;
	public ArrayList<Position> serpent;
	public ArrayList<Position> fleurs;
	
	// constructeur
    public Serpent() {
                
        
        initSerpent();
        initDirection();
        initFleurs();
        
        Grille grid = new Grille();
        barreMenu = new JMenuBar();
        pane = frame.getContentPane();
        menuNewPartie = new JMenu("Partie");
        menuAide = new JMenu("Aide");
         
        menuNouvellePartie = new JMenuItem("Nouvelle Partie") ;
        menuNouvellePartie.addActionListener(new Actions()); // définition de l'évènement click pour le sous-menu  
        menuQuitter = new JMenuItem("Quitter la partie") ;
        menuQuitter.addActionListener(new Actions());

        barreMenu.add(menuNewPartie);
        
        menuNewPartie.add(menuNouvellePartie);
        menuNewPartie.addSeparator();
        menuNewPartie.add(menuNouvellePartie);
        menuNewPartie.add(menuQuitter);
        
        etat = new JTextField(""); 
        
        
        pane.add(grid,BorderLayout.CENTER);

        
        pane.add(etat,BorderLayout.SOUTH);
	 	frame.setJMenuBar(barreMenu);
	 	frame.addWindowListener(new WindowAdapter() {
	 		public void windowClosing(WindowEvent e) {
	 			boolean valeur = Msgbox.affQuest(frame, "Etes-vous sûr de vouloir quitter?");
				if (valeur) System.exit(0);
	 			System.exit(0);
	 		}});
		frame.pack();
		frame.show();
		grid.requestFocus();
    }

	public void initSerpent() {
		
		//serpent = new Position[LENGTH];
		serpent = new ArrayList<Position>();
		int compteur = 0;
		Position tmp = null;
		
		while(LENGTH > compteur) {
			tmp = new Position(2 * RAYON, 2 * RAYON);
			//serpent[compteur] = tmp;
			serpent.add(tmp);
			//System.out.println(serpent[compteur].toString());
			System.out.println(tmp);
			compteur++;
		}
		
	}
	
	public void initFrequence() {
		frequence = FREQUENCE;
	}
	
	public void initFleurs() {
		
		//fleurs = new Position[LENGTH];
		fleurs = new ArrayList<Position>();
		
		int compteur = 0;
		Position tmp = null;
		
		while(LENGTH > compteur) {
			//tmp = new Position((int) (Math.random()*47 + 1), (int) (Math.random()*39+1));
			tmp = new Position((int) (Math.random()*((LT/RAYON)-1) + 1), (int) (Math.random()*((HT/RAYON)-1)+1));
			//fleurs[compteur] = tmp;
			fleurs.add(tmp);
			System.out.println(tmp);
			compteur++;
		}
	}
	
	public void initDirection() {
		direction = "Sud";
	}

	// méthodes
	public class Actions implements ActionListener {
	 	public void actionPerformed(ActionEvent e) {
	  	  	Object o = e.getSource();
	  	  	boolean valeur = false;
	  	  	if (o == menuQuitter) {
				valeur = Msgbox.affQuest(frame, "Etes-vous sûr de vouloir quitter?");
				if (valeur) System.exit(0);
			}
			
	 	    if (o == menuNouvellePartie) {
	 		   	valeur = Msgbox.affQuest(frame, "Etes-vous sûr de lancer une nouvelle partie?");
	 		   	if (valeur) nouvellePartie();
	 	  	}
		}
    }
	
	//evenements souris
	public class myKeyboardTouch extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			if ( (ke.getKeyText(ke.getKeyCode()).equals("Up")) || (ke.getKeyText(ke.getKeyCode()).equals("Haut")) || (ke.getKeyText(ke.getKeyCode()).equals("↑")) ) {
				if (!direction.equals("Sud")) {
					direction = "Nord";
				}
			}
			if ( (ke.getKeyText(ke.getKeyCode()).equals("Down")) || (ke.getKeyText(ke.getKeyCode()).equals("Bas")) || (ke.getKeyText(ke.getKeyCode()).equals("↓")) ) {
				if (!direction.equals("Nord")) {
					direction = "Sud";
				}
			}
			if ( (ke.getKeyText(ke.getKeyCode()).equals("Left")) || (ke.getKeyText(ke.getKeyCode()).equals("Gauche")) || (ke.getKeyText(ke.getKeyCode()).equals("←")) ) {
				if (!direction.equals("Est")) {
					direction = "Ouest";
				}
			}
			if ( (ke.getKeyText(ke.getKeyCode()).equals("Right")) || (ke.getKeyText(ke.getKeyCode()).equals("Droite")) || (ke.getKeyText(ke.getKeyCode()).equals("→")) ) {
				if (!direction.equals("Ouest")) {
					direction = "Est";
				}
			}
			System.out.println(ke.getKeyText(ke.getKeyCode()));
		} 
	}
	
	// méthode qui permet de lancer le jeu
	public void launch() {
		timer = new Timer();
		double result = delai * frequence;
		int int_result = (int) result;
		timer.schedule(new Repetition(), new Date(), int_result);	
	}

	public void nouvellePartie() {
		stop();
		initSerpent();
		initFrequence();
		initDirection();
		initFleurs();
		launch();
	}
	
	public void stop() {
		timer.cancel();
	}
	
	// Methode d'affichage/reaffichage
    public void afficher() { 
		d.repaint();      // reaffichage graphique
    }

	// méthode main
    public static void main(String args[]) {
        System.out.println("Serpent version 1.0");
        Serpent s = new Serpent();
        
        s.launch();
        System.out.println("Serpent launched!");
    }

	// Classe annexe pour dessiner le fond (peut être une classe interne)
    public class Fond extends JPanel { 
		public Fond() {
	    	setOpaque(true);
	    	setPreferredSize(new Dimension(LT,HT));
		}
	
		public void paintComponent(Graphics g) {
	    	int i,j;
	    	super.paintComponent(g);
	    	g.setColor(new Color(0,0,123)); // couleur du fond
	    	g.fillRect(0,0,LT,HT);
		    //g.setColor(Color.black);
		}
    }

	// Composant multicouches (fond et dessin) l'ajouter à la fenetre (peut être une classe interne)
    public class Grille extends JLayeredPane {
	
		//constructeur
		public Grille() {
		    setLayout(null);
		    setPreferredSize(new Dimension(LT,HT)); 
		    	    
		    d=new Dessin();
		    d.addKeyListener(new myKeyboardTouch());
		    add(d,new Integer(1)); // couche 1
		    d.setBounds(0,0,LT,HT); 
		    
		    Fond f=new Fond();
		    f.addKeyListener(new myKeyboardTouch());
		    add(f,new Integer(0)); // couche 0
		    f.setBounds(0,0,LT,HT); 
		}
    }

	// Classe annexe pour dessiner les tas de cartes, les adapter (peut être une classe interne)
    public class Dessin extends JPanel { 
	
		public Dessin() {
		    setOpaque(false);
		    setPreferredSize(new Dimension(LT,HT));
		}
		
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    
			//g.drawOval(20, 20, 20, 20);		    
			
			g.setColor(Color.red);
			
			//int longueur = serpent.length;
			
			//for(int i=0; i<longueur-1; i++) {
			//for(Iterator i=serpent.iterator();i.hasNext();) {
			for(int i=0; i<(serpent.size())-1; i++) {
				//Position tmp = (Position) i.next();
				Position tmp = (Position) serpent.get(i);
				//g.drawOval(serpent[i].getX() * RAYON, serpent[i].getY() * RAYON, RAYON, RAYON);
				g.drawOval(tmp.getX() * RAYON, tmp.getY() * RAYON, RAYON, RAYON);
				//g.drawOval(serpent.get(i).getX() * RAYON, serpent.get(i).getY() * RAYON, RAYON, RAYON);
			//}
			//}
			}

			g.setColor(Color.green);
			
			//longueur = fleurs.length;
			
			//for(int i=0; i<longueur-1; i++) {
			for(int i=0; i<(fleurs.size())-1; i++) {
				Position tmp = (Position) fleurs.get(i);
				
				//g.drawOval(fleurs[i].getX() * RAYON, fleurs[i].getY() * RAYON, RAYON, RAYON);
				g.drawOval(tmp.getX() * RAYON, tmp.getY() * RAYON, RAYON, RAYON);
			//}
			}
			
			//g.drawOval(tete.getX() * RAYON, tete.getY() * RAYON, RAYON, RAYON);
		}
    }

	// classe interne qui permet de faire des répétitions
    public class Repetition extends TimerTask {
        public void run() {
            //System.out.println("SsssSsss !!");
            
            /*for(int i=0;i<fleurs.length-1;i++) {
            	if (serpent[0].equals(fleurs[i])) {
            		Position[] tab_fleurs_tmp = new Position[fleurs.length - 1];
            		for(int j=0;j<fleurs.length-2;j++) {
            			tab_fleurs_tmp[j] = fleurs[j];
            		}
            		fleurs = new Position[fleurs.length + 1];
            		fleurs = tab_fleurs_tmp;
            		
            		Position[] tab_serpent_tmp = new Position[serpent.length + 1];
            		for(int k=0;k<serpent.length-1;k++) {
            			tab_serpent_tmp[k] = serpent[k];
            		}
            		tab_serpent_tmp[tab_serpent_tmp.length-1] = new Position(serpent[0].getX(), serpent[0].getY());
            		serpent = new Position[serpent.length + 1];
            		serpent = tab_serpent_tmp;
            		break;
            	}
            }*/
            
			boolean perdu = false;
            
            //int longueur = serpent.length;
			int longueur = serpent.size();
            
            for(int i=longueur-2; i>=0; i--) {
            	
            	//serpent[i+1].setX(serpent[i].getX());
            	((Position)serpent.get(i+1)).setX(((Position)serpent.get(i)).getX());
            	//serpent[i+1].setY(serpent[i].getY());
            	((Position)serpent.get(i+1)).setY(((Position)serpent.get(i)).getY());

            }
            
            int tmp = 0;
            //Position s = new Position(tete);
            if (direction.equals("Nord")) {
				//tmp = serpent[0].getY();
            	tmp = ((Position) serpent.get(0)).getY();
				tmp--;
				//serpent[0].setY(tmp);
				((Position) serpent.get(0)).setY(tmp);
        	}
        	
        	
        	if (direction.equals("Sud")) {
            	//tmp = serpent[0].getY();
        		tmp = ((Position) serpent.get(0)).getY();
            	tmp++;
				//serpent[0].setY(tmp);
            	((Position) serpent.get(0)).setY(tmp);
            }
        	
        	if (direction.equals("Ouest")) {
            	//tmp = serpent[0].getX();
        		tmp = ((Position) serpent.get(0)).getX();
				tmp--;
				//serpent[0].setX(tmp);       
				((Position) serpent.get(0)).setX(tmp);
        	}
        	
        	if (direction.equals("Est")) {
            	//tmp = serpent[0].getX();
        		tmp = ((Position) serpent.get(0)).getX();
				tmp++;
				//serpent[0].setX(tmp);
				((Position) serpent.get(0)).setX(tmp);
        	}
            
            Position position_tete_serpent = (Position) serpent.get(0);
            
            if (fleurs.contains(position_tete_serpent)) {
            	System.out.println("fleur trouvée en position : "+position_tete_serpent);
            	Position pos_tmp = new Position(position_tete_serpent.getX(), position_tete_serpent.getY());
            	fleurs.remove(pos_tmp);
            	serpent.add(pos_tmp);
            	frequence = frequence - 0.1;
            	stop();
            	launch();
            }
            
     		/*Position tmp = null;
     		Position tmp2 = null;
            int ancien_x = 0;
            int ancien_y = 0;
            
            
            for(int i=0;i<longueur-1;i++) {
            	tmp = (Position) liste.get(i);
            	ancien_x = tmp.getX();
            	ancien_y = tmp.getY();
            	
            	tmp2 = (Position) liste.get(i+1);
            	tmp2.setX(ancien_x);
            	tmp2.setY(ancien_y);
            	
            	
            }*/
            
            //System.out.println(serpent[0]);
        	System.out.println((Position)serpent.get(0));
            
            
            afficher();
            d.requestFocus();
            
            //if ( (serpent[0].getX() < 0) || (serpent[0].getX() > 47) ) {
            //if ( ((((Position)serpent.get(0)).getX()) < 0) || ((((Position)serpent.get(0)).getX()) > 47) ) {
            if ( ((((Position)serpent.get(0)).getX()) < 0) || ((((Position)serpent.get(0)).getX()) > ((LT/RAYON)-1)) ) {
            	perdu = true;
            }
            //if ( (serpent[0].getY() < 0) || (serpent[0].getY() > 39) ) {
            //if ( ((((Position)serpent.get(0)).getY()) < 0) || ((((Position)serpent.get(0)).getY()) > 39) ) {
            if ( ((((Position)serpent.get(0)).getY()) < 0) || ((((Position)serpent.get(0)).getY()) > ((HT/RAYON)-1)) ) {
            	perdu = true;
            }
            
            
            
            
            if (perdu == true) {
            	stop();
            	boolean valeur = Msgbox.affQuest(frame, "Vous avez perdu ! Rejouer ?");
            	if (valeur == true) {
            		nouvellePartie();
            	}
            }
            
        }
    }

}
