package lab1;

import java.util.*;

public class Node {
	private Operator o;
	private int priority;
	private List<Double> values=new ArrayList<>();
	private Node parent;
	private List<Node> children=new ArrayList<>();
	
	
	public Node(Operator o,int p) {
		this.o=o;
		priority=p;
	}
	public void addValue(Double v) {
		values.add(Double.valueOf(v));
	}
	public int getPriority() {
		return priority;
	}
	
	public Node getParent() {
		return parent;
	}

	public Node findNode(int p) {
		if (priority==p) return this;
		if (parent==null||parent.getPriority()<p) return this;
		return this.getParent().findNode(p);
	}
	
	public double getResult() {
		System.out.println(toString());
		double result=(o==Operator.ADD)?0:1;
		if (o==Operator.ADD) {
			for (Double d:values) result+=d;
			for (Node n:children) result+=n.getResult();
		}
		else {
			for (Double d:values) result*=d;
			for (Node n:children) result*=n.getResult();
		}
		return result;
	}
	
	public void removeChild(Node n) {
		children.remove(n);
	}
	public void setParent(Node n) {
		parent=n;
	}
	public void addChild(Node n) {
		children.add(n);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Operator is ");
		sb.append(o);
		sb.append("\n");
		sb.append("Priority is ");
		sb.append(priority);
		sb.append("\n");
		if (parent==null) sb.append("no parent");
		else {
			sb.append("there is a parent with priority ");
			sb.append(parent.getPriority());
		}
		sb.append("\n");
		if (children.isEmpty()) sb.append("no children");
		else {
			sb.append("there are ");
			sb.append(children.size());
			sb.append(" children");
			sb.append("\n");
		}
		sb.append(values.toString());
		return sb.toString(); 
	}

}
