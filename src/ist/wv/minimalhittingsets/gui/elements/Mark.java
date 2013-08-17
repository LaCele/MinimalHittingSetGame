package ist.wv.minimalhittingsets.gui.elements;

import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class Mark implements Element{

	private Point x1;
	private Point x2;
	private Point x3;
	private Point x4;
	private int width;
	private int height;
	
	public Mark(Point x1, Point x2, Point x3, Point x4){
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.width = 5;
		this.height = 5;
	}
	
	public void draw(){
		HittingSetPanel.getInstance().fill(0, 0, 0);
		HittingSetPanel.getInstance().rect(x1.getX(), x1.getY(), width, height);
		HittingSetPanel.getInstance().rect(x2.getX() - 5, x2.getY(), width, height);
		HittingSetPanel.getInstance().rect(x3.getX() - 5, x3.getY() - 5, width, height);
		HittingSetPanel.getInstance().rect(x4.getX(), x4.getY() - 5, width, height);
	}
}
