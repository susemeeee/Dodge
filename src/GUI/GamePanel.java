package GUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.FallingObjectManager;
import core.FallingObjectThread;
import core.GameCharacter;

public class GamePanel extends JPanel {
	private final int MIN_X = 0;
	private final int MAX_X = 750;
	private GameCharacter gameCharacter = new GameCharacter();
	private JLabel characterLabel;
	private ImageIcon groundIcon = new ImageIcon("gamefiles/images/ground.png");
	private JLabel groundLabel;
	private FallingObjectManager fallingManager = new FallingObjectManager();
	private ArrayList<JLabel> fallingLabel = new ArrayList<JLabel>();
	private FallingObjectThread fallingThread = new FallingObjectThread(this);
	private int fallingObjectNum = 30;

	public GamePanel() {
		setLayout(null);
		
		setDefaultpanel();
		
		fallingThread.start();
	
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();

				switch (keycode) {
				case KeyEvent.VK_LEFT:
					gameCharacter.getCurrentPosition().x -= 20;
					setCharacterPosition();
					break;
				case KeyEvent.VK_RIGHT:
					gameCharacter.getCurrentPosition().x += 20;
					setCharacterPosition();
					break;
				default:
					break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keycode = e.getKeyCode();

				switch (keycode) {
				case KeyEvent.VK_LEFT:
					gameCharacter.getCurrentPosition().x -= 20;
					setCharacterPosition();
					break;
				case KeyEvent.VK_RIGHT:
					gameCharacter.getCurrentPosition().x += 20;
					setCharacterPosition();
					break;
				default:
					break;
				}
			}
		});
		
		for(int i=0; i<fallingObjectNum; i++) {
			add(fallingLabel.get(i));
			System.out.println("ADD DONE " + i);
		}
		
		add(characterLabel);
		add(groundLabel);
	}
	
	private void setDefaultpanel() {
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
	
	private void setCharacterPosition() {
		if(gameCharacter.getCurrentPosition().x < MIN_X) {
			gameCharacter.getCurrentPosition().x = 0;
		}
		else if(gameCharacter.getCurrentPosition().x > MAX_X) {
			gameCharacter.getCurrentPosition().x = 750;
		}
		
		characterLabel.setLocation(gameCharacter.getCurrentPosition().x, gameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);
	}
	
	public void setFallingObjectPosition() {
		for(int i=0; i<fallingManager.getCurrentObjectCount(); i++) {
			fallingManager.getFallingObject(i).setCurrentPosition(5);
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
		}
	}
	
}