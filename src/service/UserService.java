package service;

import java.util.List;

import controller.MainController;
import dao.UserDao;
import vo.UserVo;

public class UserService {
	private static UserService instance = null;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	UserDao dao = UserDao.getInstance();
	
	
	public boolean login(List<Object> param) {
		UserVo user =  dao.login(param);
		if(user != null) {
			MainController.sessionStorage.put("user", user);
			return true;
		}
		return false;
	}
	
	public void memberJoin(List<Object> param) {
		dao.memberJoin(param);
	}
}
