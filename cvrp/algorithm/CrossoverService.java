package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Gene;

import java.util.ArrayList;

import cvrp.dataLoader.GeneSet;

public class CrossoverService {

	public static Chromosone pmxCrossover(Chromosone father, Chromosone mother) {
		
		Chromosone offspring = new Chromosone();
		
		int x = (int)(Math.random() * GeneSet.size());
		int y = (int)(Math.random() * GeneSet.size());
		
		if(y < x) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		for(int i = 0; i < Chromosone.length(); i++) {
			if(i >= x && i <= y) {
				offspring.setGene(i, father.getGene(i));
			} else {
				offspring.setGene(i, null);
			}
		}
		
		ArrayList<Gene> insertionList = generateInsertionList(mother, offspring, x, y);
		
		while(!insertionList.isEmpty()) {
			int index = pmxInsert(insertionList.get(0).getIndex(), mother, father, offspring);
			offspring.setGene(index, insertionList.remove(0));
		}
		
		offspring = completePmx(mother, offspring);
		//System.out.println(offspring.validate());
		
		return offspring;
	}

	private static Chromosone completePmx(Chromosone mother, Chromosone offspring) {
		for(int i = 0; i < Chromosone.length(); i++) {
			if(offspring.getGene(i) == null) {
				offspring.setGene(i, mother.getGene(i));
			}
		}
		
		return offspring;
	}

	private static int pmxInsert(int geneId, Chromosone mother, Chromosone father, Chromosone offspring) {
		int geneIndex = mother.getGeneIndex(geneId);
		boolean occupied = true;
		do {
			int geneID = father.getGene(geneIndex).getID();;
			geneIndex = mother.getGeneIndex(geneID);
			occupied = isOccupied(geneIndex, offspring);
		} while(occupied);
		
		return geneIndex;
	}
	
	private static boolean isOccupied(int geneIndex, Chromosone offspring) {
		if(offspring.getGene(geneIndex) == null) {
			return false;
		}
		return true;
	}

	private static int findIndex(int geneID, Chromosone chromosone) {
		int index = 0;
		while(chromosone.getGene(index).getIndex() != geneID) {
			index++;
		}
		return index;
	}
	
	private static int findID(int geneIndex, Chromosone chromosone) {
		return chromosone.getGene(geneIndex).getIndex();
	}
	
	private static ArrayList<Gene> generateInsertionList(Chromosone mother, Chromosone offspring, int x, int y) {
		ArrayList<Gene> insertionList = new ArrayList<Gene>();
		for(int i = x; i <=y; i++) {
			if(!offspring.contains(mother.getGene(i))) {
				insertionList.add(mother.getGene(i));
			}
		}
		return insertionList;
	}
	
}
