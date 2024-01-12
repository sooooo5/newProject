package service;

import java.util.List;

import controller.MainController;
import dao.AdminDao;
import vo.AdminVo;
import vo.UserVo;

public class AdminService {
	private static AdminService instance = null;

	private AdminService() {
	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	AdminDao dao = AdminDao.getInstance();
	
	public boolean login(List<Object> param) {
		AdminVo admin =  dao.login(param);
		if(admin != null) {
			MainController.sessionStorage.put("admin", admin);
			return true;
		}
		return false;
	}
}
