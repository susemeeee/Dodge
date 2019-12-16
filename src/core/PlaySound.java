package core;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlaySound {
	private Clip clip;
	AudioInputStream sound;
	String fileName;
	
	public void playSound(String name) {
		try {
			fileName = new String("gamefiles/sounds/" + name + ".wav");
			sound = AudioSystem.getAudioInputStream(new File(fileName));
			clip = AudioSystem.getClip();
			clip.stop();
			clip.open(sound);
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void stopSound() {
		clip.stop();
	}
	
}
