package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Shop;

public class MainWindow extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private int nrQueues;
	private JPanel contentPane;
	
	private List<JLabel> cashierL;
	private List<JLabel> nrClientsL;
	private List<ArrayList<JLabel>> clients;
	private JLabel minServeTimeL;
	private JTextField minServeTimeTf;
	private JLabel maxServeTimeL;
	private JTextField maxServeTimeTf;
	private JLabel minArrTimeL;
	private JTextField minArrTimeTf;
	private JLabel maxArrTimeL;
	private JTextField maxArrTimeTf;
	private JLabel nrQueuesL;
	private JTextField nrQueuesTf;
	private JLabel simTimeL;
	private JTextField simTimeTf;
	private JButton startBtn;
	private JButton pauseBtn;
	private JButton resumeBtn;
	private JScrollPane loggerFrame;
	private JTextArea logger;
	private JLabel avgWaitTimeL;
	private JLabel avgWaitTimeLV;
	private JLabel avgServeTimeL;
	private JLabel avgServeTimeLV;
	private JLabel totClientsL;
	private JLabel totClientsLV;
	private JLabel peakHourL;
	private JLabel peakHourV;
		
	public MainWindow() {
		
		nrQueues = Shop.NR_QUEUES;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Shop simulation");
		setBounds(100, 100, 1010, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		minServeTimeL = new JLabel("Minimum serving time:");
		minServeTimeL.setBounds(30, 30, 150, 25);
		contentPane.add(minServeTimeL);
		
		minServeTimeTf = new JTextField();
		minServeTimeTf.setBounds(30, 60, 150, 25);
		contentPane.add(minServeTimeTf);
		
		maxServeTimeL = new JLabel("Maximum serving time:");
		maxServeTimeL.setBounds(30, 90, 150, 25);
		contentPane.add(maxServeTimeL);
		
		maxServeTimeTf = new JTextField();
		maxServeTimeTf.setBounds(30, 125, 150, 25);
		contentPane.add(maxServeTimeTf);
		
		minArrTimeL = new JLabel("Minimum arrival time:");
		minArrTimeL.setBounds(30, 150, 150, 25);
		contentPane.add(minArrTimeL);
		
		minArrTimeTf = new JTextField();
		minArrTimeTf.setBounds(30, 180, 150, 25);
		contentPane.add(minArrTimeTf);
		
		maxArrTimeL = new JLabel("Maximum arrival time:");
		maxArrTimeL.setBounds(30, 210, 150, 25);
		contentPane.add(maxArrTimeL);
		
		maxArrTimeTf = new JTextField();
		maxArrTimeTf.setBounds(30, 240, 150, 25);
		contentPane.add(maxArrTimeTf);
		
		nrQueuesL = new JLabel("Number of Queues:");
		nrQueuesL.setBounds(30, 270, 150, 25);
		contentPane.add(nrQueuesL);
		
		nrQueuesTf = new JTextField();
		nrQueuesTf.setBounds(30, 300, 150, 25);
		contentPane.add(nrQueuesTf);
		
		simTimeL = new JLabel("Simulation Time:");
		simTimeL.setBounds(30, 330, 150, 25);
		contentPane.add(simTimeL);
		
		simTimeTf = new JTextField();
		simTimeTf.setBounds(30, 360, 150, 25);
		contentPane.add(simTimeTf);
		
		startBtn = new JButton("START");
		startBtn.setBounds(30,  400, 150, 50);
		contentPane.add(startBtn);
		
		pauseBtn = new JButton("PAUSE");
		pauseBtn.setBounds(30,  460, 150, 30);
		contentPane.add(pauseBtn);
		
		resumeBtn = new JButton("RESUME");
		resumeBtn.setBounds(30,  500, 150, 30);
		contentPane.add(resumeBtn);
		
		cashierL = new ArrayList<JLabel>(Shop.NR_QUEUES);
		for(int i = 0; i < Shop.NR_QUEUES; i++) {
			JLabel label = new JLabel("Q " + (i + 1));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(220 + 80 * i, 30, 50, 30);
			label.setVisible(true);
			cashierL.add(i, label);
			contentPane.add(label);
		}
		
		nrClientsL = new ArrayList<JLabel>(Shop.NR_QUEUES);
		for(int i = 0; i < Shop.NR_QUEUES; i++) {
			JLabel label = new JLabel("0");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(220 + 80 * i, 15, 50, 30);
			label.setVisible(true);
			nrClientsL.add(i, label);
			contentPane.add(label);
		}
		
		clients = new ArrayList<ArrayList<JLabel>>(nrQueues);
		for(int i = 0; i < nrQueues; i++) {
			ArrayList<JLabel> clientsQueueI = new ArrayList<JLabel>(Shop.MAX_CLIENTS_QUEUE);
			for(int j = 0; j < Shop.MAX_CLIENTS_QUEUE; j++) {
				JLabel c = new JLabel("C");
				c.setBounds(220 + 80 * i, 70 + 40 * j, 50, 30);
				if(j != 0) {
					c.setBackground(Color.GREEN);
				}
				else {
					c.setBackground(Color.RED);
				}
				c.setOpaque(true);
				c.setHorizontalAlignment(SwingConstants.CENTER);
				c.setVisible(false);
				contentPane.add(c);
				clientsQueueI.add(j, c);
			}
			clients.add(i, clientsQueueI);
		}
		
		loggerFrame = new JScrollPane();
		loggerFrame.setBounds(780, 30, 200, 300);
		
		logger = new JTextArea();
		loggerFrame.setViewportView(logger);
		contentPane.add(loggerFrame);
		
		avgWaitTimeL = new JLabel("Average waiting time:  ");
		avgWaitTimeL.setBounds(780, 360, 130, 25);
		contentPane.add(avgWaitTimeL);
		
		avgWaitTimeLV = new JLabel();
		avgWaitTimeLV.setBounds(920, 360, 40, 25);
		contentPane.add(avgWaitTimeLV);
		
		avgServeTimeL = new JLabel("Average serving time: ");
		avgServeTimeL.setBounds(780, 390, 130, 25);
		contentPane.add(avgServeTimeL);
		
		avgServeTimeLV = new JLabel("");
		avgServeTimeLV.setBounds(920, 390, 40, 25);
		contentPane.add(avgServeTimeLV);
		
		totClientsL = new JLabel("Tot. no. Clients:       ");
		totClientsL.setBounds(780, 420, 130, 25);
		contentPane.add(totClientsL);
		
		totClientsLV = new JLabel();
		totClientsLV.setBounds(920, 420, 130, 25);
		contentPane.add(totClientsLV);
		
		peakHourL = new JLabel("Peak hour:                ");
		peakHourL.setBounds(780, 450, 130, 25);
		contentPane.add(peakHourL);
		
		peakHourV = new JLabel();
		peakHourV.setBounds(920, 450, 130, 25);
		contentPane.add(peakHourV);
		
		setVisible(true);
	}
	
	public void run() {
		repaint();
	}
	
	public String getMinArrTimeText() {
		return minArrTimeTf.getText();
	}
	
	public String getMaxArrTimeText() {
		return maxArrTimeTf.getText();
	}
	
	public String getMinServeTimeText() {
		return minServeTimeTf.getText();
	}
	
	public String getMaxServeTimeText() {
		return maxServeTimeTf.getText();
	}
	
	public String getNrQueuesText() {
		return nrQueuesTf.getText();
	}
	
	public String getSimTime() {
		return simTimeTf.getText();
	}
	
	public void setNrQueues(int n) {
		this.nrQueues = n;
	}
	
	public void addQueues(int n) {
		nrQueues = n;
		for(int i = 0; i < nrQueues; i++) {
			cashierL.get(i).setForeground(Color.BLUE);
		}
	}
	
	public void updateQueue(int[][] c) {
		for(int i = 0; i < nrQueues; i++) {
			for(int j = 0; j < Shop.MAX_CLIENTS_QUEUE; j++) {
				if(c[i][j] == -1) {
					clients.get(i).get(j).setVisible(false);
				}
				else {
					clients.get(i).get(j).setText("C" + c[i][j]);
					clients.get(i).get(j).setVisible(true);
				}
			}
		}
	}
	
	public void updateNrClients(int[] n) {
		for(int i = 0; i < nrQueues; i++) {
			nrClientsL.get(i).setText(" "  + n[i] + " ");
		}
	}
	
	public void updateAvgServeTime(float f) {
		avgServeTimeLV.setText(f + "");
	}
	
	public void updateAvgWaitTime(float f) {
		avgWaitTimeLV.setText(f + "");
	}
	
	public void updateTotNrClients(int n) {
		totClientsLV.setText(n + "");
	}
	
	public void updatePeakHour(int n) {
		peakHourV.setText(n + "");
	}
	
	public void updateLog(String s) {
		logger.setText(s);
	}
	
	public void addStartBtnActionListener(ActionListener e) {
		startBtn.addActionListener(e);
	}
	
	public void addPauseBtnActionListener(ActionListener e) {
		pauseBtn.addActionListener(e);
	}
	
	public void addResumeBtnActionListener(ActionListener e) {
		resumeBtn.addActionListener(e);
	}
}