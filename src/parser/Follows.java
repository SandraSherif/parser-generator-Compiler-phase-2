package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Follows extends LL1Grammar{
	
	
	public Follows(ArrayList <String> terminals,ArrayList <String> nonTerminals,HashMap<String,ArrayList<String>> cfg){
		super(terminals,nonTerminals,cfg);
		follow =  new HashMap<String,ArrayList<String>>();
		initializeFollows();
		
	}
	private void initializeFollows(){
		//starting of the grammar always have no follows so its initialized with $
		follow.put(nonTerminals.get(0), new ArrayList<String>());
		follow.get(nonTerminals.get(0)).add("$");
	}
	
	public  HashMap<String,ArrayList<String>>  computeFollows(){ 
		ArrayList<String> temp;
		ArrayList<String> certainTerminalFollows;
		Stack <String>stack = null;
		for(int i=1;i<nonTerminals.size();i++){
			String nonTerminal = nonTerminals.get(i);
			certainTerminalFollows = new ArrayList <String>();
			//loop using each terminal on all other terminals 
			for(int j=0;j<nonTerminals.size();j++){
				String currentnonTerminalChecking = nonTerminals.get(j);
				temp = cfg.get(currentnonTerminalChecking);
				//skip terminal itself RE
				if(nonTerminal.equals(currentnonTerminalChecking))
					continue;
				
				//check presence of terminal in RE's
				for(String ter: temp){
					int checkFirstToken=0;
					String [] arr =ter.split(" ");
					//terminal not found in this expression
					if(!Arrays.asList(arr).contains(nonTerminal))
						continue;
					//token found in this RE but at the end so follow are added
					if(Arrays.asList(arr).indexOf(nonTerminal)==arr.length-1){
						for(String e:follow.get(currentnonTerminalChecking) ){
							if(!certainTerminalFollows.contains(e))
								certainTerminalFollows.add(e);
						}
						continue;
					}
					stack = new Stack<String>();
					stack.push("$");
					for(int k=arr.length-1;k>=Arrays.asList(arr).indexOf(nonTerminal)+1;k--)
						stack.push(arr[k]);
					
					
					while(!stack.peek().equals("$")){
						String token = stack.pop();
						//if starts with a terminal
						if(checkFirstToken==0&&isTerminal(token)){
							if(!certainTerminalFollows.contains(token))
								certainTerminalFollows.add(token);
							break;
						}	
						checkFirstToken=1;
						//if non terminal token
						if(isTerminal(token)){
							dealingWithTerminalToken(certainTerminalFollows, token);
						}
						//terminal token
						else if(isNonTerminal(token)){
							if(first.containsKey(token))
								dealingWithNonTerminalToken(stack, token);
							else
								break;
						}
						else if(stack.peek().equals("$")){
							for(String e:follow.get(currentnonTerminalChecking) ){
								if(!certainTerminalFollows.contains(e))
									certainTerminalFollows.add(e);
							}
						}
					}
				}
			}
			follow.put(nonTerminal, certainTerminalFollows);
		}
		return follow;
	}
	
	
}
