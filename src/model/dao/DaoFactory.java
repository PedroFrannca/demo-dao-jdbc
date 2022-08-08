package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	/* o metodo tem o tipo de retorno a interface(SellerDao), 
	 * mas retrona a implementaçao da classe, é uma maneira de esconder a implementaçao!!
	 */
	
}
