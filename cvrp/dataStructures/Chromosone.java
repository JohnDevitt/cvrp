package cvrp.dataStructures;

import java.util.Arrays;

import cvrp.dataLoader.GeneSet;
import cvrp.dataLoader.MutableGeneSet;

public class Chromosone {
	
//----------------------------- CLASS VARIABLES AND CONSTRUCTOR --------------------//

	private Gene[] chromosone;
	private static int length = GeneSet.size();
	
	private boolean[] delimiterIndex;
	
	// Randomly populates a chromosone
	public Chromosone() {
		MutableGeneSet geneSet = GeneSet.getMutableGeneSet();
		chromosone = new Gene[length];
		for(int i = 0; i < length; i++) {
			chromosone[i] = geneSet.getElement();
		}
		
		delimiterIndex = new boolean[length];
		for(int i = 0; i < length; i++) {
			delimiterIndex[i] = false;
		}
	}
	
//--------------------------------- GETTERS AND SETTERS --------------------------//

	public Gene getGene(int index) {
		return chromosone[index];
	}
	
	public int getGeneIndex(int id) {
		int index = 0;
		while(chromosone[index].getId() != id) {
			index++;
		}
		return index;
	}
	
	public boolean isOccupied(int index) {
		if(chromosone[index] == null) {
			return false;
		}
		return true;
	}

	public void setGene(int index, Gene gene) {
		chromosone[index] = gene;
	}
	
	public static int length() {
		return length;
	}
	
//-----------------  -------- FITNESS RELATED FUNCTION ----------------------------//
	
	public double getFitness(){
		
		return improvedFitness();
		
		/*
		double fitness = 0.0;
		
		while(!chromosone[0].equals(GeneSet.getDepot())) {
			rotateChromosone();
		}
		
		int load = 0;
		for(int i = 0; i < length - 1; i++) {
			load += chromosone[i].getDemand();
			if((chromosone[i + 1].getDemand() + load) < 500) {
				fitness += chromosone[i].getDistance(chromosone[i + 1]);
			} else {
				delimiterIndex[i] = true;
				fitness += chromosone[i].getDistance(GeneSet.getDepot());
				fitness += GeneSet.getDepot().getDistance(chromosone[i + 1]);
				load = 0;
			}
		}
		
		fitness += chromosone[length - 1].getDistance(GeneSet.getDepot());
		return fitness;
		*/
	}
	
	public double improvedFitness() {
		
		while(!chromosone[0].equals(GeneSet.getDepot())) {
			rotateChromosone();
		}
		
		int[] pred = new int[length];
		double[] fitness = new double[length];
		fitness[0] = 0;
		for(int i = 1; i < length; i++) {
			fitness[i] = 1000000000;
		}
		for(int i = 0; i < length - 1; i++) {
			double load = 0;
			double fitnessVal = 0;
			int j = i + 1;
			while(j < length && (load + chromosone[j].getDemand()) <= 500) {
				load = load + chromosone[j].getDemand();
				if(j == i + 1) {
					fitnessVal += GeneSet.getDepot().getDistance(chromosone[j]);
				} else {
					fitnessVal += chromosone[j - 1].getDistance(chromosone[j]);
				}
				if( (fitness[i] + fitnessVal + GeneSet.getDepot().getDistance(chromosone[j])) < fitness[j] ) {
					fitness[j] = fitness[i] + fitnessVal + GeneSet.getDepot().getDistance(chromosone[j]);
					pred[j] = i;
				}
				j++;
			}
		}
		
		for(int i = 1; i < length; i++) {
			if(pred[i] != pred[i - 1]) {
				delimiterIndex[i] = true;
			}
		}
		return fitness[length - 1];
	}
	
	public void rotateChromosone() {
		Gene head = chromosone[length - 1];
		for(int i = length - 1; i > 0; i--) {
			chromosone[i] = chromosone[i - 1];
		}
		chromosone[0] = head;
	}
	
//------------------------------ OTHER STANDARD FUNCTIONS ------------------------------//
	
	@Override
	public String toString() {
		String result = "login jd15290 23734 \n";
		result += "name John Devitt \n";
		result += "algorithm Genetic Algorithm with greedy vehicle assignment \n";
		result += "cost " + getFitness() + "\n";
		for(int i = 0; i < length; i++) {
			if(chromosone[i] == null) {
				result += "null" + "->";
			} else {
				result += chromosone[i].getId() + "->";
			}
			if(delimiterIndex[i] == true) {
				result += GeneSet.getDepot().getId() + "\n" + GeneSet.getDepot().getId() + "->";
			}
		}
		
		result += GeneSet.getDepot().getId();
		return result;
	}
	
	public boolean contains(Gene gene) {
		for(int i = 0; i < length; i++) {
			if(!(chromosone[i] == null)) {	
				if(chromosone[i].getId() == gene.getId()) {
					return true;
				}
			}
		}
		
		return false;
	}
}
