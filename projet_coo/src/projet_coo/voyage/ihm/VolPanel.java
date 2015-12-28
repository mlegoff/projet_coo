package projet_coo.voyage.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class VolPanel extends GestionPanel{
	
JLabel searchLabel;
JScrollPane scrollPane;
JButton ajouter;
JTextField depart,arrive,date,duree,nbpassger2,nbpassager1, annulation,heure;
	
	public VolPanel(Dimension d){
		
		panelDroite = new JPanel();
		panelGauche = new JPanel();
		panelGauche.setBackground(Color.WHITE);
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(50,3));
		searchButton = new JButton("Rechercher");
		searchButton.addActionListener(this);
		searchLabel = new JLabel("Rechercher un vol : ");
		listVolInit();
		panelGauche.add(searchLabel);
		panelGauche.add(searchField);
		panelGauche.add(searchButton);
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(d.width,300));
		panelGauche.add(scrollPane);
		panelGauche.setPreferredSize(d);
		ajoutInit();
		this.add(panelGauche);
		
	}
	public void searchAction(){
		
		searchField.getText();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void listVolInit(){
		this.listModel = new DefaultListModel<Vol>();
		List<Vol> listes = FabriqueVol.getInstance().getAllVol();
		for(Vol v : listes){
			this.listModel.addElement(v);
		}
		this.list =  new JList<Vol>(listModel);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setModel(listModel);
		list.getSelectionModel().addListSelectionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == searchButton){
			searchAction();
		}
		if(source == ajouter){
			ajouterAction();
		}
		
	}
	public void ajoutInit(){
		ajouter = new JButton("Ajouter");
		depart = new JTextField("Ville de départ");
		arrive = new JTextField("Ville d'arrivé");
		date =  new JTextField("Date (dd/mm/YYYY)");
		heure = new JTextField("Heure du départ(hh:mm)");
		duree = new JTextField("Durée du vol");
		nbpassger2 = new JTextField("Nombre de place en 2nd classe");
		nbpassager1 = new JTextField("Nombre de place en 1ere classe");
		annulation = new JTextField("Nombre jours annulation");
		JLabel ajoutVol = new JLabel(" Ajout d'un nouveau vol ");
		ajoutVol.setPreferredSize(new Dimension(this.getWidth(),50));
		this.panelGauche.add(ajoutVol);
		this.panelGauche.add(depart);
		this.panelGauche.add(arrive);
		this.panelGauche.add(date);
		this.panelGauche.add(heure);
		this.panelGauche.add(duree);
		this.panelGauche.add(nbpassger2);
		this.panelGauche.add(nbpassager1);
		this.panelGauche.add(annulation);
		this.panelGauche.add(ajouter);
		ajouter.addActionListener(this);
	}
	public void ajouterAction(){
		int dureeInt = -1;
		int nbJoursAnnulationInt = -1;
		int nbPassager1ereInt = -1;
		int nbPassager2emeInt = -1;
		java.util.Date heureInt = null;
		java.util.Date dateParse = null;
		java.util.Date dateParse2 = null;

		try{
		dureeInt = Integer.parseInt(duree.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Duree invalide");
			
		}
		try{
			nbJoursAnnulationInt = Integer.parseInt(annulation.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de jours annulation invalide");
		
		}
		try{
			nbPassager1ereInt = Integer.parseInt(nbpassager1.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de passager de 1ère classe invalide");
		
		}
		try{
			nbPassager2emeInt = Integer.parseInt(nbpassger2.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de passager de 2ème classe invalide");
			
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
			dateParse = df.parse(date.getText());
		}
		catch(ParseException pe){
			JOptionPane.showMessageDialog(null, "Date invalide");
		
		}
		DateFormat df2 = new SimpleDateFormat("HH:mm");
		try{
			dateParse2 = df2.parse(heure.getText());
		}
		catch(ParseException pe){
			JOptionPane.showMessageDialog(null, "Heure invalide");
		}
		if(!FabriqueVille.getInstance().villeExists(this.depart.getText())){
			JOptionPane.showMessageDialog(null, "La ville de départ n'existe pas");
		}
		else{
		if(!FabriqueVille.getInstance().villeExists(this.arrive.getText())){
			JOptionPane.showMessageDialog(null, "La ville d'arrive n'existe pas");
		}
		else{
		if(duree.equals("") || duree == null || dureeInt<0){
			JOptionPane.showMessageDialog(null, "Duree invalide");
		}
		else{
			int idDepart = FabriqueVille.getInstance().getVilleByName(depart.getText()).getid();
			int idArrive = FabriqueVille.getInstance().getVilleByName(arrive.getText()).getid();
			Vol vol = FabriqueVol.getInstance().createNewVol(idDepart, idArrive, dateParse, dureeInt,heure.getText(), nbJoursAnnulationInt, nbPassager1ereInt, nbPassager2emeInt);
			this.listModel.addElement(vol);
		}
		}
		}
	}

}
