package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.GameManager;
import core.PlaySound;
import gameobject.GameCharacter;

public class LoginPanel extends JPanel {
	private JLabel gameTitleLabel1 = new JLabel("Dodge");
	private JLabel gameTitleLabel2 = new JLabel("Youtube");
	private JLabel loginLabel = new JLabel("username : ");
	private JLabel youtubeLabel = new JLabel(new ImageIcon("gamefiles/images/obstacle.png"));
	private JLabel charcterLabel = new JLabel(new ImageIcon("gamefiles/images/gamecharacter.png"));
	private JLabel moveEffectLabel = new JLabel("(((       (((");
	private JTextField usernameField = new JTextField();
	private JButton loginButton = new JButton("login");
	private GameCharacter gameCharacter = new GameCharacter();
	private PlaySound playSound = new PlaySound();
	
	public LoginPanel() {
		setDefaultPanel();
		setLayout(null);
		setBackground(Color.WHITE);

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String username = usernameField.getText();
				gameCharacter.setUserName(username);
				
				playSound.playSound("button");
				if(username.trim().length() == 0) {
					int result =JOptionPane.showConfirmDialog(null, "닉네임을 입력하세요", "error", JOptionPane.OK_OPTION);;
					
					if(result != JOptionPane.OK_OPTION) {
						System.exit(0);
					}
				}
				else {
					try {
						GameManager.loadUser(gameCharacter.getUserName(), gameCharacter);
						GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String username = usernameField.getText();
					gameCharacter.setUserName(username);
					
					playSound.playSound("button");
					if(username.trim().length() == 0) {
						int result =JOptionPane.showConfirmDialog(null, "닉네임을 입력하세요", "error", JOptionPane.OK_OPTION);;
						
						if(result != JOptionPane.OK_OPTION) {
							System.exit(0);
						}
					}
					else {
						try {
							GameManager.loadUser(gameCharacter.getUserName(), gameCharacter);
							GameFrame.changePanel(MainPanel.class.getName(), gameCharacter);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		
		add(gameTitleLabel1);
		add(gameTitleLabel2);
		add(loginButton);
		add(loginLabel);
		add(usernameField);
		add(charcterLabel);
		add(youtubeLabel);
		add(moveEffectLabel);
	}
	
	private void setDefaultPanel() {	
		gameTitleLabel1.setSize(400, 100);
		gameTitleLabel1.setLocation(150, 80);
		gameTitleLabel1.setFont(new Font("consolas", Font.ITALIC, 48));
		gameTitleLabel1.setForeground(Color.BLACK);
		
		gameTitleLabel2.setSize(200, 100);
		gameTitleLabel2.setLocation(150, 180);
		gameTitleLabel2.setFont(new Font("consolas", Font.BOLD, 48));
		gameTitleLabel2.setForeground(Color.RED);
		
		loginButton.setSize(100, 29);
		loginButton.setLocation(550, 348);
		loginButton.setContentAreaFilled(false);
		loginButton.setFont(new Font("consolas", Font.BOLD, 20));
		loginButton.setForeground(Color.BLACK);
		
		usernameField.setSize(200, 25);
		usernameField.setLocation(300, 350);
		
		loginLabel.setSize(200, 25);
		loginLabel.setLocation(150, 350);
		loginLabel.setFont(new Font("consolas", Font.PLAIN, 20));
		loginLabel.setForeground(Color.BLACK);
		
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