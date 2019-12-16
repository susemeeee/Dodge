package core;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlaySound {
	private Clip clip;
	
	public void playSound(String name) {
		try {
			String fileName = new String("gamefiles/sounds/" + name + ".wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File(fileName));
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
