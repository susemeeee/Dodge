package core;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FallingObjectManager {
	private ArrayList<FallingObject> objectList = new ArrayList<FallingObject>();
	private ArrayList<FallingObject> itemList = new ArrayList<FallingObject>();
	private int	currentObjectCount = 0;
	
	public FallingObjectManager() {  
		itemList.add(new ClearItem());
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
	
	public FallingObject pickItem() {
		return itemList.get(0);
	}//아이템 여러개 구현 시 이거 수정
}