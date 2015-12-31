package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class PrincipalPanel  extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel reservation,vol,ville,hotel;
	private JButton reservationButton,volButton,clientButton,hotelButton, retourButton;
	private LayoutManager layout;
	private Color bleuStyle = new Color(7, 174,240);
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
		ImageIcon retourIcon = createImageIcon("/retour.png","retourIcon");
		retourButton = new JButton(retourIcon);
		retourButton.setBackground(bleuStyle);
		retourButton.setPreferredSize(new Dimension(40, 40));
		retourButton.setBorder(new EmptyBorder(0,0,0,0));	
		retourButton.addActionListener(this);
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
//		this.setBackground(bleuStyle);
		this.repaint();	
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		DetailVolPanel panelDroite = new DetailVolPanel(new Dimension(this.getWidth()/2,this.getHeight()));
		VolPanel gpanel = new VolPanel(new Dimension(this.getWidth()/2,this.getHeight()),panelDroite);
		panelDroite.setVolPanel(gpanel);
		gpanel.setRetourButton(retourButton);
		gpanel.setBackground(Color.WHITE);
		this.add(gpanel, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		this.validate();
		
		
	}
	
	public void hotel(){
		removeButtons();
		this.repaint();
		JInternalFrame fenetre = new JInternalFrame();	
		JInternalFrame fenetre2 = new JInternalFrame();	
		JInternalFrame fenetre3 = new JInternalFrame();	
		
		ChambrePanel chambrePane = new ChambrePanel(this);
		HotelPanel hotelpan = new HotelPanel(this,chambrePane);
		
		fenetre3.add(chambrePane);
		fenetre3.setVisible(true);
		fenetre2.add(hotelpan);
		fenetre2.setVisible(true);
		fenetre.add(new VillePanel(this,hotelpan));
		fenetre.setVisible(true);
		this.add(fenetre);		
		this.add(fenetre2);	
		this.add(fenetre3);
	}
	
	public void retour(){
		this.removeAll();
		this.repaint();
		this.setLayout(new FlowLayout());
		initialise();
		this.validate();
		
		
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
		if(source == retourButton){
			retour();
		}
		
	}

}
