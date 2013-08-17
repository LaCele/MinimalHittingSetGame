package ist.wv.minimalhittingsets.gui.elements;

import processing.core.PApplet;
import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class Circle implements Element{

	private int x_pos;
	private int y_pos;
	private int width;
	private int height;
	private int fill;
	private PApplet instance;
	
	public Circle(int x_pos, int y_pos, int width, int height, int fill, PApplet instance){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.width = width;
		this.height = height;
		this.fill = fill;
		this.instance = instance;
	}
	
	public void draw(){
		switch(fill){
			case 1: instance.fill(107, 114, 247); break; //BLUE
			case 2: instance.fill(236,45,89); break;     //RED
			case 3: instance.fill(244,244, 42); break;   //YELLOW
			case 4: instance.fill(115, 188, 38); break;  //GREEN
			case 5: instance.fill(246, 139, 25); break;  //ORANGE
		}
		instance.ellipse(x_pos, y_pos, width, height);
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

	public int getFill() {
		return fill;
	}
}
