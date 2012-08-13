package gamelogic;

public class Building {

	private String buildingName;
	private int buildingLevel;
	
	public int getBuildingLevel() {
		return buildingLevel;
	}
	public void setBuildingLevel(int buildingLevel) {
		this.buildingLevel = buildingLevel;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public Resource getUpdateRequiredResource(String buildingName, int level)
	{
		return new Resource(0,0,0);
	}
	
}
