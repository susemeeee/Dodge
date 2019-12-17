package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.GameManager;
import core.PlaySound;
import gameobject.GameCharacter;

public class GameOverPanel extends JPanel {
	private final int MAX_ROULETTE = 3;
	private JButton backButton = new JButton("Back");
	private JLabel gameOverLabel = new JLabel("게임 오버");
	private JLabel scoreTitleLabel = new JLabel("Score: ");
	private JLabel scoreLabel = new JLabel();
	private JLabel newRecordLabel = new JLabel("new Record!");
	private PlaySound playSound = new PlaySound();
	private JButton rouletteButton = new JButton("룰 렛");
	private JLabel rouletteInfoLabel = new JLabel("1회 돌려서 점수변경 3회 돌릴 시 최대 64배!!!");
	private int rouletteCount = 0;
	private JLabel remainingCountLabel = new JLabel(Integer.toString(MAX_ROULETTE - rouletteCount) + " 회 남음");
	
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
				rouletteCount++;
				if(rouletteCount < MAX_ROULETTE) {
					rouletteScore(gameCharacter);
					scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
					remainingCountLabel.setText(Integer.toString(MAX_ROULETTE - rouletteCount) + " 회 남음");
					
					revalidate();
					repaint();
				}
				else {
					rouletteScore(gameCharacter);
					scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
					remainingCountLabel.setText(Integer.toString(MAX_ROULETTE - rouletteCount) + " 회 남음");
					
					remove(rouletteButton);
					remove(rouletteInfoLabel);
					remove(remainingCountLabel);
					
					revalidate();
					repaint();
				}
			}
		});
		
		add(backButton);
		add(gameOverLabel);
		add(scoreTitleLabel);
		add(scoreLabel);
		add(rouletteButton);
		add(rouletteInfoLabel);
		add(remainingCountLabel);
	}
	
	private void setDefaultPanel(GameCharacter gameCharacter) {
		gameOverLabel.setSize(300, 70);
		gameOverLabel.setLocation(320, 100);
		gameOverLabel.setFont(new Font("궁서체", Font.BOLD, 36));
		
		scoreTitleLabel.setSize(300, 70);
		scoreTitleLabel.setLocation(270, 250);
		scoreTitleLabel.setFont(new Font("궁서체", Font.BOLD, 24));
		
		scoreLabel.setText(Integer.toString(gameCharacter.getCurrentScore()));
		scoreLabel.setSize(200, 70);
		scoreLabel.setLocation(320, 250);
		scoreLabel.setFont(new Font("궁서체", Font.PLAIN, 24));
		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
		backButton.setContentAreaFilled(false);
		
		rouletteButton.setSize(100, 40);
		rouletteButton.setLocation(10, 400);
		rouletteButton.setFont(new Font("굴림체", Font.BOLD, 24));
		rouletteButton.setContentAreaFilled(false);
		
		rouletteInfoLabel.setSize(550, 70);
		rouletteInfoLabel.setLocation(10, 460);
		rouletteInfoLabel.setFont(new Font("굴림체", Font.BOLD, 20));
		
		remainingCountLabel.setSize(400, 70);
		remainingCountLabel.setLocation(10, 500);
		remainingCountLabel.setFont(new Font("굴림체", Font.BOLD, 20));
	}
	
	private void setNewRecordLabel() {
		newRecordLabel.setSize(300, 70);
		newRecordLabel.setLocation(300, 400);
		newRecordLabel.setFont(new Font("궁서체", Font.BOLD, 36));
	}
	
	private void rouletteScore(GameCharacter gameCharacter) {
		double magnification = Math.pow(2.0, ThreadLocalRandom.current().nextDouble(-3.0, 2.0));
		gameCharacter.setCurrentScore((int)(gameCharacter.getCurrentScore() * magnification));
	}
}