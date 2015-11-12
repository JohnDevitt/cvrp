package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Population;

public class SelectionService {
	
	public static Chromosone select(Population population) {
		int tournamentSize = 5;
		
		Chromosone fittest = population.getChromosone((int) (Math.random() * population.size()));
		for(int i = 0; i < tournamentSize - 1; i++) {
			int index = (int) (Math.random() * population.size());
			if(population.getChromosone(index).getFitness() < fittest.getFitness()) {
				fittest = population.getChromosone(index);
			}
		}
		
		return fittest;
	}
}
