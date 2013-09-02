package data;

public class Auction {
	// required below

	/** auction id */
	private long aucId;
	/** item id */
	private long itemId;
	private String owner;
	private long bid;
	private long buyout;
	private int quantity;
	private int timeLeft; // VERY_LONG
	private int rand;
	private long seed;

	// optional below
	private int petSpeciesId;
	private int petBreedId;
	private int petLevel;
	private int petQualityId;

	private Auction(Builder builder) {
		this.aucId = builder.aucId;
		this.itemId = builder.itemId;
		this.owner = builder.owner;
		this.bid = builder.bid;
		this.buyout = builder.buyout;
		this.quantity = builder.quantity;
		this.timeLeft = builder.timeLeft;
		this.rand = builder.rand;
		this.seed = builder.seed;
		this.petSpeciesId = builder.petSpeciesId;
		this.petBreedId = builder.petBreedId;
		this.petLevel = builder.petLevel;
		this.petQualityId = builder.petQualityId;
	}

	public static class Builder {
		/** auction id */
		private long aucId;
		/** item id */
		private long itemId;
		private String owner;
		private long bid;
		private long buyout;
		private int quantity;
		private int timeLeft; // VERY_LONG
		private int rand;
		private long seed;

		// optional below
		private int petSpeciesId;
		private int petBreedId;
		private int petLevel;
		private int petQualityId;

		private static final int VERY_LONG = 0;
		private static final int LONG = 1;
		private static final int MEDIUM = 2;
		private static final int SHORT = 3;

		public Builder(long aucId, long itemId, String owner, long bid,
				long buyout, int quantity, String timeLeft, int rand, long seed) {
			this.aucId = aucId;
			this.itemId = itemId;
			this.owner = owner;
			this.bid = bid;
			this.buyout = buyout;
			this.quantity = quantity;
			this.timeLeft = convertTimeLeft(timeLeft);
			this.rand = rand;
			this.seed = seed;
		}

		public Builder petSpeciesId(int petSpeciesId) {
			this.petSpeciesId = petSpeciesId;
			return this;
		}

		public Builder petBreedId(int petBreedId) {
			this.petBreedId = petBreedId;
			return this;
		}

		public Builder petLevel(int petLevel) {
			this.petLevel = petLevel;
			return this;
		}

		public Builder petQualityId(int petQualityId) {
			this.petQualityId = petQualityId;
			return this;
		}

		public Auction build() {
			return new Auction(this);
		}

		private int convertTimeLeft(String timeLeft) {
			if ("VERY_LONG".equals(timeLeft))
				return VERY_LONG;
			if ("LONG".equals(timeLeft))
				return LONG;
			if ("MEDIUM".equals(timeLeft))
				return MEDIUM;
			if ("SHORT".equals(timeLeft))
				return SHORT;

			return MEDIUM;
		}
	}

}