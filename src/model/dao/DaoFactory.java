package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	/* o metodo tem o tipo de retorno a interface(SellerDao), 
	 * mas retrona a implementašao da classe, Ú uma maneira de esconder a implementašao!!
	 */
	
}
