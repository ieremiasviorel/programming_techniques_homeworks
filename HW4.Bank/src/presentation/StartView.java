package presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton adminBtn;
	private JButton userBtn;
	public StartView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bank management system");
		setBounds(100, 100, 410, 320);
		setLayout(null);
		
		adminBtn = new JButton("Admin");
		adminBtn.setBounds(80, 40, 235, 80);
		add(adminBtn);
		
		userBtn = new JButton("User");
		userBtn.setBounds(80, 160, 235, 80);
		add(userBtn);
		
		setVisible(true);
	}
	
	public void addAdminButtonActionListener(ActionListener e) {
		adminBtn.addActionListener(e);
	}
	
	public void addUserButtonActionListener(ActionListener e) {
		userBtn.addActionListener(e);
	}
}