package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Follows extends LL1Grammar{
	
	private HashMap<String,ArrayList<String>> follow;
	
	public Follows(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		super(terminals,nonTerminals,cfg);
		this.follow =  new HashMap<String,ArrayList<String>>();
	}
	
	
}
