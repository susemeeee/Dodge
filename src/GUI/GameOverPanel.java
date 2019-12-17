package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.crypto.spec.GCMParameterSpec;
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
	private JButton rouletteButton = new JButton("·ê ·¿");
	private JLabel rouletInfoLabel = new JLabel("1È¸ µ¹·Á¼­ Á¡¼öº¯°æ");
	
	public GameOverPanel(GameCharacter gameCharacter) throws IOException {
		setLayout(null);
		setDefaultPanel(gameCharacter);
		
		setLayout(null);
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameCharacter.getCurrentScore() > gameCharacter.getHighScore()) {
					gameCharacter.setHighScore(gameCharacter.getCurrentScore());
					
					try {
						GameManager.saveHighScore(gameCharacter.getUserName(), gameCharacter.getCurrentScore());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					setNewRecordLabel();
					add(newRecordLabel);
				}
				
				try {
					GameManager.sortRanking(gameCharacter.getUserName(), gameCharacter.getCurrentScore());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				playSound.playSound("button");
				
				try {
					gameCharacter.setCurrentScore(0);
					GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		rouletteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rouletteScore(gameCharacter);
				scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
				
				remove(rouletteButton);
				remove(rouletInfoLabel);
				revalidate();
				repaint();
			}
		});
		
		add(backButton);
		add(gameOverLabel);
		add(scoreTitleLabel);
		add(scoreLabel);
		add(rouletteButton);
		add(rouletInfoLabel);
	}
	
	private void setDefaultPanel(GameCharacter gameCharacter) {
		gameOverLabel.setSize(300, 70);
		gameOverLabel.setLocation(320, 100);
		gameOverLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 36));
		
		scoreTitleLabel.setSize(300, 70);
		scoreTitleLabel.setLocation(270, 250);
		scoreTitleLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 24));
		
		scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
		scoreLabel.setSize(200, 70);
		scoreLabel.setLocation(320, 250);
		scoreLabel.setFont(new Font("±Ã¼­Ã¼", Font.PLAIN, 24));
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
		backButton.setContentAreaFilled(false);
		
		rouletteButton.setSize(100, 40);
		rouletteButton.setLocation(10, 400);
		rouletteButton.setFont(new Font("±¼¸²Ã¼", Font.BOLD, 24));
		rouletteButton.setContentAreaFilled(false);
		
		rouletInfoLabel.setSize(400, 70);
		rouletInfoLabel.setLocation(10, 460);
		rouletInfoLabel.setFont(new Font("±¼¸²Ã¼", Font.BOLD, 20));
	}
	
	private void setNewRecordLabel() {
		newRecordLabel.setSize(300, 70);
		newRecordLabel.setLocation(300, 400);
		newRecordLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 36));
	}
	
	private void rouletteScore(GameCharacter gameCharacter) {
		double magnification = Math.random() * 1.2 + 0.4;
		gameCharacter.setCurrentScore((int)(gameCharacter.getCurrentScore() * magnification));
		System.out.println(gameCharacter.getCurrentScore());
	}
}