package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Gene;
import cvrp.dataLoader.GeneSet;

public class MutationService {
	/*
	public static void rsmMutate(Chromosone chromosone) {
		int i = (int)(Math.random() * GeneSet.size());
		int j = (int)(Math.random() * GeneSet.size());
		
		Gene tmpGene = chromosone.getGene(j);
		chromosone.setGene(j, chromosone.getGene(i));
		chromosone.setGene(i, tmpGene);
	}
	*/
	
	public static Chromosone rsmMutate(Chromosone chromosone) {
		int x = (int)(Math.random() * GeneSet.size());
		int y = (int)(Math.random() * GeneSet.size());
		
		// Keep x less than y, makes it easier to visualise
		if(y < x) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		
		for(int i = 0; i < (y-x)/2; i++) {
			Gene tmp = chromosone.getGene(x + i);
			chromosone.setGene(x + i, chromosone.getGene(y - i));
			chromosone.setGene(y - i, tmp);
		}
		
		return chromosone;
	}

}
