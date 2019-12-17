package gameobject;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class FallingObject {
	protected Point sizeRange;
	protected Point currentPosition;
	protected ImageIcon fallingObjectImage;
	
	public Point getCurrentPosition() {
		return currentPosition;
	}
	
	public Point getSizeRange() {
		return sizeRange;
	}
	
	public ImageIcon getFallingObjectImage() {
		return fallingObjectImage;
	}
	
	public void objectFalling(int fallHeight) {
		currentPosition.y += fallHeight;
		if(currentPosition.y > 525) {
			currentPosition.x = ThreadLocalRandom.current().nextInt(0, 800);
			currentPosition.y = -250;
		}
	}
	
	public void setNewObjectPosition() {
		currentPosition.x = ThreadLocalRandom.current().nextInt(0, 800);
		currentPosition.y -= 850;
	}
	
	public void setItemPosition(int fallHeight) {
		currentPosition.y += fallHeight;
		if(currentPosition.y > 525) {
			setNewItemPosition();
		}
	}
	
	public void setNewItemPosition() {
		currentPosition.x = ThreadLocalRandom.current().nextInt(0, 800);
		currentPosition.y = ThreadLocalRandom.current().nextInt(-5000, -2000);
	}
}