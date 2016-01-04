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

import projet_coo.voyage.domaine.Chambre;
import projet_coo.voyage.domaine.Client;
import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Reservation;
import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueChambre;
import projet_coo.voyage.fabrique.FabriqueClient;
import projet_coo.voyage.fabrique.FabriqueHotel;
import projet_coo.voyage.fabrique.FabriqueReservation;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class DetailResaPanel extends JPanel implements ActionListener{
	
	int currentID;
	ResaPanel resaPanel;
	JLabel title;
	Font font = new Font("Lato",Font.CENTER_BASELINE,14);
	Color bleuStyle = new Color(7, 174,240);
	JButton supprimer;
	JPanel depart,arrive,volDepart,volArrive,voyageur,chambre,hotel,client,supprimerPanel;
	JLabel departLabel,volDLabel,arriveLabel,volALabel,voyageurLabel,chambreLabel,hotelLabel,clientLabel;
	JLabel departValue,arriveValue,volDepartValue,volArriveValue,voyageurValue,chambreValue,hotelValue,clientValue;
	
	public DetailResaPanel( Dimension d){
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
		title = new JLabel("Reservation");
		title.setFont(font);
		title.setForeground(Color.white);
		title.setBackground(bleuStyle);
		titlePanel.add(title);
		this.setBorder(BorderFactory.createLineBorder(bleuStyle));
		this.add(titlePanel);
		
		depart = new JPanel();
		arrive  = new JPanel();
		volDepart  = new JPanel();
		volArrive = new JPanel();
		voyageur = new JPanel();
		chambre = new JPanel();
		hotel = new JPanel();
		client  = new JPanel();
		
		departLabel = new JLabel("Ville de départ :");
		arriveLabel = new JLabel("Ville de retour :");
		volDLabel = new JLabel("Vol de départ :");
		volALabel = new JLabel("Vol de retour :");
		voyageurLabel = new JLabel("Nombre de voyageurs :");
		chambreLabel = new JLabel("Chambre reservée :");
		hotelLabel = new JLabel("Hotel :");
		clientLabel = new JLabel("Dossier client :");

		depart.setBorder(new EmptyBorder(10,10,10,10));
		depart.setLayout(new BorderLayout());
		depart.add(departLabel, BorderLayout.WEST);

		arrive.setBorder(new EmptyBorder(10,10,10,10));
		arrive.setLayout(new BorderLayout());
		arrive.add(arriveLabel, BorderLayout.WEST);

		
		volDepart.setBorder(new EmptyBorder(10,10,10,10));
		volDepart.setLayout(new BorderLayout());
		volDepart.add(volDLabel, BorderLayout.WEST);

		volArrive.setBorder(new EmptyBorder(10,10,10,10));
		volArrive.setLayout(new BorderLayout());
		volArrive.add(volALabel, BorderLayout.WEST);
		
		hotel.setBorder(new EmptyBorder(10,10,10,10));
		hotel.setLayout(new BorderLayout());
		hotel.add(hotelLabel, BorderLayout.WEST);
		
		chambre.setBorder(new EmptyBorder(10,10,10,10));
		chambre.setLayout(new BorderLayout());
		chambre.add(chambreLabel, BorderLayout.WEST);
		
		client.setBorder(new EmptyBorder(10,10,10,10));
		client.setLayout(new BorderLayout());
		client.add(clientLabel, BorderLayout.WEST);
		
		voyageur.setBorder(new EmptyBorder(10,10,10,10));
		voyageur.setLayout(new BorderLayout());
		voyageur.add(voyageurLabel, BorderLayout.WEST);
		
		depart.setBackground(Color.WHITE);
		arrive.setBackground(Color.WHITE);
		volDepart.setBackground(Color.WHITE);
		volArrive.setBackground(Color.WHITE);
		hotel.setBackground(Color.WHITE);
		chambre.setBackground(Color.WHITE);
		voyageur.setBackground(Color.WHITE);
		client.setBackground(Color.WHITE);
		
		departLabel.setForeground(bleuStyle);
		arriveLabel.setForeground(bleuStyle);
		volDLabel.setForeground(bleuStyle);
		volALabel.setForeground(bleuStyle);
		hotelLabel.setForeground(bleuStyle);
		chambreLabel.setForeground(bleuStyle);
		voyageurLabel.setForeground(bleuStyle);
		clientLabel.setForeground(bleuStyle);
		
		supprimerPanel = new JPanel();
		supprimerPanel.setBackground(Color.WHITE);
		supprimer = new JButton("Supprimer cette réservation");
		supprimer.setBackground(Color.WHITE);
		supprimer.setForeground(bleuStyle);
		supprimer.setFont(font);
		supprimer.addActionListener(this);
		supprimerPanel.setBorder(new EmptyBorder(30,0,30,0));
		supprimerPanel.add(supprimer);
		this.add(depart);
		this.add(arrive);
		this.add(volDepart);
		this.add(volArrive);
		this.add(hotel);
		this.add(chambre);
		this.add(client);
		this.add(voyageur);
		this.add(supprimerPanel);
		
		
	}
	public void setClientPanel(ResaPanel resaPanel){
		this.resaPanel = resaPanel;
	}
	public void resetLabelValues(){
		if(departValue != null){
			this.removeAll();
			init();
			this.validate();
			this.repaint();
		}
	}
	public void updateResa(int id){
		resetLabelValues();
		this.currentID = id;
		Reservation resa = FabriqueReservation.getInstance().getResaById(currentID);
		Client c = FabriqueClient.getInstance().getClientById(resa.getIdClient());
		Ville d = FabriqueVille.getInstance().getVilleById(resa.getVilleDepart());
		Ville a = FabriqueVille.getInstance().getVilleById(resa.getVilleArrive());
		Vol volDep = FabriqueVol.getInstance().getVolById(resa.getIdVolDepart());
		Vol volArr = FabriqueVol.getInstance().getVolById(resa.getIdVolRetour());
		Chambre ch = FabriqueChambre.getInstance().getChambreParId(resa.getIdchambre());
		Hotel ho = FabriqueHotel.getInstance().hotelParId(ch.getIdHotel());
		
		departValue = new JLabel(d.getNomVille());
		arriveValue = new JLabel(a.getNomVille());
		volDepartValue = new JLabel(volDep.toString());
		volArriveValue = new JLabel(volArr.toString());
		clientValue = new JLabel(c.toString());
		hotelValue = new JLabel(ho.getNom());
		chambreValue = new JLabel(ch.toString());
		voyageurValue = new JLabel(String.valueOf(resa.getNbVoyageurs()));

		departValue.setForeground(bleuStyle);
		arriveValue.setForeground(bleuStyle);
		volDepartValue.setForeground(bleuStyle);
		volArriveValue.setForeground(bleuStyle);
		clientValue.setForeground(bleuStyle);
		hotelValue.setForeground(bleuStyle);
		chambreValue.setForeground(bleuStyle);
		voyageurValue.setForeground(bleuStyle);
		
		this.depart.add(departValue, BorderLayout.EAST);
		this.arrive.add(arriveValue,BorderLayout.EAST);
		this.volDepart.add(volDepartValue,BorderLayout.EAST);
		this.volArrive.add(volArriveValue,BorderLayout.EAST);
		this.client.add(clientValue,BorderLayout.EAST);
		this.hotel.add(hotelValue,BorderLayout.EAST);
		this.chambre.add(chambreValue,BorderLayout.EAST);
		this.voyageur.add(voyageurValue,BorderLayout.EAST);
		this.validate();
		this.repaint();
		
		
	}
	public void suppAction(){
		FabriqueVol.getInstance().deleteVol(currentID);
		resetLabelValues();
		this.resaPanel.suppSelectedResa();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == supprimer){
			suppAction();
		}
	}

}
