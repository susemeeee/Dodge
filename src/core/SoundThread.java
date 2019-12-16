package core;

public class SoundThread extends Thread {
	private PlaySound playSound = new PlaySound();
	private boolean isRunning = true;
	
	public void run() {
		while (isRunning == true) {
			try {
				playSound.playSound("background");
				Thread.sleep(108000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		isRunning = false;
		playSound.stopSound();
	}

}
