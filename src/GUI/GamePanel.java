package GUI;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.FallingObject;
import core.FallingObjectManager;
import core.FallingObjectThread;
import core.GameCharacter;
import core.MovingThread;
import core.RulingThread;
import core.ScoreThread;

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
	private RulingThread rulingThread = new RulingThread(this);
	private ScoreThread scoreThread = new ScoreThread(this);
	private JLabel scoreTitle;
	private JLabel scoreLabel;
	private JLabel highScoreTitle;
	private JLabel highScoreLabel;
	private int score = 0;
	private int fallingObjectNum = 30;
	private int isKeyPressed = 0;
	private int keycode = 0;

	public GamePanel(GameCharacter gameCharacter) {
		this.currentGameCharacter = gameCharacter;
		setLayout(null);

		setDefaultpanel(gameCharacter);

		fallingThread.start();
		movingThread.start();
		rulingThread.start();
		scoreThread.start();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {// ?
				keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_RIGHT) {
					isKeyPressed = 1;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {// ?
				int releasedKeycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_RIGHT) {
					if (releasedKeycode != keycode)
						return;
					isKeyPressed = 0;
				}
			}
		});

		for (int i = 0; i < fallingObjectNum; i++) {
			add(fallingLabel.get(i));
		}

		add(characterLabel);
		add(groundLabel);
		add(scoreTitle);
		add(scoreLabel);
		add(highScoreTitle);
		add(highScoreLabel);
	}

	private void setDefaultpanel(GameCharacter gameCharacter) {
		characterLabel = new JLabel(gameCharacter.getCharacterImage());
		characterLabel.setLocation(gameCharacter.getCurrentPosition().x, gameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);

		groundLabel = new JLabel(groundIcon);
		groundLabel.setLocation(0, 525);
		groundLabel.setSize(800, 50);
		
		scoreTitle = new JLabel("Score : ");
		scoreTitle.setLocation(550,20);
		scoreTitle.setSize(120,50);
		scoreTitle.setFont(new Font("Consolas", Font.BOLD, 25));
		scoreTitle.setHorizontalAlignment(JLabel.CENTER);
		
		highScoreTitle = new JLabel("HighScore : ");
		highScoreTitle.setLocation(482,50);
		highScoreTitle.setSize(200,50);
		highScoreTitle.setFont(new Font("Consolas", Font.BOLD, 25));
		highScoreTitle.setHorizontalAlignment(JLabel.CENTER);
		
		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setLocation(650,20);
		scoreLabel.setSize(100,50);
		scoreLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		gameCharacter.setHighScore(1000); // 1000으로 테스트
		highScoreLabel = new JLabel(Integer.toString(score-gameCharacter.getHighScore()));
		highScoreLabel.setLocation(650,50);
		highScoreLabel.setSize(100,50);
		highScoreLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		highScoreLabel.setHorizontalAlignment(JLabel.RIGHT);

		for (int i = 0; i < fallingObjectNum; i++) {
			fallingManager.addFallingObject(i * (-50));

			fallingLabel.add(new JLabel(fallingManager.getFallingObjectImage(i)));
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
			fallingLabel.get(i).setSize(40, 40);
		}
	}

	public void setCharacterPosition() {
		if (currentGameCharacter.getCurrentPosition().x < MIN_X) {
			currentGameCharacter.getCurrentPosition().x = 0;
		} else if (currentGameCharacter.getCurrentPosition().x > MAX_X) {
			currentGameCharacter.getCurrentPosition().x = 750;
		}

		characterLabel.setLocation(currentGameCharacter.getCurrentPosition().x,
				currentGameCharacter.getCurrentPosition().y);
		characterLabel.setSize(50, 50);
	}

	public void setFallingObjectPosition() {
		for (int i = 0; i < fallingManager.getCurrentObjectCount(); i++) {
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
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int num) {
		score += num;
		String toHighScore;
		if(score-currentGameCharacter.getHighScore() > 0) {
			toHighScore = "+" + Integer.toString(score-currentGameCharacter.getHighScore());
		}
		else {
			toHighScore = Integer.toString(score-currentGameCharacter.getHighScore());
		}
		scoreLabel.setText(Integer.toString(score));
		highScoreLabel.setText(toHighScore);
	}

	public void hitRuling() {
		int characterLeft = currentGameCharacter.getCurrentPosition().x+10;
		int characterRight = characterLeft + currentGameCharacter.getSizeRange().x;
		int characterTop = currentGameCharacter.getCurrentPosition().y+10;
		
		for (int i = 0; i < fallingManager.getCurrentObjectCount(); i++) {
			int objectLeft = fallingManager.getFallingObject(i).getCurrentPosition().x+10;
			int objectRight = objectLeft + fallingManager.getFallingObject(i).getSizeRange().x;
			int objectBottom = fallingManager.getFallingObject(i).getCurrentPosition().y + fallingManager.getFallingObject(i).getSizeRange().y+10;
			
			if (((characterRight > objectLeft && characterLeft < objectLeft)
					|| (characterRight > objectRight && characterLeft < objectRight))
				&& characterTop < objectBottom)
			{
				characterLabel.setIcon(new ImageIcon("gamefiles/images/shockwave.png"));
				fallingThread.stop();
				movingThread.stop();
				scoreThread.stop();
				try {
					Thread.sleep(2000);
					GameFrame.changePanel(GameOverPanel.class.getName(), currentGameCharacter);
				} catch (IOException e) {
					e.printStackTrace();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				rulingThread.stop();
			}
		}
	}

}