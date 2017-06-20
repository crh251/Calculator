package calcUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SwitchFun extends HBox{
	
	public static final double SW_WIDTH = CalcPane.CALC_WIDTH;
	public static final double SW_HEIGHT = 45;
	
	private static final double SWFUN_HEIGHT = SW_HEIGHT,SWFUN_WIDTH = SW_HEIGHT;
	
	private static Button sw = new Button();
	private static Group group = new Group();
	private static Line line1,line2,line3;
	
	private static Label hint = new Label("标准计算");
	
	private HBox pane = this;

	public SwitchFun() {
		super();
		init();
	}

	public static Label getHint() {
		return hint;
	}

	public static Button getSwButton() {
		return sw;
	}

	private void init() {
		pane.getChildren().addAll(sw,hint);
		pane.setAlignment(Pos.CENTER_LEFT);
		HBox.setMargin(hint, new Insets(0,0,0,10));
		sw.setId("switchButton");
		sw.setPrefSize(SWFUN_WIDTH, SWFUN_HEIGHT);
		hint.setId("hint");
		setBTeffect();
		setSWgraphic();
	}
	
	private void setSWgraphic() {
		line1 = new Line(10,7,35,7);
		line2 = new Line(10,17,35,17);
		line3 = new Line(10,27,35,27);
		group.getChildren().addAll(line1,line2,line3);
		sw.setGraphic(group);
		line1.setStrokeWidth(2);
		line2.setStrokeWidth(2);
		line3.setStrokeWidth(2);
		
		setLinePaint();
	}
	
	private void setLinePaint(){
		for(int i=1;i<=3;i++){
			double r=Math.random(),g=Math.random(),b=Math.random(),o=Math.random();
			if(o<0.5){
				o+=0.5;
			}
			if(i==1){
				line1.setStroke(Color.color(r, g, b, o));
			}else if(i==2){
				line2.setStroke(Color.color(r, g, b, o));
			}else{
				line3.setStroke(Color.color(r, g, b, o));
			}
		}
	}

	private void setBTeffect(){
		sw.setOnMouseEntered(e->{
			sw.setStyle("-fx-background-color:#B0C4DE");
			group.setRotate(90);
			setLinePaint();
		});
		sw.setOnMouseExited(e->{
			sw.setStyle("-fx-background-color:transparent");
			group.setRotate(0);
			setLinePaint();
		});
	}

}
