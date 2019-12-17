package gamethread;

import GUI.GamePanel;

public class FallingObjectThread extends Thread {
	private GamePanel gamePanel;
	private boolean isRunning = true;
	
	public FallingObjectThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		while(isRunning) {
			gamePanel.setFallingObjectPosition();
			try {
				if(gamePanel.getFallingSpeed() == 0)
					Thread.sleep(2);
				else if(gamePanel.getFallingSpeed() == 1) 
					Thread.sleep(5);
				else
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
