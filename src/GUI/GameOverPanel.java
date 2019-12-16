package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.GameCharacter;
import core.GameManager;

public class GameOverPanel extends JPanel {
	private JButton backButton = new JButton("Back");
	private JLabel gameOverLabel = new JLabel("∞‘¿” ø¿πˆ");
	private JLabel scoreTitleLabel = new JLabel("Score: ");
	private JLabel scoreLabel = new JLabel();
	private JLabel newRecordLabel = new JLabel("new Record!");
	
	public GameOverPanel(GameCharacter gameCharacter) throws IOException {
		setLayout(null);
		setDefaultPanel(gameCharacter);
		if(gameCharacter.getCurrentScore() > gameCharacter.getHighScore()) {
			gameCharacter.setHighScore(gameCharacter.getCurrentScore());
			GameManager.saveHighScore(gameCharacter.getUserName(), gameCharacter.getCurrentScore());
			setNewRecordLabel();
			add(newRecordLabel);
		}
		
		//GameManager.sortRanking(gameCharacter.getHighScore());
		//∑©≈∑ ºº¿Ã∫Í«‘ºˆ ¿€µø
		
		setLayout(null);
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		gameOverLabel.setFont(new Font("±√º≠√º", Font.BOLD, 36));
		
		scoreTitleLabel.setSize(300, 70);
		scoreTitleLabel.setLocation(200, 200);
		scoreTitleLabel.setFont(new Font("±√º≠√º", Font.BOLD, 24));
		
		scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
		scoreLabel.setSize(300, 70);
		scoreLabel.setLocation(300, 200);
		scoreLabel.setFont(new Font("±√º≠√º", Font.PLAIN, 24));
	}
	
	private void setNewRecordLabel() {
		newRecordLabel.setSize(300, 70);
		newRecordLabel.setLocation(300, 400);
		newRecordLabel.setFont(new Font("±√º≠√º", Font.BOLD, 36));
	}
}