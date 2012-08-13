package gamelogic;
public class Resource {
	int metal;

	int crystal;

	int deuterium;

	public Resource(int metal, int crystal, int deuterium)
	{
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
