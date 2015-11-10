package cvrp;

import cvrp.algorithm.PopulationEvolutionService;
import cvrp.dataStructures.Population;

public class App {
	public static void main(String[] args) {
		Population population = new Population(100);
		System.out.println(population.getFittest().getFitness());
		
		// Evolve our population until we reach an optimum solution
        int generationCount = 0;
        //while (generationCount < 100) {
        while (generationCount < 1000000) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());
            population = PopulationEvolutionService.evolvePopulation(population);
        }
        System.out.println(population);
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());
	}
}
