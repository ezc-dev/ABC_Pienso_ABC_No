package application;

import javafx.scene.Node;

public class DraggableMaker {
	
	private double mouseAnchorX;
	private double mouseAnchorY;
	
	public void makeDraggable(Node node) {
		node.setOnMousePressed(mouseEvent -> {
			mouseAnchorX = mouseEvent.getX();
			mouseAnchorY = mouseEvent.getY();
		});
		
		node.setOnMouseDragged(mouseEvent -> {
			node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
			node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
		});
	}
	
	public double[] getPosition(Node node) {
		
		double[] list = new double[2];
		
		node.setOnMouseReleased(mouseEvent -> {
			list[0] = node.getLayoutX();
			list[1] = node.getLayoutY();
		});
		
		System.out.println("Hola");
		return list;
	}

}
