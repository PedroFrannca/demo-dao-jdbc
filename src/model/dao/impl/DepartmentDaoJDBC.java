package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	Connection conn = null;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					"insert into departament "
					+ "(Name) "
					+ "VALUES "
					+ "(?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE departament "
					+ "SET Name = ? "
					+ "WHERE Id = ?"
					);
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
		
			int arrowsAffected = st.executeUpdate();
			
			if(arrowsAffected == 0) {
				throw new DbException("Unexpected error! Update dont executed!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}	

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement(
					"DELETE FROM departament "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected == 0) {
				throw new DbException("Unexpected error! Id dont deleted!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT * "
					+ "FROM departament "
					+ "WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = new Department(rs.getInt("Id"),rs.getString("Name"));
				return dep;
			}
			
			throw new DbException("Dont have Id in DepartmentTable in database!");
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Department> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		
		try {

			st = conn.prepareStatement("SELECT * FROM departament");
			rs = st.executeQuery();

			while(rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				list.add(dep);
			}
			
			return list;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	
	
}
