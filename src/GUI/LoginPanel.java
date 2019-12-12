package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{
	private JLabel loginLabel = new JLabel("username");
	private JTextField username = new JTextField();
	private JButton loginButton = new JButton("login");
	
	public LoginPanel() {
		setDefaultPanel();
		setLayout(null);

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.changePanel(MainPanel.class.getName());
			}
		});
		
		add(loginButton);
	}
	
	private void setDefaultPanel() {
		loginButton.setSize(100, 50);
		loginButton.setLocation(200, 300);
		loginButton.setFont(new Font("consolas", Font.BOLD, 20));
	}
}