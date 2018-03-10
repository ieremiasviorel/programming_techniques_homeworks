package presentation.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bll.AbstractBLL;
import presentation.views.AbstractView;
import presentation.views.TableCreator;

public class AbstractController<T> {

	@SuppressWarnings("rawtypes")
	private AbstractView abstractView;
	@SuppressWarnings("rawtypes")
	private AbstractBLL abstractBLL;
	
	@SuppressWarnings("rawtypes")
	public AbstractController(AbstractView abstractView, AbstractBLL abstractBLL) {
		this.abstractView = abstractView;
		this.abstractBLL = abstractBLL;
		this.abstractView.addInsertButtonListener(new InsertActionListener());
		this.abstractView.addDeleteButtonListener(new DeleteActionListener());
		this.abstractView.addUpdateButtonListener(new UpdateActionListener());
	}
	
	public class InsertActionListener implements ActionListener {
		
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			Object t = abstractView.getObjectToInsert();
			abstractView.displayMessage(0);
			abstractView.displayMessage(abstractBLL.insert(t));
			abstractView.updateTable(TableCreator.createTable(abstractBLL.showAll()));
			abstractView.clearFields();
		}
	}
	
	public class DeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			abstractView.displayMessage(0);
			abstractBLL.deleteById(abstractView.getSelectedRowId());
			abstractView.updateTable(TableCreator.createTable(abstractBLL.showAll()));
			abstractView.clearFields();
		}
	}
	
	public class UpdateActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			abstractView.displayMessage(0);
			abstractBLL.update(abstractView.getFieldsToUpdate(), abstractView.getSelectedRowId());
			abstractView.updateTable(TableCreator.createTable(abstractBLL.showAll()));
			abstractView.clearFields();
		}
	}
}