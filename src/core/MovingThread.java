package core;

import java.awt.event.KeyEvent;

import GUI.GameFrame;
import GUI.GameOverPanel;
import GUI.GamePanel;
import GUI.MainPanel;

public class MovingThread extends Thread {
	private GamePanel gamePanel;
	
	public MovingThread(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void run() {
		GameCharacter character = gamePanel.getGameCharacter();
        while(true) {
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
            
            for(int i=0; i<gamePanel.getFallingManager().getCurrentObjectCount(); i++) {
            	FallingObject fallingObject = gamePanel.getFallingManager().getFallingObject(i);
            	if(character.getCurrentPosition().x > fallingObject.getCurrentPosition().x - 20 &&
            			character.getCurrentPosition().x < fallingObject.getCurrentPosition().x + 20 &&
            			character.getCurrentPosition().y < fallingObject.getCurrentPosition().y + 20 &&
            			character.getCurrentPosition().y > fallingObject.getCurrentPosition().y - 20 ) {
            		GameFrame.changePanel(GameOverPanel.class.getName(), character);
            		return;
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
