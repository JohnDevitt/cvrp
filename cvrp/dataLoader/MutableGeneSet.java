package cvrp.dataLoader;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import cvrp.dataStructures.Gene;

// This class is meant to behave like a set, although it's based on a list
// for simplicities sake. It provides classses in the Datastructures package
// a copy of the set of Gene's to be used in the problem. Genes can be removed
// random but not re added to the set. It's basically used for populating a
// Chromosone randomly
public class MutableGeneSet {

	private List<Gene> geneList;
	
	public MutableGeneSet(HashSet<Gene> geneSet) {
		geneList = new LinkedList<Gene>();
		geneList.addAll((HashSet<Gene>)geneSet.clone());
		Collections.shuffle(geneList);
	}
	
	public Gene getElement() {
		return geneList.remove(0);
	}
	
	public int size() {
		return geneList.size();
	}
	
	@Override
	public String toString() {
		return "MutableGeneSet [geneList=" + geneList + "]";
	}
}
