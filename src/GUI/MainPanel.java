package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.PlaySound;
import gameobject.GameCharacter;

public class MainPanel extends JPanel {
	private JButton startButton = new JButton("start");
	private JButton rankingButton = new JButton("ranking");
	private JLabel gameTitleLabel1 = new JLabel("Dodge");
	private JLabel gameTitleLabel2 = new JLabel("Youtube");
	private JLabel youtubeLabel = new JLabel(new ImageIcon("gamefiles/images/obstacle2.png"));
	private JLabel charcterLabel = new JLabel(new ImageIcon("gamefiles/images/gamecharacter2.png"));
	private JLabel moveEffectLabel = new JLabel("(((       (((");
	private PlaySound playSound = new PlaySound();

	public MainPanel(GameCharacter gameCharacter) {
		setDefaultPanel();
		setLayout(null);
		setBackground(Color.WHITE);

		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				playSound.playSound("button");
				try {
					GameFrame.changePanel(GamePanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		rankingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				playSound.playSound("button");
				try {
					GameFrame.changePanel(RankingPanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		add(gameTitleLabel1);
		add(gameTitleLabel2);
		add(startButton);
		add(rankingButton);
		add(charcterLabel);
		add(moveEffectLabel);
		add(youtubeLabel);
	}

	private void setDefaultPanel() {
		startButton.setSize(300, 60);
		rankingButton.setSize(300, 60);

		startButton.setLocation(250, 300);
		rankingButton.setLocation(250, 400);

		startButton.setFont(new Font("Consolas", Font.BOLD, 30));
		rankingButton.setFont(new Font("Consolas", Font.BOLD, 30));
		
		startButton.setContentAreaFilled(false);
		rankingButton.setContentAreaFilled(false);

		gameTitleLabel1.setSize(400, 100);
		gameTitleLabel1.setLocation(150, 80);
		gameTitleLabel1.setFont(new Font("consolas", Font.ITALIC, 48));
		gameTitleLabel1.setForeground(Color.BLACK);
		
		gameTitleLabel2.setSize(200, 100);
		gameTitleLabel2.setLocation(150, 180);
		gameTitleLabel2.setFont(new Font("consolas", Font.BOLD, 48));
		gameTitleLabel2.setForeground(Color.RED);
		
		charcterLabel.setSize(50,50);
		charcterLabel.setLocation(650,200);
		
		youtubeLabel.setSize(40,40);
		youtubeLabel.setLocation(500,210);
		
		moveEffectLabel.setSize(400, 25);
		moveEffectLabel.setLocation(450, 220);
		moveEffectLabel.setFont(new Font("consolas", Font.BOLD, 28));
		moveEffectLabel.setForeground(Color.DARK_GRAY);
	}
}