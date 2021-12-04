package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Level1Controller implements Initializable{

	VistaPrincipalController vistaPrincipalController;
	
	@FXML private Label lbPoints;
	@FXML private Label lbTitleLevel;
	@FXML private Label lbTime;
	@FXML private Button btnHome;
	@FXML private ImageView imgHead;
	@FXML private ImageView imgHandRight;
	@FXML private ImageView imgHandLeft;
	@FXML private ImageView imgFooterLeft;
	@FXML private ImageView imgFooterRight;
	@FXML private ImageView imgTrunk;	
	
	
	Nivel level1 = new Nivel(1, "Nivel 1: Las Partes del Cuerpo", 287, 510, 131, 588);
	
	
	
	DraggableMaker draggableMaker = new DraggableMaker();
	HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
	
	
	
	public VistaPrincipalController getVistaPrincipalController() {
		return vistaPrincipalController;
	}

	public void setVistaPrincipalController(VistaPrincipalController vistaPrincipalController) {
		this.vistaPrincipalController = vistaPrincipalController;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		draggableMaker.makeDraggable(imgHead);
		draggableMaker.makeDraggable(imgHandRight);
		draggableMaker.makeDraggable(imgHandLeft);
		draggableMaker.makeDraggable(imgTrunk);
		draggableMaker.makeDraggable(imgFooterRight);
		draggableMaker.makeDraggable(imgFooterLeft);
		lbPoints.setText(VistaPrincipalController.jugador.getPoints());
		loadLevel();
		createObjects();
	}
	
	GameObject head = new GameObject(imgHead, 98, 274, 309, 135, 5);
	
	void createObjects() {
		objects.put("imgHead", head);
	}
		

	@FXML
	void goHome(ActionEvent event) {
		vistaPrincipalController.loadHomeScene();
	}
	
	@FXML
	void updateControl(ActionEvent event) {
		VistaPrincipalController.jugador.setPoints("300");
		lbPoints.setText(VistaPrincipalController.jugador.getPoints());
		
	}
	
	
	
	
	@FXML
	void validatePosition(MouseEvent e) {
		ImageView image = (ImageView) e.getSource();
		GameObject pru = objects.get(image.getId());
		if(image.getLayoutX() >= level1.getMinX() && image.getLayoutX() <= level1.getMaxX() &&
				image.getLayoutY() >= level1.getMinY() && image.getLayoutY() <= level1.getMaxY()) {
			if(image.getLayoutX() >= pru.getCorrectPositionX() - pru.getRange() &&
					image.getLayoutX() <= pru.getCorrectPositionX() + pru.getRange() &&
					image.getLayoutY() >= pru.getCorrectPositionY() - pru.getRange() &&
					image.getLayoutY() <= pru.getCorrectPositionY() + pru.getRange()) {
				pru.adjustObject(image);
				image.setEffect(null);
			} else {
				System.out.println("Pierde una vida");
			}
				
		} else {		
			pru.resetObject(image);
		}
	}
	
	void loadLevel() {
		lbTitleLevel.setText(level1.getTitle());
	}

}
