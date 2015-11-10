package cvrp.dataLoader;

import java.io.IOException;
import java.util.HashSet;

import cvrp.dataStructures.Gene;

// A singleton object containing the set of Genes to be used in the problem. It can
// only be instated once and does not change once instantiated. But does provide a 
// getMutableGeneSet() method that provides a mutable copy of this set for populating
// data structures
public class GeneSet {
	
	private static GeneSet instance;
	private static HashSet<Gene> geneSet;
	private static int size;
	
	private GeneSet() {
		geneSet = new HashSet<Gene>();
	}
	
	public static GeneSet getInstance(){
	       if(instance == null){
	           instance = new GeneSet();
	           GeneSet.populateHashSet();
	       }
	       return instance;
	    }
	
	public static void populateHashSet() {
		String[] filename = new String[1];
		filename[0] = "fruitybun250.vrp";
		try {
			CVRPData.begin(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 1; i <= CVRPData.NUM_NODES; i++) {
			Gene gene = new Gene((int)CVRPData.getLocation(i).getX(), (int)CVRPData.getLocation(i).getY());
			geneSet.add(gene);
		}
		
		size = CVRPData.NUM_NODES;
	}
	
	public static MutableGeneSet getMutableGeneSet() {
		GeneSet.getInstance();
		return new MutableGeneSet(geneSet);
	}
	
	public static int size() {
		GeneSet.getInstance();
		return size;
	}

	@Override
	public String toString() {
		return "GeneSet [geneSet=" + geneSet + "]";
	}

}
