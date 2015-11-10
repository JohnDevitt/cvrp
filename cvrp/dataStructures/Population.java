package cvrp.dataStructures;

import java.util.Arrays;

public class Population {

//----------------------------- CLASS VARIABLES AND CONSTRUCTOR --------------------//
	
	private Chromosone[] population;
	private int size;
	
	// Randomly populates a Population
	public Population(int size) {
		// I see no reason that population sizes always have to be uniform(Although I see
		// no reason for them to be otherwise either), but still this value doesn't have to
		// be static and can vary
		this.size = size;
		population = new Chromosone[size];
		for(int i = 0; i < size; i++) {
			population[i] = new Chromosone();
		}
	}
	
//----------------------------  SETTERS AND GETTERS --------------------------------//

	public Chromosone getChromosone(int index) {
		return population[index];
	}

	public void setChromosone(int index, Chromosone chromosone) {
		population[index] = chromosone;
	}
	
	public int size() {
		return size;
	}
	
//--------------------------------- FITNESS RELATED FUNCTIONS --------------------------//
	
	public Chromosone getFittest() {
		Chromosone fittest = population[0];
		for(int i = 0 ; i < size; i++) {
			if(population[i].getFitness() < fittest.getFitness()) {
				fittest = population[i];
			}
		}
		return fittest;
	}
	
//------------------------------ STRING REPRESENTATION ----------------------------------//
	
	@Override
	public String toString() {
		return "Population [population=" + Arrays.toString(population) + ", size=" + size + "]";
	}
}
