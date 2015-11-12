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

	public void setGene(int index, Gene gene) {
		chromosone[index] = gene;
	}
	
	public static int length() {
		return length;
	}
	
//-----------------  -------- FITNESS RELATED FUNCTION ----------------------------//
	
	public double getFitness() {
		
		return getCVRPFitness();
		/*
		double fitness = 0;
		for(int i = 0; i < GeneSet.size() - 1; i++) {
			fitness += chromosone[i].getDistance(chromosone[i + 1]);
		}
		fitness += chromosone[length - 1].getDistance(GeneSet.getDepot());
		return fitness;
		*/
	}
	
	public double getCVRPFitness(){
		
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
				fitness += GeneSet.getDepot().getDistance(chromosone[i]);
				load = 0;
			}
		}
		
		fitness += chromosone[length - 1].getDistance(GeneSet.getDepot());
		return fitness;
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
		result += "cost " + getCVRPFitness() + "\n";
		for(int i = 0; i < length; i++) {
			if(chromosone[i] == null) {
				result += "null" + "->";
			} else {
				result += chromosone[i].getIndex() + "->";
			}
			if(delimiterIndex[i] == true) {
				result += GeneSet.getDepot().getIndex() + "\n" + GeneSet.getDepot().getIndex() + "->";
			}
		}
		
		result += GeneSet.getDepot().getIndex();
		return result;
	}
	
	public boolean contains(Gene gene) {
		for(int i = 0; i < length; i++) {
			if(!(chromosone[i] == null)) {	
				if(chromosone[i].getIndex() == gene.getIndex()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean validate() {
		MutableGeneSet set = GeneSet.getMutableGeneSet();
		while(set.size() != 0) {
			boolean flag = false;
			Gene gene = set.getElement();
			if(this.contains(gene)) {
				flag = true;
			}
			if(flag == false) {
				System.out.println("missing: " + gene.getIndex());
				System.out.println(toString());
				return false;
			}
		}
		
		return true;
	}
	
}
