package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VistaPrincipalController {
	private Stage mainStage;
	private BorderPane borderPane;
	public static Jugador jugador;
	
	public VistaPrincipalController(Stage stage) {
		this.mainStage = stage;
		loadMainScene();
	}
	
	
	public void loadMainScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VistaPrincipal.fxml"));
			borderPane = (BorderPane) loader.load();
			Scene scene = new Scene(borderPane);
			mainStage.setScene(scene);
			mainStage.setTitle("ABC Pienso! ABC No! - Inicio");
			mainStage.show();
			MainController c = loader.getController();
			loadHomeScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
	}
	
	public void loadHomeScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("HomeView.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			borderPane.setCenter(pane);
			mainStage.setTitle("ABC Pienso! ABC No! - Inicio");
			HomeController homeController = loader.getController();
			homeController.setVistaPrincipalController(this);
		} catch (Exception e) {
			
		}
	}
	
	public void loadLevel1Scene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Level1.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			borderPane.setCenter(pane);
			mainStage.setTitle("ABC Pienso! ABC No! - Nivel 1");
			Level1Controller level1Controller = loader.getController();
			level1Controller.setVistaPrincipalController(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void mostrarControl(String id) {
		borderPane.lookup(id).setVisible(true);
	}
	
	public void ocultarControl(String id) {
		borderPane.lookup(id).setVisible(false);
	}
	
	public void crearJugador(String points, int vidas) {
		jugador = new Jugador(points, vidas);
	}
}
