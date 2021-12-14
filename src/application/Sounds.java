package application;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {
	
	private String nameSound;
	private int cantLoop;
	
	private Clip clip;
	
	public Sounds(String nameSound, int cantLoop) {
		this.nameSound = nameSound;
		this.cantLoop = cantLoop;
	}
	
	public void loadSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nameSound).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			if (cantLoop > 0) {
				clip.loop(cantLoop);
			}
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
	}
	
	public void stopSound() {
		clip.stop();
	}
}
