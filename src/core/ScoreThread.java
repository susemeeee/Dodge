package core;

import GUI.GamePanel;

public class ScoreThread extends Thread {
	GamePanel gamePanel;
	public ScoreThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		while(true) {
			try {
            	Thread.sleep(15);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
			gamePanel.setScore(1);
		}
	}
	
}
