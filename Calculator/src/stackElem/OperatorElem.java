package stackElem;

public class OperatorElem {
	
	public static final int ADD = 1;
	public static final int SUB = 2;
	public static final int MULTIPLY = 3;
	public static final int DIVIDE = 4;
	
	private int operatorCase;

	public OperatorElem(int operatorCase) {
		super();
		this.operatorCase = operatorCase;
	}

	public int getOperatorCase() {
		return operatorCase;
	}

	public void setOperatorCase(int operatorCase) {
		this.operatorCase = operatorCase;
	}
	
	public void changeOperatorCase(int newOperatorCase) {
		this.operatorCase = newOperatorCase;
	}
	
	@Override
	public String toString(){
		if(operatorCase==ADD){
			return "+";
		}else if(operatorCase==SUB){
			return "-";
		}else if(operatorCase==MULTIPLY){
			return "x";
		}else{
			return "÷";
		}
	}
	

}
