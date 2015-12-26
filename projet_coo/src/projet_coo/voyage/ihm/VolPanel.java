package projet_coo.voyage.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Vol;
import projet_coo.voyage.fabrique.FabriqueVol;

public class VolPanel extends GestionPanel{
	
JLabel searchLabel;
	
	public VolPanel(){
		panelDroite = new JPanel();
		panelGauche = new JPanel();
		searchField = new JTextField();
		searchButton = new JButton("Rechercher");
		searchButton.addActionListener(this);
		searchLabel = new JLabel();
		
	}
	public void searchAction(){
		
		searchField.getText();
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void listVolInit(){
		this.listModel = new DefaultListModel<Vol>();
		List<Vol> listes = FabriqueVol.getInstance().getAllVol();
		for(Vol v : listes){
			this.listModel.addElement(v);
		}
		this.list =  new JList(listModel);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == searchButton){
			searchAction();
		}
		
	}
	
	

}
