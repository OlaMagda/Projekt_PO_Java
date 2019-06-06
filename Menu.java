package Projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame 
{
	private static final long serialVersionUID = 1L;
	final String inFileName1 = "./resources/arcade.wav";
	final String inFileName2 = "./resources/mountain_king.wav";
	
	ImageIcon graj_but = new ImageIcon("./resources/graj_b.png");
	ImageIcon play_but = new ImageIcon("./resources/play_b.png");
	ImageIcon spiel_but = new ImageIcon("./resources/spiel_b.png");
	
	ImageIcon opcje_but = new ImageIcon("./resources/opcje_b.png");
	ImageIcon settings_but = new ImageIcon("./resources/settings_b.png");
	ImageIcon einstel_but = new ImageIcon("./resources/einst_b.png");
	
	ImageIcon wyjscie_but = new ImageIcon("./resources/wyjscie_b.png");
	ImageIcon exit_but = new ImageIcon("./resources/exit_b.png");
	ImageIcon ausgang_but = new ImageIcon("./resources/ausgang_b.png");
	
	
	
	int czas = 0;
	int lGr = 1;
	int lPrz = 1;
	String lang = "pl";
	
	Pasek pasek = new Pasek(this);	//klasa z paskiem menu na górze
	
	Gra gra = new Gra(czas, lGr, lPrz, lang);
	Dzwiek muzyka = new Dzwiek();
	Dzwiek muzyka2 = new Dzwiek();

	JButton przyciskGra;
	JButton przyciskOpcje;
	JButton przyciskWyjscie;

	Opcje opcje = new Opcje();
	public int czasGry; 
	public Color kolorTla; 
	public String wybranyJezyk; 
	
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel gora, dol;
	JPanel g;
	
	public Menu() throws HeadlessException 	//KONSTRUKTOR DOMYŒLNY
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(1950,1100);
		setUndecorated(true);
		
		muzyka.play(inFileName2);
		muzyka2.play(inFileName1);
		muzyka2.setVolume(0f);
		
		czasGry=180;
		kolorTla=Color.white;
		wybranyJezyk = "Polski";
		
		pasek.setJezyk(wybranyJezyk);
		
		
		// GÓRNY PANEL
		gora = new JPanel();
		add(gora, BorderLayout.PAGE_START);
	
		ImagePanel wrona = new ImagePanel("obrazki/raven.png");
		ImagePanel obrazek = new ImagePanel("obrazki/cz1.jpg");
		ImagePanel obrazek2 = new ImagePanel("obrazki/cz2.jpg");
		JLabel przerwa = new JLabel("               ");
		gora.add(obrazek);
		gora.add(przerwa);
		gora.add(obrazek2);
		
		
	// G£ÓWNE PRZYCISKI
		JPanel panel = new JPanel();	//panel z trzema przyciskami na œrodku ekranu
		panel.setLayout(new GridLayout(4,1));
		add(panel, BorderLayout.CENTER);
		
		
		g = new JPanel();
		panel.add(g);
				
		przyciskGra = new JButton();
		przyciskOpcje = new JButton();
		przyciskWyjscie = new JButton();
		
		// JÊZYKI
		przyciskGra.setIcon(graj_but);
		przyciskOpcje.setIcon(opcje_but);
		przyciskWyjscie.setIcon(wyjscie_but);
		
		przyciskGra.setPreferredSize(new Dimension(200, 100));
		przyciskOpcje.setPreferredSize(new Dimension(200, 100));
		przyciskWyjscie.setPreferredSize(new Dimension(200, 100));
		
		przyciskGra.setForeground(Color.blue);
		przyciskGra.setBackground(Color.pink);
		
		przyciskOpcje.setForeground(Color.blue);
		przyciskOpcje.setBackground(Color.pink);
		
		przyciskWyjscie.setForeground(Color.blue);
		przyciskWyjscie.setBackground(Color.pink);
		
		
		p1.add(przyciskGra);
		p2.add(przyciskOpcje);
		p3.add(przyciskWyjscie);
		
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		
		dol = new JPanel();
		add(dol, BorderLayout.EAST);
		dol.add(wrona);
		
		
		ActionListener exit = new ActionListener() // do przycisku wyjœcie
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						System.exit(0);
					}		
				};
		przyciskWyjscie.addActionListener(exit);
				
		ActionListener options = new ActionListener() // do przycisku opcje
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						opcje.setVisible(true);		//otwiera okno z opcjami
	
					}
					
				};
		przyciskOpcje.addActionListener(options);
		
		
		ActionListener game = new ActionListener() // do przycisku START
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						gra = new Gra(czas, lGr, lPrz, lang);
						gra.setVisible(true);		
						setVisible(false);			
					}		
				};
		przyciskGra.addActionListener(game);
		
		
//kolor domyœlnego t³a
		Color dom = new Color(0, 102 ,51);	
		p1.setBackground(dom);
		p2.setBackground(dom);
		p3.setBackground(dom);
		g.setBackground(dom);
		gora.setBackground(dom);
		dol.setBackground(dom);
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
}
