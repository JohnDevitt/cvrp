package cvrp;

import cvrp.algorithm.CrossoverService;
import cvrp.algorithm.PopulationEvolutionService;
import cvrp.dataLoader.GeneSet;
import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Population;

public class App {
	public static void main(String[] args) {
		
		Chromosone ch1 = new Chromosone();
		Chromosone ch2 = new Chromosone();
		Chromosone ch3 = CrossoverService.pmxCrossover(ch1, ch2);
		
		Population population = new Population(250);
		Chromosone fittest = population.getFittest();
		
		for(int generation = 0; generation < 1000000; generation++){
			if(population.getFittest().getFitness() < fittest.getFitness()) {
				fittest = population.getFittest();
				System.out.println("Fittest so far: " + fittest.getFitness() + ". Found in generation: " + generation);
				System.out.println(fittest.validate());
			}
            population = PopulationEvolutionService.evolvePopulation(population);
        }
        System.out.println(population.getFittest());
	}
}
