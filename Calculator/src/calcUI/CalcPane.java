package calcUI;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class CalcPane extends BorderPane {
	
	public static final double CALC_WIDTH = 310;
	public static final double CALC_HEIGHT = 460;
	
	private static DataComputeUI dataCalc = new DataComputeUI();
	private static SwitchFun swFun = new SwitchFun();
	private static Result res = new Result();
	private static Num number = new Num();
	private BorderPane pane = this;
	
	public static final double resScale = 0.25;
	public static final double numberScale = 0.75;
	
	private static boolean state = true;
	
	public CalcPane() {
		super();
		init();
		setSize();
	}

	private void init() {
		pane.setId("CalcPane");
		setsimpleCalc();
		SwitchFun.getSwButton().setOnAction(e->{
			switchPane();
		});
	}
	
	
	private void setSize() {
		swFun.setPrefSize(pane.getWidth(), SwitchFun.SW_HEIGHT);
		res.prefWidthProperty().bind(pane.widthProperty());
		res.prefHeightProperty().bind((pane.heightProperty().subtract(SwitchFun.SW_HEIGHT)).multiply(resScale));
		
		number.prefWidthProperty().bind(pane.widthProperty());
		number.prefHeightProperty().bind((pane.heightProperty().subtract(SwitchFun.SW_HEIGHT)).multiply(numberScale));
	}
	
	private void switchPane(){
		state = !state;
		if(state){
			pane.getChildren().clear();
			setsimpleCalc();
		}else{
			pane.getChildren().clear();
			setDataCompute();
		}
	}
	
	private void setsimpleCalc(){
		pane.setTop(swFun);
		pane.setCenter(res);
		pane.setBottom(number);
		BorderPane.setAlignment(swFun, Pos.TOP_CENTER);
		BorderPane.setAlignment(number, Pos.BOTTOM_CENTER);
		SwitchFun.getHint().setText("标准计算");
		//为Calc设置CSS
		pane.getStylesheets().add("file:///C:/Users/SKteam/Documents/WorkSpace/myJavaEx/Calculator/src/skin.css");
	}
	
	private void setDataCompute(){
		pane.setTop(swFun);
		SwitchFun.getHint().setText("日期计算");
		pane.setCenter(dataCalc);
		BorderPane.setAlignment(dataCalc, Pos.BOTTOM_CENTER);
		pane.getStylesheets().add("file:///C:/Users/SKteam/Documents/WorkSpace/myJavaEx/Calculator/src/skin.css");
	}
	
}
