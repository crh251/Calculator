package stackElem;

public class ResElem {
	
	private String process;
	
	private String resultNum;

	public ResElem() {
		super();
	}

	public ResElem(String process, String resultNum) {
		super();
		this.process = process;
		this.resultNum = resultNum;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getResultNum() {
		return resultNum;
	}

	public void setResultNum(String resultNum) {
		this.resultNum = resultNum;
	}

	@Override
	public String toString(){
		return resultNum;
	}
}
