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
		
		//------------------------ SAFE -----------------------------//
		
		// This Gene will go into this index
		Gene insertionGene;
		
		// And replace this gene
		Gene targetGene;
		
		
		ArrayList<Gene> test = new ArrayList<Gene>();
		
		// Loop through the mother Chromosone(swath bounded), find a gene that isn't in the
		// child
		for(int i = x; i <= y; i++) {
			if(!offspring.contains(mother.getGene(i))) {
				
				// This gene is the gene to be inserted
				insertionGene = mother.getGene(i);
				//// It will replace some Gene in the father
				targetGene = father.getGene(i);
				
				
				//------------------------------ SAFE ------------------------------//
				
				for(int j = 0; j < Chromosone.length(); j++) {
					if(mother.getGene(j) == (targetGene)) {
						if(offspring.contains(father.getGene(j))) {
							targetGene = father.getGene(j);
							j = 0;
						} else {
							offspring.setGene(j, insertionGene);
						}
					}
				}
				
				/*
				boolean inserted = false;
				// Loop through the mother and find the target gene in the mother.
				while(inserted == false) {
					for(int j = 0; j < Chromosone.length(); j++) {
						// When found ...
						if(mother.getGene(j).equals(targetGene)) {
							// If it's already contained
							if(offspring.contains(father.getGene(j))) {
								// Reset our target gene and try again
								targetGene = father.getGene(j);
							} else {
								offspring.setGene(j, insertionGene);
								inserted = true;
							}
						}
					}
				}
				*/
			}
		}
		/*
		for(int i = 0; i < test.size(); i++) {
			if(offspring.contains(test.get(i))) {
				System.out.println("true");
			} else {
				System.out.println(false);
			}
		}*/
		
		for(int i = 0; i < Chromosone.length(); i++) {
			if(offspring.getGene(i) == null){
				offspring.setGene(i, mother.getGene(i));
			}
		}
		
		//offspring.isValid();
		return offspring;
		
	}
	
}
