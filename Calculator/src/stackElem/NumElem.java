package stackElem;

public class NumElem {
	
	private String NumElement;

	public NumElem(String num) {
		super();
		this.NumElement = num;
	}

	public String getNumElement() {
		return NumElement;
	}

	public void setNumElement(String numElement) {
		NumElement = numElement;
	}
	
	public void add(String elem) {
		NumElement += elem;
	}
	
	private void addNegate(){
		NumElement = "-" + NumElement;
	}
	
	private void delNegate(){
		if(!NumElement.isEmpty())
			NumElement = NumElement.substring(1, NumElement.length());
	}
	
	public void Del() {
		if(!NumElement.isEmpty()){
			NumElement = NumElement.substring(0, NumElement.length()-1);
		}
	}

	public void changeNegate() {
		if(!NumElement.isEmpty()){
			if(NumElement.charAt(0)=='-'){
				delNegate();
			}else{
				addNegate();
			}
		}
		
	}
	
	@Override
	public String toString(){
		return NumElement;
	}
	
}
