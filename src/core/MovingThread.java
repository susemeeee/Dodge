package core;

import java.awt.event.KeyEvent;

import GUI.GamePanel;

public class MovingThread extends Thread {
	private GamePanel gamePanel;
	
	public MovingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
        while(true) {
            if(gamePanel.getKeyStatus() == 1) {
                if(gamePanel.getKeyCode() == KeyEvent.VK_LEFT) {
                    gamePanel.getGameCharacter().getCurrentPosition().x -= 1;
                    gamePanel.setCharacterPosition();
                }
                else if(gamePanel.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gamePanel.getGameCharacter().getCurrentPosition().x += 1;
                    gamePanel.setCharacterPosition();
                }
            }

            try {
            Thread.sleep(2);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
