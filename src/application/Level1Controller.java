package application;

import java.awt.event.WindowListener;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Level1Controller implements Initializable{

	VistaPrincipalController vistaPrincipalController;
	
	@FXML private AnchorPane view;
	@FXML private Group gameResult;
	@FXML private Group levelWin;
	@FXML private Group groupMain;
	@FXML private Label lbPoints;
	@FXML private Label lbTitleLevel;
	@FXML private Label lbTime;
	@FXML private Label lbReason;
	@FXML private Button btnHome;
	@FXML private HBox lifes;
	@FXML private ImageView imgHead;
	@FXML private ImageView imgHandRight;
	@FXML private ImageView imgHandLeft;
	@FXML private ImageView imgFooterLeft;
	@FXML private ImageView imgFooterRight;
	@FXML private ImageView imgTrunk;
	
	
	
	Nivel level1 = new Nivel(6, "Nivel 1: Las Partes del Cuerpo", 287, 510, 131, 588);
	
	
	
	DraggableMaker draggableMaker = new DraggableMaker();
	HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
	SepiaTone sepiaTone = new SepiaTone();
	private Timer time;
	private int progress;
	
	
	
	
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
		loadLevel();
		createObjects();
	}
	
	GameObject head = new GameObject(imgHead, 98, 274, 309, 135, 5);
	GameObject handRight = new GameObject(imgHandRight, 567, 164, 284, 335, 5);
	GameObject handLeft = new GameObject(imgHandLeft, 69, 443, 415, 335, 5);
	GameObject trunk = new GameObject(imgTrunk, 641, 331, 365, 319, 5);
	GameObject footerRight = new GameObject(imgFooterRight, 525, 423, 321, 442, 5);
	GameObject footerLeft = new GameObject(imgFooterLeft, 158, 99, 399, 443, 5);
	
	void createObjects() {
		objects.put("imgHead", head);
		objects.put("imgHandRight", handRight);
		objects.put("imgHandLeft", handLeft);
		objects.put("imgTrunk", trunk);
		objects.put("imgFooterRight", footerRight);
		objects.put("imgFooterLeft", footerLeft);
	}
	
	void loadLevel() {
		lbTitleLevel.setText(level1.getTitle());
		lbPoints.setText(String.format("%03d", VistaPrincipalController.jugador.getPoints()));
		inicializeTime();
		progress= 0;
	}
		
	@FXML
	void goHome(ActionEvent event) {
		vistaPrincipalController.loadHomeScene();
	}
	
	@FXML
	void goNextLevel(ActionEvent event) {
		vistaPrincipalController.loadLevel2Scene();
	}

	int timeS = 180;
	
	void inicializeTime() {
		time = new Timer(); 
		time.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				Platform.runLater(() -> {
					lbTime.setText(timeRemaining(timeS));
					timeS -= 1;
					if(timeS == 0) {
						time.cancel();
						sepiaTone.setLevel(0.7);
						groupMain.setEffect(sepiaTone);
						groupMain.setDisable(true);
						lbReason.setText("Te quedaste sin tiempo");
						gameResult.setVisible(true);
					}
				});
			}
			
		}, 0, 1000);
		
	}
	
	String timeRemaining(int time) {
		int seconds = 0, minutes = 0;
		minutes = time/60;
		seconds = time - minutes*60;
		return String.format("Tiempo Restante: %02d:%02d",minutes, seconds);
	}
	
	void updatePoints(int points) {
		int beforePoints = VistaPrincipalController.jugador.getPoints();
		VistaPrincipalController.jugador.setPoints(beforePoints + points);
		lbPoints.setText(String.format("%03d", VistaPrincipalController.jugador.getPoints()));
	}
	
	int makePoints() {
		int points = 0;
		double maxTime = 170.0;
		if(timeS >= maxTime) {
			points = 300;
		} else {
			points = (int) ((300 * (timeS / maxTime)) / 1);
		}
		return points;
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
				draggableMaker.unMakeDraggable(image);
				progress += 1;
				if(progress == level1.getNumber()) {
					time.cancel();
					updatePoints(makePoints());
					sepiaTone.setLevel(0.7);
					groupMain.setEffect(sepiaTone);
					groupMain.setDisable(true);
					levelWin.setVisible(true);
				}
			} else {
				int lifesActive = VistaPrincipalController.jugador.getVidas();
				lifes.getChildren().remove(lifesActive - 1);
				VistaPrincipalController.jugador.setVidas(lifesActive - 1);
				if(lifesActive - 1 == 0) {
					sepiaTone.setLevel(0.7);
					groupMain.setEffect(sepiaTone);
					groupMain.setDisable(true);
					lbReason.setText("Te quedaste sin vidas");
					gameResult.setVisible(true);
				} else {
					pru.resetObject(image);
				}
			}
				
		} else {		
			pru.resetObject(image);
		}
	}
	
	

}
