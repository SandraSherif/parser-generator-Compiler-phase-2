package parser;

import java.util.HashMap;
import java.util.Map.Entry;

import javafx.util.Pair;

import java.util.ArrayList;

public class ParsingTable {
	
	//each entry in 2D array is a Pair<Symbol,Prodution>
	//rows -> Non-Terminal LHS symbols
	//cols -> Terminal symbols
	public static Pair <String, String> [][] ParsingTable;
		
	
	@SuppressWarnings("unchecked")
	public static void EvaluateParsingTable(HashMap<String,ArrayList<String>> CFG,
											ArrayList<String> terminals,
											ArrayList<String> nonTerminals,
											HashMap<String,ArrayList<String>> first,
											HashMap<String,ArrayList<String>> follow){
		///should remove '' in each first & follow value
		
		//initialise 2D array
		ParsingTable = new Pair[nonTerminals.size()][terminals.size()];
		
		///// iterate through all procutions in CFG
//		for (Entry<String, ArrayList<String>> entry : CFG.entrySet()) {
		for (String entry : nonTerminals) {
		    
			String key = entry;
			ArrayList<String> val = CFG.get(key);
			
			//initialize row and col indices for a production 
			int rowIndex = nonTerminals.indexOf(key);
			int colIndex;
			
			//iterate through all possible RHSs in 1 production (ORs)
			for (String s:val){
				//check for epsilon in RHS
				if(!s.equals("\\L")){
					// take first of RHS of the production
					
					if(terminals.contains(s.split(" ")[0])){
						//if first char in RHS is terminal
						colIndex = terminals.indexOf(s.split(" ")[0]);
						ParsingTable[rowIndex][colIndex] = new Pair<String, String>(key,s);
						
						
					}else{
						
						ArrayList<String> firstArr = first.get(s.split(" ")[0]);
							
						for(String firstElement:firstArr){
							
							colIndex = terminals.indexOf(firstElement);
							ParsingTable[rowIndex][colIndex] = new Pair<String, String>(key,s);
							
						}
													
						
					}
						
					
					
				}else{
					//take follow of LHS of the production
					
					ArrayList<String> followArr = follow.get(key);
					for(String followElement:followArr){
						
						colIndex = terminals.indexOf(followElement);
						ParsingTable[rowIndex][colIndex] = new Pair<String, String>(key,s);
						
					}
					
				}		
		    
		}
		
		
		}
		
		
	}
	
	
	public static void ShowParsingTable(ArrayList<String> terminals,
										 ArrayList<String> nonTerminals){
		///more beautiful output by mainting the max length of a production
		String term ="\t";
		for(String s:terminals)
			term += s+"\t";
		System.out.println(term+"\n");
		
		for(int i=0; i<nonTerminals.size(); i++){
			String temp = nonTerminals.get(i)+"\t";
			for(int j=0; j<terminals.size();j++){
				
				if(ParsingTable[i][j] == null)
					temp += "none\t";
				else
					temp += ParsingTable[i][j] +"\t";
			}
			System.out.println(temp);
			System.out.println("\n");
		}
	}
	
	
	
	
}
