package ist.wv.minimalhittingsets;

import java.util.ArrayList;
import java.util.List;

import ist.wv.minimalhittingsets.gui.ResultPanel;
import ist.wv.minimalhittingsets.gui.elements.Circle;
import ist.wv.minimalhittingsets.gui.elements.Text;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class MinimalHittingSet {
	private Text text;
	private int elementCount;
	private List<Element> minimalHittingSet = new ArrayList<Element>();
	
	public MinimalHittingSet(Text text){
		this.text = text;
		elementCount = 0;
	}
	
	public void addElement(Element element){
		minimalHittingSet.add(element);
		ResultPanel.getInstance().addElement(element);
		ResultPanel.getInstance().redraw();
		elementCount++;
	}
	
	public void remove(){
		ResultPanel.getInstance().removeElement(text);
		for(Element e : minimalHittingSet)
			ResultPanel.getInstance().removeElement(e);
		
		ResultPanel.getInstance().redraw();
	}
	
	public Text getText(){
		return text;
	}

	public int getElementCount() {
		return elementCount;
	}
	
	public List<Integer> getValues(){
		List<Integer> values = new ArrayList<Integer>();
		
		for(Element e : minimalHittingSet){
			if(e.getClass().equals(Circle.class))
				values.add(((Circle)e).getFill());
		}
		
		return values;
	} 
}
