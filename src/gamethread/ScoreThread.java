package gamethread;

import GUI.GamePanel;

public class ScoreThread extends Thread {
	GamePanel gamePanel;
	private boolean isRunning = true;
	
	public ScoreThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		while(isRunning == true) {
			try {
            	Thread.sleep(15);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
			gamePanel.setScore(1);
		}
	}
	
	public void shutdown() {
		isRunning = false;
	}
	
}
