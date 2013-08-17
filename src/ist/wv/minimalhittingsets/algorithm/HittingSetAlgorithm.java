package ist.wv.minimalhittingsets.algorithm;

import java.util.ArrayList;
import java.util.List;

import ist.wv.minimalhittingsets.Gamehandler;
import ist.wv.minimalhittingsets.gui.AppletPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Algorithm;

public class HittingSetAlgorithm implements Algorithm {
	
	private List<Node> L1 = new ArrayList<Node>();
	private List<Node> L2 = new ArrayList<Node>();
	private List<Node> H = new ArrayList<Node>();
	private List<Node> nodes = new ArrayList<Node>();
	private int label;
	
	public HittingSetAlgorithm(){
		label = 0;
	}

	@Override
	public void execute() {
		L1.clear();
		L2.clear();
		H.clear();
		nodes.clear();
		label = 0;
		
		// create rootnode
		Node root = new Node(label++, null);
		L1.add(root);
		nodes.add(root);
		
		System.out.println("----solving Minimal Hittingset----");
		// print conflicts
		for (Conflict c : Gamehandler.getInstance().getConflictSet())
			System.out.println("conflicts = " + c.getVal(0) + " " + c.getVal(1));
		
		while (true) {
			for (Node n : L1) {
				
				// search for intersection
				boolean conflictExists = false;
				for (Conflict c : Gamehandler.getInstance().getConflictSet()) {
					if (!n.isInHittingSet(c.getVal(0)) && !n.isInHittingSet(c.getVal(1))) {
						conflictExists = true;
						
						for(int i = 0; i < 2; i++){
							
							boolean nodeExists = false;
							for(Node m : nodes){
								if(m.getLabel() != n.getLabel() && m.nodeHittingSetExisting(n, c.getVal(i))){
									// Generate a new arc from n to m.
									n.addChild(m);
									nodeExists = true;
								}
							}
		
							if(!nodeExists){
								Node n2 = new Node(label++, n);
						
								List<Integer> h1 = new ArrayList<Integer>(n.getHittingSet());
								if(!h1.contains(c.getVal(i)))
									h1.add(c.getVal(i));
								n2.setHittingSet(h1);
								
								for(Node m : H){
									if(m.getLabel() != n2.getLabel() && m.isSubset(n2))
										n2.setMark(false);
									
								}
								if(n2.getMark())
									L2.add(n2);
								
								nodes.add(n2);
								n.addChild(n2);
							}
						}
						break;
					}
				}
				
				if(!conflictExists){
					boolean isMinimal = true;
					
					for(Node m : H){
						if(m.isSubset(n))
							isMinimal = false;
					}
					if(isMinimal){
						n.setMark(true);
						H.add(n);
					}
				}
			}

			if (!L2.isEmpty()) {
				L1.clear();
				for(int i = 0; i < L2.size(); i++)
					L1.add(L2.get(i));
				
				L2.clear();
			} else {
				break;
			}
		}
		
		System.out.println("--------------------------------------");
		for(Node n : H){
			if(n.getMark()){
				List<Integer> list = n.getHittingSet();
				Gamehandler.getInstance().setMinimalHittingSet(new HittingSet(list));
				System.out.println("minimal hitting set:" + list.toString());
			}
		} 
	}
}
