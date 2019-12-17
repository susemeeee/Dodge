package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class RandomScoreItem extends FallingObject{
	public RandomScoreItem() {
		fallingObjectImage = new ImageIcon("gamefiles/images/randomscoreitem.png");
		currentPosition = new Point((int)(ThreadLocalRandom.current().nextInt(0, 800)), ThreadLocalRandom.current().nextInt(-5000, -2000));
		sizeRange = new Point(20, 20);
	}
}