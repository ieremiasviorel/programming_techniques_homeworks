package presentation.views;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bll.CustomerBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Customer;
import model.Ord;
import model.Product;

public class AbstractView<T> extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected final Class<T> type;
	
	protected JPanel contentPane;
	protected JTable table;
	protected JScrollPane tableHolder;
	protected JButton insertBtn;
	protected JButton deleteBtn;
	protected JButton updateBtn;
	protected List<JLabel> fieldsL;
	protected List<JTextField> fieldsTf;
	protected JLabel errorMsgL;
	@SuppressWarnings("unchecked")
	public AbstractView() {
		
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		setTitle(this.type.getSimpleName());
		setBounds(700, 100, 600, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(type.equals(Product.class)) {
			table = TableCreator.createTable(new ProductBLL().showAll());
		}
		else if(type.equals(Customer.class)) {
			table = TableCreator.createTable(new CustomerBLL().showAll()); 
		}
		else if(type.equals(Ord.class)) {
			table = TableCreator.createTable(new OrderBLL().showAll()); 
		}
		else {
			table = new JTable();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setFillsViewportHeight(true);
		tableHolder = new JScrollPane(table);
		tableHolder.setBounds(10, 10, 565, 200);
		contentPane.add(tableHolder);
		
		fieldsL = new ArrayList<JLabel>();
		fieldsTf = new ArrayList<JTextField>();
		Field[] fields = type.getDeclaredFields();
		int i = 0;
		for(Field f : fields) {
			JLabel label = new JLabel(f.getName());
			label.setBounds(15, 225 + 30 * i, 80, 25);
			fieldsL.add(label);
			contentPane.add(label);
			JTextField field = new JTextField();
			field.setBounds(100, 225 + 30 * i, 100, 25);
			fieldsTf.add(field);
			contentPane.add(field);
			i++;
		}
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(320, 225, 150, 30);
		contentPane.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(320, 265, 150, 30);
		contentPane.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(320, 305, 150, 30);
		contentPane.add(deleteBtn);
		
		errorMsgL = new JLabel("Understock!");
		errorMsgL.setBounds(320, 345, 150, 30);
		errorMsgL.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsgL.setForeground(Color.RED);
		errorMsgL.setVisible(false);
		contentPane.add(errorMsgL);
		
		setVisible(true);
	}
	
	private List<String> getTextFieldsContent() {
		List<String> content = new ArrayList<String>();
		for(JTextField tf : fieldsTf) {
			content.add(tf.getText());
		}
		return content;
	}
	
	public T getObjectToInsert() {
		
		List<String> content = getTextFieldsContent();
		T objToInsert = null;
		try {
			objToInsert = type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		Field[] fields = type.getDeclaredFields();
		int nrFields = fields.length;
		
		for(int i = 0; i < nrFields; i++) {
			Object value = null;
			try {
				if(fields[i].getType().equals(int.class)) {
					value = Integer.parseInt(content.get(i));
				} else if(fields[i].getType().equals(double.class)) {
					value = Double.parseDouble(content.get(i));
				} else {
					value  = content.get(i);
				}
			} catch (NumberFormatException e){
				System.err.println("Invalid arguments!");
			} catch (IllegalArgumentException e){
				System.err.println("Invalid arguments!");
			}
			
			PropertyDescriptor propertyDescriptor;
			try {
				propertyDescriptor = new PropertyDescriptor(fields[i].getName(), type);
				Method method = propertyDescriptor.getWriteMethod();
				fields[i].setAccessible(true);
				method.invoke(objToInsert, value);	
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.err.println("Invalid arguments!");
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return objToInsert;
	}
	
	public List<String> getFieldsToUpdate() {
		
		List<String> content = getTextFieldsContent();
		
		List<String> fieldsToUpdate = new ArrayList<String>();
		Field[] fields = type.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++) {
			if(!content.get(i).trim().equals("")) {
				fieldsToUpdate.add(fields[i].getName());
				fieldsToUpdate.add(content.get(i));
			}
		}
		return fieldsToUpdate;
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
	
	public int getSelectedRowId() {
		DefaultTableModel defaultModel = (DefaultTableModel)table.getModel();
		try {
			return Integer.parseInt((String)defaultModel.getValueAt(table.getSelectedRow(), 0));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No selection made!");
		}
		return 0;
	}
	
	public void updateTable(JTable newTable) {
		table = newTable;
		tableHolder.setViewportView(newTable);
	}
	
	public void displayMessage(int flag) {
		if(flag == 0) {
			errorMsgL.setVisible(false);
		}
		else {
			errorMsgL.setVisible(true);
		}
	}
	
	public void clearTextFields() {
		for(JTextField field : fieldsTf) {
			field.setText("");
		}
	}
	
	public void clearFields() {
		for(JTextField tf : fieldsTf) {
			tf.setText("");
		}
	}
}