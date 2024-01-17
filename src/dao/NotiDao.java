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
				"    S.MESSAGE_ID,\r\n" + 
				"    S.MESSAGE_CONTENT,\r\n" + 
				"    TO_CHAR(S.MESSAGE_DATE, 'MM/DD') MESSAGE_DATE,\r\n" + 
				"    S.MEM_ID,\r\n" + 
				"    S.MEM_ID2,\r\n" + 
				"    M.MEM_NICK\r\n" + 
				"FROM MESSAGE S, MEMBER M\r\n" + 
				"WHERE CHAT_NO = "+no+"\r\n" + 
				"AND S.MEM_ID =M.MEM_ID) A)"; 
		return jdbc.selectList(sql, MessageVo.class);
	}
}
