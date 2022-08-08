package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Test one: Seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("=== Test two: Seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller a:list)
			System.out.println(a);
		
		System.out.println("=== Test three: Seller findAll ===");
		list = sellerDao.findAll();
		for(Seller a: list) {
			System.out.println(a);
		}
		
		System.out.println("=== Test four: Seller insert ===");
		Seller otherSeller = new Seller(null, "Jose", "jhs@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(otherSeller);
		System.out.println("Inserted! New id = " + otherSeller.getId());
		
		System.out.println("=== Test five: Seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Jucanalha");
		sellerDao.update(seller);
		System.out.println("UPDATE COMPLETED!");
	
		System.out.println("=== Test six: Seller delete ===");
		System.out.println("Enter id for delete test: ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Deleted!");
		sc.close();
		
	}

}
