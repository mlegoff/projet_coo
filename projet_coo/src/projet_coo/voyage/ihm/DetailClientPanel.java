package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Client;
import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueClient;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class DetailClientPanel extends JPanel implements ActionListener{
	int currentID;

	ClientPanel clientPanel;
	JLabel title;
	Font font = new Font("Lato",Font.CENTER_BASELINE,14);
	Color bleuStyle = new Color(7, 174,240);
	JButton supprimer;
	JPanel nom,prenom,date,ville,supprimerPanel;
	JLabel nomLabel,prenomLabel,dateLabel,villeLabel;
	JLabel nomValue,prenomValue,dateValue,villeValue;
	
	public DetailClientPanel( Dimension d){
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(d);
		this.setBackground(Color.white);;
		init();
	}
	
	private void init(){
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(this.getPreferredSize().width,50));
		titlePanel.setBackground(bleuStyle);
		title = new JLabel("Dossier Client");
		title.setFont(font);
		title.setForeground(Color.white);
		title.setBackground(bleuStyle);
		titlePanel.add(title);
		this.setBorder(BorderFactory.createLineBorder(bleuStyle));
		this.add(titlePanel);
		
		nom = new JPanel();
		prenom = new JPanel();
		date= new JPanel();
		ville= new JPanel();
		nomLabel = new JLabel("Nom : ");
		nom = new JPanel();
		nom.setBorder(new EmptyBorder(10,10,10,10));
		nom.setLayout(new BorderLayout());
		nom.add(nomLabel, BorderLayout.WEST);
		prenomLabel  = new JLabel("Prenom : ");
		prenom.setBorder(new EmptyBorder(10,10,10,10));
		prenom.setLayout(new BorderLayout());
		prenom.add(prenomLabel, BorderLayout.WEST);
		dateLabel  = new JLabel("Date de naissance: ");
		date = new JPanel();
		date.setBorder(new EmptyBorder(10,10,10,10));
		date.setLayout(new BorderLayout());
		date.add(dateLabel, BorderLayout.WEST);
		villeLabel  = new JLabel("Ville de résidence : ");
		ville.setBorder(new EmptyBorder(10,10,10,10));
		ville.setLayout(new BorderLayout());
		ville.add(villeLabel, BorderLayout.WEST);
		nom.setBackground(Color.WHITE);
		prenom.setBackground(Color.WHITE);
		date.setBackground(Color.WHITE);
		ville.setBackground(Color.WHITE);
		nomLabel.setForeground(bleuStyle);
		prenomLabel.setForeground(bleuStyle);
		dateLabel.setForeground(bleuStyle);
		villeLabel.setForeground(bleuStyle);
		supprimerPanel = new JPanel();
		supprimerPanel.setBackground(Color.WHITE);
		supprimer = new JButton("Supprimer ce client");
		supprimer.setBackground(Color.WHITE);
		supprimer.setForeground(bleuStyle);
		supprimer.setFont(font);
		supprimer.addActionListener(this);
		supprimerPanel.setBorder(new EmptyBorder(30,0,30,0));
		supprimerPanel.add(supprimer);
		this.add(nom);
		this.add(prenom);
		this.add(date);
		this.add(ville);
		this.add(supprimerPanel);
		
		
	}
	public void setClientPanel(ClientPanel clientPanel){
		this.clientPanel = clientPanel;
	}
	public void resetLabelValues(){
		if(nomValue != null){
			this.removeAll();
			init();
			this.validate();
			this.repaint();
		}
	}
	public void updateClient(int id){
		resetLabelValues();
		this.currentID = id;
		Client c = FabriqueClient.getInstance().getClientById(id);
		nomValue = new JLabel(c.getNom());
		this.nom.add(nomValue, BorderLayout.EAST);
		prenomValue = new JLabel(String.valueOf(c.getPrenom()));
		dateValue = new JLabel(String.valueOf(c.getDateString()));
		villeValue = new JLabel(FabriqueVille.getInstance().getVilleById(c.getVille()).getNomVille());
		nomValue.setForeground(bleuStyle);
		prenomValue.setForeground(bleuStyle);
		dateValue.setForeground(bleuStyle);
		villeValue.setForeground(bleuStyle);
		dateValue.setForeground(bleuStyle);
		this.nom.add(nomValue,BorderLayout.EAST);
		this.prenom.add(prenomValue,BorderLayout.EAST);
		this.date.add(dateValue,BorderLayout.EAST);
		this.ville.add(villeValue,BorderLayout.EAST);
		this.validate();
		this.repaint();
		
		
	}
	public void suppAction(){
		FabriqueVol.getInstance().deleteVol(currentID);
		resetLabelValues();
		this.clientPanel.suppSelectedVol();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == supprimer){
			suppAction();
		}
	}

}
