package projet_coo.voyage.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Chambre;
import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.fabrique.FabriqueChambre;
import projet_coo.voyage.fabrique.FabriqueHotel;
import projet_coo.voyage.fabrique.FabriqueVille;

public class ChambrePanel extends JPanel implements ActionListener,ListSelectionListener{		
		/**
		 * 
		 */
		private PrincipalPanel JPrinc ;
		private static final long serialVersionUID = 1L;
		private JList<Chambre> lesChambres;
		private DefaultListModel<Chambre> objetChambre;
		private JLabel categorieLabel,tarifLabel,capaciteLabel,nbChambreLabel ;
		private Dimension butdim  = new Dimension(100,20); 
		private JButton ajouter = new JButton("Ajouter");
		private JButton supprimer = new JButton("supprimer");
		private JButton retour = new JButton("Retour");
		private JTextField addChambreCatField,addChambreTarifField,addChambreCapField,addChambrenbField;
		private int hotel;
		
		public ChambrePanel(PrincipalPanel princ) {
			super();
			JPrinc = princ;
			this.initial();		
		}
		
		
		public void initial(){
			this.setPreferredSize(new Dimension(300, 300));
			this.setBackground(Color.BLACK);
			BoxLayout boxlayout = new BoxLayout(this,BoxLayout.Y_AXIS);		
			this.setLayout(boxlayout);	
			
			
			//bouton 
			JPanel boutonpanel = new JPanel();	
			JPanel boutonpanel2 = new JPanel();	
			
			//labels
			categorieLabel = new JLabel(" Categorie : ");
			categorieLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
			categorieLabel.setSize(butdim);		
			
			tarifLabel = new JLabel(" Tarif : ");
			tarifLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
			tarifLabel.setSize(butdim);	
			
			capaciteLabel = new JLabel(" Capacité : ");
			capaciteLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
			capaciteLabel.setSize(butdim);	
			
			nbChambreLabel = new JLabel("Nb chambre : ");
			nbChambreLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
			nbChambreLabel.setSize(butdim);	
			
			
			
			//zone de text
			addChambreCatField = new JTextField();
			this.addChambreCatField.setPreferredSize(new Dimension(150,20));
			addChambreTarifField = new JTextField();
			this.addChambreTarifField.setPreferredSize(new Dimension(150,20));
			addChambreCapField = new JTextField();
			this.addChambreCapField.setPreferredSize(new Dimension(150,20));
			addChambrenbField = new JTextField();
			this.addChambrenbField.setPreferredSize(new Dimension(150,20));
			
			//Les scrolleur
			
			objetChambre = new DefaultListModel<Chambre>();
			lesChambres = new JList<Chambre>(objetChambre);		
			this.lesChambres.setVisibleRowCount(-1);
			this.lesChambres.setModel(objetChambre);
			this.lesChambres.getSelectionModel().addListSelectionListener(this);
			
			JScrollPane panelScrol = new JScrollPane(lesChambres);
			
			boutonpanel.add(categorieLabel);
			boutonpanel.add(addChambreCatField);
			boutonpanel.add(tarifLabel);
			boutonpanel.add(addChambreTarifField);
			boutonpanel.add(capaciteLabel);
			boutonpanel.add(addChambreCapField);
			boutonpanel.add(nbChambreLabel);
			boutonpanel.add(addChambrenbField);
			
			//ajout des listener au bouton
			ajouter.addActionListener(this);
			retour.addActionListener(this);
			supprimer.addActionListener(this);
			
			boutonpanel2.add(ajouter);
			boutonpanel2.add(supprimer);
			boutonpanel2.add(retour);
			
			this.add(panelScrol);	
			this.add(boutonpanel);
			this.add(boutonpanel2);
			
			chambreinit();
			
		}
		
		
		public void setHotel(int id){
			this.hotel = id;
		}		
		
		
		public void chambreinit(){
			objetChambre.clear();
			List<Chambre> allChambres = FabriqueChambre.getInstance().chambreParId(this.hotel);
			for ( Chambre v : allChambres){
				objetChambre.addElement(v);
			}
		}
		
		
		private void ajouter(){
			String cat = addChambreCatField.getText();
			String tarif = addChambreTarifField.getText();
			String cap = addChambreCapField.getText();
			String nb = addChambrenbField.getText();
			cat.trim();tarif.trim();cap.trim();nb.trim();			
			if(cat != null && !cat.equals("") && tarif != null && !tarif.equals("") && cap != null && !cap.equals("") && nb != null && !nb.equals("") && this.hotel != -1){
				int tarif2 = Integer.parseInt(addChambreTarifField.getText(),10);
				int cap2 = Integer.parseInt(addChambreCapField.getText(),10);
				int nb2 = Integer.parseInt(addChambrenbField.getText(),10);
				Chambre newChambre = FabriqueChambre.getInstance().createNewChambre(cap2, tarif2, cat, nb2, this.hotel);
				this.objetChambre.addElement(newChambre);
			}else if(this.hotel == -1){
				JOptionPane.showMessageDialog(null, "Veuillez selectionner un hotel");
			}else{
				JOptionPane.showMessageDialog(null, "Veuillez saisir les infos");
			}
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source =  e.getSource();
			if(source == retour){			
				JPrinc.init2();
				System.out.println("retour");
			}
			else if(source == ajouter){
				ajouter();
			}else if (source == supprimer){
				if(lesChambres.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ville");
				}else{
					int index = lesChambres.getSelectionModel().getMinSelectionIndex();
					int id = lesChambres.getModel().getElementAt(index).getId();
					FabriqueChambre.getInstance().deleteChambre(id);
					this.objetChambre.remove(index);
				}
			}
			
		}


		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
		public void clear(){
			this.hotel = -1;
			chambreinit();
		}
			

}




