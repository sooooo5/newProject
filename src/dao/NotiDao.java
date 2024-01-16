package dao;

import java.util.List;

import util.JDBCUtil;
import vo.MessageVo;

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
	
	
	
	public List<MessageVo> getNoti(int no) {
		String sql = "SELECT *\r\n" + 
				"FROM (SELECT ROWNUM RN, A.* FROM\r\n" + 
				"        (SELECT\r\n" + 
				"    MESSAGE_ID,\r\n" + 
				"    MESSAGE_CONTENT,\r\n" + 
				"    TO_CHAR(MESSAGE_DATE, 'MM/DD') MESSAGE_DATE,\r\n" + 
				"    MEM_ID,\r\n" + 
				"    MEM_ID2\r\n" + 
				"FROM MESSAGE\r\n" + 
				"WHERE CHAT_NO = "+no+") A)"; 
		return jdbc.selectList(sql, MessageVo.class);
	}
}
