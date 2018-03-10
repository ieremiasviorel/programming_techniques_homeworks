package Views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton additionBtn;
	private JButton subtractionBtn;
	private JButton multiplicationBtn;
	private JButton divisionBtn;
	private JButton diffBtn;
	private JButton intBtn;
	private JLabel p1L;
	private JLabel p11L;
	private JLabel p2L;
	private JLabel p22L;
	private JLabel resL;
	private JTextField p1Tf;
	private JTextField p2Tf;
	private JLabel res2L;
	private JLabel instL;
	private JLabel inst2L;
	private JLabel warn1L;
	private JLabel warn2L;
		
	public MainView() {
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Polynomial Calculator");
		setBounds(100, 100, 570, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		additionBtn = new JButton("P1 + P2");
		additionBtn.setBounds(40, 30, 140, 30);
		contentPane.add(additionBtn);
			
		subtractionBtn = new JButton("P1 - P2");
		subtractionBtn.setBounds(40, 70, 140, 30);
		contentPane.add(subtractionBtn);
		
		multiplicationBtn = new JButton("P1 * P2");
		multiplicationBtn.setBounds(40, 110, 140, 30);
		contentPane.add(multiplicationBtn);
		
		divisionBtn = new JButton("P1 / P2");
		divisionBtn.setBounds(40, 150, 140, 30);
		contentPane.add(divisionBtn);
		
		diffBtn = new JButton("differentiate P1");
		diffBtn.setBounds(40, 190, 140, 30);
		contentPane.add(diffBtn);
		
		intBtn = new JButton("integrate P1");
		intBtn.setBounds(40, 230, 140, 30);
		contentPane.add(intBtn);
		
		p1L = new JLabel("P1 = ");
		p1L.setBounds(240, 50, 40, 30);
		contentPane.add(p1L);
		
		p1Tf = new JTextField();
		p1Tf.setBounds(270, 50, 250, 30);
		contentPane.add(p1Tf);
		
		p11L = new JLabel();
		p11L.setBounds(270, 80, 250, 30);
		contentPane.add(p11L);
		
		p2L = new JLabel("P2 = ");
		p2L.setBounds(240, 130, 40, 30);
		contentPane.add(p2L);
		
		p2Tf = new JTextField();
		p2Tf.setBounds(270, 130, 250, 30);
		contentPane.add(p2Tf);
		
		p22L = new JLabel();
		p22L.setBounds(270, 160, 250, 30);
		contentPane.add(p22L);
		
		resL = new JLabel("Result = ");
		resL.setBounds(220, 210, 60, 30);
		contentPane.add(resL);
		
		res2L = new JLabel();
		res2L.setBounds(270, 210, 600, 30);
		contentPane.add(res2L);
		
		instL = new JLabel("*Note: Specifiy a polyomial as array of coefficients. Ex.: write X^2 + 1 as 1 0 1");
		instL.setBounds(40, 280, 530, 20);
		contentPane.add(instL);
		
		inst2L = new JLabel("*Legend: q - quotient, r - remainder");
		inst2L.setBounds(40, 305, 530, 20);
		contentPane.add(inst2L);
		
		warn1L = new JLabel();
		warn1L.setBounds(270, 20, 250, 30);
		warn1L.setVisible(false);
		warn1L.setForeground(Color.RED);
		contentPane.add(warn1L);
		
		warn2L = new JLabel();
		warn2L.setBounds(270, 100, 250, 30);
		warn2L.setVisible(false);
		warn2L.setForeground(Color.RED);
		contentPane.add(warn2L);
		
		setVisible(true);
	}
	
	public String getP1String() {
		return p1Tf.getText();
	}
	
	public String getP2String() {
		return p2Tf.getText();
	}
	
	public JLabel getP1L() {
		return p11L;
	}
	
	public JLabel getP2L() {
		return p22L;
	}
	
	public void setResult(String res) {
		res2L.setText(res);
	}
	
	public JLabel getWarn1() {
		return warn1L;
	}
	
	public JLabel getWarn2() {
		return warn2L;
	}
	public void addAdditionListener(ActionListener e) {
		additionBtn.addActionListener(e);
	}
	
	public void addSubtractionListener(ActionListener e) {
		subtractionBtn.addActionListener(e);
	}
	
	public void addMultiplicationListener(ActionListener e) {
		multiplicationBtn.addActionListener(e);
	}
	
	public void addDivisionListener(ActionListener e) {
		divisionBtn.addActionListener(e);
	}
	
	public void addDifferentiationListener(ActionListener e) {
		diffBtn.addActionListener(e);
	}
	
	public void addIntegrationListener(ActionListener e) {
		intBtn.addActionListener(e);
	}
}