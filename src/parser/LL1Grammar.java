package parser;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class LL1Grammar {
	protected ArrayList <String> terminals;
	protected ArrayList <String> nonTerminals;
	protected HashMap<String,ArrayList<String>> cfg;
	
	public LL1Grammar(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		this.terminals = terminals;
		this.nonTerminals = nonTerminals;
		this.cfg = cfg;
	}
}
