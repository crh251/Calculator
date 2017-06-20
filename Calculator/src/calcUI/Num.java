package calcUI;

import algorithm.Calculate;
import effects.SetEffects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;

public class Num extends GridPane{
	
	public static final double NUM_WIDTH = CalcPane.CALC_WIDTH;
	public static final double NUM_HEIGHT = (CalcPane.CALC_HEIGHT-SwitchFun.SW_HEIGHT)*CalcPane.numberScale;
	
	/*求余 开方  平方  倒数*/
	private static Button BUTTON_Fact = new Button("!");
	private static Button BUTTON_Sqrt = new Button("√");
	private static Button BUTTON_Square = new Button("x²");
	private static Button BUTTON_Reciprocal = new Button("1/X");
	
	/*全清  清除  删除*/
	private static Button BUTTON_Clear_All = new Button("C");
	private static Button BUTTON_Clear = new Button("CE");
	private static Button BUTTON_Del = new Button("<-");
	
	/*加  减  乘  除  等于  负数  点*/
	private static Button BUTTON_Add = new Button("+");
	private static Button BUTTON_Sub = new Button("-");
	private static Button BUTTON_Multiply = new Button("X");
	private static Button BUTTON_Divide = new Button("÷");
	private static Button BUTTON_Equal = new Button("=");
	private static Button BUTTON_Negate = new Button("(-)");
	private static Button BUTTON_Dot = new Button(".");
	
	/*0 1 2 3 4 5 6 7 8 9*/
	private static Button BUTTON_0 = new Button("0");	
	private static Button BUTTON_1 = new Button("1");
	private static Button BUTTON_2 = new Button("2");
	private static Button BUTTON_3 = new Button("3");
	private static Button BUTTON_4 = new Button("4");
	private static Button BUTTON_5 = new Button("5");
	private static Button BUTTON_6 = new Button("6");
	private static Button BUTTON_7 = new Button("7");
	private static Button BUTTON_8 = new Button("8");
	private static Button BUTTON_9 = new Button("9");
	
	private final double hgap = 4;
	private final double vgap = 4;
	private final double padding = 5;
	private GridPane pane = this;

	public Num() {
		super();
		init();
	}


	private void setAction() {
		
		BUTTON_Fact.setOnAction(e->{
			Calculate.Factorial();
		});

		BUTTON_Sqrt.setOnAction(e->{
			Calculate.Sqrt();
		});

		BUTTON_Square.setOnAction(e->{
			Calculate.Square();
		});

		BUTTON_Reciprocal.setOnAction(e->{
			Calculate.Reciprocal();
		});

		BUTTON_Clear_All.setOnAction(e->{
			Calculate.CLEAR_ALL();
		});

		BUTTON_Clear.setOnAction(e->{
			Calculate.CLEAR();
		});

		BUTTON_Del.setOnAction(e->{
			Calculate.Del();
		});

		BUTTON_Add.setOnAction(e->{
			Calculate.Add();
		});

		BUTTON_Sub.setOnAction(e->{
			Calculate.Sub();
		});

		BUTTON_Multiply.setOnAction(e->{
			Calculate.Multiply();
		});

		BUTTON_Divide.setOnAction(e->{
			Calculate.Divide();
		});

		BUTTON_Equal.setOnAction(e->{
			Calculate.Equal();
		});

		BUTTON_Dot.setOnAction(e->{
			Calculate.Dot();
		});

		BUTTON_Negate.setOnAction(e->{
			Calculate.Negate();
		});

		BUTTON_0.setOnAction(e->{
			Calculate.Number_0();
		});

		BUTTON_1.setOnAction(e->{
			Calculate.Number_1();
		});

		BUTTON_2.setOnAction(e->{
			Calculate.Number_2();
		});

		BUTTON_3.setOnAction(e->{
			Calculate.Number_3();
		});

		BUTTON_4.setOnAction(e->{
			Calculate.Number_4();
		});

		BUTTON_5.setOnAction(e->{
			Calculate.Number_5();
		});

		BUTTON_6.setOnAction(e->{
			Calculate.Number_6();
		});

		BUTTON_7.setOnAction(e->{
			Calculate.Number_7();
		});

		BUTTON_8.setOnAction(e->{
			Calculate.Number_8();
		});

		BUTTON_9.setOnAction(e->{
			Calculate.Number_9();
		});
	}


	private void setEffects() {
		
		for(Node child: pane.getChildren()) {
			if(child.getId().equals("number")) {
				SetEffects.setNumMouseEnter((Button)child);
				SetEffects.setNumMouseExited((Button)child);
			}else if(child.getId().equals("operator")) {
				SetEffects.setOpMouseEnter((Button)child);
				SetEffects.setOpMouseExited((Button)child);
			}else if(child.getId().equals("baseOperator")) {
				SetEffects.setBaseOpMouseEnter((Button)child);
				SetEffects.setBaseOpMouseExited((Button)child);
			}
		}		
	}


	private void setID() {
		BUTTON_Fact.setId("operator");
		BUTTON_Sqrt.setId("operator");
		BUTTON_Square.setId("operator");
		BUTTON_Reciprocal.setId("operator");
		
		BUTTON_Clear_All.setId("operator");
		BUTTON_Clear.setId("operator");
		BUTTON_Del.setId("operator");
		
		BUTTON_Add.setId("baseOperator");
		BUTTON_Sub.setId("baseOperator");
		BUTTON_Multiply.setId("baseOperator");
		BUTTON_Divide.setId("baseOperator");
		BUTTON_Equal.setId("baseOperator");
		
		BUTTON_Negate.setId("operator");
		BUTTON_Dot.setId("operator");
		
		BUTTON_0.setId("number");
		BUTTON_1.setId("number");
		BUTTON_2.setId("number");
		BUTTON_3.setId("number");
		BUTTON_4.setId("number");
		BUTTON_5.setId("number");
		BUTTON_6.setId("number");
		BUTTON_7.setId("number");
		BUTTON_8.setId("number");
		BUTTON_9.setId("number");
		
	}


	private void init() {
		addToPane();
		setSize();
		setID();
		setEffects();
		setAction();
		super.setPadding(new Insets(padding));
		super.setHgap(hgap);
		super.setVgap(vgap);
		
	}
	
	private void setSize() {
		layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> observableValue, Bounds oldBounds, Bounds newBounds) {
				for (Node child : pane.getChildrenUnmodifiable()) {
					Control tile = (Control) child;
					tile.setPrefSize((pane.getWidth()-3*hgap-2*padding)/4,(pane.getHeight()-5*hgap-2*padding)/6 );  //设置宽和高
				}
			}
		});
		
	}
	

	private void addToPane() {
		super.add(BUTTON_Fact, 0, 0);
		super.add(BUTTON_Sqrt, 1, 0);
		super.add(BUTTON_Square, 2, 0);
		super.add(BUTTON_Reciprocal, 3, 0);
		
		super.add(BUTTON_Clear_All, 1, 1);
		super.add(BUTTON_Clear, 0, 1);
		super.add(BUTTON_Del, 2, 1);
		
		super.add(BUTTON_Divide, 3, 1);
		super.add(BUTTON_Multiply, 3, 2);
		super.add(BUTTON_Sub, 3, 3);
		super.add(BUTTON_Add, 3, 4);
		super.add(BUTTON_Equal, 3, 5);
		super.add(BUTTON_Dot, 2, 5);
		super.add(BUTTON_Negate, 0, 5);
		
		super.add(BUTTON_0, 1, 5);
		super.add(BUTTON_1, 0, 4);
		super.add(BUTTON_2, 1, 4);
		super.add(BUTTON_3, 2, 4);
		super.add(BUTTON_4, 0, 3);
		super.add(BUTTON_5, 1, 3);
		super.add(BUTTON_6, 2, 3);
		super.add(BUTTON_7, 0, 2);
		super.add(BUTTON_8, 1, 2);
		super.add(BUTTON_9, 2, 2);	
		
	}

}
