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
		
		powrot = new JMenuItem("Wr�� do menu g��wnego");
		exit = new JMenuItem("Zamknij program");
		
		wycisz = new JRadioButton("Wycisz dzwi�k");
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
		wlacz = new JRadioButton("W��cz dzwi�k",true);
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
		
		
		powrot.addActionListener (new ActionListener()	// POWR�T do menu
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
		
		
		
		dzwiek = new JMenu("Dzwi�k");
		menuBar.add(dzwiek);
		
		muzyka = new JMenuItem("Wy��cz muzyk�");
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
			powrot.setText("Wr�� do menu g��wnego");
			exit.setText("Zamknij program");
			dzwiek.setText("D�wi�k"); 
			muzyka.setText("Wy��cz muzyk�");
			wlacz.setText("W��cz muzyk�");
			wycisz.setText("Wycisz muzyk�");
			
			naPewno = "Na pewno? Stan gry nie zostanie zapisany";
			powrotMenu = "Powr�t do menu";
			zakonczGre = "Czy na pewno chcesz zako�czy� gr�?";
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
			powrot.setText("Zuruck zu Hauptmen�");
			exit.setText("Anwendung schlie�en");
			dzwiek.setText("Musik"); 
			muzyka.setText("Musik abschalten");
			wlacz.setText("Musik anschalten");
			wycisz.setText("Musik stumm schalten");
			
			naPewno = "Bist du sicher? Stand des Spiel wird nicht gespeichert sind";
			powrotMenu = "Zuruck zu Hauptmen�";
			zakonczGre = "M�chtest du wirklich das Spiel beendet?";
			zamknijProgram = "Anwendung schlie�en";
			doZoba = "Auf Wiedersehen!";
		}
		
		
		
	}
}