package dao;

import java.util.List;

import util.JDBCUtil;
import vo.AdminVo;
import vo.UserVo;

public class AdminDao {
	private static AdminDao instance = null;

	private AdminDao() {
	}

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public AdminVo login(List<Object> param) {
		String sql = " SELECT *\r\n" + 
				" FROM ADMIN\r\n" + 
				" WHERE ADMIN_ID = ? \r\n" + 
				" AND ADMIN_PASS = ? ";
		return jdbc.selectOne(sql,param,AdminVo.class);
	}
}
