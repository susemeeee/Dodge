package core;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import gameobject.ClearItem;
import gameobject.FallingObject;
import gameobject.FastItem;
import gameobject.ObstacleObject;
import gameobject.RandomScoreItem;
import gameobject.SlowItem;

public class FallingObjectManager {
	private ArrayList<FallingObject> objectList = new ArrayList<FallingObject>();
	private ArrayList<FallingObject> itemList = new ArrayList<FallingObject>();
	private int	currentObjectCount = 0;
	
	public FallingObjectManager() {  
		itemList.add(new ClearItem());
		itemList.add(new RandomScoreItem());
		itemList.add(new SlowItem());
		itemList.add(new FastItem());
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
		FallingObject item = itemList.get(ThreadLocalRandom.current().nextInt(0, itemList.size()));
		item.setNewItemPosition();
		System.out.println(item.getClass());
		return item;
	}
}