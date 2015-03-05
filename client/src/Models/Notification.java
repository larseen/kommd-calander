package Models;

public class Notification {

	private int id;
	private String text;
	
	public Notification(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	public int getId(){
		return id;
	}
	
	public String getText(){
		return text;
	}
}
