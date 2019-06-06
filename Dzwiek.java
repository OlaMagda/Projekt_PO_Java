package Projekt;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Dzwiek  {
     
	Clip audioClip = null;
	File audioFile = null;
	AudioInputStream audioStream = null;
     
    void play(String audioFilePath) 
    {
    	
        try
        {
            audioFile = new File(audioFilePath);
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            
            Thread thread = new Thread(new Runnable() 
            {
                public void run() 
                {
                	  
                	 if (audioFilePath == "./resources/arcade.wav"
                			|| audioFilePath == "./resources/mountain_king.wav")
                	{
                		audioClip.loop(Clip.LOOP_CONTINUOUSLY);
                	 	audioClip.start();
                	}
                	else
                	{
                	 	audioClip.start();
                	}
                	 
                }
                        
            });
            thread.start();
            

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error playing the audio file.");
			e1.printStackTrace();
		} 
         
    }
    public void setVolume(float volume) 
    {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    
  
}