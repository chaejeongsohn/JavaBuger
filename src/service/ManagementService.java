package service;

import dao.ManagementDAO;
import dao.ManagementDAOImpl;

public class ManagementService {
	ManagementDAO managementDAO = new ManagementDAOImpl();

	public void updatePassword(String managerPw){
		
	}

	public void updateStoreName(String storeName){

	}

	public boolean checkPassword(String managerPw){

		return false;
	}
}
