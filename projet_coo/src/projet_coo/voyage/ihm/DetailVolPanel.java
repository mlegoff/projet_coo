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

import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class DetailVolPanel extends JPanel implements ActionListener{
	int currentID;
	JLabel departLabel,arriveLabel,dateLabel,dureeLabel,nb2Label,nb1Label,annuLabel,heureLabel;
	JPanel depart,arrive,date,duree,nbpassger2,nbpassager1, annulation,heure;
	VolPanel volPanel;
	JLabel title;
	Font font = new Font("Lato",Font.CENTER_BASELINE,14);
	Color bleuStyle = new Color(7, 174,240);
	JButton supprimer;
	JLabel departValue, arriveValue,nb1,nb2,annuValues,heureValue, dateValue, dureeValue;
	
	public DetailVolPanel( Dimension d){
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
		title = new JLabel("Détail du vol");
		title.setFont(font);
		title.setForeground(Color.white);
		title.setBackground(bleuStyle);
		titlePanel.add(title);
		this.setBorder(BorderFactory.createLineBorder(bleuStyle));
		this.add(titlePanel);
		departLabel = new JLabel("Ville de départ : ");
		depart = new JPanel();
		depart.setBorder(new EmptyBorder(10,10,10,10));
		depart.setLayout(new BorderLayout());
		depart.add(departLabel, BorderLayout.WEST);
		arriveLabel  = new JLabel("Ville d'arrivé : ");
		arrive = new JPanel();
		arrive.setBorder(new EmptyBorder(10,10,10,10));
		arrive.setLayout(new BorderLayout());
		arrive.add(arriveLabel, BorderLayout.WEST);
		dateLabel  = new JLabel("Date : ");
		date = new JPanel();
		date.setBorder(new EmptyBorder(10,10,10,10));
		date.setLayout(new BorderLayout());
		date.add(dateLabel, BorderLayout.WEST);
		heureLabel   = new JLabel("Heure du départ : ");
		heure = new JPanel();
		heure.setBorder(new EmptyBorder(10,10,10,10));
		heure.setLayout(new BorderLayout());
		heure.add(heureLabel, BorderLayout.WEST);
		dureeLabel = new JLabel("Durée du vol (en min) : ");
		duree = new JPanel();
		duree.setBorder(new EmptyBorder(10,10,10,10));
		duree.setLayout(new BorderLayout());
		duree.add(dureeLabel, BorderLayout.WEST);
		nb2Label = new JLabel("Nombre de place en 2nd classe : ");
		nb1Label = new JLabel("Nombre de place en 1ere classe : ");
		annuLabel = new JLabel("Nombre jours annulation : ");
		nbpassager1 = new JPanel();
		nbpassager1.setBorder(new EmptyBorder(10,10,10,10));
		nbpassager1.setLayout(new BorderLayout());
		nbpassager1.add(nb1Label, BorderLayout.WEST);
		nbpassger2 = new JPanel();
		nbpassger2.setBorder(new EmptyBorder(10,10,10,10));
		nbpassger2.setLayout(new BorderLayout());
		nbpassger2.add(nb2Label, BorderLayout.WEST);
		annulation = new JPanel();
		annulation.setBorder(new EmptyBorder(10,10,10,10));
		annulation.setLayout(new BorderLayout());
		annulation.add(annuLabel, BorderLayout.WEST);
		depart.setBackground(Color.WHITE);
		arrive.setBackground(Color.WHITE);
		duree.setBackground(Color.WHITE);
		heure.setBackground(Color.WHITE);
		date.setBackground(Color.WHITE);
		nbpassager1.setBackground(Color.WHITE);
		nbpassger2.setBackground(Color.WHITE);
		annulation.setBackground(Color.WHITE);
		departLabel.setForeground(bleuStyle);
		arriveLabel.setForeground(bleuStyle);
		dureeLabel.setForeground(bleuStyle);
		heureLabel.setForeground(bleuStyle);
		dateLabel.setForeground(bleuStyle);
		nb1Label.setForeground(bleuStyle);
		nb2Label.setForeground(bleuStyle);
		annuLabel.setForeground(bleuStyle);
		supprimer = new JButton("Supprimer ce vol");
		//supprimer.setPreferredSize(new Dimension(this.getPreferredSize().width,40));
		supprimer.setBackground(Color.WHITE);
		supprimer.setForeground(bleuStyle);
		supprimer.setFont(font);
		supprimer.addActionListener(this);
		this.add(depart);
		this.add(arrive);
		this.add(date);
		this.add(heure);
		this.add(duree);
		this.add(nbpassger2);
		this.add(nbpassager1);
		this.add(annulation);
		this.add(supprimer);
		
		
	}
	public void setVolPanel(VolPanel volPanel){
		this.volPanel = volPanel;
	}
	public void resetLabelValues(){
		if(departValue != null){
			this.removeAll();
			init();
			this.validate();
			this.repaint();
		}
	}
	public void updateVol(int id){
		resetLabelValues();
		this.currentID = id;
		Vol v = FabriqueVol.getInstance().getVolById(this.currentID);
		Ville dep = FabriqueVille.getInstance().getVilleById(v.getIdVilleDepart());
		Ville arr = FabriqueVille.getInstance().getVilleById(v.getIdVilleArrivee());
		departValue = new JLabel(dep.getNomVille());
		this.depart.add(departValue, BorderLayout.EAST);
		arriveValue = new JLabel(arr.getNomVille());
		this.arrive.add(arriveValue, BorderLayout.EAST);
		nb1 = new JLabel(String.valueOf(v.getNbPassager1ere()));
		nb2 = new JLabel(String.valueOf(v.getNbPassager1ere()));
		annuValues = new JLabel(String.valueOf(v.getNbJoursAnnulation()));
		dureeValue = new JLabel(String.valueOf(v.getDuree()));
		heureValue = new JLabel(v.getHeure());
		dateValue = new JLabel(v.getDate().toString());
		nb1.setForeground(bleuStyle);
		nb2.setForeground(bleuStyle);
		annuValues.setForeground(bleuStyle);
		dureeValue.setForeground(bleuStyle);
		dateValue.setForeground(bleuStyle);
		heureValue.setForeground(bleuStyle);
		arriveValue.setForeground(bleuStyle);
		departValue.setForeground(bleuStyle);
		this.annulation.add(annuValues,BorderLayout.EAST);
		this.duree.add(dureeValue,BorderLayout.EAST);
		this.nbpassager1.add(nb1,BorderLayout.EAST);
		this.nbpassger2.add(nb2,BorderLayout.EAST);
		this.heure.add(heureValue,BorderLayout.EAST);
		this.date.add(dateValue,BorderLayout.EAST);
		this.validate();
		this.repaint();
		
		
	}
	public void suppAction(){
		FabriqueVol.getInstance().deleteVol(currentID);
		resetLabelValues();
		this.volPanel.suppSelectedVol();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == supprimer){
			suppAction();
		}
	}

}
