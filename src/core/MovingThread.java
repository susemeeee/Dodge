package core;

import java.awt.event.KeyEvent;

import GUI.GamePanel;

public class MovingThread extends Thread {
	private GamePanel gamePanel;
	
	public MovingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		int countLeft = 0;
		int countRight = 0;
		while(true) {
			System.out.println(" ");
			if(gamePanel.getKeyStatus() == 1) {
				if(gamePanel.getKeyCode() == KeyEvent.VK_LEFT) {
					if(countLeft == 700) {
						gamePanel.getGameCharacter().getCurrentPosition().x -= 1;
						gamePanel.setCharacterPosition();
						countLeft = 0;
					}
					countLeft++;
					countRight = 0;
				}
				else if(gamePanel.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(countRight == 700) {
						gamePanel.getGameCharacter().getCurrentPosition().x += 1;
						gamePanel.setCharacterPosition();
						countRight = 0;
					}
					countRight++;
					countLeft = 0;
				}
			}
		}
	}

}
