package projet_coo.voyage.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import projet_coo.voyage.domaine.Ville;

public class PrincipalPanel  extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel reservation,vol,ville,hotel;
	private JButton reservationButton,volButton,clientButton,hotelButton;
	private LayoutManager layout;
	
	public PrincipalPanel(){
		super();
		initialise();	
	}
	
	public void createButtons(){
		ImageIcon volIcon = createImageIcon("/airplane_icon.png","volIcon");
		volButton = new JButton(volIcon);
		volButton.setBackground(Color.WHITE);
		volButton.setPreferredSize(new Dimension(250, 250));
		volButton.setBorder(new EmptyBorder(0,0,0,0));		
		ImageIcon resaIcon = createImageIcon("/resa_icon.jpg","resaIcon");
		reservationButton = new JButton(resaIcon);
		reservationButton.setBackground(Color.WHITE);
		reservationButton.setPreferredSize(new Dimension(250, 250));
		reservationButton.setBorder(new EmptyBorder(0,0,0,0));
		ImageIcon clientIcon = createImageIcon("/client_icon.png","clientIcon");
		clientButton = new JButton(clientIcon);
		clientButton.setBackground(Color.WHITE);
		clientButton.setPreferredSize(new Dimension(250, 250));
		clientButton.setBorder(new EmptyBorder(0,0,0,0));
		ImageIcon hotelIcon = createImageIcon("/hotel_icon.png","hotelIcon");
		hotelButton = new JButton(hotelIcon);
		hotelButton.setBackground(Color.WHITE);
		hotelButton.setPreferredSize(new Dimension(250, 250));
		hotelButton.setBorder(new EmptyBorder(0,0,0,0));	
		
		this.add(volButton);
		this.add(reservationButton);
		this.add(hotelButton);
		this.add(clientButton);
		
		volButton.addActionListener(this);
		reservationButton.addActionListener(this);
		clientButton.addActionListener(this);
		hotelButton.addActionListener(this);
		
		
	}
	private  ImageIcon createImageIcon(String path, String description) {
	     java.net.URL imgURL = PrincipalPanel.class.getResource(path);
			      
	     if (imgURL != null) {
		    return new ImageIcon(imgURL, description);
		 } else {            
		    System.err.println("Couldn't find file: " + path);
		    return null;
		}
   }
	public void initialise(){		
		this.setBackground(Color.WHITE);
		Border border = this.getBorder();
		Border margin = new EmptyBorder(200,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));
		createButtons();		
	}
	
	public void init2(){
		this.removeAll();
		this.setBackground(Color.WHITE);
		Border border = this.getBorder();
		Border margin = new EmptyBorder(0,0,10,10);
		this.setBorder(new CompoundBorder(border, margin));
		createButtons();	
	}
	
	public void removeButtons(){
		this.remove(clientButton);
		this.remove(volButton);
		this.remove(reservationButton);
		this.remove(hotelButton);
	}
	
	public void reservation(){
		removeButtons();
		this.repaint();
	}
	
	public void client(){
		removeButtons();
		this.repaint();
	}
	
	public void vol(){
		removeButtons();
		this.repaint();
		JInternalFrame fenetre = new JInternalFrame();		
		fenetre.add(new VillePanel(this));
		fenetre.setVisible(true);
		this.add(fenetre);
	}
	
	public void hotel(){
		removeButtons();
		this.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == reservationButton){
			reservation();			
		}
		if(source == volButton){
			vol();
		}
		if(source == clientButton){
			client();
		}
		if(source == hotelButton){
			hotel();
		}
		
	}

}
