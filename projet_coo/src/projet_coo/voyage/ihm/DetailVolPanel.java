package projet_coo.voyage.ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DetailVolPanel extends JPanel implements ActionListener, ListSelectionListener{
	
	VolPanel volPanel;
	JLabel title;
	
	public DetailVolPanel(VolPanel volPanel, Dimension d){
		super();
		this.volPanel = volPanel;
		this.setPreferredSize(d);
		init();
	}
	
	private void init(){
		title = new JLabel("Détail du vol");
		this.add(title);
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
