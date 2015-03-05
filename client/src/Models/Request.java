package Models;

public class Request {
	
	private int id;
	private String text;
	private String requestType;
	
	public Request(int id, String text, String requestType){
		this.id = id;
		this.text = text;
		this.requestType = requestType;
	}
	
	public int getId(){
		return id;
	}
	
	public String getText(){
		return text;
	}
	
	public String getType(){
		return requestType;
	}
}
