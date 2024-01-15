package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BoardVo;
import vo.UserVo;

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
	
	public List<BoardVo> printBoard(List<Object>param) {
		String sql ="SELECT *\r\n" + 
				"FROM (SELECT ROWNUM RN, A.* FROM\r\n" + 
				"        (SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N') A)\r\n" + 
				"WHERE RN BETWEEN ? AND ?";
		return jdbc.selectList(sql,param,BoardVo.class);
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
	
	public void boardLike(int sel) {
		String sql = "UPDATE BOARD\r\n" + 
				"SET BOARD_LIKE =BOARD_LIKE+1\r\n" + 
				"WHERE BOARD_NO ="+sel;
		jdbc.update(sql);
	}
	
	public void boardViews(int con) {
		String sql = "UPDATE BOARD\r\n" + 
				"SET BOARD_VIEWS =BOARD_VIEWS+1\r\n" + 
				"WHERE BOARD_NO ="+con;
		jdbc.update(sql);
	}
	
	public void boardDel(int con) {
		String sql ="UPDATE BOARD\r\n" + 
				"SET DELYN = 'Y'\r\n" + 
				"WHERE BOARD_NO ="+con;
		jdbc.update(sql);
	}
	
	public void boardUpdate(List<Object> list,int sel) {
		String sql_front = "UPDATE BOARD\r\n" + 
				" SET ";
		String format ="%s =?";
		String sql = sql_front;
			if(sel == 1) {
				sql+=String.format(format, "BOARD_TITLE");
				}
			if(sel == 2) {
				sql+=String.format(format, "BOARD_CONTENT");
				}
			if(sel == 3) {
				sql+=String.format(format, "BOARD_PRICE");
			}
			if(sel == 4) {
				sql+=String.format(format, "BOARD_TITLE")+", ";
				sql+=String.format(format, "BOARD_CONTENT")+", ";
				sql+=String.format(format, "BOARD_PRICE")+"";
			}
			sql +="WHERE BOARD_NO = ?";
			jdbc.update(sql, list);
	}
	
	public List<UserVo> boardSeller(String seller) {
		String sql = "SELECT\r\n" + 
				"    MEM_NICK,\r\n" + 
				"    MEM_TEM,\r\n" + 
				"    AREA_NO\r\n" + 
				"    FROM MEMBER\r\n" + 
				"    WHERE MEM_ID = '"+seller+"'";
		return jdbc.selectList(sql, UserVo.class);
	}
	
	public List<BoardVo> boardSellerItem(String seller) {
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n"+
				"AND MEM_SELLER ='"+seller+"'";
		return jdbc.selectList(sql,BoardVo.class);
	}
	
	public Map<String, Object> maxBoard() {
		String sql = "SELECT COUNT(*)\r\n" + 
				"FROM board\r\n" + 
				"WHERE DELYN = 'N'";
		return jdbc.selectOne(sql);
	}
	
	public void boardWrite(List<Object>param,String id) {
		String sql = "INSERT INTO BOARD\r\n" + 
				"VALUES(BOARD_NO_SEQ.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, '"+id+"', '"+id+"', ?)";
		jdbc.update(sql, param);
	}
	
	public List<BoardVo> boardSearch(int cate) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"AND CATE_ID = "+cate;
		return jdbc.selectList(sql, BoardVo.class);
	}
	public List<BoardVo> boardSearch(String title) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"AND BOARD_TITLE LIKE '%"+title+"%'";
		return jdbc.selectList(sql, BoardVo.class);
	}
	public List<BoardVo> boardSearch2(String content) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"AND BOARD_CONTENT LIKE '%"+content+"%'";
		return jdbc.selectList(sql, BoardVo.class);
	}
	
	public List<BoardVo> boardPrice(){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"ORDER BY BOARD_PRICE DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardTem(){
		String sql = "SELECT \r\n" + 
				"    B.BOARD_NO, \r\n" + 
				"    B.BOARD_TITLE, \r\n" + 
				"    SUBSTR(B.BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    B.BOARD_PRICE, \r\n" + 
				"    TO_CHAR(B.BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    B.BOARD_STAT\r\n" + 
				"FROM BOARD B, MEMBER M\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND B.MEM_BUYER =M.MEM_ID\r\n" + 
				"ORDER BY M.MEM_TEM DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardLike(){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"ORDER BY BOARD_LIKE DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardNew(){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE DELYN = 'N'\r\n" + 
				"ORDER BY BOARD_NO DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	
	public List<BoardVo> mySell(String id) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE MEM_SELLER = '"+id+"'";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> myBuy(String id) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT\r\n" + 
				"FROM BOARD\r\n" + 
				"WHERE MEM_BUYER = '"+id+"'";
		return jdbc.selectList(sql,BoardVo.class);
	}
	
	public List<UserVo> printMyProfile(String id) {
		String sql = "SELECT\r\n" + 
				"    MEM_NICK,\r\n" + 
				"    MEM_TEM,\r\n" + 
				"    AREA_NO\r\n" + 
				"    FROM MEMBER\r\n" + 
				"    WHERE MEM_ID = '"+id+"'";
		return jdbc.selectList(sql,UserVo.class);
	}
	
	public void myProfileUpdate(List<Object> list,int sel) {
		String sql_front = "UPDATE MEMBER\r\n" + 
				" SET ";
		String format ="%s =?";
		String sql = sql_front;
			if(sel == 1) {
				sql+=String.format(format, "MEM_PASS");
				}
			if(sel == 2) {
				sql+=String.format(format, "MEM_NICK");
				}
			if(sel == 3) {
				sql+=String.format(format, "AREA_NO");
			}
			if(sel == 4) {
				sql+=String.format(format, "MEM_ADDRESS");
			}
			if(sel == 5) {
				sql+=String.format(format, "TEL");
			}
			sql +="WHERE MEM_ID = ?";
			jdbc.update(sql, list);
	}
	
	public void memDel(String id) {
			String sql = "UPDATE MEMBER\r\n" + 
					"SET DELYN = 'Y'\r\n" + 
					"WHERE MEM_ID = '"+id+"'";
			jdbc.update(sql);
	}
}
