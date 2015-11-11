package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Gene;
import cvrp.dataStructures.Population;
import cvrp.dataLoader.GeneSet;

public class PopulationEvolutionService {

	private static double mutationRate = 0.1;
	
	public static Population evolvePopulation(Population population) {
		Population nextGenPopulation = new Population(population.size());
 		for(int i = 0; i < population.size()/2; i++) {
			Chromosone father = SelectionService.select(population);
			Chromosone mother = SelectionService.select(population);
			nextGenPopulation.setChromosone(i, CrossoverService.pmxCrossover(father, mother));
			nextGenPopulation.setChromosone(i, CrossoverService.pmxCrossover(mother, father));
		}
 		
 		for(int i = 0; i < population.size(); i++) {
 			if(Math.random() < mutationRate) {
 				population.setChromosone(i, MutationService.rsmMutate(population.getChromosone(i)));
 			}
 		}
		return nextGenPopulation;
	}
}
