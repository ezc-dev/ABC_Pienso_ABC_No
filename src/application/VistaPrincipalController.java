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
	public static Sounds bgSound;
	
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
			//MainController c;
			//c = loader.getController();
			bgSound = new Sounds("src/sounds/Friends.wav", 3);
			bgSound.loadSound();
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
	
	public void loadLevel2Scene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Level2.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			borderPane.setCenter(pane);
			mainStage.setTitle("ABC Pienso! ABC No! - Nivel 2");
			Level2Controller level2Controller = loader.getController();
			level2Controller.setVistaPrincipalController(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadLevel3Scene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Level3.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			borderPane.setCenter(pane);
			mainStage.setTitle("ABC Pienso! ABC No! - Nivel 3");
			Level3Controller level3Controller = loader.getController();
			level3Controller.setVistaPrincipalController(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadLevel4Scene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Level4.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			borderPane.setCenter(pane);
			mainStage.setTitle("ABC Pienso! ABC No! - Nivel 4");
			Level4Controller level4Controller = loader.getController();
			level4Controller.setVistaPrincipalController(this);
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
	
	public void crearJugador(int points, int vidas) {
		jugador = new Jugador(points, vidas);
	}
}
