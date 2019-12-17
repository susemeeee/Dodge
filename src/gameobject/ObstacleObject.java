package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class ObstacleObject extends FallingObject{	
	public ObstacleObject(int y) {
		fallingObjectImage = new ImageIcon("gamefiles/images/obstacle2.png");
		currentPosition = new Point(ThreadLocalRandom.current().nextInt(0, 800), y);
		sizeRange = new Point(20, 20);
	}
}
