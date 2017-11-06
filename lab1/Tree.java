package lab1;
import java.util.*;

public class Tree {
	private String expression;
	private String[] operandStr, operators;
	private double[] operandNum;
	private int[] position, openBrackets, closeBrackets;
	private boolean flag=false;
	
	private List<String> listOfOperators=new ArrayList<>();
	private List<String> listOfOperands=new ArrayList<>();
	private List<Integer> listOfPriorities=new ArrayList<>();
	
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
	private void parsing() {
		operandStr=expression.split("\\+|-|\\*|\\/|\\(|\\)");
//		System.out.println(String.join(" ",operandStr));
		for (String s:operandStr) {
			if (s.length()!=0) listOfOperands.add(s);
		}
//		System.out.println(listOfOperands.toString());
		operandNum=new double[listOfOperands.size()];
		for (int i=0; i<listOfOperands.size();i++) {
			operandNum[i]=Double.valueOf(listOfOperands.get(i));
		}
		if (flag) operandNum[0]=-operandNum[0];
		operators=expression.split("\\d|\\(|\\)");
//		System.out.println(Arrays.toString(operandNum));
//		System.out.println("<<"+operators[0].length()+">>");
//		System.out.println(String.join(" ",operators));
//		List<String> l=Arrays.asList(operators);
//		System.out.println(l.toString());
		
		
	}

	public boolean check() {
		parsing();
		if (expression.endsWith("\\+|-|\\*|\\/")) return false;
		for (String s:operators) {
			if (s.length()>1) return false;
			if (s.length()==1&&!s.equals(".")) listOfOperators.add(s);
		}		
		//проверить, что длину operators. должна быть 1. Если 0, то удалить, 2 - ошибка. Строка
		//Строка expression не должна заканчиваться */+-
//		System.out.println(listOfOperators.toString());
		return true;
	}
	
	private void findOperatorPosition() {
		position=new int[listOfOperators.size()];
		int index=0;
		for (int i=0;i<position.length;i++) {
			position[i]=expression.indexOf(listOfOperators.get(i), index);
			index=position[i]+1;
		}
//		System.out.println(Arrays.toString(position));

	}
	
	private void findBrackets() {
		String[] s=expression.split("\\("); 
		int number=s.length-1;
		openBrackets=new int[number];
		closeBrackets=new int[number];
		int index=0;
		for (int i=0;i<openBrackets.length;i++) {
			openBrackets[i]=expression.indexOf('(', index);
			index=openBrackets[i]+1;
		}
		index=0;
		for (int i=0;i<closeBrackets.length;i++) {
			closeBrackets[i]=expression.indexOf(")", index);
			index=closeBrackets[i]+1;
		}
//		System.out.println(Arrays.toString(openBrackets));
//		System.out.println(Arrays.toString(closeBrackets));
	}
	private void simplify() {
		List<String> list=new ArrayList<>();
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
//		System.out.println(Arrays.toString(operandNum));
		listOfOperators=list;
		
		
	}
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
	
	private void getPriorities() {
		for (int i=0;i<listOfOperators.size();i++) {
			if (listOfOperators.get(i).equals("+")) listOfPriorities.add(2*getMulti(position[i]));
			else listOfPriorities.add(2*getMulti(position[i])+1);
		}
//		System.out.println("Приоритеты ");
//		System.out.println(listOfPriorities.toString());
	}
	
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
	
	private Node getRoot(Node node) {
		if (node.getParent()==null) return node;
		Node n=node.getParent();
		return getRoot(n);
	}

	public double calculate() {
		findOperatorPosition();
		findBrackets();
		simplify();
		getPriorities();
		Node n=buildTree();
//		System.out.println(n.toString());
		n=getRoot(n);
//		System.out.println(n.toString());
		
		return n.getResult();
	}

}
