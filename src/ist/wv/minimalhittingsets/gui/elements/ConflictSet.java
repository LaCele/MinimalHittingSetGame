package ist.wv.minimalhittingsets.gui.elements;

import java.util.ArrayList;
import java.util.List;

import ist.wv.minimalhittingsets.Gamehandler;
import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class ConflictSet {

	private List<Element> elements = new ArrayList<Element>();
	private int val1;
	private int val2;

	public ConflictSet(int x, int y, int val1, int val2) {
		elements.add(new Circle(x, y, Gamehandler.getInstance().getCirclesize(), 
				        Gamehandler.getInstance().getCirclesize(), val1, 
				        HittingSetPanel.getInstance()));
		elements.add(new Circle(x + 100, y, Gamehandler.getInstance().getCirclesize(),
						Gamehandler.getInstance().getCirclesize(), val2, 
						HittingSetPanel.getInstance()));
		elements.add(new Line(0, y + 30, Gamehandler.getInstance().getLinesize(), y + 30));
	}

	public List<Element> getElements() {
		return elements;
	}

	public int getVal1() {
		return val1;
	}

	public int getVal2() {
		return val2;
	}
}
