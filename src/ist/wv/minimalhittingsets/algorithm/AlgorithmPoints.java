package ist.wv.minimalhittingsets.algorithm;

import ist.wv.minimalhittingsets.Gamehandler;
import ist.wv.minimalhittingsets.gui.AppletPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Algorithm;

public class AlgorithmPoints implements Algorithm {
	
	private Algorithm hittingSetAlgo;
	
	public AlgorithmPoints(){
	}

	@Override
	public void execute() {
		AppletPanel.getInstance().setPoints(Integer.toString(Gamehandler.getInstance().getPoints()));
		
		hittingSetAlgo = new HittingSetAlgorithm();
		hittingSetAlgo.execute();
	}
}
