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
				if(gamePanel.getDurationTime() > 3000) {
					gamePanel.getGameCharacterLabel().setIcon(gamePanel.getGameCharacter().getCharacterImage());
					gamePanel.resetDurationTime();
					gamePanel.getGameCharacter().setInvincible(false);
				}
			}
			
			if(gamePanel.getRandomScore() != 0) {
				gamePanel.setRandomScoreVisible(true);
				gamePanel.setDurationTime();
				if(gamePanel.getDurationTime() > 500) {
					gamePanel.setRandomScoreVisible(false);
					gamePanel.resetDurationTime();
					gamePanel.resetRandomScore();
				}
			}
			
			if(gamePanel.getIsEffectOn()) {
				gamePanel.setDurationTime();
				if(gamePanel.getDurationTime() > 200) {
					gamePanel.setEffectVisible(false);
					gamePanel.resetDurationTime();
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
