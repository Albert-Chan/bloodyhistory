package action.executor;

import gamelogic.Coordinate;

public class RouteOccupancy {

	public static final int TYPE_SHEEP_HUNTING = 0;
	public static final int TYPE_FS = 1;
	public static final int TYPE_PLANED_ATTACK = 2;

	/**
	 * 1.SheepHunting 2.FS 3.Planed attack
	 */
	private int type;

	/**
	 * only useful for type1:SheepHunting
	 */
	private Coordinate dest;
	/**
	 * freeTimePoint is reserved to calculate which route is used for next FS.
	 * only useful for type1:SheepHunting
	 */
	private long freeTimePoint;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Coordinate getDest() {
		return dest;
	}

	public void setDest(Coordinate dest) {
		this.dest = dest;
	}

	public long getFreeTimePoint() {
		return freeTimePoint;
	}

	public void setFreeTimePoint(long freeTimePoint) {
		this.freeTimePoint = freeTimePoint;
	}

}
