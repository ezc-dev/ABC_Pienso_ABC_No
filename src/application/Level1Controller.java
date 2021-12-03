package application;

import java.net.URL;
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
	@FXML private ImageView imgHandRight;
	@FXML private ImageView imgHandLeft;
	@FXML private ImageView imgHead;
	@FXML private ImageView imgFooterLeft;
	@FXML private ImageView imgFooterRight;
	@FXML private ImageView imgTrunk;
	
	private double[] prueba1;
	
	Nivel level1 = new Nivel(1, "Nivel 1: Las Partes del Cuerpo", 287, 510, 131, 588);
	
	
	
	DraggableMaker draggableMaker = new DraggableMaker();
	
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
	void getLocationHandRight(MouseEvent e) {
		double x = imgHandRight.getLayoutX();
		double y = imgHandRight.getLayoutY();
		if(x >= 10.0 && x <= 20.0)
			System.out.println("Funciona");
		else
			System.out.println("Nada");
	}
	
	@FXML
	void prueba(MouseEvent e) {
		System.out.println(e.getSource());
	}
	
	void loadLevel() {
		lbTitleLevel.setText(level1.getTitle());
	}

}
