package gameobject;

import java.awt.Point;

import javax.swing.ImageIcon;

public class GameCharacter {
	private Point currentPosition;
	private Point sizeRange;
	private ImageIcon characterImage = new ImageIcon("gamefiles/images/gamecharacter2.png");
	private int highScore;
	private int currentScore;
	private String username;
	private boolean invincible;
	
	public GameCharacter() {
		currentPosition = new Point(400, 500);
		sizeRange = new Point(30,30);
		highScore = 0;
		currentScore = 0;
		invincible = false;
	}
	
	public Point getCurrentPosition() {
		return currentPosition;
	}
	
	public Point getSizeRange() {
		return sizeRange;
	}
	
	public ImageIcon getCharacterImage() {
		return characterImage;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public int getCurrentScore() {
		return currentScore;
	}
	public String getUserName() {
		return username;
	}
	
	public boolean isInvincible() {
		return invincible;
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public void setCurrentScore(int score) {
		this.currentScore = score;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
}
