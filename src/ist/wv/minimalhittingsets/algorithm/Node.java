package ist.wv.minimalhittingsets.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int label;
	private boolean mark;
	private List<Integer> hittingSet = new ArrayList<Integer>();
	private Node parent;
	private List<Node> childs = new ArrayList<Node>();
	
	public Node(int label, Node parent){
		this.setLabel(label);
		this.parent = parent;
		this.mark = true;
	}
	
	boolean isInHittingSet(int val){
		for(int i : hittingSet){
			if(i == val)
				return true;
		}
		return false;
	}
	
	public boolean isSubset(Node n2) {
		List<Integer> h2 = new ArrayList<Integer>(n2.getHittingSet());
		for(Integer i : hittingSet){
			if(!h2.contains(i))
				return false;
		}
		return true;
	}
	
	public boolean nodeHittingSetExisting(Node n, int val) {
		List<Integer> h1 = new ArrayList<Integer>(n.getHittingSet());
		h1.add(val);
		if(hittingSet.containsAll(h1))
			return true;
		return false;
	}
	
	public void setMark(boolean mark){
		this.mark = mark;
	}
	
	public boolean getMark(){
		return mark;
	}

	public List<Integer> getHittingSet() {
		return hittingSet;
	}

	public void setHittingSet(List<Integer> hittingSet) {
		this.hittingSet = hittingSet;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
	public void addChild(Node child){
		childs.add(child);
	}
}
