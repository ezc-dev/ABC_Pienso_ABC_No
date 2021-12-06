package application;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Level4Controller implements Initializable{

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
	@FXML private Label lbScapula;
	@FXML private Label lbRadio;
	@FXML private Label lbUlna;
	@FXML private Label lbFemur;
	@FXML private Label lbSkull;
	@FXML private Label lbMaxillary;
	@FXML private Label lbHumereus;
	@FXML private Label lbPelvis;
	@FXML private Label lbFibula;
	@FXML private Label lbTibia;
	
	
	
	Nivel level1 = new Nivel(10, "Nivel 4: El Sistema Oseo", 187, 798, 136, 576);
	
	
	
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
		draggableMaker.makeDraggable(lbScapula);
		draggableMaker.makeDraggable(lbRadio);
		draggableMaker.makeDraggable(lbUlna);
		draggableMaker.makeDraggable(lbFemur);
		draggableMaker.makeDraggable(lbSkull);
		draggableMaker.makeDraggable(lbMaxillary);
		draggableMaker.makeDraggable(lbHumereus);
		draggableMaker.makeDraggable(lbPelvis);
		draggableMaker.makeDraggable(lbFibula);
		draggableMaker.makeDraggable(lbTibia);
		loadLevel();
		createObjects();
	}
	
	GameObject scapula = new GameObject(lbScapula, 18, 260, 220, 236,  10);
	GameObject radio = new GameObject(lbRadio, 18, 214, 217, 288, 10);
	GameObject ulna = new GameObject(lbUlna, 18, 358, 219, 338, 10);
	GameObject femur = new GameObject(lbFemur, 18, 488, 203, 452, 10);
	GameObject skull = new GameObject(lbSkull, 18, 119, 625, 149, 10);
	GameObject maxillary = new GameObject(lbMaxillary, 18, 444, 628, 249, 10);
	GameObject humereus = new GameObject(lbHumereus, 18, 168, 632, 217, 10);
	GameObject pelvis = new GameObject(lbPelvis, 18, 306, 641, 395, 10);
	GameObject fibula = new GameObject(lbFibula, 18, 352, 638, 456, 10);
	GameObject tibia = new GameObject(lbTibia, 18, 536, 634, 527, 10);
	
	void createObjects() {
		objects.put("lbMaxillary", maxillary);
		objects.put("lbRadio", radio);
		objects.put("lbUlna", ulna);
		objects.put("lbFemur", femur);
		objects.put("lbSkull", skull);
		objects.put("lbMaxillary", maxillary);
		objects.put("lbHumereus", humereus);
		objects.put("lbPelvis", pelvis);
		objects.put("lbFibula", fibula);
		objects.put("lbTibia", tibia);
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
		Label lb = (Label) e.getSource();
		GameObject pru = objects.get(lb.getId());
		if(lb.getLayoutX() >= level1.getMinX() && lb.getLayoutX() <= level1.getMaxX() &&
				lb.getLayoutY() >= level1.getMinY() && lb.getLayoutY() <= level1.getMaxY()) {
			if(lb.getLayoutX() >= pru.getCorrectPositionX() - pru.getRange() &&
					lb.getLayoutX() <= pru.getCorrectPositionX() + pru.getRange() &&
					lb.getLayoutY() >= pru.getCorrectPositionY() - pru.getRange() &&
					lb.getLayoutY() <= pru.getCorrectPositionY() + pru.getRange()) {
				pru.adjustObject(lb);
				lb.setEffect(null);
				draggableMaker.unMakeDraggable(lb);
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
					pru.resetObject(lb);
				}
			}
				
		} else {		
			pru.resetObject(lb);
		}
	}
	
	

}
