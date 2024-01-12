package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoardDao {
	private static BoardDao instance = null;

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
//	게시물번호, 제목, 가격, 등록일, 거래상태, 판매자닉네임 출력
	public List<Map<String, Object>> printBoard() {
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD";
		return jdbc.selectList(sql);
	}
	
}
