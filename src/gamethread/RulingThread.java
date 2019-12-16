package gamethread;

import GUI.GamePanel;
import gameobject.GameCharacter;

public class RulingThread extends Thread {
	private GamePanel gamePanel;
	private boolean isRunning = true;
	
	public RulingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void run() {
		GameCharacter character = gamePanel.getGameCharacter();
		while (isRunning == true) {
			gamePanel.hitRuling();
			try {
            	Thread.sleep(1);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
		}

	}
	
	public void shutdown() {
		isRunning = false;
	}
}
