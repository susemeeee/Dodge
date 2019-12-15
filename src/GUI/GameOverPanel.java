package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.GameCharacter;

public class GameOverPanel extends JPanel {
	private JButton backButton = new JButton("Back");
	public GameOverPanel(GameCharacter gameCharacter) {
		setBackground(Color.GREEN);//test
		//현재점수가 최고점수일때 최고점수를 바꿈
		//현재점수를 0으로 돌림
		setLayout(null);
		
		backButton.setSize(100,40);
		backButton.setLocation(10, 10);
		backButton.setFont(new Font("consolas", Font.BOLD, 24));
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		add(backButton);
	}
}