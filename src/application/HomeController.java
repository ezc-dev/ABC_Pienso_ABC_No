package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomeController implements Initializable{
	
	@FXML private Button btnPlay;
	@FXML private ImageView imgBtnPlay;
	
	
	
	
	VistaPrincipalController vistaPrincipalController;
	
	
	public VistaPrincipalController getVistaPrincipalController() {
		return vistaPrincipalController;
	}

	public void setVistaPrincipalController(VistaPrincipalController vistaPrincipalController) {
		this.vistaPrincipalController = vistaPrincipalController;
	}

	@FXML
	void goSceneLevel1() {
		vistaPrincipalController.crearJugador("010", 3);
		vistaPrincipalController.loadLevel1Scene();
		
	}
	
	@FXML
	void showHelp(ActionEvent event) {
		vistaPrincipalController.mostrarControl("#groupHelp");
	}
	
	@FXML
	void hideHelp(ActionEvent event) {
		vistaPrincipalController.ocultarControl("#groupHelp");
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
