package cvrp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cvrp.algorithm.PopulationEvolutionService;
import cvrp.algorithm.SelectionService;
import cvrp.algorithm.crossover.PmxCrossoverService;
import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Population;

public class App {
	public static void main(String[] args) {
		
		
		Population population = new Population(2500);
		Chromosone fittest = population.getFittest();
		
		while(true){
			if(population.getFittest().getFitness() < fittest.getFitness()) {
				fittest = population.getFittest();
				PrintWriter out;
				try {
					out = new PrintWriter("/home/msc15/jd15290/linux/Desktop/Java/best-solution.txt");
					out.println(fittest.toString());
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
            population = PopulationEvolutionService.evolvePopulation(population);
        }
	}
}
