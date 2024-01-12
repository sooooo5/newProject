package dao;

import java.util.List;

import util.JDBCUtil;
import vo.Fboard;

public class NotiDao {
	private static NotiDao singleTon = null;

	private NotiDao() {
	};

	public static NotiDao getInstance() {
		if (singleTon == null) {
			singleTon = new NotiDao();
		}
		return singleTon;
	}


	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	public List<Fboard> getNoti() {
		String sql = " select * from free_board where noti ='y'"; 
		return jdbc.selectList(sql, Fboard.class);
	}
	
	public List<Fboard> getList() {
		String sql = " select * from free_board"; 
		return jdbc.selectList(sql, Fboard.class);
	}
	
	public void setNoti(int no) {
		String sql = " update free_board set noti ='y' where board_no="+no; 
		jdbc.update(sql);
	}
}
