package GUI;

import java.io.IOException;

import javax.swing.JFrame;

import gameobject.GameCharacter;

public class GameFrame extends JFrame{
	private static GameFrame instance;
	
	public GameFrame() {
		setTitle("Dodge Youtube");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new LoginPanel());
        this.getContentPane().revalidate();
	}
	
	public static void createFrame() {
		instance = new GameFrame();
	}
	
	public static void changePanel(String panelName, GameCharacter gameCharacter) throws IOException {
		instance.getContentPane().removeAll();
		
		if(panelName.equals(MainPanel.class.getName())) {
	        instance.setContentPane(new MainPanel(gameCharacter));
		}
		else if(panelName.equals(GamePanel.class.getName())) {
	        instance.setContentPane(new GamePanel(gameCharacter));
	        instance.getContentPane().requestFocus();
	        instance.getContentPane().setFocusable(true);
		}
		else if(panelName.equals(RankingPanel.class.getName())) {
	        instance.setContentPane(new RankingPanel(gameCharacter));
		}
		else if(panelName.equals(GameOverPanel.class.getName())) {
			instance.setContentPane(new GameOverPanel(gameCharacter));
		}
		
        instance.getContentPane().revalidate();
        instance.getContentPane().repaint();
	}
}