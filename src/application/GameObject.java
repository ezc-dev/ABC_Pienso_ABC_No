package application;

import javafx.scene.Node;

public class GameObject {

	private Node object;
	private int initialPositionX;
	private int initialPositionY;
	private int correctPositionX;
	private int correctPositionY;
	private int range;
	
	public GameObject(Node object, int initialPositionX, int initialPositionY, int correctPositionX,
			int correctPositionY, int range) {
		this.object = object;
		this.initialPositionX = initialPositionX;
		this.initialPositionY = initialPositionY;
		this.correctPositionX = correctPositionX;
		this.correctPositionY = correctPositionY;
		this.range = range;
	}

	public Node getObject() {
		return object;
	}

	public void setObject(Node object) {
		this.object = object;
	}

	public int getInitialPositionX() {
		return initialPositionX;
	}

	public void setInitialPositionX(int initialPositionX) {
		this.initialPositionX = initialPositionX;
	}

	public int getInitialPositionY() {
		return initialPositionY;
	}

	public void setInitialPositionY(int initialPositionY) {
		this.initialPositionY = initialPositionY;
	}

	public int getCorrectPositionX() {
		return correctPositionX;
	}

	public void setCorrectPositionX(int correctPositionX) {
		this.correctPositionX = correctPositionX;
	}

	public int getCorrectPositionY() {
		return correctPositionY;
	}

	public void setCorrectPositionY(int correctPositionY) {
		this.correctPositionY = correctPositionY;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public void resetObject() {
		object.setLayoutX(initialPositionX);
		object.setLayoutY(initialPositionY);
	}
	
}
