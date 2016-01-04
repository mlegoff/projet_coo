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

import projet_coo.voyage.domaine.Client;
import projet_coo.voyage.fabrique.FabriqueClient;
import projet_coo.voyage.fabrique.FabriqueVille;

public class ClientPanel extends GestionPanel{
	
JLabel searchLabel,ajoutClient;
JScrollPane scrollPane;
JButton ajouter;
JPanel nom,prenom,date,ville,ajouterPanel;
JTextField nomT,prenomT,dateT,villeT;
JLabel nomLabel,prenomLabel,dateLabel,villeLabel;
private JPanel formulaireAjout,searchPanel;
Dimension d;
Color bleuStyle;
DefaultListModel<Client> listModel;
JList<Client> list;
DetailClientPanel detailPanel;
Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
JButton retour;

	public ClientPanel(Dimension d, DetailClientPanel detailPanel){
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
		searchLabel = new JLabel("Rechercher un client : ");
		listClientInit();
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
		
		List<Client> clients = FabriqueClient.getInstance().searchClient(searchField.getText());
		listModel.clear();
		for(Client c : clients){
			listModel.addElement(c);
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectionModel().isSelectionEmpty()) {
			 System.out.println("Rien n'est selectionne.");				 
		}else{
			int debutIndex = list.getSelectionModel().getMinSelectionIndex();   
			Client idclient = list.getModel().getElementAt(debutIndex);
			this.detailPanel.updateClient(idclient.getId());
			
		}
		
		
	}
	public void listClientInit(){
		this.listModel = new DefaultListModel<Client>();
		List<Client> listes = FabriqueClient.getInstance().getAllClients();
		for(Client c : listes){
			this.listModel.addElement(c);
		}
		this.list =  new JList<Client>(listModel);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setModel(listModel);
		list.getSelectionModel().addListSelectionListener(this);
		list.setForeground(bleuStyle);
		list.setFont(lato);
	}
	
	public void suppSelectedVol(){
		Client clientSupp = this.list.getModel().getElementAt(list.getSelectionModel().getMinSelectionIndex());
		this.listModel.removeElement(clientSupp);
		FabriqueClient.getInstance().deleteClient(clientSupp.getId());
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
		
		ajouter = new JButton("Ajouter un Client");
		ajouter.setBackground(Color.WHITE);
		ajouter.setForeground(bleuStyle);
		ajouterPanel = new JPanel();
		ajouterPanel.setBackground(bleuStyle);
		ajouterPanel.add(ajouter);
		ajouterPanel.setBorder(new EmptyBorder(22,10,22,10));
		nomLabel = new JLabel("Nom : ");
		nomT = new JTextField();
		nomLabel.setForeground(Color.WHITE);
		nomT.setPreferredSize(new Dimension(d.width - 250,20));
		nom = new JPanel();
		nom.setBorder(new EmptyBorder(22,10,22,10));
		nom.setLayout(new BorderLayout());
		nom.add(nomLabel, BorderLayout.WEST);
		nom.add(nomT, BorderLayout.EAST);
		nom.setBackground(bleuStyle);
		nom.setPreferredSize(new Dimension(d.width ,10));
		prenomLabel  = new JLabel("Prenom : ");
		prenomT = new JTextField();
		prenomT.setPreferredSize(new Dimension(d.width - 250,10));
		prenomLabel.setForeground(Color.WHITE);
		prenom = new JPanel();
		prenom.setBorder(new EmptyBorder(22,10,22,10));
		prenom.setLayout(new BorderLayout());
		prenom.setPreferredSize(new Dimension(d.width ,10));
		prenom.add(prenomLabel, BorderLayout.WEST);
		prenom.add(prenomT, BorderLayout.EAST);
		prenom.setBackground(bleuStyle);
		dateLabel  = new JLabel("Date de naissance (dd/mm/YYYY) : ");
		dateT =  new JTextField();
		dateT.setPreferredSize(new Dimension(d.width - 250,20));
		dateLabel.setForeground(Color.WHITE);
		date = new JPanel();
		date.setBorder(new EmptyBorder(22,10,22,10));
		date.setLayout(new BorderLayout());
		date.add(dateLabel, BorderLayout.WEST);
		date.add(dateT, BorderLayout.EAST);
		date.setBackground(bleuStyle);
		date.setPreferredSize(new Dimension(d.width ,10));
		villeLabel = new JLabel("Ville : ");
		villeT = new JTextField();
		villeT.setPreferredSize(new Dimension(d.width - 250,20));
		villeLabel.setForeground(Color.WHITE);
		ville = new JPanel();
		ville.setBorder(new EmptyBorder(22,10,22,10));
		ville.setLayout(new BorderLayout());
		ville.add(villeLabel, BorderLayout.WEST);
		ville.add(villeT, BorderLayout.EAST);
		ville.setBackground(bleuStyle);
		ville.setPreferredSize(new Dimension(d.width ,10));
		this.formulaireAjout = new JPanel();
		this.formulaireAjout.setPreferredSize(new Dimension(d.width, 415));
		this.formulaireAjout.add(nom);
		this.formulaireAjout.add(prenom);
		this.formulaireAjout.add(date);
		this.formulaireAjout.add(ville);
		this.formulaireAjout.add(ajouterPanel);
		formulaireAjout.setLayout(new BoxLayout(formulaireAjout, BoxLayout.Y_AXIS));
		formulaireAjout.setBackground(bleuStyle);
		formulaireAjout.setForeground(bleuStyle);
		TitledBorder bd = new TitledBorder("Ajout d'un nouveau vol");
		bd.setTitleFont(lato);
		bd.setTitleColor(bleuStyle);
		formulaireAjout.setBorder(new EmptyBorder(30,0,30,0));
		ajouter.addActionListener(this);
		formulaireAjout.validate();
		this.add(formulaireAjout);
	}
	public void ajouterAction(){
		
		java.util.Date dateParse = null;	
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
			dateParse = df.parse(dateT.getText());
		}
		catch(ParseException pe){
			JOptionPane.showMessageDialog(null, "Date invalide");
		
		}
		
		if(!FabriqueVille.getInstance().villeExists(this.villeT.getText())){
			JOptionPane.showMessageDialog(null, "La ville n'existe pas");
		}
		
		else{
			Client c = null;
			if(!nomT.getText().equals("") && !(nomT.getText() == null) && !prenomT.getText().equals("") && !(prenomT.getText() == null) )
			c = FabriqueClient.getInstance().createNewClient(nomT.getText(), prenomT.getText(), FabriqueVille.getInstance().getVilleByName(villeT.getText()).getid(), dateParse) ;
			this.listModel.addElement(c);
			resetTextFields();
			this.validate();
			
		}
		
		}
	
	public void resetTextFields(){
		this.dateT.setText(null);
		this.nomT.setText(null);
		this.prenomT.setText(null);
		this.villeT.setText(null);
		
	}
}
