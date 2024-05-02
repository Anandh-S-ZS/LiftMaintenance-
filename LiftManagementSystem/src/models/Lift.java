package models;

import java.util.List;

public class Lift {
	private static int idGenerator = 1;
	private String id;
	private int position;
	private List<Integer> positions;
	private int totalCapacity;
	private int currentCapacity;
	
	public Lift(int position, List<Integer> positions,int capacity) {
		this.id = "L"+idGenerator;
		this.position = position;
		this.positions = positions;
		this.totalCapacity = capacity;
		this.currentCapacity = 0;
		idGenerator++;
	}

	public String getId() {
		return id;
	}

	public int getPosition() {
		return position;
	}

	public List<Integer> getPositions() {
		return positions;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}
	
	
	
}
