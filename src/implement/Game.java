package implement;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Game extends Application{
	private GraphicsContext graphics;
	private Group root;
	private Scene scene;
	private Canvas canvas;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		initComponents();
		window.setScene(scene);
		window.setTitle("ABC Pienso! ABC No!");
		window.show();
		paint();
	}
	
	public void initComponents() {
		root = new Group();
		scene = new Scene(root, 800, 600);
		canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);
		graphics = canvas.getGraphicsContext2D();
	}
	
	public void paint() {
		graphics.fillRect(0, 0, 100, 100);
	}
	
	

}
