package cvrp.dataStructures;

import java.util.Arrays;

import cvrp.dataLoader.GeneSet;
import cvrp.dataLoader.MutableGeneSet;

public class Chromosone {
	
//----------------------------- CLASS VARIABLES AND CONSTRUCTOR --------------------//

	private Gene[] chromosone;
	private static int length = GeneSet.size();
	
	// Randomly populates a chromosone
	public Chromosone() {
		MutableGeneSet geneSet = GeneSet.getMutableGeneSet();
		chromosone = new Gene[length];
		for(int i = 0; i < length; i++) {
			chromosone[i] = geneSet.getElement();
		}
	}
	
//--------------------------------- GETTERS AND SETTERS --------------------------//

	public Gene getGene(int index) {
		return chromosone[index];
	}

	public void setGene(int index, Gene gene) {
		chromosone[index] = gene;
	}
	
	public static int length() {
		return length;
	}
	
//-----------------  -------- FITNESS RELATED FUNCTION ----------------------------//
	
	public int getFitness() {
		int fitness = 0;
		for(int i = 0; i < GeneSet.size() - 1; i++) {
			fitness += chromosone[i].getDistance(chromosone[i + 1]);
		}
		return fitness;
	}
	
//------------------------------ OTHER STANDARD FUNCTIONS ------------------------------//
	
	@Override
	public String toString() {
		return "Chromosone [chromosone=" + Arrays.toString(chromosone) + "]\n";
	}
	
	public boolean contains(Gene gene) {
		for(int i = 0; i < GeneSet.size(); i++) {
			if(chromosone[i] == null) {return false;}
			// Choose == over .equals here as in practice it's totally plausable that two
			// genes will have the same value, i.e. shallow compare the objects, not deep
			// compare their values. In a real-life system it would make sense that this
			// is a configurable value(e.g. An allowDuplicates boolean)
			if(chromosone[i] == gene) {
				return true;
			}
		}
		
		return false;
	}
	
}
