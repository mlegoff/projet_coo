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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	JPanel panel, nb1,nb2;
	JTextField nb1T,nb2T;
	JLabel nb1Label,nb2Label;
	JButton choisir,annuler;
	Vol v;
	int nbvoyageur;
	int nb1ere;
	int nb2eme;
	public VolPopUp(JFrame parent,String title,int idVilleDep, int idVilleArr, int nbvoyageur){
		super(parent,title,true);
		this.nbvoyageur = nbvoyageur;
		nb1ere =0;
		nb2eme = 0;
		this.bleuStyle = new Color(7, 174,240);
		this.setLocationRelativeTo(parent);
		v = null;
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.setResizable(false);
		panel = new JPanel();
		
		this.idVilleDep = idVilleDep;
		this.idVilleArr = idVilleArr;
		
		initList();
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(new Dimension(400,400));
		JPanel boutons = new JPanel();
//		boutons.setLayout(new BorderLayout());
		boutons.setPreferredSize(new Dimension(400,100));
		choisir = new JButton("Choisir ce vol");
		annuler = new JButton("Annuler");
		choisir.setPreferredSize(new Dimension(150,25));
		annuler.setPreferredSize(new Dimension(100,25));
		choisir.setBackground(Color.WHITE);
		annuler.setBackground(Color.WHITE);
		choisir.setForeground(bleuStyle);
		annuler.setForeground(bleuStyle);
		this.setBackground(Color.white);
		boutons.setBackground(bleuStyle);
		panel.setBackground(bleuStyle);
		
		nb2Label = new JLabel("Nombre de place en 2nd classe : ");
		nb2T = new JTextField();
		nb2T.setPreferredSize(new Dimension(100,25));
		nb2Label.setForeground(Color.WHITE);
		nb2 = new JPanel();
		nb2.setBorder(new EmptyBorder(10,10,10,10));
		nb2.setLayout(new BorderLayout());
		nb2.add(nb2Label, BorderLayout.WEST);
		nb2.add(nb2T, BorderLayout.EAST);
		nb2.setBackground(bleuStyle);
		
		nb1Label = new JLabel("Nombre de place en 1ere classe : ");
		nb1T = new JTextField();
		nb1T.setPreferredSize(new Dimension(100,25));
		nb1Label.setForeground(Color.WHITE);
		nb1 = new JPanel();
		nb1.setBorder(new EmptyBorder(10,10,10,10));
		nb1.setLayout(new BorderLayout());
		nb1.add(nb1Label, BorderLayout.WEST);
		nb1.add(nb1T, BorderLayout.EAST);
		nb1.setBackground(bleuStyle);
		
		boutons.setBorder(new EmptyBorder(0,0,0,0));
		boutons.add(choisir);
		boutons.add(annuler);
		
		choisir.addActionListener(this);
		annuler.addActionListener(this);
		
		panel.add(nb1);
		panel.add(nb2);
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
	public boolean checkTextField(JTextField jText){
		String txt = jText.getText();
		boolean check = true;
		if(txt == null || txt.equals("")){
			JOptionPane.showMessageDialog(null, "Choisir un nombre de places");
			check = false;
		}
		else{
			try{
				int txtInt = Integer.parseInt(txt);
				if(txtInt<0){
					JOptionPane.showMessageDialog(null, "Nombre de passager invalide");
					check = false;
				}
			}
			catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Nombre de passager invalide");
				check = false;
			}
		}
		return check;
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
			   setVisible(false); 
			    dispose();
		}
		if(source == choisir){
			if(list.getSelectionModel().isSelectionEmpty()){
				JOptionPane.showMessageDialog(null, "Aucun vol n'est selectionné");
			}
			else{
			this.v = this.list.getModel().getElementAt(list.getSelectedIndex());
			if(checkTextField(this.nb1T) && checkTextField(this.nb2T )){
				int nb1Int = Integer.parseInt(this.nb1T.getText());
				int nb2Int = Integer.parseInt(this.nb2T.getText());
				if(nb1Int<= v.getNbPassager1ere() && nb2Int<=v.getNbPassager2eme()){
					nb1ere = nb1Int;
					nb2eme = nb2Int;
				   setVisible(false); 
				    dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Nombre de place invalide");
				}
			}
		}
		}

		
	}
	public int getNb1ere() {
		return nb1ere;
	}
	public int getNb2eme() {
		return nb2eme;
	}
	public Vol getVol(){
	return this.v;
	}
}
