package core;

import GUI.GamePanel;

public class FallingObjectThread extends Thread {
	private GamePanel gamePanel;
	
	public FallingObjectThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		while(true) {
			gamePanel.setFallingObjectPosition();
		}
	}
}
