package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class InvincibleItem extends FallingObject{
	
	public InvincibleItem() {
		fallingObjectImage = new ImageIcon("gamefiles/images/invincibleitem.png");
		currentPosition = new Point((int)(ThreadLocalRandom.current().nextInt(0, 800)), ThreadLocalRandom.current().nextInt(-5000, -2000));
		sizeRange = new Point(30, 30);
	}

}
