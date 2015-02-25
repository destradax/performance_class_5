package co.com.psl.elitemovie.model;

public class Seat {

	private int row;
	
	private int column;
	
	private boolean isAisle; //Pasillo
	
	private boolean isBooked;
	
	private int preferencePoints;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isAisle() {
		return isAisle;
	}

	public void setAisle(boolean isAisle) {
		this.isAisle = isAisle;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public int getPreferencePoints() {
		return preferencePoints;
	}

	public void setPreferencePoints(int points) {
		this.preferencePoints = points;
	}

	public Seat(int row, int column, boolean isAisle, boolean isBooked,
			int preferencePoints) {
		super();
		this.row = row;
		this.column = column;
		this.isAisle = isAisle;
		this.isBooked = isBooked;
		this.preferencePoints = preferencePoints;
	}
	
	
}
