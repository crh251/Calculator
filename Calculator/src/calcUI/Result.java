package calcUI;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Result extends VBox {
	
	public static final double RES_WIDTH = CalcPane.CALC_WIDTH;
	public static final double RES_HEIGHT = (CalcPane.CALC_HEIGHT-SwitchFun.SW_HEIGHT)*CalcPane.resScale;
	
	private static final double upperScale = 0.40;
	private static final double lowerScale = 0.60;
	
	private static final double upperTextScale = 0.5;
	private static final double lowerTextScale = 0.7;
	
	private static Label upper = new Label();
	
	private static Label lower = new Label("0");
	
	private VBox pane = this;

	public static void setUpperText(String text) {
		upper.setText(text);
	}

	public static void setLowerText(String text) {
		lower.setText(text);
	}
	
	public static String getUpperText() {
		return upper.getText();
	}

	public Result() {
		super();
		init();
		setSize();
	}

	private void init() {
		upper.setId("res");
		lower.setId("res");
		
		upper.setFont(Font.font("Microsoft YaHei Light", FontWeight.LIGHT, RES_HEIGHT*upperScale*upperTextScale));
		
		lower.setFont(Font.font("Microsoft YaHei", FontWeight.BOLD,RES_HEIGHT*lowerScale*lowerTextScale));
		
		upper.setTextFill(Color.GRAY);
		
		upper.setPadding(new Insets(0,10,0,0));
		lower.setPadding(new Insets(0,10,0,0));
		
		pane.getChildren().addAll(upper,lower);
		
	}
	
	private void setSize() {
		upper.prefWidthProperty().bind(pane.widthProperty());
		upper.prefHeightProperty().bind(pane.heightProperty().multiply(upperScale));
		
		lower.prefWidthProperty().bind(pane.widthProperty());
		lower.prefHeightProperty().bind(pane.heightProperty().multiply(lowerScale));
		
		
		layoutBoundsProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable observable) {
				if(upper.getHeight()!=0)
					upper.setFont(Font.font("Microsoft YaHei Light", FontWeight.LIGHT, upper.getHeight()*upperTextScale));
				if(lower.getHeight()!=0)
					lower.setFont(Font.font("Microsoft YaHei", FontWeight.BOLD,lower.getHeight()*lowerTextScale));
				
			}
		});	
	}
}
