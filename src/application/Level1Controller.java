package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Level1Controller implements Initializable{

	VistaPrincipalController vistaPrincipalController;
	
	@FXML private Label lbPoints;
	@FXML private Label lbTitleLevel;
	@FXML private Label lbTime;
	@FXML private Button btnHome;
	@FXML private ImageView imgHandRight;
	@FXML private ImageView imgHandLeft;
	@FXML private ImageView imgHead;
	
	
	
	DraggableMaker draggableMaker = new DraggableMaker();
	
	public VistaPrincipalController getVistaPrincipalController() {
		return vistaPrincipalController;
	}

	public void setVistaPrincipalController(VistaPrincipalController vistaPrincipalController) {
		this.vistaPrincipalController = vistaPrincipalController;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		draggableMaker.makeDraggable(imgHandRight);
		draggableMaker.makeDraggable(imgHead);
		lbPoints.setText(VistaPrincipalController.jugador.getPoints());
	}

}
