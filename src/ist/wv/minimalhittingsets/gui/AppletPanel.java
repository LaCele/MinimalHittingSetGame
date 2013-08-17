package ist.wv.minimalhittingsets.gui;

import ist.wv.minimalhittingsets.Gamehandler;
import ist.wv.minimalhittingsets.gui.interfaces.GameModes;

import javax.swing.JApplet;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class AppletPanel extends JApplet {

	private JPanel panel; 
	private JTextField points;
	private static AppletPanel instance;
	private JTextField time;
	
	/**
	 * Create the applet.
	 */
	public AppletPanel() {
		instance = this;
		initGui();
		initProcessing();
		
		Gamehandler.getInstance().changeGameMode(GameModes.POINTS);
	}
	
	public void initGui(){
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.add(HittingSetPanel.getInstance());
		panel.setLayout(null);
		
		HittingSetPanel.getInstance().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse clicked");
				int fill = HittingSetPanel.getInstance().isElementClicked();
				Gamehandler.getInstance().setHittingValue(fill);
				System.out.println(fill);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// do nothing
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}
		});
		
		JButton btnNewMhs = new JButton("New MHS");
		btnNewMhs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New MHS");
				Gamehandler.getInstance().newMHS();
			}
		});
		btnNewMhs.setBounds(200, 6, 117, 29);
		panel.add(btnNewMhs);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add");
				Gamehandler.getInstance().add();
			}
		});
		btnAdd.setBounds(200, 80, 117, 29);
		panel.add(btnAdd);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Reset");
				Gamehandler.getInstance().reset();
			}
		});
		btnReset.setBounds(200, 110, 117, 29);
		panel.add(btnReset);
		
		JButton btnComplete = new JButton("Complete");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Complete");
				Gamehandler.getInstance().solve();
			}
		});
		btnComplete.setBounds(200, 140, 117, 29);
		panel.add(btnComplete);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Next Level");
				Gamehandler.getInstance().nextLevel();
			}
		});
		btnNextLevel.setBounds(200, 36, 117, 29);
		panel.add(btnNextLevel);
		
		JPanel resultpanel = new JPanel();
		resultpanel.setBackground(Color.WHITE);
		resultpanel.setBounds(200, 200, 210, 200);
		panel.add(resultpanel);
		resultpanel.add(ResultPanel.getInstance());
		
		points = new JTextField();
		points.setHorizontalAlignment(SwingConstants.RIGHT);
		points.setBackground(Color.WHITE);
		points.setEditable(false);
		points.setBounds(316, 5, 117, 28);
		panel.add(points);
		points.setColumns(10);
		
		final JComboBox gameMode = new JComboBox();
		gameMode.addItem(GameModes.POINTS);
		gameMode.addItem(GameModes.TIME);
		gameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Gamemode changed");
				Gamehandler.getInstance().changeGameMode((String)gameMode.getSelectedItem());
			}
		});
		gameMode.setBounds(316, 37, 117, 27);
		panel.add(gameMode);
		
		time = new JTextField();
		time.setHorizontalAlignment(SwingConstants.RIGHT);
		time.setBackground(Color.WHITE);
		time.setEditable(false);
		time.setBounds(316, 79, 117, 28);
		panel.add(time);
		time.setColumns(10);
	}
	
	public void initProcessing(){
		HittingSetPanel.getInstance().init();
		ResultPanel.getInstance().init();
		ResultPanel.getInstance().draw();
	}

	public JTextField getTime() {
		return points;
	}

	public void setTime(String time) {
		this.time.setText(time);
	}
	
	public JTextField getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points.setText(points);
	}
	
	public static AppletPanel getInstance(){
		return instance;
	}
}
