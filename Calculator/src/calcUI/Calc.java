package calcUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calc extends Application {
	
	private static CalcPane pane = new CalcPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scence = new Scene(pane,CalcPane.CALC_WIDTH,CalcPane.CALC_HEIGHT);
		primaryStage.setScene(scence);
		primaryStage.setTitle("Meric's Calc");
		primaryStage.getIcons().add(new Image("file:///C:/Users/SKteam/OneDrive/WorkSpace/myJavaEx/Calculator/images/logo.png"));
		primaryStage.show();
		primaryStage.setMinWidth(primaryStage.getWidth());
		primaryStage.setMinHeight(primaryStage.getHeight());
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
