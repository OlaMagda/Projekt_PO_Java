package Projekt;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.Random;
import java.util.Timer;
import javax.swing.*;


/* LEGENDA
 * 
 * 1 - mur nietykalny
 * 2 - puste pole
 * 3 - ściana do zniszczenia
 * 4 - gawron
 * 5 - bomba
 * 6 - wybuch
 * 7 - drugi przeciwnik ludzki
 * 8 - przeciwnik komputerowy
 * 
 */


public class Gra extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	
	final String inFileName = "./resources/explosion.wav";
	final String kraa = "./resources/crow_caw.wav";
	final String ptasz = "./resources/vulture-1.wav";
	Dzwiek bum = new Dzwiek();
	Dzwiek kra = new Dzwiek();
	Dzwiek ptaszysko = new Dzwiek();
	
	int liczbaPrzeciwnikow = 10;		 //!!!		0 - nie ma przeciwników

	int XGawron = 1;
	int YGawron = 1;
	
	boolean g = true, k = true;
	
	int LiczGraczy = 1; // tutaj bedzie przekazywana informacja o ilosci graczy z opcji
	int wybrCzas = 0;
	
	Kostka obrazki;
	
	int XKot, YKot;
	
	Akcja akcja, akcjaP, akcjaPP, akcjaPD;
	
	int szerokosc, wysokosc;
	
	refresh R;
	czas Czas;
	String jezyk = "pl";
	
	Pasek pasek = new Pasek(this);
	
	int[][] typ = new int[21][17];
	
	public Gra(int t, int l, int p, String jez)
	{
		wybrCzas = t;
		LiczGraczy = l;
		liczbaPrzeciwnikow = p;
		jezyk = jez;
		pasek.setJezyk(jezyk);
		
		if (LiczGraczy == 1)
			k = false;
		
		if (LiczGraczy == 2)
		{
			///////// DRUGI
			XKot = 19;
			YKot = 15;
		}
		
		addKeyListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1950,1100);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		szerokosc = getWidth()/21; 		// szerokosc i wysokosc pola na podsatwie wymiarów ekranu (Jframe)
		wysokosc = getHeight()/17;
		
		setUndecorated(true);
		
		
		akcja = new Akcja();
		add(akcja);

		
		obrazki = new Kostka(szerokosc,wysokosc, BufferedImage.TYPE_4BYTE_ABGR);
			
		
		
		
		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j < 21; j++)	//j=x
			{
				if (j%2 == 1)
					typ[j][i] = 3;
				else
					typ[j][i] = 1;
				
				if (i%2 == 1)
					typ[j][i] = 3;
				
				//ramka
				if (j == 0 || j == 20 || i == 0 || i == 16)
					typ[j][i] = 1;
				
				// pola startowe
				if (j == 1 && i == 1)
					typ[j][i] = 2;
				if (j == 1 && i == 2)
					typ[j][i] = 2;
				if (j == 2 && i == 1)
					typ[j][i] = 2;
				
				////////////// DRUGI
				
				if (LiczGraczy == 2) {
				if (j == 19 && i == 15)
					typ[j][i] = 2;
				if (j == 18 && i == 15)
					typ[j][i] = 2;
				if (j == 19 && i == 14)
					typ[j][i] = 2;
				}
			}
		}
	
		
		typ[1][1] = 4;	// prototyp Gawrona - niebieska kostka
		
		// DRUGI
		if (LiczGraczy == 2)
			typ[XKot][YKot] = 7;	// prototyp Kota (?)
		
		
		// PRZECIWNICY
		for (int i = 0; i < liczbaPrzeciwnikow; i++)
		{
			addEnemies E = new addEnemies(i);
			E.start();
		}
		
		

			R = new refresh();
			R.start();

			
			if (wybrCzas != 0)
			{
				Czas = new czas();
				Czas.start();
			}
	}
	
	
	
	class czas extends Thread
	{
		public void run()
		{
			try {
				sleep(wybrCzas*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (R.odswiez) {
			R.koniec();
			
			if (jezyk == "pl")
				JOptionPane.showMessageDialog(null, "Czas się skończył", "Koniec gry", JOptionPane.WARNING_MESSAGE);
			if (jezyk == "en")
				JOptionPane.showMessageDialog(null, "Time is over", "Game over", JOptionPane.WARNING_MESSAGE);
			if (jezyk == "de")
				JOptionPane.showMessageDialog(null, "Die Zeit ist vorbei", "Ende des Spiels", JOptionPane.WARNING_MESSAGE);
			}
			
			// !!!!!!!!!!!!!!!!!!!!
		}
	}
	
	
	
	public class Akcja extends JPanel
	{
		private static final long serialVersionUID = 1L;
		

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			
			for (int i = 0; i < 17; i++) //17
			{
				for (int j = 0; j < 21; j++)	//j=x 21
				{
					switch (typ[j][i])
					{
						case 1: 
							g.drawImage(obrazki.czarny(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 2:
							g.drawImage(obrazki.bialy(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 3: 
							g.drawImage(obrazki.szary(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 4: 
							g.drawImage(obrazki.niebieski(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 5: 
							g.drawImage(obrazki.zolty(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 6: 
							g.drawImage(obrazki.czerwony(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 7: 
							g.drawImage(obrazki.rozowy(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
						case 8: 
							g.drawImage(obrazki.zielony(), j*szerokosc, i*wysokosc, szerokosc, wysokosc, this);
							break;
					}
					
				}
			}			
		}
	}
	
	
	class refresh extends Thread		// repaintuje cały czas
	{
		boolean odswiez = true;		
		
		public void run()
		{
			while (odswiez)
			{
				/*
				try {
					sleep(350);								// może wpływać na płynność
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
				akcja.repaint();
				
				if (liczbaPrzeciwnikow == 0)
					if (g == false || k == false)
					{
						if (g) {
						switch (jezyk)
						{
						case "pl":
							JOptionPane.showMessageDialog(null, "Gawron zwyciężył!", "GRATULACJE", JOptionPane.WARNING_MESSAGE);	
							break;
							
						case "en":
							JOptionPane.showMessageDialog(null, "The Rook won!", "CONGRATULATIONS", JOptionPane.WARNING_MESSAGE);	
							break;
							
						case "de":
							JOptionPane.showMessageDialog(null, "Saatkrähe hat gewonnen!", "GLÜCKWÜNSCHE", JOptionPane.WARNING_MESSAGE);	
							break;
						}}
						
						if (k) {
							switch (jezyk)
							{
							case "pl":
								JOptionPane.showMessageDialog(null, "Kot zwyciężył!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "en":
								JOptionPane.showMessageDialog(null, "The Cat won!", "GAME OVER", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "de":
								JOptionPane.showMessageDialog(null, "Die Katze hat gewonnen!", "GLÜCKWÜNSCHE", JOptionPane.WARNING_MESSAGE);	
								break;
							}}
						
						odswiez = false;
					}

			}
		}
		
		void koniec()
		{
			odswiez = false;
		}
	}

	
	
	// wątek z przeciwnikiem
	class addEnemies extends Thread
	{
		boolean live = true;
		int XEnemy;
		int YEnemy;

		Random rand = new Random();
		
		addEnemies (int l)
		{
			Random r = new Random();
			XEnemy = r.nextInt(15) + 3;			// rozmieszczenie losowe
			YEnemy = r.nextInt(11) + 3;	
		}
		
		
		public void run()
		{
			int kier; 				// 0 prawo	 1 dol	 2 lewo	 3 gora
			int poprzedni = 3;
			
			while (live)
			{
					kier = rand.nextInt(4);
					
					typ[XEnemy][YEnemy] = poprzedni;

					if (kier == 0)
						if (typ[XEnemy+1][YEnemy] != 1 && typ[XEnemy+1][YEnemy] != 8 && typ[XEnemy+1][YEnemy] != 5)
						{
							poprzedni = typ[XEnemy+1][YEnemy];
							if (poprzedni == 6)
								poprzedni = 2;
							XEnemy++;
						}
					
					if (kier == 1)
						if (typ[XEnemy][YEnemy-1] != 1 && typ[XEnemy][YEnemy-1] != 8 && typ[XEnemy][YEnemy-1] != 5)
						{
							poprzedni = typ[XEnemy][YEnemy-1];
							if (poprzedni == 6)
								poprzedni = 2;
							YEnemy--;
						}
					
					if (kier == 2)
						if (typ[XEnemy-1][YEnemy] != 1 && typ[XEnemy-1][YEnemy] != 8 && typ[XEnemy-1][YEnemy] != 5)
						{
							poprzedni = typ[XEnemy-1][YEnemy];
							if (poprzedni == 6)
								poprzedni = 2;
							XEnemy--;
						}
					
					if (kier == 3)
						if (typ[XEnemy][YEnemy+1] != 1 && typ[XEnemy][YEnemy+1] != 8 && typ[XEnemy][YEnemy+1] != 5   )
						{
							poprzedni = typ[XEnemy][YEnemy+1];
							if (poprzedni == 6)
								poprzedni = 2;
							YEnemy++;
						}
							

					typ[XEnemy][YEnemy] = 8;

					
					
					// atak na gawrona
					if (XEnemy == XGawron && YEnemy == YGawron)
					{
						kra.play(kraa);
						g = false;
						
						if (k == false)
						{
							R.koniec();
							
							switch (jezyk)
							{
							case "pl":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Gawron zginął!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "en":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Rook is dead!!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "de":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Saatkrähe ist gestorben!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								break;
							}
							
							
							// KONIEC GRY
						}
							  

						poprzedni = 2;
						XGawron = 0; YGawron = 0;  // żeby nie przeszkadzał
						
						
					}
					
					// atak na kota
					if (XEnemy == XKot && YEnemy == YKot)
					{
						kra.play(kraa);
						k = false;
						
						if (g == false)
						{
							R.koniec();
							
							switch (jezyk)
							{
							case "pl":
								JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "en":
								JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "de":
								JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);	
								break;
							}
							

							// KONIEC GRY
						}
							 
						
						poprzedni = 2;
						XKot = 20; YKot = 16;  // żeby nie przeszkadzał
						
						
					}
				
					
				for (int i = 0; i < 5; i++) {  // zagęszczenie sprawdzania ognia
					
				// jeśli ogień
					
					 if (typ[XEnemy][YEnemy] == 6)
					 {
						 live = false;
						 liczbaPrzeciwnikow--;
						 ptaszysko.play(ptasz);
						 typ[XEnemy][YEnemy] = 2;
					 }			 
					
								
				// spanko
				try {
					sleep(100);	// czas między skokami / 5
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
				
			}
		}
	}
	

	
	
	
	
	// Z TYMI LINIJKAMI NIE MA ERRORÓW JAK SIĘ PRZYTRZYMUJE KLAWISZ
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	

	// PORUSZAINE GAWRONEM
	@Override
	public void keyTyped(KeyEvent e) 
	{
		if (g) {
			
		if (e.getKeyChar() == 'd')
		{
			if (typ[XGawron+1][YGawron] == 2)
			{
				if (typ[XGawron][YGawron] != 5)
					typ[XGawron][YGawron] = 2;
				XGawron++;
			}
		}
		if (e.getKeyChar() == 'a')
		{
			if (typ[XGawron-1][YGawron] == 2)
			{
				if (typ[XGawron][YGawron] != 5)
					typ[XGawron][YGawron] = 2;
				XGawron--;
			}
		}
		if (e.getKeyChar() == 'w')
		{
			if (typ[XGawron][YGawron-1] == 2)
			{
				if (typ[XGawron][YGawron] != 5)
					typ[XGawron][YGawron] = 2;
				YGawron--;
			}
		}
		if (e.getKeyChar() == 's')
		{
			if (typ[XGawron][YGawron+1] == 2)
			{
				if (typ[XGawron][YGawron] != 5)
					typ[XGawron][YGawron] = 2;
				YGawron++;
			}
		}
		
		if (typ[XGawron][YGawron] != 5)
			typ[XGawron][YGawron] = 4;
		
		
		if (e.getKeyChar() == ' ')  	// SPACJA
		{
			typ[XGawron][YGawron] = 5;
			
			try {
				wybuch(XGawron, YGawron);			// BUUUUM
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		}
		
		
		if (LiczGraczy == 2 && k) {
		
		/////////////////////////// DRUGI  (można zamknąć w jednym ifie) // to samo co wyżej
		
		// sterowanie      IJKL P
		
		if (e.getKeyChar() == 'l')
		{
			if (typ[XKot+1][YKot] == 2)
			{
				if (typ[XKot][YKot] != 5)
					typ[XKot][YKot] = 2;
				XKot++;
			}
		}
		if (e.getKeyChar() == 'j')
		{
			if (typ[XKot-1][YKot] == 2)
			{
				if (typ[XKot][YKot] != 5)
					typ[XKot][YKot] = 2;
				XKot--;
			}
		}
		if (e.getKeyChar() == 'i')
		{
			if (typ[XKot][YKot-1] == 2)
			{
				if (typ[XKot][YKot] != 5)
					typ[XKot][YKot] = 2;
				YKot--;
			}
		}
		if (e.getKeyChar() == 'k')
		{
			if (typ[XKot][YKot+1] == 2)
			{
				if (typ[XKot][YKot] != 5)
					typ[XKot][YKot] = 2;
				YKot++;
			}
		}
		
		if (typ[XKot][YKot] != 5)
			typ[XKot][YKot] = 7;
		
		
		if (e.getKeyChar() == 'p')  	// bomba
		{
			typ[XKot][YKot] = 5;
			

			try {
				wybuch(XKot, YKot);			// BUUUUM
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		
		//////////////////////////////////// DRUGI (wyżej)
		
		}
	
	}
	
	
	
	
	// BUUUM
	void wybuch(int x, int y) throws InterruptedException
	{
		Timer doWybuchu = new Timer();
		doWybuchu.schedule(new TimerTask() {
			  @Override
			  public void run() 
			  {
				  // jak się który wpierniczy pod wybuch
				  
				  if (typ[x+1][y] == 4 || typ[x-1][y] == 4 ||
						  typ[x][y+1] == 4 || typ[x][y-1] == 4)
				  {
					  kra.play(kraa);
					  g = false;
					  
					  if (k == false)
						{
							R.koniec();
							
							switch (jezyk)
							{
							case "pl":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Gawron zginął!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "en":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Rook is dead!!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "de":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Saatkrähe ist gestorben!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								break;
							}

							// KONIEC GRY
						}

				  }
				  
				  
				  if (XGawron == x && YGawron == y)		// gdy gawron stoi na bombie
				  {
					  g = false;
					  
					  if (k == false)
						{
							R.koniec();
							
							switch (jezyk)
							{
							case "pl":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Gawron zginął!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "en":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Rook is dead!!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);
								break;
								
							case "de":
								if (LiczGraczy == 1)
									JOptionPane.showMessageDialog(null, "Saatkrähe ist gestorben!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);
								break;
							}
							
							// KONIEC GRY
						}
				  }
				  
				  
				  ///////////////// DRUGI
				  
				  if (typ[x+1][y] == 7 || typ[x-1][y] == 7 ||
						  typ[x][y+1] ==  7 || typ[x][y-1] == 7)
				  {
					  kra.play(kraa);
					  k = false;
					  
					  if (g == false)
						{
							R.koniec();
							
							switch (jezyk)
							{
							case "pl":
								JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "en":
								JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "de":
								JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);	
								break;
							}
							
							// KONIEC GRY
						}
				  }
				  
				  if (XKot == x && YKot == y)		// gdy kot stoi na bombie
				  {
					  kra.play(kraa);
					  k = false;
					  
					  if (g == false)
						{
							R.koniec();

							switch (jezyk)
							{
							case "pl":
								JOptionPane.showMessageDialog(null, "Gawron i kot zginęli!", "KONIEC GRY", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "en":
								JOptionPane.showMessageDialog(null, "Rook and cat are dead!", "GAME OVER", JOptionPane.WARNING_MESSAGE);	
								break;
								
							case "de":
								JOptionPane.showMessageDialog(null, "Saatkrähe und Katze wurden getötet!", "SPIEL IST AUS", JOptionPane.WARNING_MESSAGE);	
								break;
							}
							
							// KONIEC GRY
						}
				  }
				  
				  ///////////////////////////
				  
				  
				  
				  // właściwy wybuch
				  

				  bum.play(inFileName);	// dzwięk
				  
				  typ[x][y] = 6;
				  if (typ[x+1][y] != 1)
					  typ[x+1][y] = 6;
				  if (typ[x-1][y] != 1)
					  typ[x-1][y]= 6;
				  if (typ[x][y-1] != 1)
					  typ[x][y-1] = 6;
				  if (typ[x][y+1] != 1)
					  typ[x][y+1] = 6;
				  
				  
				  Timer poWybuchu = new Timer();
				  poWybuchu.schedule(new TimerTask() {
					  @Override
					  public void run()
					  {
						  typ[x][y] = 2;
						  if (typ[x+1][y] != 1)
							  typ[x+1][y] = 2;
						  if (typ[x-1][y] != 1)
							  typ[x-1][y] = 2;
						  if (typ[x][y-1] != 1)
							  typ[x][y-1] = 2;
						  if (typ[x][y+1] != 1)
							  typ[x][y+1] = 2;
						  
					  }
				  }, 500);
	  
			  }
			}, 1000);
	}


	
}
