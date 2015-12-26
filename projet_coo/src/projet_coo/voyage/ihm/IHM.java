package projet_coo.voyage.ihm;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * Classe IHM représentant la fenêtre principale de l'application
 *
 */
public class IHM extends JFrame{
	
	private PanelVille Villes;	
	JPanel grosPanel ;
	
	public IHM() {
		super("Annuaire");
		grosPanel = new JPanel( new BorderLayout() );
		Villes = new PanelVille();
		
		
	}
/**
 * Créer les différents éléments graphiques de l'application
 */
	private void createAndShowGUI() {
		
		this.setSize(700, 600);
		this.setPreferredSize(new Dimension(700,600));		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Villes.setSize(350,600);
		Villes.setPreferredSize(new Dimension(350,600));	
	

		
		// Ajout des panels au panel principal
		grosPanel.add(Villes);		
		
		this.setTitle("test");
		this.setContentPane(grosPanel);
		
		// Display the window.
		this.setVisible(true);
		
	}
	
/**
 * Lance la construction de l'application
 */
	public void run() {
		createAndShowGUI();

	}

}
