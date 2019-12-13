package core;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FallingObjectManager {
	private ArrayList<FallingObject> objectList = new ArrayList<FallingObject>();
	private int	currentObjectCount = 0;
	
	public FallingObjectManager() {
		objectList.add(new ObstacleObject());
		currentObjectCount++;
	}
	
	public ImageIcon getFallingObjectImage(int index) {
		return objectList.get(index).getFallingObjectImage();
	}
}