package cvrp.dataStructures;

public class Gene {

//--------------------------------------- CLASS VARIBLES --------------------------------//
	
	private int index;
	private int x;
	private int y;
	private int demand;
	
//------------------------------------- CONSTRUCTORS -------------------------------------//
	
	public Gene(int index, int x, int y, int demand) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.demand = demand;
	}
	
	// Fix to allow negatives
	public Gene() {
		x = (int)(Math.random() * 100);
		y = (int)(Math.random() * 100);
		demand = (int)(Math.random() * 100);
	}

//--------------------------------- GETTERS AND SETTERS --------------------------------//
	
	public int getIndex() {
		return index;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDemand() {
		return demand;
	}
	
//------------------------------ FITNESS RELATED FUNCTIONS --------------------------------//
	
	public double getDistance(Gene gene) {
		return Math.sqrt(Math.pow((x-gene.getX()),2) + Math.pow((y-gene.getY()),2));
	}
	
//------------------------------ OTHER STANDARD METHODS ---------------------------------//

	@Override
	public String toString() {
		return "Gene [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gene other = (Gene) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
