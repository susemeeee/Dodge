package core;

import java.awt.Point;

import javax.swing.ImageIcon;

public class FallingObject {
	protected Point sizeRange;
	protected Point currentPosition;
	protected ImageIcon fallingObjectImage;
	
	public Point getCurrentPosition() {
		return currentPosition;
	}
	
	public ImageIcon getFallingObjectImage() {
		return fallingObjectImage;
	}
	
	public void setCurrentPosition(int fallHeight) {
		currentPosition.y += fallHeight;
		if(currentPosition.y > 525) {
			currentPosition.y = -250;
		}
	}
	
}