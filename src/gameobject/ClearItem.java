package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class ClearItem extends FallingObject{
	public ClearItem() {
		fallingObjectImage = new ImageIcon("gamefiles/images/clearitem.png");
		currentPosition = new Point(ThreadLocalRandom.current().nextInt(0, 800), ThreadLocalRandom.current().nextInt(-5000, -2000));
		sizeRange = new Point(20, 20);
	}
}