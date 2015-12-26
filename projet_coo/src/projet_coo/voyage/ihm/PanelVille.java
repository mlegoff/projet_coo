package voyage.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projet_coo.voyage.domaine.Ville;

public class PanelVille extends JPanel implements ActionListener,ListSelectionListener{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Ville> annuaireVille ;
	private DefaultListModel<Ville> lmodelannuaire;
	
	
	public PanelVille(){
		super();
		this.init();
	}

	private void init(){
		BoxLayout boxlayout = new BoxLayout(this,BoxLayout.Y_AXIS);
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
