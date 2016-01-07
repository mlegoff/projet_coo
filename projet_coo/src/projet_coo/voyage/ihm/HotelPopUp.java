package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Chambre;
import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueChambre;
import projet_coo.voyage.fabrique.FabriqueHotel;
import projet_coo.voyage.fabrique.FabriqueVol;

public class HotelPopUp extends JDialog implements ActionListener,ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
	 Color bleuStyle;
	JScrollPane scrollPane;
	JScrollPane scrollPane2;
	int idVilleDep; int idVilleArr;
	DefaultListModel<Hotel> listModel;
	DefaultListModel<Chambre> listModel2;
	JList<Hotel> list;
	JList<Chambre> list2;
	JPanel panel;
	JButton choisir,annuler;
	Hotel h;
	Chambre c;
	public HotelPopUp(JFrame parent,String title,int idVilleArr){
		super(parent,title,true);
		this.bleuStyle = new Color(7, 174,240);
		this.setLocationRelativeTo(parent);
		h = null;
		c = null;
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.setResizable(false);
		panel = new JPanel();
		this.idVilleArr = idVilleArr;
		initList();
		this.setPreferredSize(new Dimension(400,300));
		this.setSize(new Dimension(400,300));
		JPanel boutons = new JPanel();
//		boutons.setLayout(new BorderLayout());
		boutons.setPreferredSize(new Dimension(400,100));
		choisir = new JButton("Choisir ce vol");
		annuler = new JButton("Annuler");
		choisir.setPreferredSize(new Dimension(100,25));
		annuler.setPreferredSize(new Dimension(100,25));
		choisir.setBackground(bleuStyle);
		annuler.setBackground(bleuStyle);
		choisir.setForeground(Color.WHITE);
		annuler.setForeground(Color.WHITE);
		this.setBackground(Color.white);
		boutons.setBackground(Color.white);
		panel.setBackground(Color.WHITE);
		boutons.setBorder(new EmptyBorder(0,0,0,0));
		boutons.add(choisir);
		boutons.add(annuler);
		
		choisir.addActionListener(this);
		annuler.addActionListener(this);
		panel.add(boutons);
		this.setContentPane(panel);
		this.setVisible(true);
		
	}
	public void initList(){
		List<Hotel> hotels = FabriqueHotel.getInstance().hotelParVille(idVilleArr);
		listModel = new DefaultListModel<Hotel>();
		for(Hotel h : hotels){
			listModel.addElement(h);
		}
		list = new JList<Hotel>(listModel);
		list.setForeground(bleuStyle);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(400,200));
		scrollPane.setBorder(BorderFactory.createLineBorder(bleuStyle));
		scrollPane2 = new JScrollPane();
		scrollPane2.setPreferredSize(new Dimension(400,200));
		scrollPane2.setBorder(BorderFactory.createLineBorder(bleuStyle));
		JPanel scrollPanel = new JPanel();
		scrollPanel.setPreferredSize(new Dimension(800,200));
		scrollPanel.add(scrollPane);
		panel.add(scrollPane);
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!list.getSelectionModel().isSelectionEmpty()) {
			Hotel h = list.getModel().getElementAt(list.getMinSelectionIndex());   
			list2 = new JList<Chambre>();
			listModel2 = new DefaultListModel<Chambre>();
			List<Chambre> chambres = FabriqueChambre.getInstance().chambreParId(h.getId());
			for(Chambre c : chambres){
				listModel2.addElement(c);
			}
			scrollPane2.setViewportView(list);			 
		}else{
			
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == annuler){
			this.c = null;
			this.h = null;
		}
		if(source == choisir){
			this.h = this.list.getModel().getElementAt(list.getSelectedIndex());
			this.c = this.list2.getModel().getElementAt(list2.getSelectedIndex());
		}
		   setVisible(false); 
		    dispose();
		
	}
	public Chambre getChambre(){
	return this.c;
	}
	public Hotel getHotel(){
	return this.h;
	}
}
