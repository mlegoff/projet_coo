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

import projet_coo.voyage.domaine.Hotel;
import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.fabrique.FabriqueHotel;
import projet_coo.voyage.fabrique.FabriqueVille;

public class HotelPanel  extends JPanel implements ActionListener,ListSelectionListener{		
		/**
		 * 
		 */
		private PrincipalPanel JPrinc ;
		private static final long serialVersionUID = 1L;
		private JList<Hotel> lesHotels;
		private DefaultListModel<Hotel> objetHotel;
		private JLabel HotelNomLabel ;
		private Dimension butdim  = new Dimension(100,20); 
		private JButton ajouter = new JButton("Ajouter");
		private JButton supprimer = new JButton("supprimer");
		private JButton retour = new JButton("Retour");
		private JTextField addHotelNomField;
		private int ville;
		private ChambrePanel chambrePane;
		
		public HotelPanel(PrincipalPanel princ,ChambrePanel chambrePane) {
			super();
			JPrinc = princ;
			this.initial();		
			this.chambrePane = chambrePane;			
		}
		
		
		public void initial(){
			this.setPreferredSize(new Dimension(300, 300));
			this.setBackground(Color.BLACK);
			BoxLayout boxlayout = new BoxLayout(this,BoxLayout.Y_AXIS);		
			this.setLayout(boxlayout);	
			
			
			//bouton 
			JPanel boutonpanel = new JPanel();	
			JPanel boutonpanel2 = new JPanel();	
			
			HotelNomLabel = new JLabel(" Hotel : ");
			HotelNomLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,75));
			HotelNomLabel.setSize(butdim);		
			
			
			//zone de text
			addHotelNomField = new JTextField();
			this.addHotelNomField.setPreferredSize(new Dimension(150,20));
			
			//Les scrolleur
			
			objetHotel = new DefaultListModel<Hotel>();
			lesHotels = new JList<Hotel>(objetHotel);		
			this.lesHotels.setVisibleRowCount(-1);
			this.lesHotels.setModel(objetHotel);
			this.lesHotels.getSelectionModel().addListSelectionListener(this);
			
			JScrollPane panelScrol = new JScrollPane(lesHotels);
			
			boutonpanel.add(HotelNomLabel);
			boutonpanel.add(addHotelNomField);		
			
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
			
			hotelinit();
			
		}
		
		
		public void setVille(int id){
			this.ville = id;
		}
		
		
		
		public void hotelinit(){
			objetHotel.clear();
			List<Hotel> allHotels = FabriqueHotel.getInstance().hotelParVille(this.ville);
			for ( Hotel v : allHotels){
				objetHotel.addElement(v);
			}
		}
		
		
		private void ajouter(){
			String hotel = addHotelNomField.getText();
			hotel.trim();
			if(hotel != null && !hotel.equals("")){
				Hotel newhotel = FabriqueHotel.getInstance().createNewHotel(hotel, this.ville);
				objetHotel.addElement(newhotel);				
			}else{
				JOptionPane.showMessageDialog(null, "Veuillez saisir une ville");
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
				if(lesHotels.getSelectionModel().isSelectionEmpty()){
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ville");
				}else{
					int index = lesHotels.getSelectionModel().getMinSelectionIndex();
					int id = lesHotels.getModel().getElementAt(index).getId();
					FabriqueHotel.getInstance().deleteHotel(id);
					this.objetHotel.remove(index);
				}
			}
			
		}


		@Override
		public void valueChanged(ListSelectionEvent e) {			
			if (lesHotels.getSelectionModel().isSelectionEmpty()) {
				 System.out.println("Rien n'est selectionne.");				 
			}else{
				int debutIndex = lesHotels.getSelectionModel().getMinSelectionIndex();   
				this.chambrePane.setHotel(lesHotels.getModel().getElementAt(debutIndex).getId());
				this.chambrePane.chambreinit();
			}
			
		}	
		
				

}


