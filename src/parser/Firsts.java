package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
public class Firsts{
	private ArrayList <String> terminals;
	private ArrayList <String> nonTerminals;
	private HashMap<String,ArrayList<String>> cfg;
	
	private HashMap<String,ArrayList<String>> first;
	
	public Firsts(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		this.first =  new HashMap<String,ArrayList<String>>();
		this.terminals = terminals;
		this.nonTerminals = nonTerminals;
		this.cfg = cfg;
	}
	public boolean isTerminal(String s){
		return terminals.contains(s)? true:false;
	}
	public boolean isNonTerminal(String s){
		return nonTerminals.contains(s)? true:false;
	}
	
	public  HashMap<String,ArrayList<String>> computeFirsts(){
		ArrayList<String> temp;
		ArrayList<String> certainTerminalFirsts;
		Stack <String>stack;
		for(int i=terminals.size()-1;i>=0;i--){
			// ex: temp = [DECLARATION, IF, WHILE, ASSIGNMENT]
			temp = cfg.get(terminals.get(i));
			certainTerminalFirsts = new ArrayList <String>();
			
			//ex : e = DECLARATION
			for(String e: temp){
				stack = new Stack<String>();
				stack.push("$");
				int checkFirstToken=0;
				//split each token e in case there are spaces 
				String [] arr = e.split(" ");
				for(int j=arr.length-1;j>=0;j--)
					stack.push(arr[j]);
				
				while(!stack.peek().equals("$")){
					String token = stack.pop();
					
					//if starts with a non-terminal
					if(checkFirstToken==0&&isNonTerminal(token)){
						if(!certainTerminalFirsts.contains(token))
							certainTerminalFirsts.add(token);
						break;
					}	
					else
						checkFirstToken=1;
					
					
					//if non terminal token
					if(isNonTerminal(token)){
						if(!certainTerminalFirsts.contains(token))
							certainTerminalFirsts.add(token);
					}
					//terminal token
					else if(isTerminal(token)){
						if(first.containsKey(token)){
							//in case of recursive check presence of epsilon later
							if(!first.get(token).contains("\\L")){
								stack.clear();
								stack.push("$");
							}
							for(int h=first.get(token).size()-1;h>=0;h--)
								stack.push(first.get(token).get(h));
						}
						else
							break;
					}
					//epsilon
					else if(token.equals("\\L")){
						if(stack.peek().equals("$")){
							 if(!certainTerminalFirsts.contains(token)){
								certainTerminalFirsts.add(token);
								break;
							}
						}
						
					}
				}
			} 
			first.put(terminals.get(i), certainTerminalFirsts);
		}
		return first;
	}
	

}
