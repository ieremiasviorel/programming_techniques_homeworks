package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ProductBLL;
import connection.ConnectionFactory;
import model.Ord;

public class AbstractDAO<T> {
	
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	protected final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		if(field != null) {
			sb.append(" WHERE " + field + " = ?");
			return sb.toString();
		}
		else {
			return sb.toString();
		}
	}
	
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = ?");
		return sb.toString();
	}
	
	private String createUpdateQuery(List<String> fieldsToUpdate, String conditionField) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for(int i = 0; i < fieldsToUpdate.size(); i++) {
			if((i % 2) == 0) {
				if(i == 0) {
					sb.append(fieldsToUpdate.get(i) + " = ?");
				}
				else {
					sb.append(", " + fieldsToUpdate.get(i) + " = ?");
				}
			}
		}
		sb.append(" WHERE " + conditionField + " = ?");
		return sb.toString();
	}
	
	private String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + type.getSimpleName() + " (");
		Field[] fields = type.getDeclaredFields();
		sb.append(fields[0].getName());
		for(int i = 1; i < fields.length; i++) {
			sb.append("," + fields[i].getName());
		}
		sb.append(") VALUES (");
		for(int i = 0; i < fields.length; i++) {
			if(i == fields.length - 1) {
				sb.append("?)");
			}
			else {
				sb.append("?,");
			}
		}
		return sb.toString();
	}

	public T findById(int id) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public T findByName(String name) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("name");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(null);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public int deleteById(int id) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet= null;
		String query = createDeleteQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: deleteById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return id;
	}

	
	public int insert(T t) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		String query = createInsertQuery();
		
		try {
			insertStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(t);
				insertStatement.setString(i++, value.toString());
			}
			if(type.equals(Ord.class)) {
				int unitsInStock = new ProductBLL().findById(((Ord)t).getProductId()).getUnitsInStock();
				int unitsRequired = ((Ord)t).getQuantity();
				if(unitsInStock >= unitsRequired) {
					insertStatement.executeUpdate();
					List<String> updateProductQuantity = new ArrayList<String>();
					updateProductQuantity.add("unitsInStock");
					updateProductQuantity.add(((Integer)(unitsInStock - unitsRequired)).toString());
					new ProductBLL().update(updateProductQuantity, ((Ord)t).getProductId());
				}
				else {
					System.err.println("The required quantity is not available!");
					return -1;
				}
			}
			else {
				insertStatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return 0;
	}

	public int updateById(List<String> fieldsToUpdate, int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet= null;
		String query = createUpdateQuery(fieldsToUpdate, "id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int k = 1;
			for(int i = 0; i < fieldsToUpdate.size(); i++) {
				if((i % 2 ) == 1) {
					statement.setString(k++, fieldsToUpdate.get(i));
				}
			}
			statement.setInt(k, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: updatedById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}
	
	private List<T> createObjects(ResultSet resultSet) {
		
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
}