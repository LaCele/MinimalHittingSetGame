package ist.wv.minimalhittingsets.gui.elements;

import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.ResultPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Element;

public class Text implements Element{

	private int x_pos;
	private int y_pos;
	private String text;
	
	public Text(int x_pos, int y_pos, String text){
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.setText(text);
	}
	
	public void draw(){
		ResultPanel.getInstance().fill(0, 102, 153);
		ResultPanel.getInstance().text(text, x_pos, y_pos);
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
