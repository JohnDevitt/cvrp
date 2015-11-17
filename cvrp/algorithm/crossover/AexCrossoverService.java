package cvrp.algorithm.crossover;

import cvrp.dataLoader.GeneSet;
import cvrp.dataStructures.Chromosone;

public class AexCrossoverService {
	
	public static Chromosone AexCrossover(Chromosone father, Chromosone mother) {
		Chromosone offspring = new Chromosone();
		
		for(int i = 0; i < Chromosone.length(); i++) {
			offspring.setGene(i, GeneSet.getDummyGene());
		}
		
		Chromosone contributingParent = randomlySelectParent(father, mother);
		
		offspring.setGene(0, contributingParent.getGene(0));
		
		int x = 1;
		while(Math.random() > 0.01 && x < Chromosone.length()) {
			offspring.setGene(x, contributingParent.getGene(x));
			x++;
		}
		
		int j = updateParentIndex(contributingParent, offspring, x);
		
		for(int i = x; i < Chromosone.length(); i++) {
			while(offspring.contains(contributingParent.getGene(j))) {
				contributingParent = swapContributingParent(contributingParent, father, mother);
				j = updateParentIndex(contributingParent, offspring, i);
				j = wraparoundIncrement(j);
			}
			
			offspring.setGene(i, contributingParent.getGene(j));
			j = wraparoundIncrement(j);
		}
		
		return offspring;
}
	
	private static Chromosone randomlySelectParent(Chromosone father, Chromosone mother) {
		if(Math.random() < 0.5) {
			return father;
		} else {
			return mother;
		}
	}
	
	public static Chromosone swapContributingParent(Chromosone parent, Chromosone father, Chromosone mother) {
		if(parent.equals(father)) {
			parent = mother;
		} else {
			parent = father;
		}
		
		return parent;
	}
	
	public static int updateParentIndex(Chromosone parent, Chromosone offspring, int i) {
		int id = offspring.getGene(i - 1).getId();
		int index = parent.getGeneIndex(id);
		index = wraparoundIncrement(index);
		return index;
	}
	
	private static int wraparoundIncrement(int index) {
		index++;
		if(index == Chromosone.length()) {
			index = 0;
		}
		return index;
	}

}
