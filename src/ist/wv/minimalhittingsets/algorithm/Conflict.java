package ist.wv.minimalhittingsets.algorithm;

public class Conflict {

	private int val1;
	private int val2;
	
	public Conflict(int val1, int val2){
		if(val1 < val2){
			this.val1 = val1;
			this.val2 = val2;
		}else{
			this.val1 = val2;
			this.val2 = val1;
		}
	}

	public int getVal(int i) {
		if(i == 0)
			return val1;
		else
			return val2;
	}
}
