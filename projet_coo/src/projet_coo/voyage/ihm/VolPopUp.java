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

import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueVol;

public class VolPopUp extends JDialog implements ActionListener,ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
	 Color bleuStyle;
	JScrollPane scrollPane;
	int idVilleDep; int idVilleArr;
	DefaultListModel<Vol> listModel;
	JList<Vol> list;
	JPanel panel;
	JButton choisir,annuler;
	Vol v;
	public VolPopUp(JFrame parent,String title,int idVilleDep, int idVilleArr){
		super(parent,title,true);
		this.bleuStyle = new Color(7, 174,240);
		this.setLocationRelativeTo(parent);
		v = null;
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.setResizable(false);
		panel = new JPanel();
		this.idVilleDep = idVilleDep;
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
		List<Vol> vols = FabriqueVol.getInstance().getVolsVilles(idVilleDep,idVilleArr);
		listModel = new DefaultListModel<Vol>();
		for(Vol v : vols){
			listModel.addElement(v);
		}
		list = new JList<Vol>(listModel);
		list.setForeground(bleuStyle);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(400,200));
		scrollPane.setBorder(BorderFactory.createLineBorder(bleuStyle));
		panel.add(scrollPane);
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectionModel().isSelectionEmpty()) {
			 System.out.println("Rien n'est selectionne.");				 
		}else{
			int debutIndex = list.getSelectionModel().getMinSelectionIndex();   
			
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == annuler){
			this.v = null;
		}
		if(source == choisir){
			this.v = this.list.getModel().getElementAt(list.getSelectedIndex());
		}
		   setVisible(false); 
		    dispose();
		
	}
	public Vol getVol(){
	return this.v;
	}
}
