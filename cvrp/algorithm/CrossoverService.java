package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Gene;
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
			if(i <= x || i >= y) {
				offspring.setGene(i, null);
			} else {
				offspring.setGene(i, father.getGene(i));
			}
		}	
		
		// FIND THE GENE TO BE INSERTED
		
		Gene insertionGene = new Gene();
		int insertionIndex = 0;
		Gene targetGene = father.getGene(insertionIndex);
		for(int i = x; i <= y; i++) {
			if(!offspring.contains(mother.getGene(i))) {
				insertionGene = mother.getGene(i);
				insertionIndex = i;
				
				// SEARCH FOR ITS INSERTION POINT
				targetGene = father.getGene(insertionIndex);
				boolean inserted = false;
				while(inserted = false) {
					for(int j = 0; j < GeneSet.size(); j++) {
						if(mother.getGene(j).equals(targetGene)) {
							if(j < x || j > y) {
								offspring.setGene(j, insertionGene);
								inserted = true;
								j = GeneSet.size();
							} else {
								targetGene = father.getGene(i);
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < GeneSet.size(); i++) {
			if(offspring.getGene(i) == null){
				offspring.setGene(i, mother.getGene(i));
			}
		}
		
		//offspring.isValid();
		return offspring;
		
	}
	
}
