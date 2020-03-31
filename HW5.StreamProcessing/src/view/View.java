package view;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton distinctDays;
	private JButton noActivities;
	private JButton activitiesPerDay;
	private JButton totalTimePerActivity;
	private JButton shortActivities;
	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Stream processing");
		setBounds(100, 100, 225, 320);
		setLayout(null);
		
		distinctDays = new JButton("Distinct days");
		distinctDays.setBounds(30, 30, 150, 30);
		add(distinctDays);
		
		noActivities = new JButton("Activity count");
		noActivities.setBounds(30, 80, 150, 30);
		add(noActivities);
		
		activitiesPerDay = new JButton("Activities Per Day");
		activitiesPerDay.setBounds(30, 130, 150, 30);
		add(activitiesPerDay);
		
		totalTimePerActivity = new JButton("Activity total time");
		totalTimePerActivity.setBounds(30, 180, 150, 30);
		add(totalTimePerActivity);
		
		shortActivities = new JButton("Short activities");
		shortActivities.setBounds(30, 230, 150, 30);
		add(shortActivities);
		
		setVisible(true);
	}
	
	public void addButtonListener(ActionListener e, int identifier) {
		switch(identifier) {
		case 0 : distinctDays.addActionListener(e);
		break;
		case 1 : noActivities.addActionListener(e);
		break;
		case 2 : activitiesPerDay.addActionListener(e);
		break;
		case 3 : totalTimePerActivity.addActionListener(e);
		break;
		case 4 : shortActivities.addActionListener(e);
		break;
		}
	}
}
