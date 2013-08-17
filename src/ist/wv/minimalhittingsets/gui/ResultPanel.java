package ist.wv.minimalhittingsets.gui;

import ist.wv.minimalhittingsets.gui.interfaces.Element;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class ResultPanel extends PApplet {
	private static ResultPanel instance = null;
	private int height = 200;
	private int width = 200;
	private List<Element> elements = new ArrayList<Element>();

	private ResultPanel() {
	}

	public void setup() {
		size(this.width, this.height);
		background(255, 255, 255);
		noLoop();
	}

	public synchronized static ResultPanel getInstance() {
		if (instance == null)
			instance = new ResultPanel();
		return instance;
	}

	public void draw() {
		clear();
		noStroke();
		fill(255);
		rect(0, 0, this.width, this.height);
		stroke(0);
		textSize(16);
		fill(0, 102, 153);
		for(Element element : elements)
			element.draw();
	}
	
	public void clear(){
		background(255, 255, 255);
	}
	
	public void addElement(Element element){
		elements.add(element);
	}
	
	public void removeElement(Element element){
		elements.remove(element);
	}
	
	public void clearElements(){
		elements.clear();
	}
}
