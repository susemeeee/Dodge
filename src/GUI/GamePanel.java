package GUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.FallingObjectManager;
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

	public GamePanel() {
		setLayout(null);
		
		characterLabel = new JLabel(gameCharacter.getCharacterImage());
		groundLabel = new JLabel(groundIcon);
		
		setDefaultpanel();
	
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
		
		add(characterLabel);
		add(groundLabel);
	}
	
	private void setDefaultpanel() {
		characterLabel.setLocation(gameCharacter.getCurrentPosition().x, gameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);
		
		groundLabel.setLocation(0, 525);
		groundLabel.setSize(800, 50);
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
	
}