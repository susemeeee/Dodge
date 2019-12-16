package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.GameCharacter;
import core.PlaySound;

public class MainPanel extends JPanel {
	private JButton startButton = new JButton("start");
	private JButton rankingButton = new JButton("ranking");
	private JLabel gameTitleLabel = new JLabel("¶ËÇÇÇÏ±â");
	private PlaySound playSound = new PlaySound();

	public MainPanel(GameCharacter gameCharacter) {
		setDefaultPanel();
		setLayout(null);

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

		add(gameTitleLabel);
		add(startButton);
		add(rankingButton);
	}

	private void setDefaultPanel() {
		startButton.setSize(300, 60);
		rankingButton.setSize(300, 60);

		startButton.setLocation(250, 300);
		rankingButton.setLocation(250, 400);

		startButton.setFont(new Font("Consolas", Font.BOLD, 30));
		rankingButton.setFont(new Font("Consolas", Font.BOLD, 30));

		gameTitleLabel.setSize(400, 100);
		gameTitleLabel.setLocation(300, 100);
		gameTitleLabel.setFont(new Font("±Ã¼­Ã¼", Font.BOLD, 48));
	}
}