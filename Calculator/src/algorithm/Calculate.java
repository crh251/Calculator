package algorithm;

import java.util.ArrayList;

import calcUI.Result;
import stackElem.NumElem;
import stackElem.OperatorElem;
import stackElem.ResElem;

public class Calculate {
	
	private static ArrayList<Object> list = new ArrayList<Object>();
	
	private static void showList() {
		//show the list
		StringBuilder tmp = new StringBuilder();
		for(Object ob:list){
			if(ob instanceof ResElem){
				tmp.append(((ResElem) ob).getProcess());
			}else{
				tmp.append(ob.toString());
			}
		}
		Result.setUpperText(tmp.toString());
	}

	private static void showLastAnswer() {
		//show the last answer
		String temp = list.get(list.size()-1).toString();
		Result.setLowerText(temp);
	}
	
	private static void showSimpleLast() {
		//show the last answer
		if(list.get(list.size()-1) instanceof NumElem||list.get(list.size()-1) instanceof ResElem){
			String temp = list.get(list.size()-1).toString();
			double tmpN = Double.parseDouble(temp);
			if(tmpN==0){
				Result.setLowerText("0");
			}else{
				int tmpInt = (int)tmpN;
				if(tmpInt==tmpN){
					Result.setLowerText(String.format("%d", tmpInt));
				}else{
					StringBuilder tmp = new StringBuilder(temp);
					for(int i=tmp.length()-1;tmp.charAt(i)=='0'||tmp.charAt(i)=='.';i--){
						tmp.deleteCharAt(i);
					}
					if(tmp.length()>10){
						Result.setLowerText(tmp.substring(0, 10));
					}else{
						Result.setLowerText(tmp.toString());
					}
				}
			}
		}else{
			Result.setLowerText(list.get(list.size()-1).toString());
		}
	}
	
	public static void Equal() {
		if(list.size()>=3){
			Compute();
		}
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof NumElem&&list.size()==1){
				Result.setUpperText("");
				showSimpleLast();
			}else if(list.get(list.size()-1) instanceof ResElem){
				showList();
				showSimpleLast();
				String temp = Result.getUpperText();
				StringBuilder tmp = new StringBuilder(temp.substring(1, temp.length()-1));
				tmp.append("=");
				Result.setUpperText(tmp.toString());
			}
		}
	}

	private static void sendNeverCompute(){
		CLEAR_ALL();
		Result.setUpperText("");
		Result.setLowerText("function limited...");
	}
	
	private static void sendInputError(){
		CLEAR_ALL();
		Result.setUpperText("");
		Result.setLowerText("input error...");
	}
	
	public static void CLEAR_ALL() {
		list.clear();
		Result.setUpperText("");
		Result.setLowerText("0");
	}
	
	public static void CLEAR() {
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof NumElem) {
				//just remove last NumElem
				list.remove(list.size()-1);
				Result.setLowerText("0");
			}
		}
	}
	
	private static void Compute(){
		if(list.size()>=3){
			for(int i=0;i<list.size();i++){
				if(list.get(i) instanceof OperatorElem){
					if(i+1>=list.size() || i-1<0){
						return;
					}
					ResElem res = new ResElem();
					Double f = new Double(list.get(i-1).toString());
					Double s = new Double(list.get(i+1).toString());
					Double r = null;
					
					if(((OperatorElem)list.get(i)).getOperatorCase()==OperatorElem.ADD){
						r = add(f,s);
					}else if(((OperatorElem)list.get(i)).getOperatorCase()==OperatorElem.SUB){
						r = sub(f,s);
					}else if(((OperatorElem)list.get(i)).getOperatorCase()==OperatorElem.MULTIPLY){
						r = multiply(f,s);
					}else if(((OperatorElem)list.get(i)).getOperatorCase()==OperatorElem.DIVIDE){
						r = divide(f,s);
					}
					res.setResultNum(r.toString());
					StringBuilder tmp = new StringBuilder("(");
					for(int j=i-1;j<=i+1;j++){
						if(list.get(j) instanceof ResElem){
							tmp.append("("+((ResElem)list.get(j)).getProcess()+")");
						}else{
							tmp.append(list.get(j).toString());
						}
					}
					tmp.append(")");
					res.setProcess(tmp.toString());
					for(int j=i+1;j>=i-1;j--){
						list.remove(j);
					}
					list.add(i-1,res);
				}
			}
		}
	}
	
	private static Double divide(Double a,Double b) {
		return a/b;
	}

	private static Double multiply(Double a,Double b) {
		return a*b;
	}

	private static Double sub(Double a,Double b) {
		return a-b;
	}

	private static Double add(Double a,Double b) {
		return a+b;
	}
	
	private static void newResElem(String state,String ans){
		String tmp;
		Object o = list.get(list.size()-1);
		if(o instanceof NumElem){
			tmp = o.toString();
		}else{
			tmp = ((ResElem)o).getProcess();
		}
		ResElem elem = new ResElem();
		elem.setResultNum(ans);
		elem.setProcess(state+"("+tmp+")");
		list.remove(list.size()-1);
		list.add(elem);
		showList();
		showSimpleLast();
	}

	public static void Factorial() {
		//如果是小数则报出暂时无法计算
		//如果是0则报出结果为1
		if(!list.isEmpty()) {
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				
				String tmp = list.get(list.size()-1).toString();
				
				double numDou = Double.parseDouble(tmp);
				if(numDou>=0){
					int numInt = (int)numDou;
					if(numInt==numDou){
						long ans = 1;
						if(numInt!=0){
							for(int i=1;i<=numInt;i++){
								ans *= i;
							}
						}
						newResElem("Factorial",String.format("%d", ans));
					}else{
						//send can't compute message
						sendNeverCompute();
					}
				}else{
					//send can't compute message
					sendNeverCompute();
				}
			}
		}
		
	}
	
	public static void Sqrt() {   //开方
		if(!list.isEmpty()) {
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				String tmp = list.get(list.size()-1).toString();
				double numDou = Double.parseDouble(tmp);
				if(numDou>=0){
					double ans = Math.sqrt(numDou);
					newResElem("Sqrt",String.format("%f", ans));
				}else{
					//send can't compute message
					sendInputError();
				}
			}
		}
	}
	
	public static void Square() {
		//平方
		if(!list.isEmpty()) {
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				String tmp = list.get(list.size()-1).toString();
				double numDou = Double.parseDouble(tmp);
				long ans = (long) (numDou*numDou);
				newResElem("Square",String.format("%d", ans));
			}
		}
	}
	
	public static void Reciprocal() {
		//reciprocal operator
		if(!list.isEmpty()) {
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				String tmp = list.get(list.size()-1).toString();
				double numDou = Double.parseDouble(tmp);
				if(numDou!=0){
					double ans = 1/numDou;
					newResElem("Reciprocal",String.format("%f", ans));
				}else{
					sendInputError();
				}
			}
		}
	}
	
	public static void Del() {
		if(!list.isEmpty()) {
			if(list.get(list.size()-1) instanceof NumElem) {
				((NumElem)list.get(list.size()-1)).Del();
				if(((NumElem)list.get(list.size()-1)).getNumElement().isEmpty()){
					//remove last element if NumElem is empty
					list.remove(list.size()-1);
					Result.setLowerText("0");
				}else{
					showLastAnswer();
				}
			}
		}
	}
	
	public static void Add() {
		//加
		if(list.size()>=3){
			Compute();
		}
		if(!list.isEmpty()){
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				showList();
			}
		}
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof OperatorElem) {
				((OperatorElem)list.get(list.size()-1)).changeOperatorCase(OperatorElem.ADD);
	  		}else{
	  			list.add(new OperatorElem(OperatorElem.ADD));
	  		}
		}else{
			//list is empty
			list.add(new NumElem("0"));
			list.add(new OperatorElem(OperatorElem.ADD));
		}
		showLastAnswer();
	}
	
	public static void Sub() {
		//减
		if(list.size()>=3){
			Compute();
		}
		if(!list.isEmpty()){
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				showList();
			}
		}
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof OperatorElem) {
				((OperatorElem)list.get(list.size()-1)).changeOperatorCase(OperatorElem.SUB);
	  		}else{
	  			list.add(new OperatorElem(OperatorElem.SUB));
	  		}
		}else{
			//list is empty
			list.add(new NumElem("0"));
			list.add(new OperatorElem(OperatorElem.SUB));
		}
		showLastAnswer();
	}
	
	public static void Multiply() { 
		//乘
		if(list.size()>=3){
			Compute();
		}
		if(!list.isEmpty()){
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				showList();
//				System.out.println("good");
			}
		}
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof OperatorElem) {
				((OperatorElem)list.get(list.size()-1)).changeOperatorCase(OperatorElem.MULTIPLY);
	  		}else{
	  			list.add(new OperatorElem(OperatorElem.MULTIPLY));
	  		}
		}else{
			//list is empty
			list.add(new NumElem("0"));
			list.add(new OperatorElem(OperatorElem.MULTIPLY));
		}
		showLastAnswer();
	}
	
	public static void Divide() {
		if(list.size()>=3){
			Compute();
		}
		if(!list.isEmpty()){
			if(!(list.get(list.size()-1) instanceof OperatorElem)){
				showList();
			}
		}
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof OperatorElem) {
				((OperatorElem)list.get(list.size()-1)).changeOperatorCase(OperatorElem.DIVIDE);
	  		}else{
	  			list.add(new OperatorElem(OperatorElem.DIVIDE));
	  		}
		}else{
			//list is empty
			list.add(new NumElem("0"));
			list.add(new OperatorElem(OperatorElem.DIVIDE));
		}
		showLastAnswer();
	}
	
	public static void Negate() {
		
		if(!list.isEmpty()) {
			if(list.get(list.size()-1) instanceof NumElem) {
				((NumElem)list.get(list.size()-1)).changeNegate();
				showLastAnswer();
			}
		}
	}
	
	public static void Dot() {
		if(list.isEmpty()) {
			list.add(new NumElem("0."));
		}else{
			if(list.get(list.size()-1) instanceof NumElem) {
				if(!((NumElem)list.get(list.size()-1)).getNumElement().contains(".")){
					((NumElem)list.get(list.size()-1)).add(".");
				}
			}else if(list.get(list.size()-1) instanceof ResElem){
				list.clear();
				list.add(new NumElem("0."));
			}else if(list.get(list.size()-1) instanceof OperatorElem){
				list.add(new NumElem("0."));
			}
		}
		showLastAnswer();
	}
	
	
	public static void Number_1() {
		addNum("1");
	}
	
	public static void Number_2() {
		addNum("2");
	}
	
	public static void Number_3() {
		addNum("3");
	}
	
	public static void Number_4() {
		addNum("4");
	}
	
	public static void Number_5() {
		addNum("5");
	}
	
	public static void Number_6() {
		addNum("6");
	}
	
	public static void Number_7() {
		addNum("7");
	}
	
	public static void Number_8() {
		addNum("8");
	}
	
	public static void Number_9() {
		addNum("9");
	}
	
	public static void Number_0() {
		if(!list.isEmpty()){
			if(list.get(list.size()-1) instanceof OperatorElem){
				showList();
			}
		}
		if(list.isEmpty()) {
			list.add(new NumElem("0"));
		}else{
			if(list.get(list.size()-1) instanceof NumElem) {
				if(!((NumElem)list.get(list.size()-1)).getNumElement().equals("0")){
					((NumElem)list.get(list.size()-1)).add("0");
				}
			}else if(list.get(list.size()-1) instanceof ResElem){
				list.clear();
				list.add(new NumElem("0"));
			}else if(list.get(list.size()-1) instanceof OperatorElem){
				list.add(new NumElem("0"));
			}
		}
		showLastAnswer();
	}
	
	private static void addNum(String num){
		if(list.isEmpty()) {
			list.add(new NumElem(num));
		}else{
			if(list.get(list.size()-1) instanceof NumElem) {
				//if equal to 0
				if(!((NumElem)list.get(list.size()-1)).getNumElement().equals("0")){
					((NumElem)list.get(list.size()-1)).add(num);
				}else{
					list.remove(list.size()-1);
					list.add(new NumElem(num));
				}
			}else if(list.get(list.size()-1) instanceof ResElem){
				list.clear();
				Result.setUpperText("");
				list.add(new NumElem(num));
			}else if(list.get(list.size()-1) instanceof OperatorElem){
				showList();
				list.add(new NumElem(num));
			}
		}
		showLastAnswer();
	}
	
}
