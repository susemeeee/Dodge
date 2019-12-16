package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.GameCharacter;
import core.GameManager;
import core.PlaySound;

public class RankingPanel extends JPanel {
	private ArrayList<String> usernameArray = new ArrayList<String>();
	private ArrayList<Integer> highscoreArray = new ArrayList<Integer>();
	private ArrayList<JLabel> userNameLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> scoreLabel = new ArrayList<JLabel>();
	private JLabel rankingTitleLabel = new JLabel("·© Å·");
	private JButton backButton = new JButton("Back");
	private PlaySound playSound = new PlaySound();

	public RankingPanel(GameCharacter gameCharacter) throws IOException {
		setLayout(null);
		setDefaultPanel();
		GameManager.readRanking(usernameArray, highscoreArray);
		
		for(int i = 0; i < usernameArray.size(); ++i) {
			userNameLabel.add(new JLabel(Integer.toString(i + 1) + "  " + usernameArray.get(i)));
			userNameLabel.get(i + 1).setSize(100, 70);
			userNameLabel.get(i + 1).setLocation(155, 60 * (i + 4));
			userNameLabel.get(i + 1).setFont(new Font("±Ã¼­Ã¼", Font.PLAIN, 24));
			add(userNameLabel.get(i + 1));
			
			scoreLabel.add(new JLabel(Integer.toString(highscoreArray.get(i))));
			scoreLabel.get(i + 1).setSize(100, 70);
			scoreLabel.get(i + 1).setLocation(490, 60 * (i + 4));
			scoreLabel.get(i + 1).setFont(new Font("±Ã¼­Ã¼", Font.PLAIN, 24));
			scoreLabel.get(i + 1).setHorizontalAlignment(JLabel.RIGHT);
			add(scoreLabel.get(i + 1));
		}
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				playSound.playSound("button");
				try {
					GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		add(userNameLabel.get(0));
		add(scoreLabel.get(0));
		add(rankingTitleLabel);
		add(backButton);
	}
	
	private void setDefaultPanel() {
		userNameLabel.add(new JLabel("Name"));
		userNameLabel.get(0).setSize(100, 70);
		userNameLabel.get(0).setLocation(175, 150);
		userNameLabel.get(0).setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 30));
		userNameLabel.get(0).setHorizontalAlignment(JLabel.CENTER);
		
		scoreLabel.add(new JLabel("Score"));
		scoreLabel.get(0).setSize(100, 70);
		scoreLabel.get(0).setLocation(500, 150);
		scoreLabel.get(0).setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 30));
		scoreLabel.get(0).setHorizontalAlignment(JLabel.CENTER);
		
		rankingTitleLabel.setSize(400, 70);
		rankingTitleLabel.setLocation(325, 50);
		rankingTitleLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 48));
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
	}
}