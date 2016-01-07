package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Chambre;
import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Reservation;
import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueReservation;
import projet_coo.voyage.fabrique.FabriqueVille;
import projet_coo.voyage.fabrique.FabriqueVol;

public class ResaPanel extends GestionPanel{
	
/**
	 * 
	 */
JLabel searchLabel,ajoutResa;
JScrollPane scrollPane;
JButton ajouter;
JPanel depart,arrive,voyageur;
JTextField departT,arriveT,voyageurT;
JLabel departLabel,arriveLabel,voyageurLabel;
private JPanel formulaireAjout,searchPanel;
Dimension d;
Color bleuStyle;
DefaultListModel<Reservation> listModel;
JList<Reservation> list;
DetailResaPanel detailPanel;
Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
JButton retour;
VolPopUp pop;
HotelPopUp pop2;
JPanel ajouterPanel;
int nbV;
	public ResaPanel(Dimension d, DetailResaPanel detailPanel){
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
		searchLabel = new JLabel("Rechercher un reservation : ");
		listResaInit();
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
		
		List<Reservation> reservations = FabriqueReservation.getInstance().searchResa(searchField.getText());
		listModel.clear();
		for(Reservation r : reservations){
			listModel.addElement(r);
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectionModel().isSelectionEmpty()) {
			 System.out.println("Rien n'est selectionne.");				 
		}else{
			int debutIndex = list.getSelectionModel().getMinSelectionIndex();   
			this.detailPanel.updateResa(list.getModel().getElementAt(debutIndex).getId());
			
		}
		
		
	}
	public void listResaInit(){
		this.listModel = new DefaultListModel<Reservation>();
		List<Reservation> reservations = FabriqueReservation.getInstance().getAllResa();
		for(Reservation r: reservations){
			this.listModel.addElement(r);
		}
		this.list =  new JList<Reservation>(listModel);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setModel(listModel);
		list.getSelectionModel().addListSelectionListener(this);
		list.setForeground(bleuStyle);
		list.setFont(lato);
	}
	
	public void suppSelectedResa(){
		Reservation volSupp = this.list.getModel().getElementAt(list.getSelectionModel().getMinSelectionIndex());
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
			nbV = -1;
			try {
				if(this.departT.getText() == null || this.departT.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Saisir la ville de départ");
				}
				if(!FabriqueVille.getInstance().villeExists(departT.getText())){
					JOptionPane.showMessageDialog(null, "La ville de départ n'existe pas");
				}
				if(this.arriveT.getText() == null || this.arriveT.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Saisir la ville d'arrivé ");
				}
				if(!FabriqueVille.getInstance().villeExists(arriveT.getText())){
					JOptionPane.showMessageDialog(null, "La ville d'arrivé n'existe pas");
				}
				if(this.voyageurT.getText() == null || this.voyageurT.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Saisir un nombre de voyageur");
				}
				else{
				try{
					nbV = Integer.parseInt(this.voyageurT.getText());
					if(nbV<1){
						JOptionPane.showMessageDialog(null, "Nombre de voyageur invalide");
					}
					else{
						ajouterAction();
					}
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Nombre de voyageur invalide");
					
				}
				}
				ajouterAction();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public void ajoutInit(){
		
		ajouter = new JButton("Choisir le Vol aller");
		ajouter.setBackground(Color.WHITE);
		ajouter.setForeground(bleuStyle);
		ajouterPanel = new JPanel();
		ajouterPanel.setBackground(bleuStyle);
		ajouterPanel.add(ajouter);
		ajouterPanel.setBorder(new EmptyBorder(22,10,22,10));
		departLabel = new JLabel("Ville de départ : ");
		departT = new JTextField();
		departLabel.setForeground(Color.WHITE);
		departT.setPreferredSize(new Dimension(d.width - 250,20));
		depart = new JPanel();
		depart.setBorder(new EmptyBorder(15,10,10,10));
		depart.setLayout(new BorderLayout());
		depart.add(departLabel, BorderLayout.WEST);
		depart.add(departT, BorderLayout.EAST);
		depart.setBackground(bleuStyle);
		depart.setPreferredSize(new Dimension(d.width,25));
		depart.setSize(new Dimension(d.width,25));
		arriveLabel  = new JLabel("Ville d'arrivé : ");
		arriveT = new JTextField();
		arriveT.setPreferredSize(new Dimension(d.width - 250,20));
		arriveLabel.setForeground(Color.WHITE);
		arrive = new JPanel();
		arrive.setPreferredSize(new Dimension(d.width,25));
		arrive.setSize(new Dimension(d.width,25));
		arrive.setBorder(new EmptyBorder(15,10,10,10));
		arrive.setLayout(new BorderLayout());
		arrive.add(arriveLabel, BorderLayout.WEST);
		arrive.add(arriveT, BorderLayout.EAST);
		arrive.setBackground(bleuStyle);;
		voyageur = new JPanel();
		voyageur.setBackground(bleuStyle);
		voyageur.setPreferredSize(new Dimension(d.width,25));
		voyageur.setSize(new Dimension(d.width,25));
		voyageurLabel = new JLabel("Nombre de voyageur");
		voyageurLabel.setForeground(Color.WHITE);
		voyageurT = new JTextField();
		voyageurT.setPreferredSize(new Dimension(d.width - 250,20));
		voyageur.setBorder(new EmptyBorder(15,10,10,10));
		voyageur.setLayout(new BorderLayout());
		voyageur.add(voyageurLabel,BorderLayout.WEST);
		voyageur.add(voyageurT, BorderLayout.EAST);
		
		this.formulaireAjout = new JPanel();
		this.formulaireAjout.setPreferredSize(new Dimension(d.width, 150));
		this.formulaireAjout.add(depart);
		this.formulaireAjout.add(arrive);
		this.formulaireAjout.add(voyageur);
		
		formulaireAjout.setLayout(new BoxLayout(formulaireAjout, BoxLayout.Y_AXIS));
		formulaireAjout.setBackground(bleuStyle);
		formulaireAjout.setForeground(bleuStyle);
		ajouterPanel.setBackground(bleuStyle);
		ajouterPanel.setPreferredSize(new Dimension(d.width,100));
		TitledBorder bd = new TitledBorder("Ajout d'un nouveau vol");
		bd.setTitleFont(lato);
		bd.setTitleColor(bleuStyle);
		formulaireAjout.setBorder(new EmptyBorder(0,0,0,0));
		ajouter.addActionListener(this);
		formulaireAjout.validate();
		this.add(formulaireAjout);
		this.add(ajouterPanel);
	}
	
	public void resetTextFields(){
		this.arriveT.setText(null);
		this.departT.setText(null);
		
	}
	
	@SuppressWarnings("unused")
	public synchronized void ajouterAction() throws InterruptedException{
		int idDep = FabriqueVille.getInstance().getVilleByName(this.departT.getText()).getid();
		int idArr = FabriqueVille.getInstance().getVilleByName(this.arriveT.getText()).getid();
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		pop = new VolPopUp(topFrame,"Vol aller",idDep,idArr, nbV);
		Vol dep = pop.getVol();
		Vol arr = null;
		JPanel volD = new JPanel();
		JPanel volA = new JPanel();
		System.out.println(dep.toString());
		if(dep != null){
			this.remove(ajouterPanel);
			
			volD.setLayout(new BorderLayout());
			volD.setPreferredSize(new Dimension(d.width,30));
			JLabel label1 = new JLabel("Vol aller : ");
			label1.setForeground(Color.white);
			volD.add(label1, BorderLayout.WEST);
			JLabel volDepLabel = new JLabel(dep.toString());
			volDepLabel.setForeground(Color.WHITE);
			volD.setBackground(bleuStyle);
			volD.add(volDepLabel, BorderLayout.EAST);
			volD.setBorder(new EmptyBorder(10,10,10,10));
			this.add(volD);
			repaint();
			revalidate();
			if(dep.getNbPassager1ere()+dep.getNbPassager2eme() < Integer.parseInt(this.voyageurT.getText())){
				JOptionPane.showMessageDialog(null, "Il n'y a plus assez de place sur ce vol");
			}
			else{
			pop = new VolPopUp(topFrame,"Vol retour",idArr,idDep,nbV);
			arr = pop.getVol();
			if(arr != null){
				System.out.println(arr.toString());
				volA = new JPanel();
				volA.setLayout(new BorderLayout());
				volA.setPreferredSize(new Dimension(d.width,30));
				JLabel label2 = new JLabel("Vol reour : ");
				label2.setForeground(Color.WHITE);
				volA.add(label2, BorderLayout.WEST);
				JLabel volArrLabel = new JLabel(dep.toString());
				volArrLabel.setForeground(Color.WHITE);
				volA.setBackground(bleuStyle);
				volA.setBorder(new EmptyBorder(10,10,10,10));
				volA.add(volArrLabel, BorderLayout.EAST);
				this.add(volA);
				this.repaint();
				this.validate();
				if(arr.getNbPassager1ere()+arr.getNbPassager2eme() < Integer.parseInt(this.voyageurT.getText())){
					JOptionPane.showMessageDialog(null, "Il n'y a plus assez de place sur ce vol");
				}
				else{
				pop2 = new HotelPopUp(topFrame,"Choississez un Hotel et une Chambre",idArr);
				Hotel h = pop2.getHotel();
				if(h != null){
					Chambre c = pop2.getChambre();
					JPanel chPanel = new JPanel();
					JPanel hPanel = new JPanel();
					JLabel chLabel = new JLabel("Chambre :");
					JLabel hLabel = new JLabel("Hotel :");
					JLabel chLabel2 = new JLabel(c.getCategorie()+" "+c.getCapacite());
					JLabel hLabel2 = new JLabel(h.getNom());
				}
			}
			}
			}
		}
			else{
				this.remove(formulaireAjout);
				this.remove(volD);
				this.remove(volA);
				this.repaint();
				this.revalidate();
				this.ajoutInit();
				this.repaint();
				this.validate();
			}
		}	
		
		
	

}
