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
JPanel depart,arrive,volDepart,volArrive,voyageur,chambre,hotel,client;
JTextField departT,arriveT,dateT,dureeT,nbpassger2T,nbpassager1T, annulationT,heureT;
JLabel departLabel,arriveLabel,dateLabel,dureeLabel,nb2Label,nb1Label,annuLabel,heureLabel;
private JPanel formulaireAjout,searchPanel;
Dimension d;
Color bleuStyle;
DefaultListModel<Reservation> listModel;
JList<Reservation> list;
DetailResaPanel detailPanel;
Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
JButton retour;
VolPopUp pop;
JPanel ajouterPanel;
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
			try {
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


		this.formulaireAjout = new JPanel();
		this.formulaireAjout.setPreferredSize(new Dimension(d.width, 100));
		this.formulaireAjout.add(depart);
		this.formulaireAjout.add(arrive);
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
	public synchronized void ajouterAction() throws InterruptedException{
		int idDep = FabriqueVille.getInstance().getVilleByName(this.departT.getText()).getid();
		int idArr = FabriqueVille.getInstance().getVilleByName(this.arriveT.getText()).getid();
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		pop = new VolPopUp(topFrame,"Vol aller",idDep,idArr);
		Vol dep = pop.getVol();
		Vol arr = null;
		System.out.println(dep.toString());
		if(dep != null){
			this.remove(ajouterPanel);
			JPanel volD = new JPanel();
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
			pop = new VolPopUp(topFrame,"Vol retour",idArr,idDep);
			arr = pop.getVol();
			if(arr != null){
				System.out.println(arr.toString());
				JPanel volA = new JPanel();
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
			}
			else{
				this.remove(formulaireAjout);
				this.remove(volD);
				this.repaint();
				this.revalidate();
				this.ajoutInit();
				this.repaint();
				this.validate();
			}
		}
//		JOptionPane selectVol = new JOptionPane();
//		List<Vol>FabriqueVol.getInstance().getAllVol();
//		DefaultListModel model = new DefaultListModel();
//		JList list = new JList();
//		JScrollPane scrollPanePop = new JScrollPane();
		
		
	}

}
