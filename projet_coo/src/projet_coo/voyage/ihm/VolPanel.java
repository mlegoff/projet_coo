package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class VolPanel extends GestionPanel{
	
JLabel searchLabel,ajoutVol;
JScrollPane scrollPane;
JButton ajouter;
JPanel depart,arrive,date,duree,nbpassger2,nbpassager1, annulation,heure;
JTextField departT,arriveT,dateT,dureeT,nbpassger2T,nbpassager1T, annulationT,heureT;
JLabel departLabel,arriveLabel,dateLabel,dureeLabel,nb2Label,nb1Label,annuLabel,heureLabel;
private JPanel formulaireAjout,searchPanel;
Dimension d;
Color bleuStyle;
DefaultListModel<Vol> listModel;
JList<Vol> list;
DetailVolPanel detailPanel;
Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
JButton retour;

	public VolPanel(Dimension d, DetailVolPanel detailPanel){
		super();
		this.detailPanel = detailPanel;
		this.bleuStyle = new Color(7, 174,240);
		this.d = d;
		this.setPreferredSize(d);
		this.setBackground(bleuStyle);
		this.searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout());
		this.searchPanel.setBackground(bleuStyle);
		searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(400,10));
		searchButton = new JButton("Rechercher");
		searchButton.setBackground(Color.WHITE);
		searchButton.setForeground(bleuStyle);
		searchButton.addActionListener(this);
		searchLabel = new JLabel("Rechercher un vol : ");
		listVolInit();
		searchPanel.setPreferredSize(new Dimension(d.width,50));
		searchPanel.setBorder(new EmptyBorder(10,0,10,0));
		searchPanel.add(searchField,BorderLayout.CENTER);
		searchPanel.add(searchButton,BorderLayout.EAST);
		this.add(searchPanel);
		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(d.width,200));
		scrollPane.setBorder(BorderFactory.createLineBorder(bleuStyle));
		this.add(scrollPane);
		FlowLayout flay = new FlowLayout();
		flay.setHgap(0);
		flay.setVgap(0);
		this.setLayout(flay);
		ajoutInit();

		
	}
	public void setRetourButton(JButton retour){
		this.retour = retour;
		searchPanel.add(retour,BorderLayout.WEST);
	}
	public void searchAction(){
		
		List<Vol> vols = FabriqueVol.getInstance().searchVol(searchField.getText());
		listModel.clear();
		for(Vol v : vols){
			listModel.addElement(v);
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectionModel().isSelectionEmpty()) {
			 System.out.println("Rien n'est selectionne.");				 
		}else{
			int debutIndex = list.getSelectionModel().getMinSelectionIndex();   
			this.detailPanel.updateVol(list.getModel().getElementAt(debutIndex).getId());
			
		}
		
		
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
		list.setForeground(bleuStyle);
		list.setFont(lato);
	}
	
	public void suppSelectedVol(){
		Vol volSupp = this.list.getModel().getElementAt(list.getSelectionModel().getMinSelectionIndex());
		this.listModel.removeElement(volSupp);
		FabriqueVol.getInstance().deleteVol(volSupp.getId());
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
		
		ajouter = new JButton("Ajouter un Vol");
		ajouter.setBackground(Color.WHITE);
		ajouter.setForeground(bleuStyle);
		departLabel = new JLabel("Ville de départ : ");
		departT = new JTextField();
		departLabel.setForeground(Color.WHITE);
		departT.setPreferredSize(new Dimension(d.width - 250,20));
		depart = new JPanel();
		depart.setBorder(new EmptyBorder(10,10,10,10));
		depart.setLayout(new BorderLayout());
		depart.add(departLabel, BorderLayout.WEST);
		depart.add(departT, BorderLayout.EAST);
		depart.setBackground(bleuStyle);
		arriveLabel  = new JLabel("Ville d'arrivé : ");
		arriveT = new JTextField();
		arriveT.setPreferredSize(new Dimension(d.width - 250,20));
		arriveLabel.setForeground(Color.WHITE);
		arrive = new JPanel();
		arrive.setBorder(new EmptyBorder(10,10,10,10));
		arrive.setLayout(new BorderLayout());
		arrive.add(arriveLabel, BorderLayout.WEST);
		arrive.add(arriveT, BorderLayout.EAST);
		arrive.setBackground(bleuStyle);
		dateLabel  = new JLabel("Date (dd/mm/YYYY) : ");
		dateT =  new JTextField();
		dateT.setPreferredSize(new Dimension(d.width - 250,20));
		dateLabel.setForeground(Color.WHITE);
		date = new JPanel();
		date.setBorder(new EmptyBorder(10,10,10,10));
		date.setLayout(new BorderLayout());
		date.add(dateLabel, BorderLayout.WEST);
		date.add(dateT, BorderLayout.EAST);
		date.setBackground(bleuStyle);
		heureLabel   = new JLabel("Heure du départ(hh:mm) : ");
		heureT = new JTextField();
		heureT.setPreferredSize(new Dimension(d.width - 250,20));
		heureLabel.setForeground(Color.WHITE);
		heure = new JPanel();
		heure.setBorder(new EmptyBorder(10,10,10,10));
		heure.setLayout(new BorderLayout());
		heure.add(heureLabel, BorderLayout.WEST);
		heure.add(heureT, BorderLayout.EAST);
		heure.setBackground(bleuStyle);
		dureeLabel = new JLabel("Durée du vol (en min) : ");
		dureeT = new JTextField();
		dureeT.setPreferredSize(new Dimension(d.width - 250,20));
		dureeLabel.setForeground(Color.WHITE);
		duree = new JPanel();
		duree.setBorder(new EmptyBorder(10,10,10,10));
		duree.setLayout(new BorderLayout());
		duree.add(dureeLabel, BorderLayout.WEST);
		duree.add(dureeT, BorderLayout.EAST);
		duree.setBackground(bleuStyle);
		nb2Label = new JLabel("Nombre de place en 2nd classe : ");
		nbpassger2T = new JTextField();
		nbpassger2T.setPreferredSize(new Dimension(d.width - 250,20));
		nb2Label.setForeground(Color.WHITE);
		nbpassger2 = new JPanel();
		nbpassger2.setBorder(new EmptyBorder(10,10,10,10));
		nbpassger2.setLayout(new BorderLayout());
		nbpassger2.add(nb2Label, BorderLayout.WEST);
		nbpassger2.add(nbpassger2T, BorderLayout.EAST);
		nbpassger2.setBackground(bleuStyle);
		nb1Label = new JLabel("Nombre de place en 1ere classe : ");
		nbpassager1T = new JTextField();
		nbpassager1T.setPreferredSize(new Dimension(d.width - 250,20));
		nb1Label.setForeground(Color.WHITE);
		nbpassager1 = new JPanel();
		nbpassager1.setBorder(new EmptyBorder(10,10,10,10));
		nbpassager1.setLayout(new BorderLayout());
		nbpassager1.add(nb1Label, BorderLayout.WEST);
		nbpassager1.add(nbpassager1T, BorderLayout.EAST);
		nbpassager1.setBackground(bleuStyle);
		annuLabel = new JLabel("Nombre jours annulation : ");
		annulationT = new JTextField();
		annulationT.setPreferredSize(new Dimension(d.width - 250,20));
		annuLabel.setForeground(Color.WHITE);
		annulation = new JPanel();
		annulation.setBorder(new EmptyBorder(10,10,10,10));
		annulation.setLayout(new BorderLayout());
		annulation.add(annuLabel, BorderLayout.WEST);
		annulation.add(annulationT, BorderLayout.EAST);
		annulation.setBackground(bleuStyle);
		this.formulaireAjout = new JPanel();
		this.formulaireAjout.setPreferredSize(new Dimension(d.width, 415));
		this.formulaireAjout.add(depart);
		this.formulaireAjout.add(arrive);
		this.formulaireAjout.add(date);
		this.formulaireAjout.add(heure);
		this.formulaireAjout.add(duree);
		this.formulaireAjout.add(nbpassger2);
		this.formulaireAjout.add(nbpassager1);
		this.formulaireAjout.add(annulation);
		this.formulaireAjout.add(ajouter);
		formulaireAjout.setLayout(new BoxLayout(formulaireAjout, BoxLayout.Y_AXIS));
		formulaireAjout.setBackground(bleuStyle);
		formulaireAjout.setForeground(bleuStyle);
		TitledBorder bd = new TitledBorder("Ajout d'un nouveau vol");
		bd.setTitleFont(lato);
		bd.setTitleColor(bleuStyle);
		formulaireAjout.setBorder(new EmptyBorder(0,0,10,0));
		ajouter.addActionListener(this);
		formulaireAjout.validate();
		this.add(formulaireAjout);
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
		dureeInt = Integer.parseInt(dureeT.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Duree invalide");
			
		}
		try{
			nbJoursAnnulationInt = Integer.parseInt(annulationT.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de jours annulation invalide");
		
		}
		try{
			nbPassager1ereInt = Integer.parseInt(nbpassager1T.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de passager de 1ère classe invalide");
		
		}
		try{
			nbPassager2emeInt = Integer.parseInt(nbpassger2T.getText());
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Nombre de passager de 2ème classe invalide");
			
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
			dateParse = df.parse(dateT.getText());
		}
		catch(ParseException pe){
			JOptionPane.showMessageDialog(null, "Date invalide");
		
		}
		DateFormat df2 = new SimpleDateFormat("HH:mm");
		try{
			dateParse2 = df2.parse(heureT.getText());
		}
		catch(ParseException pe){
			JOptionPane.showMessageDialog(null, "Heure invalide");
		}
		if(!FabriqueVille.getInstance().villeExists(this.departT.getText())){
			JOptionPane.showMessageDialog(null, "La ville de départ n'existe pas");
		}
		else{
		if(!FabriqueVille.getInstance().villeExists(this.arriveT.getText())){
			JOptionPane.showMessageDialog(null, "La ville d'arrive n'existe pas");
		}
		else{
		if(duree.equals("") || duree == null || dureeInt<0){
			JOptionPane.showMessageDialog(null, "Duree invalide");
		}
		else{
			int idDepart = FabriqueVille.getInstance().getVilleByName(departT.getText()).getid();
			int idArrive = FabriqueVille.getInstance().getVilleByName(arriveT.getText()).getid();
			Vol vol = FabriqueVol.getInstance().createNewVol(idDepart, idArrive, dateParse, dureeInt,heureT.getText(), nbJoursAnnulationInt, nbPassager1ereInt, nbPassager2emeInt);
			this.listModel.addElement(vol);
		}
		}
		}
	}

}
