package moon1139.model;

public enum ResponseStatus {
	success("success"), fail("fail"), error("error"), exit("exit");
	
	private String status;
	
	ResponseStatus(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
