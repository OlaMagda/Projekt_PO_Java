package Projekt;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Pasek extends JMenuBar
{
	private static final long serialVersionUID = 1L;
	
	JMenu ust;
	JMenuItem powrot;
	JMenuItem exit;
	JMenu dzwiek;
	JMenuItem muzyka;
	JSlider volume;
	JRadioButton wycisz, wlacz;
	ButtonGroup grupa_bt;
	
	String naPewno, powrotMenu, zakonczGre, zamknijProgram, doZoba;
	
	Pasek (JFrame f)
	{
		JMenuBar menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);
		
		ust = new JMenu("Ustawienia");
		menuBar.add(ust);
		
		powrot = new JMenuItem("Wróæ do menu g³ównego");
		exit = new JMenuItem("Zamknij program");
		
		wycisz = new JRadioButton("Wycisz dzwiêk");
		wycisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				if(Main.menu.opcje.MusicList.getSelectedIndex() == 0)
				{
					Main.menu.muzyka.setVolume(0f);
				}
				else 
				{
					Main.menu.muzyka2.setVolume(0f);
				}
			}
			
		});
		wlacz = new JRadioButton("W³¹cz dzwiêk",true);
		wlacz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.menu.opcje.MusicList.getSelectedIndex() == 0)
				{
					Main.menu.muzyka.setVolume(0.5f);
				}
				else 
				{
					Main.menu.muzyka2.setVolume(0.5f);
				}

			}
		});
		
		volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		volume.setMajorTickSpacing(50);
		volume.setPaintTicks(true);
		volume.setPaintLabels(true);
		volume.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
			
				if(Main.menu.opcje.MusicList.getSelectedIndex() == 0)
				{
					Main.menu.muzyka.setVolume(((JSlider)e.getSource()).getValue()/100f);
				}
				else 
				{
					Main.menu.muzyka2.setVolume(((JSlider)e.getSource()).getValue()/100f);
				}
				
				
				wlacz.setSelected(true);
				
			}
		});
		
		
		powrot.addActionListener (new ActionListener()	// POWRÓT do menu
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						int a = JOptionPane.showConfirmDialog(null, naPewno, powrotMenu, JOptionPane.YES_NO_OPTION);
				
						if (a == 0)
						{
							f.setVisible(false);
							Main.menu.setVisible(true);
						}
					}
				});
		
		
		exit.addActionListener (new ActionListener()	// ZAMYKA PROGRAM
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						int a = JOptionPane.showConfirmDialog(null, zakonczGre, zamknijProgram, JOptionPane.YES_NO_OPTION);
						
						if (a == 0)
						{
							JOptionPane.showMessageDialog(null, doZoba, "", JOptionPane.WARNING_MESSAGE);
							System.exit(0);
						}
					}
				});
		
		
		ust.add(powrot);
		ust.add(exit);		
		
		
		
		dzwiek = new JMenu("Dzwiêk");
		menuBar.add(dzwiek);
		
		muzyka = new JMenuItem("Wy³¹cz muzykê");
		grupa_bt = new ButtonGroup();
		grupa_bt.add(wycisz);
		grupa_bt.add(wlacz);
		
		dzwiek.add(wycisz);
		dzwiek.add(wlacz);
		dzwiek.add(volume);
	}
	
	
	public void setJezyk(String s)
	{
		if(s == "Polski" || s == "pl")
		{
			ust.setText("Ustawienia");
			powrot.setText("Wróæ do menu g³ównego");
			exit.setText("Zamknij program");
			dzwiek.setText("DŸwiêk"); 
			muzyka.setText("Wy³¹cz muzykê");
			wlacz.setText("W³¹cz muzykê");
			wycisz.setText("Wycisz muzykê");
			
			naPewno = "Na pewno? Stan gry nie zostanie zapisany";
			powrotMenu = "Powrót do menu";
			zakonczGre = "Czy na pewno chcesz zakoñczyæ grê?";
			zamknijProgram = "Zamknij program";
			doZoba = "Do zobaczenia!";
		}
		else if (s == "English" || s == "en")
		{
			ust.setText("Settings");
			powrot.setText("Back to main menu");
			exit.setText("Close program ");
			dzwiek.setText("Sound"); 
			muzyka.setText("Turn off music");
			wlacz.setText("Turn on music");
			wycisz.setText("Mute music");
			
			naPewno = "For sure? The state of the game will not be saved";
			powrotMenu = "Back to main menu";
			zakonczGre = "Are you sure you want to end the game?";
			zamknijProgram = "Close program";
			doZoba = "See you later!";
		}
		else
		{
			ust.setText("Einstellungen");
			powrot.setText("Zuruck zu Hauptmenü");
			exit.setText("Anwendung schließen");
			dzwiek.setText("Musik"); 
			muzyka.setText("Musik abschalten");
			wlacz.setText("Musik anschalten");
			wycisz.setText("Musik stumm schalten");
			
			naPewno = "Bist du sicher? Stand des Spiel wird nicht gespeichert sind";
			powrotMenu = "Zuruck zu Hauptmenü";
			zakonczGre = "Möchtest du wirklich das Spiel beendet?";
			zamknijProgram = "Anwendung schließen";
			doZoba = "Auf Wiedersehen!";
		}
		
		
		
	}
}