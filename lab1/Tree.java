package lab1;
/**
 * The class {@code Tree} provides calculation of simple mathematical expressions
 * by building of modified priority queue with minimum from elements of class {@code Node}.   
 */

import java.util.*;

class Tree {
	/** an expression to be calculated*/
	private String expression;
	/** an array of operands */ 
	private double[] operandNum;
	/** arrays containing positions of opening and closing round brackets */
	private int[] openBrackets, closeBrackets;
	/** flag indicates if the expression starts from minus */
	private boolean flag=false;
	
	/** a list of operators */
	private List<String> listOfOperators=new ArrayList<>();
	/** a list of operators priorities */
	private List<Integer> listOfPriorities=new ArrayList<>();
	/** a list of operators positions */
	private List<Integer> position=new ArrayList<>();
	
    /**
     * Constructs a <code>Tree</code>. 
     * The constructor also checks if the expression starts with + or -.  
     * @param ex is an expression to be calculated 
     */
	public Tree(String ex) {
		if (ex.startsWith("+")) expression=ex.substring(1);
		else {
			if (ex.startsWith("-")) {
				expression=ex.substring(1);
				flag=true;
			}
			else expression=ex;
		}
	}
	
	/**
     * Initializes some fields depending on the field "expression" value
     * using static methods from the utility class {@code MathParser}.
     * Checks the spelling of the expression.
     */
	public String check() {
		try{
			operandNum=MathParser.getOperands(expression);
			listOfOperators=MathParser.getOperatorsStr(expression);
			openBrackets=MathParser.findSymbolIndices('(', expression);
			closeBrackets=MathParser.findSymbolIndices(')', expression);			
		}catch(Exception e) {return "Wrong number format";}
		char c=expression.charAt(expression.length()-1);
		if (Character.isLetter(c)) c=Character.toLowerCase(c);
		if (!Character.isDigit(c)&&c!=')'&&c!='a'&&c!='b'&&c!='c'&&c!='d'&&c!='e'&&c!='f') return "Wrong operators";
		for (String s:listOfOperators) {
			if (s.length()>1) return "Wrong operators";
		}
		Deque<Character> brackets=new LinkedList<>();
		for (char ch:expression.toCharArray()) {
			if (ch=='(') brackets.push(c);
			if (ch==')'&&brackets.isEmpty()) return "Wrong brackets";
			if (ch==')'&&!brackets.isEmpty()) brackets.pop();
		}
		if (!brackets.isEmpty()) return "Wrong brackets";
		return "Ok";
	}
	
	/**
     * Finds operators position in the expression
     */
	private void findOperatorPosition() {
		int index=0;
		for (int i=0;i<listOfOperators.size();i++) {
			position.add(expression.indexOf(listOfOperators.get(i), index));
			index=position.get(i)+1;
		}

	}
	
	/**
     * Provides calculation with negative numbers, e.g., 1+(-1)  
     * Simplifies calculation by changing - to + and / to *.
     */
	private void simplify() {
		List<String> list=new ArrayList<>();
		if (flag) {
			operandNum[0]=-operandNum[0];
		}
		if (listOfOperators.size()>=operandNum.length) {
			for (int i=0;i<listOfOperators.size();i++) {
				if (expression.charAt(position.get(i)-1)=='(') {
					operandNum[i]=(expression.charAt(position.get(i))=='-')? -operandNum[i]:operandNum[i];
					listOfOperators.remove(i);
					position.remove(i);
					i--;
				}
			}
		}
		for (int i=0;i<listOfOperators.size();i++) {
			if (listOfOperators.get(i).equals("-")) {
				list.add("+");
				operandNum[i+1]=-operandNum[i+1];
			}else{
				if (listOfOperators.get(i).equals("/")){
					list.add("*");
					operandNum[i+1]=1/operandNum[i+1];
				}
				else list.add(listOfOperators.get(i));
			}			
		}
		listOfOperators=list;
	}
	
	/**
     * Provides calculation of operator priority in method getPriorities()
     * @param pos is operator position in expression  
     */	
	private int getMulti(int pos) {
		int open=0;
		int close=0;
		for (int i:openBrackets) {
			if (pos>i) open++;
		}
		for (int i:closeBrackets) {
			if (pos>i) close++;
		}
		return open-close;
	}
	
	/**
     * Calculates the operator priority
     */	
	private void getPriorities() {
		for (int i=0;i<listOfOperators.size();i++) {
			if (listOfOperators.get(i).equals("+")) 
				listOfPriorities.add(2*getMulti(position.get(i)));
			else listOfPriorities.add(2*getMulti(position.get(i))+1);
		}
	}
	
	/**
     * Builds a tree from the list of operators depending on their priority using elements of class {@code Node}.
     * Returns a node corresponding to the last operator. 
     */	
	private Node buildTree() {
		Node node=null;
		Operator o=null;
		for (int i=0; i<listOfOperators.size();i++) {
			o=listOfOperators.get(i).equals("+")? Operator.ADD: Operator.MULTI;
			int pr=listOfPriorities.get(i);
			if (node==null) {
				node=new Node(o, pr);
				node.addValue(operandNum[i]);				
			}
			else {		
				if (node.getPriority()==pr) {
					node.addValue(operandNum[i]);
				}
				else {
					if (node.getPriority()<pr){
						Node n=new Node(o, pr);
						n.addValue(operandNum[i]);
						n.setParent(node);
						node.addChild(n);
						node=n;
					}
					else {
						node.addValue(operandNum[i]);
						node=node.findNode(pr);
						if (node.getPriority()==pr) continue;
						else {
							Node n=new Node(o, pr);
							n.addChild(node);
							n.setParent(node.getParent());
							if (node.getParent()!=null) {
								n.getParent().removeChild(node);
								n.getParent().addChild(n);
							}
							node.setParent(n);
							node=n;
						}
					}
				}
			}
		}
		node.addValue(operandNum[operandNum.length-1]);
		return node;
	}
	/**
     * Returns the root of the builded tree, i.e., element of class {@code Node} with the lowest priority.
     * @param node is some node in the tree 
     */		
	private Node getRoot(Node node) {
		if (node.getParent()==null) return node;
		Node n=node.getParent();
		return getRoot(n);
	}
	
	/**
     * Executes supporting methods.
     * Calculates the expression value. 
     */
	public double calculate() {
		findOperatorPosition();
		simplify();
		getPriorities();
		Node n=buildTree();
		n=getRoot(n);
		return n.getResult();
	}

}
