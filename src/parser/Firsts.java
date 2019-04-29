package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
public class Firsts extends LL1Grammar{
	
	private HashMap<String,ArrayList<String>> first;
	
	public Firsts(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		super(terminals,nonTerminals,cfg);
		this.first =  new HashMap<String,ArrayList<String>>();
	}
	private boolean isTerminal(String s){
		return terminals.contains(s)? true:false;
	}
	private boolean isNonTerminal(String s){
		return nonTerminals.contains(s)? true:false;
	}
	
	private void dealingWithNonTerminalToken(ArrayList<String> certainTerminalFirsts,String token){
		if(!certainTerminalFirsts.contains(token))
			certainTerminalFirsts.add(token);
	}
	private void dealingWithTerminalToken(Stack <String>stack,ArrayList<String> certainTerminalFirsts,String token){
		//in case of recursive check presence of epsilon later
		if(!first.get(token).contains("\\L")){
			stack.clear();
			stack.push("$");
		}
		for(int h=first.get(token).size()-1;h>=0;h--)
			stack.push(first.get(token).get(h));
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
				//check for recursion in token ex : Term = Term
				if(arr.length==1&&arr[0].equals(terminals.get(i))){
					 if(!certainTerminalFirsts.contains("\\L"))
							 certainTerminalFirsts.add("\\L");
					continue;
				}
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
						dealingWithNonTerminalToken(certainTerminalFirsts, token);
					}
					
					//terminal token
					else if(isTerminal(token)){
						if(first.containsKey(token))
							dealingWithTerminalToken(stack, certainTerminalFirsts, token);
						else
							break;
					}
					
					//epsilons
					else if(token.equals("\\L")&&stack.peek().equals("$")){
						if(!certainTerminalFirsts.contains(token)){
							certainTerminalFirsts.add(token);
							break;
						}
					}
					
				}
			}
			first.put(terminals.get(i), certainTerminalFirsts);
		}
		return first;
	}
	
}
