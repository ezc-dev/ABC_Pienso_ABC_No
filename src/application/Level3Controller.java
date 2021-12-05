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

public class Level3Controller implements Initializable{

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
	@FXML private ImageView imgMouth;
	@FXML private ImageView imgThroat;
	@FXML private ImageView imgLiver;
	@FXML private ImageView imgLargeIntestine;
	@FXML private ImageView imgSmallIntestine;
	
	
	
	Nivel level1 = new Nivel(5, "Nivel 2: El Sistema Digestivo", 311, 511, 264, 534);
	
	
	
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
		draggableMaker.makeDraggable(imgMouth);
		draggableMaker.makeDraggable(imgThroat);
		draggableMaker.makeDraggable(imgLiver);
		draggableMaker.makeDraggable(imgLargeIntestine);
		draggableMaker.makeDraggable(imgSmallIntestine);
		loadLevel();
		createObjects();
	}
	
	GameObject mouth = new GameObject(imgMouth, 118, 279, 366, 297, 5);
	GameObject throat = new GameObject(imgThroat, 704, 321, 375, 331, 5);
	GameObject liver = new GameObject(imgLiver, 173, 457, 358, 461, 5);
	GameObject largeIntestine = new GameObject(imgLargeIntestine, 626, 152, 352, 496, 5);
	GameObject smallIntestine = new GameObject(imgSmallIntestine, 47, 179, 357, 506, 5);
	
	void createObjects() {
		objects.put("imgMouth", mouth);
		objects.put("imgThroat", throat);
		objects.put("imgLiver", liver);
		objects.put("imgLargeIntestine", largeIntestine);
		objects.put("imgSmallIntestine", smallIntestine);
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
