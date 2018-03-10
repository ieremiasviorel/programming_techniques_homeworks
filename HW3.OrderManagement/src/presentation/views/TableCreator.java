package presentation.views;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bll.CustomerBLL;
import bll.ProductBLL;
import model.Ord;


public class TableCreator {

	public static JTable createTable(List<?> elements) {
		
		if(elements == null) {
			return new JTable();
		}
		
		Field[] fields = elements.get(0).getClass().getDeclaredFields();
		
		DefaultTableModel tableModel = new DefaultTableModel();
		for(Field f : fields) {
			tableModel.addColumn(f.getName());
		}
		
		for(Object e : elements) {
			
			Vector<String> oneRow = new Vector<String>();
			
			if(elements.get(0).getClass().equals(Ord.class)) {
				
				ProductBLL productBLL = new ProductBLL();
				CustomerBLL customerBLL = new CustomerBLL();
				Field f = fields[0];
				f.setAccessible(true);
				try {
					oneRow.add(f.get(e).toString());
					f = fields[1];
					f.setAccessible(true);
					oneRow.add(productBLL.findById((Integer)f.get(e)).getName());
					f = fields[2];
					f.setAccessible(true);
					oneRow.add(customerBLL.findById((Integer)f.get(e)).getName());
					f = fields[3];
					f.setAccessible(true);
					oneRow.add(f.get(e).toString());
					
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
			}
			else {
				
				for(Field f : fields) {
					f.setAccessible(true);
					try {
						oneRow.add(f.get(e).toString());
					} catch (IllegalArgumentException | IllegalAccessException ex) {
						ex.printStackTrace();
					}
				}
			}
			
			tableModel.addRow(oneRow);
		}
		JTable table = new JTable(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.clearSelection();
		return table;
	}
}