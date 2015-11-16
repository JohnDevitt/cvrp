package cvrp.algorithm.crossover;

import cvrp.dataStructures.Chromosone;

public class AexCrossoverService {
	
	public static Chromosone AexCrossover(Chromosone father, Chromosone mother) {
		Chromosone offspring = new Chromosone();
		int index = 1, count = 1;
		int countv2 = 0;
		
		Chromosone targetChromosone = new Chromosone();
		
		if(Math.random() < 0.5) {
			targetChromosone = father;
		} else {
			targetChromosone = mother;
		}
		
		offspring.setGene(0, targetChromosone.getGene(0));
		
		while(count < Chromosone.length()) {
			if(Math.random() < 0.1 || offspring.contains(targetChromosone.getGene(index))) {
				if(targetChromosone == father) {
					targetChromosone = mother;
					int id = offspring.getGene(count - 1).getId();
					index = mother.getGeneIndex(id) + 1;
					if(index == Chromosone.length()) {
						index = 0;
					}
				} else {
					targetChromosone = father;
					int id = offspring.getGene(count - 1).getId();
					index = father.getGeneIndex(id) + 1;
					if(index == Chromosone.length()) {
						index = 0;
					}
				}
			}
			
			offspring.setGene(count, targetChromosone.getGene(index));
			
			index++;
			if(index == Chromosone.length()) {
				index = 0;
			}
			count++;
		}
		
		return offspring;
	}

}
