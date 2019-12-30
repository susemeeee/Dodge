package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class SlowItem extends FallingObject{
	public SlowItem() {
		fallingObjectImage = new ImageIcon("gamefiles/images/slowitem.png");
		currentPosition = new Point(ThreadLocalRandom.current().nextInt(0, 800), ThreadLocalRandom.current().nextInt(-5000, -2000));
		sizeRange = new Point(30, 30);
	}
}