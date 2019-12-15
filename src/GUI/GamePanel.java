package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.FallingObjectManager;
import core.FallingObjectThread;
import core.GameCharacter;
import core.MovingThread;

public class GamePanel extends JPanel {
	private final int MIN_X = 0;
	private final int MAX_X = 750;
	private GameCharacter currentGameCharacter;
	private JLabel characterLabel;
	private ImageIcon groundIcon = new ImageIcon("gamefiles/images/ground.png");
	private JLabel groundLabel;
	private FallingObjectManager fallingManager = new FallingObjectManager();
	private ArrayList<JLabel> fallingLabel = new ArrayList<JLabel>();
	private FallingObjectThread fallingThread = new FallingObjectThread(this);
	private MovingThread movingThread = new MovingThread(this);
	private int fallingObjectNum = 30;
	private int isKeyPressed = 0;
	private int keycode = 0;

	public GamePanel(GameCharacter gameCharacter) {
		this.currentGameCharacter = gameCharacter;
		setLayout(null);
		
		setDefaultpanel(gameCharacter);
		
		fallingThread.start();
		movingThread.start();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//?
				keycode = e.getKeyCode();
				if(keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_RIGHT) {
					isKeyPressed = 1;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {//?
				int releasedKeycode = e.getKeyCode();
				if(keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_RIGHT) {
					if(releasedKeycode != keycode)
						return;
					isKeyPressed = 0;
				}
			}
		});
		
		for(int i=0; i<fallingObjectNum; i++) {
			add(fallingLabel.get(i));
		}
		
		add(characterLabel);
		add(groundLabel);
	}
	
	private void setDefaultpanel(GameCharacter gameCharacter) {
		characterLabel = new JLabel(gameCharacter.getCharacterImage());
		characterLabel.setLocation(gameCharacter.getCurrentPosition().x, gameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);
		
		groundLabel = new JLabel(groundIcon);
		groundLabel.setLocation(0, 525);
		groundLabel.setSize(800, 50);
		
		for(int i=0; i<fallingObjectNum; i++) {
			fallingManager.addFallingObject(i*(-50));
			
			fallingLabel.add(new JLabel(fallingManager.getFallingObjectImage(i)));
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
			fallingLabel.get(i).setSize(40, 40);
		}
	}
	
	public void setCharacterPosition() {
		if(currentGameCharacter.getCurrentPosition().x < MIN_X) {
			currentGameCharacter.getCurrentPosition().x = 0;
		}
		else if(currentGameCharacter.getCurrentPosition().x > MAX_X) {
			currentGameCharacter.getCurrentPosition().x = 750;
		}
		
		characterLabel.setLocation(currentGameCharacter.getCurrentPosition().x, currentGameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);
	}
	
	public void setFallingObjectPosition() {
		for(int i=0; i<fallingManager.getCurrentObjectCount(); i++) {
			fallingManager.getFallingObject(i).setCurrentPosition(1);
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
		}
	}
	
	public int getKeyStatus() {
		return isKeyPressed;
	}
	
	public int getKeyCode() {
		return keycode;
	}
	
	public GameCharacter getGameCharacter() {
		return currentGameCharacter;
	}
	
	public FallingObjectManager getFallingManager() {
		return fallingManager;
	}
	
}