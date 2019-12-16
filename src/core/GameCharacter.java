package core;

import java.awt.Point;

import javax.swing.ImageIcon;

public class GameCharacter {
	private Point currentPosition;
	private Point sizeRange;
	private ImageIcon characterImage = new ImageIcon("gamefiles/images/gamecharacter.png");
	private int highScore;//일단 100으로 때려박아놓고 랭킹기능 테스트
	private int currentScore;
	private String username;
	
	public GameCharacter() {
		currentPosition = new Point(400, 500);
		sizeRange = new Point(30,30);
		highScore = 0;
		currentScore = 0;
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
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public void setCurrentScore(int score) {
		this.currentScore = score;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
}
