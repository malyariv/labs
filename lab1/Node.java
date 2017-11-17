package lab1;
/**
 * The class {@code Node} is a basic element to build a tree, like a Priority Queue, 
 * to calculate simple mathematical expressions
 */
import java.util.*;

class Node {
	
	/**a type of operator*/ 
	private Operator o;
	/** a priority of a node */
	private int priority;
	/** operands for operation depending on operator type */ 
	private List<Double> values=new ArrayList<>();
	/** pointer to the parent's node, i.e., one with lower priority */
	private Node parent;
	/** pointer to the children's nodes, i.e., ones with higher priority */
	private List<Node> children=new ArrayList<>();
	/** flag helps to divide base and exponent*/  
	private boolean powerFlag=true;
    /**
     * Constructs a <code>Node</code> and initializes some fields.  
     * @param o is a type of operator
     * @param p is a priority value
     */
	public Node(Operator o,int p) {
		this.o=o;
		priority=p;
	}
	
    /**
     * Adds a number to the list of operators of the current node.  
     * @param v is a value to be added
     */	
	public void addValue(Double v) {
		values.add(Double.valueOf(v));
	}
	
    /**
     * Returns the priority value of the current node.  
     */	
	public int getPriority() {
		return priority;
	}
	
    /**
     * Returns the parent's node of the current node.  
     */	
	public Node getParent() {
		return parent;
	}
	
    /**
     * Searches for a node with an appropriate priority value and returns it.
     * Otherwise, returns a node with minimal positive difference 
     * between its priority and desirable one, or returns the current node.    
     * @param p is a priority value of a node which it to be possessed 
     */
	public Node findNode(int p) {
		if (priority==p) return this;
		if (parent==null||parent.getPriority()<p) return this;
		return this.getParent().findNode(p);
	}

    /**
     * Calculates recursively the value of subtree from the current node 
     * and returns it.
     */
	public double getResult() {
		double result=(o==Operator.ADD)?0:1;
		switch (o) {
			case ADD: 
				for (Double d:values) result+=d; 
				for (Node n:children) result+=n.getResult();
				break;
			case MULTI:
				for (Double d:values) result*=d;
				for (Node n:children) result*=n.getResult();
				break;
			default:
				if(values.size()+children.size()>2) throw new MultiExponentException();
				if (values.size()==2) {
					result=Math.pow(values.get(0), values.get(1));
					break;
				}
				if (children.size()==2) {
					result=Math.pow(children.get(0).getResult(), children.get(1).getResult());
					break;
				}
				if (powerFlag) {
					result=Math.pow(values.get(0), children.get(0).getResult());
					}
				else result=Math.pow(children.get(0).getResult(), values.get(0));
		}
		return result;
	}
	
    /**
     * Removes a received node from the list of children's nodes of the current node.  
     * @param n is a node to be removed
     */		
	public void removeChild(Node n) {
		children.remove(n);
	}
	
    /**
     * Sets a received node as the parent's node of the current node.  
     * @param n is a node to be set as a parent one
     */		
	public void setParent(Node n) {
		parent=n;
	}
    /**
     * Adds a received node to the list of children's nodes of the current node.  
     * @param n is a node to be added
     */	
	public void addChild(Node n) {
		children.add(n);
		if (values.size()==0) powerFlag=false;
	}
    /**
     * Generates a string containing some useful information about the current node.  
     */		
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
