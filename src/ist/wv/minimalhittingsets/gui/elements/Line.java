package ist.wv.minimalhittingsets.gui.elements;

import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class Line implements Element{

	private int x_pos;
	private int y_pos;
	private int width;
	private int height;
	
	public Line(int x_pos, int y_pos, int width, int height){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.width = width;
		this.height = height;
	}
	
	public void draw(){
		HittingSetPanel.getInstance().line(x_pos, y_pos, width, height);
	}

	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
