package core;

import java.awt.Point;

import javax.swing.ImageIcon;

public class ObstacleObject extends FallingObject{
	private int damage;
	
	public ObstacleObject(int y) {
		fallingObjectImage = new ImageIcon("gamefiles/images/obstacle1.png");
		currentPosition = new Point((int)(Math.random() * 800), y);
		sizeRange = new Point(30,30);
	}
}
