package gamelogic;

public class Mission {
	public static final int SPEED_10_PERCENT = 1;
	public static final int SPEED_20_PERCENT = 2;
	public static final int SPEED_30_PERCENT = 3;
	public static final int SPEED_40_PERCENT = 4;
	public static final int SPEED_50_PERCENT = 5;
	public static final int SPEED_60_PERCENT = 6;
	public static final int SPEED_70_PERCENT = 7;
	public static final int SPEED_80_PERCENT = 8;
	public static final int SPEED_90_PERCENT = 9;
	public static final int SPEED_100_PERCENT = 10;

	public static final int MISSION_ATTACK = 1;
	public static final int MISSION_TRANSPORT = 3;
	public static final int MISSION_DEPLOY = 4;
	public static final int MISSION_ESPIAL = 6;
	public static final int MISSION_RECYCLE = 8;
	public static final int MISSION_EXPEDITION = 15;

	public int type;

	public int union = 0;
	
	public int holdingtime = 1;
	public int expeditiontime = 1;

	public int union2 = 0;
	public int holdingOrExpTime = 0;

	public int speed = SPEED_100_PERCENT;

	public String getType() {
		return Integer.toString(type);
	}

	public void setType(int type) {
		this.type = type;
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
