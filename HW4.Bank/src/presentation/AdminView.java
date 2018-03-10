package presentation;

import model.Person;

import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdminView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JScrollPane tableHolder;
	private JButton insertBtn;
	private JButton deleteBtn;
	private JButton updateBtn;
	private JButton showAccountsBtn;
	
	public AdminView() {
		
		setTitle("Admin View");
		setBounds(520, 100, 615, 340);
		setLayout(null);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(30, 250, 100, 30);
		add(insertBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(175, 250, 100, 30);
		add(deleteBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(325, 250, 100, 30);
		add(updateBtn);
		
		showAccountsBtn = new JButton("Accounts");
		showAccountsBtn.setBounds(470, 250, 100, 30);
		add(showAccountsBtn);
		
		setVisible(true);
	}
	
	public void createTable(Set<Person> people) {	
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("No. crt.");
		tableModel.addColumn("Last name");
		tableModel.addColumn("First name");
		tableModel.addColumn("Personal id no");
		tableModel.addColumn("Phone");
		
		int i = 1;
		for(Person p : people) {
			String[] oneRow = new String[5];
			oneRow[0] = i + "";
			oneRow[1] = p.getLastName();
			oneRow[2] = p.getFirstName();
			oneRow[3] = p.getNin();
			oneRow[4] = p.getPhone();
			i++;
			tableModel.addRow(oneRow);
		}
		
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setFillsViewportHeight(true);
		tableHolder = new JScrollPane(table);
		tableHolder.setBounds(10, 10, 580, 220);
		add(tableHolder);
	}
	
	public Person getSelectedPerson() {
		int selectedRow = table.getSelectedRow();
		TableModel tableModel = table.getModel();
		String lastName = (String)tableModel.getValueAt(selectedRow, 1);
		String firstName = (String)tableModel.getValueAt(selectedRow, 2);
		String nin = (String)tableModel.getValueAt(selectedRow, 3);
		String phone = (String)tableModel.getValueAt(selectedRow, 4);
		return new Person(lastName, firstName, nin, phone);
	}
	
	public void addInsertButtonListener(ActionListener e) {
		insertBtn.addActionListener(e);
	}
	
	public void addDeleteButtonListener(ActionListener e) {
		deleteBtn.addActionListener(e);
	}
	
	public void addUpdateButtonListener(ActionListener e) {
		updateBtn.addActionListener(e);
	}
	
	public void addAccountsButtonListener(ActionListener e) {
		showAccountsBtn.addActionListener(e);
	}
	
	public void addRow(Person p) {
		String[] row = new String[]{((Integer)(table.getRowCount() + 1)).toString(), p.getLastName(), p.getFirstName(), p.getNin(), p.getPhone()};
		TableModel tM = table.getModel();
		((DefaultTableModel)tM).addRow(row);
		table.setModel(tM);
	}
	
	public void deleteSelectedRow() {
		TableModel tM = table.getModel();
		((DefaultTableModel)tM).removeRow(table.getSelectedRow());
		table.setModel(tM);
	}
}