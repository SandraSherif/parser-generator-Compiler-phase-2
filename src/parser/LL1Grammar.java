package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public abstract class LL1Grammar {
	protected ArrayList <String> terminals;
	protected ArrayList <String> nonTerminals;
	protected HashMap<String,ArrayList<String>> cfg;
	protected static HashMap<String,ArrayList<String>> first;
	protected static HashMap<String,ArrayList<String>> follow;
	
	public static boolean isLL1=true; 
	public LL1Grammar(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		this.terminals = terminals;
		this.nonTerminals = nonTerminals;
		this.cfg = cfg;
	}
	protected boolean isTerminal(String s){
		return terminals.contains(s)? true:false;
	}
	
	protected boolean isNonTerminal(String s){
		return nonTerminals.contains(s)? true:false;
	}
	protected void dealingWithTerminalToken(ArrayList<String> certainTerminals,String token){
		if(!certainTerminals.contains(token))
			certainTerminals.add(token);
	}
	protected void dealingWithNonTerminalToken(Stack <String>stack,String token){
		//in case of recursive check presence of epsilon later
		if(!first.get(token).contains("\\L")){
			stack.clear();
			stack.push("$");
		}
		for(int h=first.get(token).size()-1;h>=0;h--)
			stack.push(first.get(token).get(h));
	}
}
