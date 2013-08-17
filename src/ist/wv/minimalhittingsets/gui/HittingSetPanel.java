package ist.wv.minimalhittingsets.gui;

import ist.wv.minimalhittingsets.gui.elements.Circle;
import ist.wv.minimalhittingsets.gui.elements.ConflictSet;
import ist.wv.minimalhittingsets.gui.elements.Mark;
import ist.wv.minimalhittingsets.gui.elements.Point;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class HittingSetPanel extends PApplet {
	private static HittingSetPanel instance = null;
	private int height = 500;
	private int width = 200;
	private List<Element> elements = new ArrayList<Element>();

	private HittingSetPanel() {
	}

	public void setup() {
		size(this.width, this.height);
		background(255, 255, 255);
		noLoop();
	}

	public synchronized static HittingSetPanel getInstance() {
		if (instance == null)
			instance = new HittingSetPanel();
		return instance;
	}

	public void draw() {
		clear();
		noStroke();
		fill(255);
		rect(0, 0, this.width, this.height);
		stroke(0);
		for(Element element : elements)
			element.draw();
	}
	
	public void clear(){
		background(255, 255, 255);
	}
	
	public void addElement(ConflictSet conflict){
		List<Element> confelements = conflict.getElements();
		for(Element e : confelements)
			elements.add(e);
	}
	
	public void removeElement(Element element){
		elements.remove(element);
	}
	
	public void clearElements(){
		elements.clear();
	}
	
	public int isElementClicked(){
		Point x1 = new Point(0,0);
		Point x2 = new Point(0,0);
		Point x3 = new Point(0,0);
		Point x4 = new Point(0,0);
		
		for(Element e : elements){
			if(e.getClass() == Circle.class){
				
				// get corner points
				x1.setX(((Circle)e).getX_pos() - ((Circle)e).getWidth() / 2);
				x1.setY(((Circle)e).getY_pos() - ((Circle)e).getHeight() / 2);
				x2.setX(((Circle)e).getX_pos() + ((Circle)e).getWidth() / 2);
				x2.setY(((Circle)e).getY_pos() - ((Circle)e).getHeight() / 2);
				x3.setX(((Circle)e).getX_pos() + ((Circle)e).getWidth() / 2);
				x3.setY(((Circle)e).getY_pos() + ((Circle)e).getHeight() / 2);
				x4.setX(((Circle)e).getX_pos() - ((Circle)e).getWidth() / 2);
				x4.setY(((Circle)e).getY_pos() + ((Circle)e).getHeight() / 2);
				
				
				if(mouseX > x1.getX() && mouseY > x1.getY() &&
				   mouseX < x2.getX() && mouseY > x2.getY() &&
				   mouseX < x3.getX() && mouseY < x3.getY() &&
				   mouseX > x4.getX() && mouseY < x4.getY()){
					markElement(x1, x2, x3, x4);
					return ((Circle)e).getFill();
				}
			}
		}
		return 0;
	}
	
	public void markElement(Point x1, Point x2, Point x3, Point x4){
		for(Element e : elements){
			if(e.getClass().equals(Mark.class)){
				elements.remove(e);
				break;
			}
		}
			
		elements.add(new Mark(x1, x2, x3, x4));
		redraw();
	}
}
