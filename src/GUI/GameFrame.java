package GUI;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private static GameFrame instance;
	
	public GameFrame() {
		setTitle("¶ËÇÇÇÏ±â");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new LoginPanel());
        this.getContentPane().revalidate();
	}
	
	public static void createFrame() {
		instance = new GameFrame();
	}
	
	public static void changePanel(String panelName) {
		instance.getContentPane().removeAll();
		
		if(panelName.equals(MainPanel.class.getName())) {
	        instance.setContentPane(new MainPanel());
		}
		else if(panelName.equals(GamePanel.class.getName())) {
	        instance.setContentPane(new GamePanel());
		}
		else if(panelName.equals(RankingPanel.class.getName())) {
	        instance.setContentPane(new RankingPanel());
		}
		
        instance.getContentPane().revalidate();
        instance.getContentPane().repaint();
	}
}