package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * Classe IHM représentant la fenêtre principale de l'application
 *
 */
public class IHM extends JFrame{
	

	PrincipalPanel tableauDeBord ;
	
	public IHM() {
		super("Gestion Agence de Voyage");
		tableauDeBord = new PrincipalPanel();
		
	}
/**
 * Créer les différents éléments graphiques de l'application
 */
	private void createAndShowGUI() {
		
		this.setSize(1300, 700);
		this.setPreferredSize(new Dimension(1300,700));		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setTitle("Gestion Agence de Voyages");
		this.setContentPane(tableauDeBord);
		
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
