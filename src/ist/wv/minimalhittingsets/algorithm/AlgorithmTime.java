package ist.wv.minimalhittingsets.algorithm;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ist.wv.minimalhittingsets.Gamehandler;
import ist.wv.minimalhittingsets.gui.AppletPanel;
import ist.wv.minimalhittingsets.gui.interfaces.Algorithm;

public class AlgorithmTime implements Algorithm {

	private Timer timer;
	private int time;
	private Algorithm hittingSetAlgo;
	
	public AlgorithmTime(){
		initialiseTimer();
	}
	
	@Override
	public void execute() {
		time = 0;
		timer.start();
		
		hittingSetAlgo = new HittingSetAlgorithm();
		hittingSetAlgo.execute();
	}
	
	private void initialiseTimer(){
		timer = new Timer(0, null); 
		timer.stop();
		time = 0;
		timer.setDelay(1000);
		
		AppletPanel.getInstance().setTime(Integer.toString(time));
		
		ActionListener timerinterval = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AppletPanel.getInstance().setTime(Integer.toString(time++));
				
				int hardness = 10 - Gamehandler.getInstance().getLevel();
				if(Gamehandler.getInstance().getLevel() >= 10)
					hardness = 1;
				
				if((time % hardness) == 0){
					int points = Gamehandler.getInstance().getPoints();
					
					if(points > 0){
						if(hardness != 1)
							points -= 1;
						else
							points -= Gamehandler.getInstance().getLevel();
						Gamehandler.getInstance().setPoints(points);
					}
					AppletPanel.getInstance().setPoints(Integer.toString(points));
				}
			}
		};
		
		timer.addActionListener(timerinterval);
		AppletPanel.getInstance().getTime().addActionListener(timerinterval);
	}
	
	public void stopTimer(){
		timer.stop();
	}

}
