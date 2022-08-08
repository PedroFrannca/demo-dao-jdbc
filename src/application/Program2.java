package application;

import java.util.ArrayList;
import java.util.List;

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
		
		System.out.println("=== Test Two: Update Test ===");
		Department dep2 = new Department(7, "food");
		depDao.update(dep2);
		System.out.println("Update completed! ");
		
		System.out.println("=== Test Three: Delete test ===");
		depDao.deleteById(21);
		System.out.println("Delete completed!");
		
		System.out.println("=== Test Four: FindById test ===");
		dep = depDao.findById(1);
		System.out.println(dep);
		
		System.out.println("=== Test Five: FindById test ===");
		List<Department> list = new ArrayList<>();
		list = depDao.findAll();
		for(Department a: list) {
			System.out.println(a);
		}
	}

}
