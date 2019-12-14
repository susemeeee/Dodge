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
			try {
				Thread.sleep(2);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
