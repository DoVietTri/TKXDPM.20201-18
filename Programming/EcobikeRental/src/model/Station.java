package model;

public class Station {

	private int stationId;
	private String name, position;
	
	private Bike bikes;

	public Station(String name, String position) {
		super();
		this.setName(name);
		this.setPosition(position);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
	
}
