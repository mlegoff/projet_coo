package projet_coo.voyage.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
	private JLabel reservation,vol,client,hotel;
	private JButton reservationButton,volButton,clientButton,hotelButton, retourButton;
	private LayoutManager layout;
	private Color bleuStyle = new Color(7, 174,240);
	private JPanel buttonPanel, labelPanel;
	Font lato = new Font("Lato",Font.CENTER_BASELINE,14);
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
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(this.getWidth(), 250));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(volButton);
		buttonPanel.add(reservationButton);
		buttonPanel.add(hotelButton);
		buttonPanel.add(clientButton);
		volButton.addActionListener(this);
		reservationButton.addActionListener(this);
		clientButton.addActionListener(this);
		hotelButton.addActionListener(this);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(buttonPanel);
		this.reservation = new JLabel("Réservation");
		this.reservation.setForeground(bleuStyle);
		this.reservation.setFont(lato);
		this.hotel = new JLabel("Clients");
		this.hotel.setForeground(bleuStyle);
		this.hotel.setFont(lato);
		this.vol = new JLabel("Vols");
		this.vol.setForeground(bleuStyle);
		this.vol.setFont(lato);
		this.client =  new JLabel("Hotels");
		this.client.setForeground(bleuStyle);
		this.client.setFont(lato);
		labelPanel = new JPanel();
		JPanel hotelPanel = new JPanel();
		JPanel clientPanel = new JPanel();
		JPanel volPanel = new JPanel();
		JPanel resaPanel = new JPanel();
		volPanel.add(vol);
		volPanel.setPreferredSize(new Dimension(200,100));
		volPanel.setBorder(new EmptyBorder(0,0,0,0));
		clientPanel.add(client);
		clientPanel.setPreferredSize(new Dimension(200,80));
		clientPanel.setBorder(new EmptyBorder(0,0,0,0));
		resaPanel.add(reservation);
		resaPanel.setPreferredSize(new Dimension(200,80));
		resaPanel.setBorder(new EmptyBorder(0,0,0,0));
		hotelPanel.add(hotel);
		hotelPanel.setPreferredSize(new Dimension(200,80));
		hotelPanel.setBorder(new EmptyBorder(0,0,0,0));
		volPanel.setBackground(Color.WHITE);
		clientPanel.setBackground(Color.WHITE);
		resaPanel.setBackground(Color.WHITE);
		hotelPanel.setBackground(Color.WHITE);
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.add(volPanel);
		labelPanel.add(resaPanel);
		labelPanel.add(clientPanel);
		labelPanel.add(hotelPanel);
		labelPanel.setBorder(new EmptyBorder(0,85,0,125));
		labelPanel.setBackground(Color.WHITE);
		this.add(labelPanel);
		
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
		Border margin = new EmptyBorder(175,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));
		createButtons();		
	}
	
		
	public void removeButtons(){
		this.remove(buttonPanel);
		this.remove(clientButton);
		this.remove(volButton);
		this.remove(reservationButton);
		this.remove(hotelButton);
		this.remove(labelPanel);
	}
	
	public void reservation(){
		removeButtons();
		this.repaint();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		DetailResaPanel panelDroite = new DetailResaPanel(new Dimension(this.getWidth()/2,this.getHeight()));
		ResaPanel gpanel = new ResaPanel(new Dimension(this.getWidth()/2,this.getHeight()),panelDroite);
		gpanel.setRetourButton(retourButton);
		gpanel.setBackground(bleuStyle);
		this.add(gpanel, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		this.setBackground(bleuStyle);
		this.validate();
	}
	
	public void client(){
		removeButtons();
		this.repaint();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		DetailClientPanel panelDroite = new DetailClientPanel(new Dimension(this.getWidth()/2,this.getHeight()));
		ClientPanel gpanel = new ClientPanel(new Dimension(this.getWidth()/2,this.getHeight()),panelDroite);
		panelDroite.setClientPanel(gpanel);
		gpanel.setRetourButton(retourButton);
		gpanel.setBackground(Color.WHITE);
		this.add(gpanel, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		this.validate();
		
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
		
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		
		ChambrePanel chambrePane = new ChambrePanel(this);
		HotelPanel hotelpan = new HotelPanel(this,chambrePane);
		VillePanel villepanel = new VillePanel(this,hotelpan);
		
		villepanel.setPreferredSize(new Dimension(this.getWidth()/3,this.getHeight()));
		hotelpan.setPreferredSize(new Dimension(this.getWidth()/3,this.getHeight()));
		chambrePane.setPreferredSize(new Dimension(this.getWidth()/3,this.getHeight()));
		
		this.add(retourButton,BorderLayout.NORTH);
		this.add(villepanel,BorderLayout.WEST );
		this.add(hotelpan);
		this.add(chambrePane,BorderLayout.EAST);		
		
		this.validate();
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
