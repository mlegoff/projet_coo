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
import javax.swing.BoxLayout;
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

	public class RightScroll extends JScrollPane implements ListSelectionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		DefaultListModel<Chambre> listModel2;
		JList<Chambre> list;
		Chambre c;
		@SuppressWarnings("unchecked")
		public RightScroll(){
			super();
			listModel2 = new DefaultListModel<Chambre>();
			list = new JList<Chambre>(listModel2);
			list.addListSelectionListener(this);
			list.setForeground(bleuStyle);
			this.setViewportView(list);
			this.setSize(new Dimension(400,200));
			this.setPreferredSize(new Dimension(400,200));
			this.setBorder(BorderFactory.createLineBorder(bleuStyle));
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!list.isSelectionEmpty()){
				this.c = list.getModel().getElementAt(list.getMinSelectionIndex());
			}
			
		}
		public void update(int id){
			List<Chambre> chambres = FabriqueChambre.getInstance().chambreParId(id);
			listModel2.removeAllElements();
			for(Chambre c : chambres){
				listModel2.addElement(c);
			}
		}
		public Chambre getChambre(){
			return c;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
	 Color bleuStyle;
	int idVilleDep; int idVilleArr;
	DefaultListModel<Hotel> listModel;
	JList<Hotel> list;
	JPanel panel;
	JButton choisir,annuler;
	Hotel h;
	Chambre c;
	int nbV;
	RightScroll rscroll;
	public HotelPopUp(JFrame parent,String title,int idVilleArr, int nbV){
		super(parent,title,true);
		this.nbV = nbV;
		this.bleuStyle = new Color(7, 174,240);
		this.setLocationRelativeTo(parent);
		h = null;
		c = null;
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.setResizable(false);
		panel = new JPanel();
		this.idVilleArr = idVilleArr;
		initList();
		this.setPreferredSize(new Dimension(850,300));
		this.setSize(new Dimension(850,300));
		JPanel boutons = new JPanel();

		boutons.setPreferredSize(new Dimension(850,100));
		choisir = new JButton("Choisir");
		annuler = new JButton("Annuler");
		choisir.setPreferredSize(new Dimension(150,25));
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
		
		rscroll = new RightScroll();
		list.addListSelectionListener(this);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(400,200));
		scrollPane.setSize(new Dimension(400,200));
		scrollPane.setBorder(BorderFactory.createLineBorder(bleuStyle));

		
//		JPanel scrollPanel = new JPanel();
//		scrollPanel.setLayout(new BorderLayout());
//		scrollPanel.setPreferredSize(new Dimension(900,300));
		panel.add(scrollPane);
		panel.add(rscroll);
		
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if (!list.getSelectionModel().isSelectionEmpty()) {
			h = list.getModel().getElementAt(list.getMinSelectionIndex());   
			rscroll.update(h.getId());
	
		}else{
			System.out.println("Rien n'est selectionné");
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == annuler){
			this.c = null;
			this.h = null;
			   setVisible(false); 
			    dispose();
		}
		if(source == choisir){
			this.h = this.list.getModel().getElementAt(list.getSelectedIndex());
			this.c = this.rscroll.getChambre();
			if(c == null || h == null){
				JOptionPane.showMessageDialog(null, "Selectionner un hotel et une chambre");
			}
			else{
			if(c.getCapacite()< nbV){
				JOptionPane.showMessageDialog(null, "Il n'y a pas suffisement de place dans la chambre");
			}
			else{
				   setVisible(false); 
				    dispose();
			}
		}
		}

		
	}
	public Chambre getChambre(){
	return this.c;
	}
	public Hotel getHotel(){
	return this.h;
	}
}
