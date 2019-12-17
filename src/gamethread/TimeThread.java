package gamethread;

import javax.swing.ImageIcon;

import GUI.GamePanel;

public class TimeThread extends Thread {
	GamePanel gamePanel;
	private boolean isRunning;
	
	public TimeThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		isRunning = true;
	}

	public void run() {
		while(isRunning) {
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
			
			if(gamePanel.getGameCharacter().isInvincible()) {
				gamePanel.getGameCharacterLabel().setIcon(new ImageIcon("gamefiles/images/invinciblecharacter.png"));
				gamePanel.setDurationTime();
				System.out.println(gamePanel.getDurationTime());
				if(gamePanel.getDurationTime() > 3000) {
					gamePanel.getGameCharacterLabel().setIcon(gamePanel.getGameCharacter().getCharacterImage());
					gamePanel.resetDurationTime();
					gamePanel.getGameCharacter().setInvincible(false);
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
