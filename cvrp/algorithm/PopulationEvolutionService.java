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
			Chromosone father = selection(population);
			Chromosone mother = selection(population);
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
	
	public static void mutate(Chromosone chromosone) {
		int i = (int)(Math.random() * GeneSet.size());
		int j = (int)(Math.random() * GeneSet.size());
		
		Gene tmpGene = chromosone.getGene(j);
		chromosone.setGene(j, chromosone.getGene(i));
		chromosone.setGene(i, tmpGene);
	}
	
	public static Chromosone selection(Population population) {
		Population tournament = new Population(5);
		for(int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * population.size());
			tournament.setChromosone(i, population.getChromosone(index));
		}
		return tournament.getFittest();
	}
}
