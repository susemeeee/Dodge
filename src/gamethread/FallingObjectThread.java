package gamethread;

import GUI.GamePanel;

public class FallingObjectThread extends Thread {
	private GamePanel gamePanel;
	private boolean isRunning = true;
	
	public FallingObjectThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		while(isRunning == true) {
			gamePanel.setFallingObjectPosition();
			try {
				Thread.sleep(2);
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
