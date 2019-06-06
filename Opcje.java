package Projekt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Opcje extends JFrame 
{
	
	private static final long serialVersionUID = 1L;
	
	static final int SLIDER_MIN = 45;
	static final int SLIDER_MAX = 600;
	static final int SLIDER_INIT = 120;
	
	static final int SLIDER_MIN_2 = 0;
	static final int SLIDER_MAX_2 = 50;
	static final int SLIDER_INIT_2 = 1;

	Color color1 = new Color(210, 180 ,140);
	//Color color1 = new Color(0, 102 ,51);		//0 102 51
	
	JComboBox <String> MusicList;
	JSlider slide, slide2;
	JTextField timeValue;
	JTextField ile_przeciw;
	JLabel chMusic;
	JLabel przeciw_value;
	JPanel ps4, ps5;
	JCheckBox enableTime, enable_przeci;
	JLabel slideLabel, slideLabel2, lgracz;
	JRadioButton gracz1;
	JRadioButton gracz2;
	JButton powrot, KolorTla;
	//przekazywane
	Color kolorTla = new Color(0, 102 ,51);
	int czas_gry;
	String jezyk_wybr = "Polski";
	boolean wybor_przeciwnikow;
	int LGraczy=0;
	
	JRadioButton muzyka1, muzyka2;
	

	public Color getKolorTla() 
	{
		return kolorTla;
	}

	public void setKolorTla(Color kolorTla) 
	{
		this.kolorTla = kolorTla;
	}

	public int getCzas_gry() 
	{
		return czas_gry;
	}

	public void setCzas_gry(int czas_gry) 
	{
		this.czas_gry = czas_gry;
	}
	public String getJezyk() 
	{
		return jezyk_wybr;
	}

	public void setJezyk(String jezyk_wybr) 
	{
		this.jezyk_wybr = jezyk_wybr;
	}




	public Opcje()	//konstruktor domyœlny
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setSize(800,600);
		setSize(1950,1100);
		setUndecorated(true);
		
		Pasek pasek = new Pasek(this);	//pasek na górze
		
		ImagePanel wood_back = new ImagePanel("obrazki/wood_texture.jpg");
		wood_back.setLayout(new BorderLayout());
		add(wood_back);
		
//Tytul		
		JPanel panel_new1 = new JPanel();
		panel_new1.setBackground(new Color(0, 0, 0,50));
		JLabel tytul = new JLabel("USTAWIENIA");
		tytul.setFont(new Font("Font", Font.ITALIC,46));
		tytul.setForeground(Color.WHITE);
		tytul.setPreferredSize(new Dimension(400,200));
		panel_new1.add(tytul);
		
//liczba graczy 
		JPanel panel_new2 = new JPanel ();
		panel_new2.setBackground(new Color(0, 0, 0,50));
		gracz1 = new JRadioButton("1 gracz");
		gracz1.setBackground(color1);
		gracz1.setFont(new Font("Font", Font.ITALIC,20));

		gracz2 = new JRadioButton("2 graczy");
		gracz2.setBackground(color1);
		gracz2.setFont(new Font("Font", Font.ITALIC,20));

		lgracz = new JLabel("Wybierz liczbe graczy");
		lgracz.setForeground(Color.WHITE);
		lgracz.setFont(new Font("Font", Font.ITALIC, 20));

		ButtonGroup radioPanel = new ButtonGroup();
		
		gracz1.setSelected(true);
		radioPanel.add(gracz1);
		radioPanel.add(gracz2);
		panel_new2.add(lgracz);
		panel_new2.add(gracz1);
		panel_new2.add(gracz2, BorderLayout.CENTER);
//Jezyk		
		JPanel panel_new3 = new JPanel ();
		panel_new3.setBackground(new Color(0, 0, 0,50));
		JLabel jezyk = new JLabel("Jêzyk");
		jezyk.setForeground(Color.WHITE);
		jezyk.setFont(new Font("Font", Font.ITALIC,20));

		panel_new3.add(jezyk);
		String[] LangStrings = { "Polski", "English", "Deutsch" }; 
		JComboBox <String> LangList = new JComboBox<>(LangStrings);
		LangList.setSelectedIndex(0); //ustawienie polskiego jako wybranego domyslnie 
		
		ActionListener zmianaJezyka = new ActionListener()
			{
				@Override 
				public void actionPerformed(ActionEvent e)
				{
					String s = (String) LangList.getSelectedItem();
					setJezyk(s);
					
					switch (getJezyk()) 
					{
						case "Polski": 
							tytul.setText("USTAWIENIA");
							KolorTla.setText("Zmien kolor tla");
							gracz1.setText("1 gracz");
							gracz2.setText("2 graczy");
							enableTime.setText("Graj na czas");
							slideLabel.setText("Wybrany czas wynosi:");
							powrot.setText("Zapis i Powrót");
							jezyk.setText("Jêzyk");
							pasek.ust.setText("Ustawienia");
							pasek.powrot.setText("Wróæ do menu g³ównego");
							pasek.exit.setText("Zamknij program");
							pasek.dzwiek.setText("DŸwiêk"); 
							pasek.muzyka.setText("Wy³¹cz muzykê");
							Main.menu.lang = "pl";
							
							break;
							
						case "English":
							
							tytul.setText("SETTINGS");
							KolorTla.setText("Change background color");
							gracz1.setText("1 player");
							gracz2.setText("2 players");
							enableTime.setText("Enable timer");
							slideLabel.setText("Selected time is:");
							powrot.setText("Save & Back");
							jezyk.setText("Language  ");
							pasek.ust.setText("Settings");
							pasek.powrot.setText("Back to main menu");
							pasek.exit.setText("Close program ");
							pasek.dzwiek.setText("Sound"); 
							pasek.muzyka.setText("Turn off music");
							Main.menu.lang = "en";
							
							break;
							
						case "Deutsch":
							
							tytul.setText("Einstellungen");
							KolorTla.setText("Hintergrund Farbe verändern");
							gracz1.setText("1 spieler");
							gracz2.setText("2 spielers");
							enableTime.setText("Uhr aktivieren");
							slideLabel.setText("Ausgewählt Zeit ist:");
							powrot.setText("Speichern & Ausgang");
							jezyk.setText("Sprache");
							pasek.ust.setText("Einstellungen");
							pasek.powrot.setText("Zuruck zu Hauptmenü");
							pasek.exit.setText("Anwendung schließen");
							pasek.dzwiek.setText("Musik"); 
							pasek.muzyka.setText("Musik abschalten");
							Main.menu.lang = "de";
							
							break;
							
					} 
		
				}
			};
		
		LangList.addActionListener(zmianaJezyka);
		panel_new3.add(LangList);
//kolor tla		
		JPanel panel_new4 = new JPanel ();
		panel_new4.setBackground(new Color(0, 0, 0,50));
		KolorTla = new JButton("Wybór koloru tla");
		KolorTla.setFont(new Font("Font", Font.ITALIC,20));

		panel_new4.add(KolorTla);
		ActionListener wyborkoloru = new ActionListener() // do przycisku koloru tla
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						kolorTla = JColorChooser.showDialog(null, "Wybór koloru", new Color(238,238,238));
					}		
				};
				
		KolorTla.addActionListener(wyborkoloru);
//slider		
		JPanel panel_new5 = new JPanel ();
		panel_new5.setBackground(new Color(0, 0, 0,50));
		slideLabel = new JLabel("Wybrany czas wynosi [s]:");
		slideLabel.setForeground(Color.WHITE);
		slideLabel.setFont(new Font("Font", Font.ITALIC,20));

		enableTime = new JCheckBox("Gra na czas");
		timeValue = new JTextField("120");
		timeValue.setPreferredSize(new Dimension(40,20));
		timeValue.setForeground(Color.BLACK);
        slide = new JSlider(JSlider.CENTER,SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		slide.addChangeListener(new SliderChangeListener());
	    slide.setMinorTickSpacing(50);
	    slide.setMajorTickSpacing(100);
	    slide.setPaintTicks(true);
	    slide.setPaintLabels(true);
	    slide.setBackground(color1);
	    enableTime.setBackground(color1);
		panel_new5.add(enableTime);
		panel_new5.add(slideLabel);
		panel_new5.add(timeValue);
	    panel_new5.add(slide);
//zapis		
		JPanel panel_new6 = new JPanel ();
		panel_new6.setBackground(new Color(0, 0, 0,50));
		panel_new6.setPreferredSize(new Dimension(1950,100));
	    powrot = new JButton("Zapis i Powrót");
		powrot.setFont(new Font("Font", Font.ITALIC,20));

	    panel_new6.add(powrot);
	    
	    ActionListener back = new ActionListener() // do przycisku powrót
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						
						Main.menu.p1.setBackground(kolorTla);
						Main.menu.p2.setBackground(kolorTla);
						Main.menu.p3.setBackground(kolorTla);
						Main.menu.g.setBackground(kolorTla);
						Main.menu.gora.setBackground(kolorTla);
						Main.menu.dol.setBackground(kolorTla);
						Main.menu.gra.LiczGraczy = LGraczy;
						
						if(enableTime.isSelected())
						{
							Main.menu.gra.wybrCzas = slide.getValue();
							Main.menu.czas = slide.getValue();
									
						}
						else
						{
							Main.menu.gra.wybrCzas = 0;
							Main.menu.czas = 0;
						}
						
					
						if(gracz2.isSelected())
						{
							Main.menu.gra.LiczGraczy = 2;
							Main.menu.lGr = 2;
								
						}
						else
						{
							Main.menu.lGr = 1; 
						}
						
						Main.menu.gra.liczbaPrzeciwnikow = slide2.getValue();
						Main.menu.lPrz = slide2.getValue();
						
						String wybranyJezyk = getJezyk();
						if(wybranyJezyk == "Polski") {
							Main.menu.przyciskGra.setIcon(Main.menu.graj_but);
							Main.menu.przyciskOpcje.setIcon(Main.menu.opcje_but);
							Main.menu.przyciskWyjscie.setIcon(Main.menu.wyjscie_but);
							Main.menu.pasek.ust.setText("Ustawienia");
							Main.menu.pasek.powrot.setText("Wróæ do menu g³ównego");
							Main.menu.pasek.exit.setText("Zamknij program");
							Main.menu.pasek.dzwiek.setText("DŸwiêk"); 
							Main.menu.pasek.muzyka.setText("Wy³¹cz muzykê");

							Main.menu.gra.pasek.ust.setText("Ustawienia");
							Main.menu.gra.pasek.powrot.setText("Wróæ do menu g³ównego");
							Main.menu.gra.pasek.exit.setText("Zamknij program");
							Main.menu.gra.pasek.dzwiek.setText("DŸwiêk"); 
							Main.menu.gra.pasek.muzyka.setText("Wy³¹cz muzykê");
							//System.out.println(wybranyJezyk);
							
						}
						else if (wybranyJezyk == "English") {
							Main.menu.przyciskGra.setIcon(Main.menu.play_but);
							Main.menu.przyciskOpcje.setIcon(Main.menu.settings_but);
							Main.menu.przyciskWyjscie.setIcon(Main.menu.exit_but);
							Main.menu.pasek.ust.setText("Settings");
							Main.menu.pasek.powrot.setText("Back to main menu");
							Main.menu.pasek.exit.setText("Close program ");
							Main.menu.pasek.dzwiek.setText("Sound"); 
							Main.menu.pasek.muzyka.setText("Turn off music");
							Main.menu.pasek.wycisz.setText("Mute");
							Main.menu.pasek.wlacz.setText("Unmute");
							
							Main.menu.gra.pasek.ust.setText("Settings");
							Main.menu.gra.pasek.powrot.setText("Back to main menu");
							Main.menu.gra.pasek.exit.setText("Close program ");
							Main.menu.gra.pasek.dzwiek.setText("Sound"); 
							Main.menu.gra.pasek.muzyka.setText("Turn off music");
							//System.out.println(wybranyJezyk);
							
						}
						
						else if (wybranyJezyk == "Deutsch") {
							Main.menu.przyciskGra.setIcon(Main.menu.spiel_but);
							Main.menu.przyciskOpcje.setIcon(Main.menu.einstel_but);
							Main.menu.przyciskWyjscie.setIcon(Main.menu.ausgang_but);
							Main.menu.pasek.ust.setText("Einstellungen");
							Main.menu.pasek.powrot.setText("Zuruck zu Hauptmenü");
							Main.menu.pasek.exit.setText("Anwendung schließen");
							Main.menu.pasek.dzwiek.setText("Musik"); 
							Main.menu.pasek.muzyka.setText("Musik abschalten");
							Main.menu.pasek.wycisz.setText("Musik abschalten");
							Main.menu.pasek.wlacz.setText("Musik anschalten");
							
							Main.menu.gra.pasek.ust.setText("Einstellungen");
							Main.menu.gra.pasek.powrot.setText("Zuruck zu Hauptmenü");
							Main.menu.gra.pasek.exit.setText("Anwendung schließen");
							Main.menu.gra.pasek.dzwiek.setText("Musik"); 
							Main.menu.gra.pasek.muzyka.setText("Musik abschalten");
							//System.out.println(wybranyJezyk);
							
						}
						
						
						Main.menu.setVisible(true);
						setVisible(false);
						
					}		
				};
		powrot.addActionListener(back);
//ilosc przeciwnikow		
		JPanel panel_new7 = new JPanel ();
		panel_new7.setBackground(new Color(0, 0, 0,50));
		slide2 = new JSlider(JSlider.CENTER,SLIDER_MIN_2, SLIDER_MAX_2, SLIDER_INIT_2);
		przeciw_value = new JLabel("Ilosc przeciwnikow: ");
		przeciw_value.setForeground(Color.WHITE);
		przeciw_value.setFont(new Font("Font", Font.ITALIC,20));

		ile_przeciw = new JTextField("1");
		ile_przeciw.setForeground(Color.BLACK);
		ile_przeciw.setPreferredSize(new Dimension(40,20));
	    slide2.addChangeListener(new ChangeListener() 
	    {
	        public void stateChanged(ChangeEvent event) 
	        {
	        	String value2 = String.format("%d", slide2.getValue());
	        	ile_przeciw.setText(value2);
	          
	        }
	      });
	    slide2.setMinorTickSpacing(5);
	    slide2.setMajorTickSpacing(10);
	    slide2.setPaintTicks(true);
	    slide2.setPaintLabels(true);
	    slide2.setBackground(color1);
		panel_new7.add(przeciw_value);
		panel_new7.add(ile_przeciw);
	    panel_new7.add(slide2);
//Muzyka - wybor		
		JPanel panel_new8 = new JPanel ();
		panel_new8.setBackground(new Color(0, 0, 0,50));
		chMusic = new JLabel("Wybierz muzyke: ");
		chMusic.setFont(new Font("Font", Font.ITALIC,20));

		chMusic.setForeground(Color.WHITE);
		String[] Music = { "Mountain king", "Arcade toon" }; 
		MusicList = new JComboBox<>(Music);
		MusicList.setSelectedIndex(0);
		
		ActionListener zmianaMuzyki = new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				String s = (String) MusicList.getSelectedItem();

				switch (s) 
				{
					case "Arcade toon": 
						Main.menu.muzyka2.setVolume(0.5f);
						Main.menu.muzyka.setVolume(0f);
						break;
						
					case "Mountain king":
						Main.menu.muzyka2.setVolume(0);
						Main.menu.muzyka.setVolume(0.5f);
						break;
				} 
	
			}
		};

		MusicList.addActionListener(zmianaMuzyki);
		
		panel_new8.add(chMusic);
		panel_new8.add(MusicList);
	    
		JPanel panel_center = new JPanel ();
		panel_center.setBackground(new Color(0, 0, 0,50));
		panel_center.setLayout(new GridLayout(6,1));
		panel_center.add(panel_new2);
		panel_center.add(panel_new3);
		panel_center.add(panel_new4);
		panel_center.add(panel_new5);
		panel_center.add(panel_new7);
		panel_center.add(panel_new8);
		
		
		
		wood_back.add(panel_new1, BorderLayout.PAGE_START);
		wood_back.add(panel_center, BorderLayout.CENTER);
		wood_back.add(panel_new6, BorderLayout.SOUTH);
		
		wood_back.addMouseListener(new MouseAdapter()
		 {
				 
	            public void mousePressed(MouseEvent e)
	            {
	            	repaint();
	            }
	            
	            public void mouseReleased(MouseEvent e)
	            {

	            	
				    repaint();	
				}
	                        
	        });
		
	    
	}
	
	
	
	public class SliderChangeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0) 
		{
			String value = String.format("%d", slide.getValue());
			timeValue.setText(value);
			
		}
	}
	
		
	
	
}

