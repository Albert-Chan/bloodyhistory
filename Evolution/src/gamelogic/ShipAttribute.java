package gamelogic;

public class ShipAttribute {
	String shipName;
	int type;
	int structuralIntegrity;
	int shieldPower;
	int weaponPower;
	int cargoCapacity;
	int baseSpeed;
	int fuelConsumption;
	int shiftSpeed;
	int shiftConsumption;
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}
	public int getCargoCapacity() {
		return cargoCapacity;
	}
	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	public int getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(int fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	public int getShieldPower() {
		return shieldPower;
	}
	public void setShieldPower(int shieldPower) {
		this.shieldPower = shieldPower;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}
	public void setStructuralIntegrity(int structuralIntegrity) {
		this.structuralIntegrity = structuralIntegrity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getWeaponPower() {
		return weaponPower;
	}
	public void setWeaponPower(int weaponPower) {
		this.weaponPower = weaponPower;
	}
	public int getShiftConsumption() {
		return shiftConsumption;
	}
	public void setShiftConsumption(int shiftConsumption) {
		this.shiftConsumption = shiftConsumption;
	}
	public int getShiftSpeed() {
		return shiftSpeed;
	}
	public void setShiftSpeed(int shiftSpeed) {
		this.shiftSpeed = shiftSpeed;
	}  
	
}
