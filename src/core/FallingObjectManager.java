package core;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FallingObjectManager {
	private ArrayList<FallingObject> objectList = new ArrayList<FallingObject>();
	private int	currentObjectCount = 0;
	
	public FallingObjectManager() {
		
	}
	
	public int getCurrentObjectCount() {
		return currentObjectCount;
	}
	
	public FallingObject getFallingObject(int index) {
		return objectList.get(index);
	}
	
	public ImageIcon getFallingObjectImage(int index) {
		return objectList.get(index).getFallingObjectImage();
	}
	
	public void addFallingObject(int gap) {
		objectList.add(new ObstacleObject(gap));
		currentObjectCount++;
	}
}