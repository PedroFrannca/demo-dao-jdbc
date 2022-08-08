package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		System.out.println("=== Test One: Insert Test ===");
		Department dep = new Department(null, "Tech");
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		depDao.insert(dep);
		System.out.println("Inserted! Id = " + dep.getId());
	}

}
