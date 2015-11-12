package cvrp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cvrp.algorithm.CrossoverService;
import cvrp.algorithm.PopulationEvolutionService;
import cvrp.algorithm.SelectionService;
import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Population;

public class App {
	public static void main(String[] args) {
		
		
		Population population = new Population(1000);
		Chromosone fittest = population.getFittest();
		
		for(int generation = 0; generation < 1000000; generation++){
			if(population.getFittest().getFitness() < fittest.getFitness()) {
				fittest = population.getFittest();
				System.out.println("Fittest so far: " + fittest.getFitness() + ". Found in generation: " + generation);
				System.out.println(population.getFittest());
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
        System.out.println(population);
	}
}
