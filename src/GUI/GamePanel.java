package GUI;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.FallingObjectManager;
import gameobject.FallingObject;
import gameobject.GameCharacter;
import gamethread.FallingObjectThread;
import gamethread.MovingThread;
import gamethread.RulingThread;
import gamethread.ScoreThread;
import gamethread.SoundThread;
import gamethread.TimeThread;

public class GamePanel extends JPanel {
	private final int MIN_X = 0;
	private final int MAX_X = 750;
	private final int MAX_Y = 550;
	private GameCharacter currentGameCharacter;
	private JLabel characterLabel;
	private ImageIcon groundIcon = new ImageIcon("gamefiles/images/ground.png");
	private JLabel groundLabel;
	private FallingObjectManager fallingManager = new FallingObjectManager();
	private ArrayList<JLabel> fallingLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> effectLabel = new ArrayList<JLabel>();
	private FallingObject currentItem = fallingManager.pickItem();
	private JLabel itemLabel;
	private FallingObjectThread fallingThread = new FallingObjectThread(this);
	private MovingThread movingThread = new MovingThread(this);
	private RulingThread rulingThread = new RulingThread(this);
	private ScoreThread scoreThread = new ScoreThread(this);
	private SoundThread soundThread = new SoundThread();
	private TimeThread timeThread = new TimeThread(this);
	private JLabel scoreTitle;
	private JLabel scoreLabel;
	private JLabel highScoreTitle;
	private JLabel highScoreLabel;
	private JLabel randomScoreLabel;
	private int fallingObjectNum = 30;
	private int isKeyPressed = 0;
	private int keycode = 0;
	private int fallingSpeed = 0;  // 0 = defalut, 1 = slow, 2 = fast
	private int durationTime = 0;  // 아이템 효과 지속시간
	private int randomScore = 0; // default = 0, eatItem => random number
	private boolean isEffectOn = false;

	public GamePanel(GameCharacter gameCharacter) {
		this.currentGameCharacter = gameCharacter;
		setLayout(null);

		setDefaultpanel(gameCharacter);

		fallingThread.start();
		movingThread.start();
		rulingThread.start();
		scoreThread.start();
		soundThread.start();
		timeThread.start();

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
			add(effectLabel.get(i));
		}

		add(characterLabel);
		add(groundLabel);
		add(scoreTitle);
		add(scoreLabel);
		add(highScoreTitle);
		add(highScoreLabel);
		add(itemLabel);
		add(randomScoreLabel);
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
		
		scoreLabel = new JLabel(Integer.toString(gameCharacter.getCurrentScore()));
		scoreLabel.setLocation(650,20);
		scoreLabel.setSize(100,50);
		scoreLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		highScoreLabel = new JLabel(Integer.toString(gameCharacter.getHighScore()));
		highScoreLabel.setLocation(650,50);
		highScoreLabel.setSize(100,50);
		highScoreLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		highScoreLabel.setHorizontalAlignment(JLabel.RIGHT);

		for (int i = 0; i < fallingObjectNum; i++) {
			fallingManager.addFallingObject(i * (-50));

			fallingLabel.add(new JLabel(fallingManager.getFallingObjectImage(i)));
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
			fallingLabel.get(i).setSize(40, 40);
			
			effectLabel.add(new JLabel(new ImageIcon("gamefiles/images/sparkleeffect.png")));
			effectLabel.get(i).setLocation(0, - 200);
			effectLabel.get(i).setSize(40, 40);
		}
		
		itemLabel = new JLabel(currentItem.getFallingObjectImage());
		itemLabel.setLocation(currentItem.getCurrentPosition());
		itemLabel.setSize(40, 40);
		
		randomScoreLabel = new JLabel(" ");
		randomScoreLabel.setLocation(0, -200);
		randomScoreLabel.setSize(100,50);
		randomScoreLabel.setFont(new Font("Consolas", Font.BOLD, 15));
		
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
			fallingManager.getFallingObject(i).objectFalling(1);
			fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
		}
		
		currentItem.setItemPosition(1);
		itemLabel.setLocation(currentItem.getCurrentPosition());
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
	
	public JLabel getGameCharacterLabel() {
		return characterLabel;
	}
	
	public ArrayList<JLabel> getEffectLabel() {
		return effectLabel;
	}

	public FallingObjectManager getFallingManager() {
		return fallingManager;
	}
	
	public int getFallingSpeed() {
		return fallingSpeed;
	}
	
	public void resetFallingSpeed() {
		fallingSpeed = 0;
	}
	
	public int getDurationTime() {
		return durationTime;
	}
	
	public void setDurationTime() {
		durationTime++;
	}
	
	public void resetDurationTime() {
		durationTime = 0;
	}
	
	public int getRandomScore() {
		return randomScore;
	}
	
	public void resetRandomScore() {
		randomScore = 0;
	}
	
	public void setScore(int num) {
		currentGameCharacter.setCurrentScore(currentGameCharacter.getCurrentScore() + num);
		String toHighScore;
		if(currentGameCharacter.getCurrentScore() - currentGameCharacter.getHighScore() > 0) {
			toHighScore = Integer.toString(currentGameCharacter.getCurrentScore());
		}
		else {
			toHighScore = Integer.toString(currentGameCharacter.getHighScore());
		}
		scoreLabel.setText(Integer.toString(currentGameCharacter.getCurrentScore()));
		highScoreLabel.setText(toHighScore);
	}
	
	public void setRandomScoreVisible(boolean isVisible) {
		randomScoreLabel.setVisible(isVisible);
	}
	
	public void setEffectVisible(boolean isVisible) {
		isEffectOn = isVisible;
		for(int i = 0; i<effectLabel.size(); i++) {
			effectLabel.get(i).setLocation(fallingLabel.get(i).getLocation());
			effectLabel.get(i).setVisible(isVisible);
		}
	}
	
	public boolean getIsEffectOn() {
		return isEffectOn;
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
				&& characterTop < objectBottom && !(currentGameCharacter.isInvincible()) )
			{
				characterLabel.setIcon(new ImageIcon("gamefiles/images/shockwave.png"));
				fallingThread.shutdown();
				movingThread.shutdown();
				scoreThread.shutdown();
				soundThread.shutdown();
				timeThread.shutdown();
				try {
					Thread.sleep(2000);
					GameFrame.changePanel(GameOverPanel.class.getName(), currentGameCharacter);
				} catch (IOException e) {
					e.printStackTrace();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				rulingThread.shutdown();
			}
		}
		
		int itemLeft = currentItem.getCurrentPosition().x + 5;
		int itemRight = currentItem.getCurrentPosition().x + currentItem.getSizeRange().x;
		int itemBottom = currentItem.getCurrentPosition().y + currentItem.getSizeRange().y + 5;
		
		if(((characterRight > itemLeft && characterLeft < itemLeft)
				|| (characterRight > itemRight && characterLeft < itemRight))
			&& characterTop < itemBottom) {
			itemEffect(currentItem.getClass().getName());
			currentItem = fallingManager.pickItem();
			itemLabel.setIcon(currentItem.getFallingObjectImage());
		}
		else if(itemBottom > MAX_Y) {
			currentItem = fallingManager.pickItem();
			itemLabel.setIcon(currentItem.getFallingObjectImage());
		}

	}
	
	public void itemEffect(String itemName) {
		if(itemName.equals("gameobject.ClearItem")) {
			setEffectVisible(true);
			for(int i = 0; i < fallingManager.getCurrentObjectCount(); ++i) {
				fallingManager.getFallingObject(i).setNewObjectPosition();
				fallingLabel.get(i).setLocation(fallingManager.getFallingObject(i).getCurrentPosition());
			}
		}
		if(itemName.equals("gameobject.SlowItem")) {
			fallingSpeed = 1;
		}
		
		if(itemName.equals("gameobject.FastItem")) {
			fallingSpeed = 2;
		}
		
		if(itemName.equals("gameobject.RandomScoreItem")) {
			randomScore = ThreadLocalRandom.current().nextInt(-1000, 1001);
			Point eatPoint = currentGameCharacter.getCurrentPosition();
			currentGameCharacter.setCurrentScore(currentGameCharacter.getCurrentScore() + randomScore);
			if(randomScore > 0) {
				randomScoreLabel.setText("+" + Integer.toString(randomScore));
			}
			else {
				randomScoreLabel.setText(Integer.toString(randomScore));
			}
			randomScoreLabel.setLocation(eatPoint.x, eatPoint.y-50);
		}
		
		if(itemName.equals("gameobject.InvincibleItem")) {
			currentGameCharacter.setInvincible(true);
		}
		
	}//앞으로 다른아이템 효과도 여기서
}