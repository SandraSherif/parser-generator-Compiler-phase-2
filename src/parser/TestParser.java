package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestParser {
	final static String filePath = "CFG grammer2.txt";
	public static void main(String[] args) {		
		CFGrammer dealWithGrammer = new CFGrammer(filePath);
		try {
			dealWithGrammer.dealWithFile();
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		
		// Note : \L indicates epsilons 
		ArrayList <String> terminals = dealWithGrammer.getTerminals();
		ArrayList <String> nonTerminals = dealWithGrammer.getNonTerminals();
		HashMap<String,ArrayList<String>> cfg = dealWithGrammer.getCfg();	
		System.out.println("Terminals:\n"+terminals);
		System.out.println("non Terminals:\n"+nonTerminals);
		System.out.println("cfg:\n"+cfg);
		System.out.println("--------------------------------------------------------------------");
		
		
		Firsts firsts = new Firsts(terminals,nonTerminals,cfg);
		HashMap<String,ArrayList<String>> first = firsts.computeFirsts();
		System.out.println("First");
		System.out.println(first);
		 
		 
	}
	


}
