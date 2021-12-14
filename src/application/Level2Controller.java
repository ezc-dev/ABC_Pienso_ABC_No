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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Level2Controller implements Initializable{

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
	@FXML private ImageView imgNoseMouth;
	@FXML private ImageView imgLungs;
	@FXML private ImageView imgTracheaLarinx;
	
	
	
	Nivel level1 = new Nivel(3, "Nivel 2: El Sistema Respitatorio", 311, 511, 264, 534);
	
	
	
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
		draggableMaker.makeDraggable(imgNoseMouth);
		draggableMaker.makeDraggable(imgLungs);
		draggableMaker.makeDraggable(imgTracheaLarinx);
		loadLevel();
		createObjects();
	}
	
	GameObject noseMouth = new GameObject(imgNoseMouth, 149, 289, 369, 280, 5);
	GameObject lungs = new GameObject(imgLungs, 104, 464, 362, 425, 5);
	GameObject tracheaLarinx = new GameObject(imgTracheaLarinx, 638, 300, 376, 287, 5);
	
	void createObjects() {
		objects.put("imgNoseMouth", noseMouth);
		objects.put("imgLungs", lungs);
		objects.put("imgTracheaLarinx", tracheaLarinx);
	}
	
	void loadLevel() {
		lbTitleLevel.setText(level1.getTitle());
		lbPoints.setText(String.format("%04d", VistaPrincipalController.jugador.getPoints()));
		inicializeTime();
		progress= 0;
	}
		
	@FXML
	void goHome(ActionEvent event) {
		vistaPrincipalController.loadHomeScene();
		VistaPrincipalController.bgSound.stopSound();
		VistaPrincipalController.bgSound.loadSound();
	}
	
	@FXML
	void goNextLevel(ActionEvent event) {
		VistaPrincipalController.jugador.setVidas(3);
		vistaPrincipalController.loadLevel3Scene();
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
						soundLosse();
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
		lbPoints.setText(String.format("%04d", VistaPrincipalController.jugador.getPoints()));
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
					soundWin();
				}
			} else {
				int lifesActive = VistaPrincipalController.jugador.getVidas();
				lifes.getChildren().remove(lifesActive - 1);
				VistaPrincipalController.jugador.setVidas(lifesActive - 1);
				if(lifesActive - 1 == 0) {
					time.cancel();
					sepiaTone.setLevel(0.7);
					groupMain.setEffect(sepiaTone);
					groupMain.setDisable(true);
					lbReason.setText("Te quedaste sin vidas");
					gameResult.setVisible(true);
					soundLosse();
				} else {
					pru.resetObject(image);
				}
			}
				
		} else {		
			pru.resetObject(image);
		}
	}
	
	public void soundLosse() {
		VistaPrincipalController.bgSound.stopSound();
		Sounds losseSound = new Sounds("src/sounds/losse.wav", 0);
		losseSound.loadSound();
	}
	
	public void soundWin() {
		Sounds winSound = new Sounds("src/sounds/levelWin.wav", 0);
		winSound.loadSound();
	}

}
