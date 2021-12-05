package application;

import javafx.scene.Node;

public class GameObject {

	private Node object;
	private double initialPositionX;
	private double initialPositionY;
	private int correctPositionX;
	private int correctPositionY;
	private int range;
	
	public GameObject(Node object, double initialPositionX, double initialPositionY, int correctPositionX,
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

	public double getInitialPositionX() {
		return initialPositionX;
	}

	public void setInitialPositionX(double initialPositionX) {
		this.initialPositionX = initialPositionX;
	}

	public double getInitialPositionY() {
		return initialPositionY;
	}

	public void setInitialPositionY(double initialPositionY) {
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
	
	public void resetObject(Node node) {
		node.setLayoutX(initialPositionX);
		node.setLayoutY(initialPositionY);
	}
	
	public void adjustObject(Node node) {
		node.setLayoutX(correctPositionX);
		node.setLayoutY(correctPositionY);
	}
	
}
