package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.GameManager;
import core.PlaySound;
import gameobject.GameCharacter;

public class GameOverPanel extends JPanel {
	private JButton backButton = new JButton("Back");
	private JLabel gameOverLabel = new JLabel("°ÔÀÓ ¿À¹ö");
	private JLabel scoreTitleLabel = new JLabel("Score: ");
	private JLabel scoreLabel = new JLabel();
	private JLabel newRecordLabel = new JLabel("new Record!");
	private PlaySound playSound = new PlaySound();
	
	public GameOverPanel(GameCharacter gameCharacter) throws IOException {
		setLayout(null);
		setDefaultPanel(gameCharacter);
		if(gameCharacter.getCurrentScore() > gameCharacter.getHighScore()) {
			gameCharacter.setHighScore(gameCharacter.getCurrentScore());
			GameManager.saveHighScore(gameCharacter.getUserName(), gameCharacter.getCurrentScore());
			setNewRecordLabel();
			add(newRecordLabel);
			GameManager.sortRanking(gameCharacter.getUserName(), gameCharacter.getHighScore());
		}
		
		setLayout(null);
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				playSound.playSound("button");
				try {
					gameCharacter.setCurrentScore(0);
					GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		add(backButton);
		add(gameOverLabel);
		add(scoreTitleLabel);
		add(scoreLabel);
	}
	
	private void setDefaultPanel(GameCharacter gameCharacter) {
		gameOverLabel.setSize(300, 70);
		gameOverLabel.setLocation(300, 100);
		gameOverLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 36));
		
		scoreTitleLabel.setSize(300, 70);
		scoreTitleLabel.setLocation(200, 200);
		scoreTitleLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 24));
		
		scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
		scoreLabel.setSize(300, 70);
		scoreLabel.setLocation(300, 200);
		scoreLabel.setFont(new Font("±Ã¼­Ã¼", Font.PLAIN, 24));
	}
	
	private void setNewRecordLabel() {
		newRecordLabel.setSize(300, 70);
		newRecordLabel.setLocation(300, 400);
		newRecordLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 36));
	}
}