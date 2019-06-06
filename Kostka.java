package Projekt;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Kostka extends BufferedImage
{
	BufferedImage white, gray, black, green, yellow, red, pink, blue;
	
	
	public Kostka(int width, int height, int imageType) 
	{
		super(width, height, imageType);
			
		
		
		URL resource = getClass().getResource("obrazki/mur.png");    
		try {
			black = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resource = getClass().getResource("obrazki/trawa.png");    
		try {
			white = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resource = getClass().getResource("obrazki/wood.png");    
		try {
			gray = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resource = getClass().getResource("obrazki/bird.png");    
		try {
			blue = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resource = getClass().getResource("obrazki/bomb.png");    
		try {
			yellow = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resource = getClass().getResource("obrazki/ogien.png");    
		try {
			red = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resource = getClass().getResource("obrazki/cat.png");    
		try {
			pink = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resource = getClass().getResource("obrazki/enemy.png");    
		try {
			green = ImageIO.read(resource);
	//		System.out.println("jest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public BufferedImage bialy()
	{
		return white;
	}
      
	
	public BufferedImage szary()
	{
		return gray;
	}
	
	public BufferedImage zielony()
	{
		return green;
	}
	
	public BufferedImage zolty()
	{
		return yellow;
	}
	
	public BufferedImage rozowy()
	{
		return pink;
	}
	
	public BufferedImage niebieski()
	{
		return blue;
	}
	
	public BufferedImage czerwony()
	{
		return red;
	}
	
	public BufferedImage czarny()
	{
		return black;
	}

    
}
