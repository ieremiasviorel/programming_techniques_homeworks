package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.Validator;
import dao.AbstractDAO;
public class AbstractBLL<T> {
	
	private List<Validator<T>> validators;
	@SuppressWarnings("rawtypes")
	private AbstractDAO abstractDAO;

	@SuppressWarnings("rawtypes")
	public AbstractBLL(AbstractDAO abstractDAO) {
		validators = new ArrayList<Validator<T>>();
		this.abstractDAO = abstractDAO;
	}
	
	public T findById(int id) {
		@SuppressWarnings("unchecked")
		T t = (T) abstractDAO.findById(id);
		if (t == null) {
			throw new NoSuchElementException("The Object with id =" + id + " was not found!");	
		}
		return t;
	}
	
	public T findByName(String name) {
		@SuppressWarnings("unchecked")
		T t = (T) abstractDAO.findByName(name);
		if (t == null) {
			throw new NoSuchElementException("The Object with name =" + name + " was not found!");	
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public int insert(T t) {
		for (Validator<T> v : validators) {
			v.validate(t);
		}
		return abstractDAO.insert(t);
	}
	
	public void deleteById(int id) {
		abstractDAO.deleteById(id);
	}
	
	@SuppressWarnings("unchecked")
	public void update(List<String> fieldsToUpdate, int rowToUpdate) {
		abstractDAO.updateById(fieldsToUpdate, rowToUpdate);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> showAll() {
		
		return abstractDAO.findAll();
	}
}