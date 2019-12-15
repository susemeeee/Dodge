package core;

import java.awt.Point;

import GUI.GameFrame;
import GUI.GameOverPanel;
import GUI.GamePanel;

public class RulingThread extends Thread {

	private GamePanel gamePanel;

	public RulingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void run() {
		GameCharacter character = gamePanel.getGameCharacter();

		while (true) {
			gamePanel.hitRuling();
			try {
            	Thread.sleep(1);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
		}

	}
}
