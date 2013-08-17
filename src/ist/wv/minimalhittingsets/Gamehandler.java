package ist.wv.minimalhittingsets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ist.wv.minimalhittingsets.algorithm.AlgorithmPoints;
import ist.wv.minimalhittingsets.algorithm.AlgorithmTime;
import ist.wv.minimalhittingsets.algorithm.Conflict;
import ist.wv.minimalhittingsets.algorithm.HittingSet;
import ist.wv.minimalhittingsets.gui.AppletPanel;
import ist.wv.minimalhittingsets.gui.HittingSetPanel;
import ist.wv.minimalhittingsets.gui.ResultPanel;
import ist.wv.minimalhittingsets.gui.elements.Circle;
import ist.wv.minimalhittingsets.gui.elements.ConflictSet;
import ist.wv.minimalhittingsets.gui.elements.Text;
import ist.wv.minimalhittingsets.gui.interfaces.Algorithm;
import ist.wv.minimalhittingsets.gui.interfaces.GameModes;

public class Gamehandler {

	private static Gamehandler instance = null;
	private int level;
	private final int circlesize = 50;
	private final int linesize = 200;
	private final int conflictsets = 5; 
	private int minimalHittingSetCount = 1;
	private List<MinimalHittingSet> minimalHittingSets = new ArrayList<MinimalHittingSet>();
	private List<HittingSet> minimalHittingSet = new ArrayList<HittingSet>();
	private List<Conflict> conflictSet = new ArrayList<Conflict>();
	private int hittingValue = 0;
	private Algorithm algo;
	private int points;
	private boolean doubleSolve;
	private int foundSolutions;
	
	private Gamehandler(){
		level = 0;
		points = 0;
		doubleSolve = false;
		foundSolutions = 0;
	}
	
	public static Gamehandler getInstance(){
		if(instance == null)
			instance = new Gamehandler();
		return instance;
	}
	
	public void nextLevel(){
		conflictSet.clear();
		minimalHittingSet.clear();
		level++;
		//points = 0;
		doubleSolve = false;
		HittingSetPanel.getInstance().clearElements();
		
		for(int i = 1; i <= conflictsets; i++){
			addConflictSet(50, 60 * i);
		}
		HittingSetPanel.getInstance().redraw();
		
		// reset the minimal hitting sets
		minimalHittingSets.clear();
		minimalHittingSetCount = 1;
		ResultPanel.getInstance().clearElements();
		ResultPanel.getInstance().redraw();

		// solve the minimalHittingset 
		algo.execute();
		foundSolutions = minimalHittingSet.size();
	}
	
	public void newMHS(){
		MinimalHittingSet mhs = new MinimalHittingSet(new Text(10, 20 * minimalHittingSetCount, 
															   "MHS" + minimalHittingSetCount));
		minimalHittingSets.add(mhs);
		
		ResultPanel.getInstance().addElement(mhs.getText());
		ResultPanel.getInstance().redraw();
		
		minimalHittingSetCount++;
		
		checkGuessAndGivePoints();
	}
	
	public void reset(){
		if(minimalHittingSetCount == 1)
			return;
			
		MinimalHittingSet mhs = minimalHittingSets.get(minimalHittingSetCount - 2);
		mhs.remove();
		minimalHittingSets.remove(mhs);
		minimalHittingSetCount--;
	}
	
	public void add(){
		if(minimalHittingSetCount == 1){
			JOptionPane.showMessageDialog(null, "You need to add MHS first.", "Error Add", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(hittingValue == 0){
			JOptionPane.showMessageDialog(null, "You need to select a value.", "Error Select", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		MinimalHittingSet mhs = minimalHittingSets.get(minimalHittingSetCount - 2);
		for(Integer i : mhs.getValues()){
			if(i == hittingValue){
				JOptionPane.showMessageDialog(null, "Do not enter double values.", "Error Add", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
			
		
		mhs.addElement(new Circle(70 + (30 * mhs.getElementCount()),
				                  (20 * (minimalHittingSetCount - 1) - 5),
				                  20, 20, hittingValue, ResultPanel.getInstance()));
		
	}
	
	public void solve(){
		if(doubleSolve)
			return;
		
		ResultPanel.getInstance().clearElements();
		minimalHittingSets.clear();
		minimalHittingSetCount = 1;
		doubleSolve = true;
		points += 10;	// workaround
		
		for(HittingSet h : minimalHittingSet){
			newMHS();
			MinimalHittingSet mhs = minimalHittingSets.get(minimalHittingSetCount - 2);
			for(Integer hittingValue : h.getHittingSet())
				mhs.addElement(new Circle(70 + (30 * mhs.getElementCount()),
					                  	 (20 * (minimalHittingSetCount - 1) - 5),
					                      20, 20, hittingValue, ResultPanel.getInstance()));
		}
		ResultPanel.getInstance().redraw();
	}
	
	public void changeGameMode(String mode){
		if(algo != null && algo.getClass().equals(AlgorithmTime.class))
			((AlgorithmTime)algo).stopTimer();
		
		if(mode.equals(GameModes.POINTS))
			algo = new AlgorithmPoints();
		
		if(mode.equals(GameModes.TIME))
			algo = new AlgorithmTime();
		
		nextLevel();
	}
	
	private void addConflictSet(int x, int y){
		int val1 = generateConflictSet();
		int val2 = val1;
		while(val1 == val2)
			val2 = generateConflictSet();
		
		conflictSet.add(new Conflict(val1, val2));
		HittingSetPanel.getInstance().addElement(new ConflictSet(x, y, val1, val2));
	}
	
	private int generateConflictSet(){
		int min = 1;
		int max = 5;
		
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	private void checkGuessAndGivePoints(){
		if(minimalHittingSetCount <= 2)
			return;
		
		// check for match 
		boolean solutionFound = false;
		MinimalHittingSet mhs = minimalHittingSets.get(minimalHittingSetCount - 3);
		for(HittingSet h : minimalHittingSet){
			if(h.getHittingSet().size() == mhs.getValues().size() &&
			   h.getHittingSet().containsAll(mhs.getValues())){
				points += 10;
				solutionFound = true;
				
				// delete Entry - cheat protection
				//minimalHittingSet.remove(h);
				foundSolutions--;
				
				if(foundSolutions == 0){
					JOptionPane.showMessageDialog(null, "Awesome. You have won.\nContinue to next Level...", "Win", JOptionPane.OK_OPTION);
					nextLevel();
				}	
				break;
			}
		}
		
		/*if(!solutionFound){
			reset();
			reset();
		}*/
		
		// update points
		AppletPanel.getInstance().setPoints(Integer.toString(points));
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCirclesize() {
		return circlesize;
	}

	public int getLinesize() {
		return linesize;
	}

	public void setHittingValue(int hittingValue) {
		this.hittingValue = hittingValue;
	}

	public void setMinimalHittingSet(HittingSet h) {
		this.minimalHittingSet.add(h);
	}

	public List<Conflict> getConflictSet() {
		return conflictSet;
	}
	
	public int getPoints(){
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
