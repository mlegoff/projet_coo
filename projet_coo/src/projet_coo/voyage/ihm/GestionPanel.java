package projet_coo.voyage.ihm;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public abstract class GestionPanel extends JPanel implements ListSelectionListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelGauche;
	JPanel panelDroite;
	JTextField searchField;
	DefaultListModel listModel;
	JList list;
	JButton searchButton;
	
	

}
