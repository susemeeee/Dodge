package gamethread;

import GUI.GamePanel;

public class TimeThread extends Thread {
	GamePanel gamePanel;
	private boolean isRunning;
	
	public TimeThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		isRunning = true;
	}

	public void run() {
		while(isRunning == true) {
			if(gamePanel.getFallingSpeed() == 1) {
				gamePanel.setDurationTime();
				if(gamePanel.getDurationTime() > 3000) {
					gamePanel.resetDurationTime();
					gamePanel.resetFallingSpeed();
				}
			}
			if(gamePanel.getFallingSpeed() == 2) {
				gamePanel.setDurationTime();
				if(gamePanel.getDurationTime() > 1000) {
					gamePanel.resetDurationTime();
					gamePanel.resetFallingSpeed();
				}
			}
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
