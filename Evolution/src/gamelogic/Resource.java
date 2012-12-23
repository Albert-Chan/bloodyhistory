package gamelogic;

public class Resource {
	private int metal;
	private int crystal;
	private int deuterium;

	public static final Resource NO_RESOURCE = new Resource(0, 0, 0);
	
	public Resource(int metal, int crystal, int deuterium) {
		this.metal = metal;
		this.crystal = crystal;
		this.deuterium = deuterium;
	}

	public int getCrystal() {
		return crystal;
	}

	public void setCrystal(int crystal) {
		this.crystal = crystal;
	}

	public int getDeuterium() {
		return deuterium;
	}

	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

}
