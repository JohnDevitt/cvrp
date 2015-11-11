package cvrp.algorithm;

import cvrp.dataStructures.Chromosone;
import cvrp.dataStructures.Population;

public class SelectionService {
	
	public static Chromosone select(Population population) {
		Population tournament = new Population(5);
		for(int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * population.size());
			tournament.setChromosone(i, population.getChromosone(index));
		}
		return tournament.getFittest();
	}

}
