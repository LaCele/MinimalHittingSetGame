package ist.wv.minimalhittingsets.algorithm;

import java.util.ArrayList;
import java.util.List;

public class HittingSet {

	private List<Integer> hittingSet = new ArrayList<Integer>();
	
	public HittingSet(){
	}
	
	public HittingSet(List<Integer> list){
		this.hittingSet = list;
	}
	
	public void add(int val){
		hittingSet.add(val);
	}
	
	public List<Integer> getHittingSet(){
		return hittingSet;
	}
}
