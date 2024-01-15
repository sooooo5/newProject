package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BoardVo;

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
	
	public List<BoardVo> printBoard() {
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'";
		return jdbc.selectList(sql,BoardVo.class);
	}
	
	public BoardVo boardDetail(int sel){
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT,\r\n" + 
				"    BOARD_LIKE,\r\n" + 
				"    MEM_SELLER,\r\n" + 
				"    CATE_ID\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE BOARD_NO = "+sel;
		return jdbc.selectOne(sql,BoardVo.class);
	}
	
}
