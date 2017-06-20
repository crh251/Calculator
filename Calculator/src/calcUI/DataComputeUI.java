package calcUI;

import java.util.ArrayList;
import java.util.Calendar;

import algorithm.DataCompute;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DataComputeUI extends VBox {
	
	public static final double DATACALC_WIDTH = CalcPane.CALC_WIDTH;
	public static final double DATACALC_HEIGHT = CalcPane.CALC_HEIGHT-SwitchFun.SW_HEIGHT;
	
	private static Label lab1 = new Label("计算两个日期之差:");
	
	private static HBox fromhbox = new HBox();
	private static Label lab2 = new Label("自");
	private static ComboBox<String> fromYear = new ComboBox<String>();
	private static ComboBox<String> fromMonth = new ComboBox<String>();
	private static ComboBox<String> fromDay = new ComboBox<String>();
	
	private static HBox tohbox = new HBox();
	private static Label lab3 = new Label("至");
	private static ComboBox<String> toYear = new ComboBox<String>();
	private static ComboBox<String> toMonth = new ComboBox<String>();
	private static ComboBox<String> toDay = new ComboBox<String>();
	
	private static Label lab4 = new Label("间隔天数");
	
	private static Label answer = new Label("相同日期");
	
	private VBox pane = this;
	
	private static final ArrayList<String> yearlist = getYearList();
	private static final ArrayList<String> monthlist = getMonthList();
	private static final int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	private static int fromDaysMAX,toDaysMAX,currentFromDay,currentToDay;
	
	public DataComputeUI() {
		super();
		init();
	}
	
	private void init(){
		fromhbox.getChildren().addAll(lab2,fromYear,fromMonth,fromDay);
		tohbox.getChildren().addAll(lab3,toYear,toMonth,toDay);
		pane.getChildren().addAll(lab1,fromhbox,tohbox,lab4,answer);
		
		
		/***************************计算两个日期之差******************************/
		VBox.setMargin(lab1, new Insets(30,0,0,20));
		lab1.setFont(Font.font("Couier",FontWeight.BOLD,FontPosture.ITALIC,15));
		
		
		
		/***************************自几年几月几日******************************/
		VBox.setMargin(fromhbox, new Insets(30,0,0,20));
		lab2.setFont(Font.font("Couier",FontWeight.BOLD,FontPosture.ITALIC,15));
		HBox.setMargin(fromYear, new Insets(0,0,0,10));
		fromYear.getItems().addAll(yearlist);
		fromYear.setValue(getYear()+"年");
		fromYear.setOnAction(e->{
			fromDateCheck();
		});
		HBox.setMargin(fromMonth, new Insets(0,0,0,10));
		fromMonth.getItems().addAll(monthlist);
		fromMonth.setValue(getMonth()+"月");
		fromMonth.setOnAction(e->{
			fromDateCheck();
		});
		HBox.setMargin(fromDay, new Insets(0,0,0,10));
		fromDaysMAX = fromMaxDay();
		fromDay.getItems().addAll(getDayList(fromDaysMAX));
		currentFromDay = Integer.parseInt(getDay());
		fromDay.setValue(currentFromDay+"日");
		fromDay.setOnAction(e->{
			if(fromDay.getValue()!=null){
				currentFromDay = Integer.parseInt(fromDay.getValue().substring(0, fromDay.getValue().length()-1));
				showAnswer();
			}
		});
		
		/***************************到几年几月几日******************************/
		VBox.setMargin(tohbox, new Insets(40,0,0,20));
		lab3.setFont(Font.font("Couier",FontWeight.BOLD,FontPosture.ITALIC,15));
		HBox.setMargin(toYear, new Insets(0,0,0,10));
		toYear.getItems().addAll(yearlist);
		toYear.setValue(getYear()+"年");
		toYear.setOnAction(e->{
			toDateCheck();
		});
		HBox.setMargin(toMonth, new Insets(0,0,0,10));
		toMonth.getItems().addAll(monthlist);
		toMonth.setValue(getMonth()+"月");
		toMonth.setOnAction(e->{
			toDateCheck();
		});
		HBox.setMargin(toDay, new Insets(0,0,0,10));
		toDaysMAX = toMaxDay();
		toDay.getItems().addAll(getDayList(toDaysMAX));
		currentToDay = Integer.parseInt(getDay());
		toDay.setValue(currentToDay+"日");
		toDay.setOnAction(e->{
			if(toDay.getValue()!=null){
				currentToDay = Integer.parseInt(toDay.getValue().substring(0, toDay.getValue().length()-1));
				showAnswer();
			}
		});
			
		/***************************间隔天数******************************/
		VBox.setMargin(lab4, new Insets(50,0,0,20));
		lab4.setFont(Font.font("Couier",FontWeight.BOLD,FontPosture.ITALIC,20));
		

		/***************************日期之差的结果******************************/
		VBox.setMargin(answer, new Insets(10,0,0,20));
		answer.setFont(Font.font("Couier",FontWeight.BOLD,FontPosture.ITALIC,40));
		
	}
	
	private void fromDateCheck(){
		int tmp = fromMaxDay();
		if(tmp!=fromDaysMAX){
			fromDaysMAX = tmp;
			fromDay.getItems().clear();
			fromDay.getItems().addAll(getDayList(fromDaysMAX));
			if(currentFromDay>fromDaysMAX){
				currentFromDay = 1;
				fromDay.setValue("1日");
			}else{
				fromDay.setValue(currentFromDay+"日");
			}
		}else{
			//refresh
			showAnswer();
		}
	}
	
	private void toDateCheck(){
		int tmp = toMaxDay();
		if(tmp!=toDaysMAX){
			toDaysMAX = tmp;
			toDay.getItems().clear();
			toDay.getItems().addAll(getDayList(toDaysMAX));
			if(currentToDay>toDaysMAX){
				currentToDay = 1;
				toDay.setValue("1日");
			}else{
				toDay.setValue(currentToDay+"日");
			}
		}else{
			//refresh
			showAnswer();
		}
	}
	
	private static void showAnswer(){
		String ans = DataCompute.COMPUTE(fromYear.getValue().substring(0, fromYear.getValue().length()-1), 
										 fromMonth.getValue().substring(0, fromMonth.getValue().length()-1), 
										 fromDay.getValue().substring(0, fromDay.getValue().length()-1),
										 toYear.getValue().substring(0, toYear.getValue().length()-1), 
										 toMonth.getValue().substring(0, toMonth.getValue().length()-1), 
										 toDay.getValue().substring(0, toDay.getValue().length()-1));
		if(ans.equals("0")){
			answer.setText("相同日期");
		}else{
			answer.setText(ans+"天");
		}
	}
	
	
	private static String getYear(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
		return String.format("%d", year);
	}
	
	private static String getMonth(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;
		return String.format("%d", month);
	}
	
	private static String getDay(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
		return String.format("%d", day);
	}
	
	
	private static ArrayList<String> getDayList(int endDay) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1;i<=endDay;i++){
			list.add(String.format("%d", i)+"日");
		}
		return list;
	}



	private static ArrayList<String> getMonthList() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1;i<=12;i++){
			list.add(String.format("%d", i)+"月");
		}
		return list;
	}



	private static ArrayList<String> getYearList() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1990;i<=2020;i++){
			list.add(String.format("%d", i)+"年");
		}
		return list;
	}
	
	private int fromMaxDay(){
		int year = Integer.valueOf(fromYear.getValue().substring(0, fromYear.getValue().length()-1));
		int month = Integer.valueOf(fromMonth.getValue().substring(0, fromMonth.getValue().length()-1));
		boolean isLeap;
		int maxDays;
		if((year%4==0&&year%100!=0)||(year%400==0)){
			isLeap = true;
		}else{
			isLeap = false;
		}
		if(isLeap&&month==2){
			maxDays = 29;
		}else{
			maxDays = days[month-1];
		}
		return maxDays;
	}
	
	private int toMaxDay(){
		int year = Integer.valueOf(toYear.getValue().substring(0, toYear.getValue().length()-1));
		int month = Integer.valueOf(toMonth.getValue().substring(0, toMonth.getValue().length()-1));
		boolean isLeap;
		int maxDays;
		if((year%4==0&&year%100!=0)||(year%400==0)){
			isLeap = true;
		}else{
			isLeap = false;
		}
		if(isLeap&&month==2){
			maxDays = 29;
		}else{
			maxDays = days[month-1];
		}
		return maxDays;
	}

}
