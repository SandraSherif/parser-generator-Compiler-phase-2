package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Follows {
	private ArrayList <String> terminals;
	private ArrayList <String> nonTerminals;
	private HashMap<String,ArrayList<String>> cfg;
	
	private HashMap<String,ArrayList<String>> follow;
	
	public Follows(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		this.follow =  new HashMap<String,ArrayList<String>>();
		this.terminals = terminals;
		this.nonTerminals = nonTerminals;
		this.cfg = cfg;
	}
	
	
}
