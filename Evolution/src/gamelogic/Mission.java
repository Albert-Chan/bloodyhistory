package gamelogic;

public class Mission {

	public static final int TYPE_SHEEP_HUNTING = 0;
	public static final int TYPE_FLEET_SAVING = 1;
	public static final int TYPE_DEFEAT = 2;
	public static final int TYPE_AMUSH = 3;

	private static final int SPEED_10_PERCENT = 1;
	private static final int SPEED_20_PERCENT = 2;
	private static final int SPEED_30_PERCENT = 3;
	private static final int SPEED_40_PERCENT = 4;
	private static final int SPEED_50_PERCENT = 5;
	private static final int SPEED_60_PERCENT = 6;
	private static final int SPEED_70_PERCENT = 7;
	private static final int SPEED_80_PERCENT = 8;
	private static final int SPEED_90_PERCENT = 9;
	private static final int SPEED_FULL = 10;

	private static final int MISSION_ATTACK = 1;
	private static final int MISSION_TRANSPORT = 3;
	private static final int MISSION_DEPLOY = 4;
	private static final int MISSION_ESPIAL = 6;
	private static final int MISSION_RECYCLE = 8;
	private static final int MISSION_EXPEDITION = 15;

	private int type;
	
	public Mission(int type) { 
		// the mission type, not in game.
		this.type = type;
	}

	public int getType() {
		return type;
	}

	private int mission;

	private int union = 0;

	private int holdingtime = 1;
	private int expeditiontime = 1;

	private int union2 = 0;
	private int holdingOrExpTime = 0;

	private int speed = SPEED_FULL;

	public String getMission() {
		return Integer.toString(mission);
	}

	public void setMission(int mission) {
		this.mission = mission;
	}

	public String getUnion() {
		return Integer.toString(union);
	}

	public void setUnion(int union) {
		this.union = union;
	}

	public String getHoldingtime() {
		return Integer.toString(holdingtime);
	}

	public void setHoldingtime(int holdingtime) {
		this.holdingtime = holdingtime;
	}

	public String getExpeditiontime() {
		return Integer.toString(expeditiontime);
	}

	public void setExpeditiontime(int expeditiontime) {
		this.expeditiontime = expeditiontime;
	}

	public String getUnion2() {
		return Integer.toString(union2);
	}

	public void setUnion2(int union2) {
		this.union2 = union2;
	}

	public String getHoldingOrExpTime() {
		return Integer.toString(holdingOrExpTime);
	}

	public void setHoldingOrExpTime(int holdingOrExpTime) {
		this.holdingOrExpTime = holdingOrExpTime;
	}

	public String getSpeed() {
		return Integer.toString(speed);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
