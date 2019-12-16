package gamethread;

import java.awt.event.KeyEvent;
import java.io.IOException;

import GUI.GameFrame;
import GUI.GameOverPanel;
import GUI.GamePanel;
import GUI.MainPanel;
import gameobject.GameCharacter;

public class MovingThread extends Thread {
	private GamePanel gamePanel;
	private boolean isRunning = true;
	
	public MovingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		GameCharacter character = gamePanel.getGameCharacter();
        while(isRunning == true) {
            if(gamePanel.getKeyStatus() == 1) {
                if(gamePanel.getKeyCode() == KeyEvent.VK_LEFT) {
                    character.getCurrentPosition().x -= 1;
                    gamePanel.setCharacterPosition();
                }
                else if(gamePanel.getKeyCode() == KeyEvent.VK_RIGHT) {
                    character.getCurrentPosition().x += 1;
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
	
	public void shutdown() {
		isRunning = false;
	}

}
