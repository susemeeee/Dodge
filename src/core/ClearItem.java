package core;

import java.awt.Point;

import javax.swing.ImageIcon;

public class ClearItem extends FallingObject{
	public ClearItem() {
		fallingObjectImage = new ImageIcon("gamefiles/images/clearitem.png");
		currentPosition = new Point((int)(Math.random() * 800), (int)(Math.random() * (-3000) - 2000));
		sizeRange = new Point(20, 20);
	}
}