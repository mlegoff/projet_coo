package projet_coo.voyage.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import projet_coo.voyage.domaine.Ville;
import projet_coo.voyage.fabrique.FabriqueVille;

public class VillePanel extends JPanel implements ActionListener,ListSelectionListener{

	
	/**
	 * 
	 */
	private PrincipalPanel JPrinc ;
	private static final long serialVersionUID = 1L;
	private JList<Ville> lesvilles;
	private DefaultListModel<Ville> objetVille;
	private JLabel villeLabel ;
	private Dimension butdim  = new Dimension(100,20); 
	private JButton ajouter = new JButton("Ajouter");
	private JButton supprimer = new JButton("supprimer");
	private JButton retour = new JButton("Retour");
	private JTextField addvilleField;
	private HotelPanel hotelPan;
	
	public VillePanel(PrincipalPanel princ,HotelPanel hotelPan) {
		super();
		JPrinc = princ;
		this.initial();		
		this.hotelPan = hotelPan;
	}
	
	
	public void initial(){
		this.setPreferredSize(new Dimension(300, 300));
		this.setBackground(Color.BLACK);
		BoxLayout boxlayout = new BoxLayout(this,BoxLayout.Y_AXIS);		
		this.setLayout(boxlayout);	
		
		
		//bouton 
		JPanel boutonpanel = new JPanel();	
		JPanel boutonpanel2 = new JPanel();	
		
		villeLabel = new JLabel(" Ville : ");
		villeLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,75));
		villeLabel.setSize(butdim);		
		
		
		//zone de text
		addvilleField = new JTextField();
		this.addvilleField.setPreferredSize(new Dimension(150,20));
		
		//Les scrolleur
		
		objetVille = new DefaultListModel<Ville>();
		lesvilles = new JList<Ville>(objetVille);		
		this.lesvilles.setVisibleRowCount(-1);
		this.lesvilles.setModel(objetVille);
		this.lesvilles.getSelectionModel().addListSelectionListener(this);
		
		JScrollPane panelScrol = new JScrollPane(lesvilles);
		
		boutonpanel.add(villeLabel);
		boutonpanel.add(addvilleField);		
		
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
		
		initVille();
		
	}
	
	
	public void initVille(){
		objetVille.clear();
		List<Ville> allVilles = FabriqueVille.getInstance().getVilles();
		for ( Ville v : allVilles){
			objetVille.addElement(v);
		}
	}
	
	
	private void ajouter(){
		String ville = addvilleField.getText();
		ville.trim();
		if(ville != null && !ville.equals("")){
			Ville newville = FabriqueVille.getInstance().createNewVille(ville);
			this.objetVille.addElement(newville);
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
			if(lesvilles.getSelectionModel().isSelectionEmpty()){
				JOptionPane.showMessageDialog(null, "Veuillez selectionner une ville");
			}else{
				int index = lesvilles.getSelectionModel().getMinSelectionIndex();
				int id = lesvilles.getModel().getElementAt(index).getid();
				FabriqueVille.getInstance().deleteVille(id);
				this.objetVille.remove(index);
			}
		}
		
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {		
		this.hotelPan.clear();
		int debutIndex = lesvilles.getSelectionModel().getMinSelectionIndex();   
		this.hotelPan.setVille(lesvilles.getModel().getElementAt(debutIndex).getid());
		this.hotelPan.hotelinit();
		
	}	
		

}
