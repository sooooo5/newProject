package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import vo.BoardVo;
import vo.MessageVo;
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
	
	
	// 회원 - 거래글 리스트
	public List<BoardVo> printBoard(List<Object>param,int ano) {
		String sql ="SELECT *\r\n" + 
				"FROM (\r\n" + 
				"    SELECT ROWNUM RN, A.*\r\n" + 
				"    FROM (\r\n" + 
				"        SELECT \r\n" + 
				"            BOARD_NO, \r\n" + 
				"            RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"            SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"            BOARD_PRICE, \r\n" + 
				"            TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"            BOARD_STAT, \r\n" + 
				"            BOARD_LIKE\r\n" + 
				"        FROM BOARD B\r\n" + 
				"        INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"        WHERE B.DELYN = 'N'\r\n" + 
				"        AND B.BOARD_STAT = 'N'" + 
				"        AND M.AREA_NO = "+ano+"\r\n" + 
				"    ) A\r\n" + 
				")\r\n" + 
				"WHERE RN BETWEEN ? AND ?";
		return jdbc.selectList(sql,param,BoardVo.class);
	}
	
	
	// 회원 - 거래글 상세보기
	public BoardVo boardDetail(int sel){
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE,40,' ') BOARD_TITLE, \r\n" + 
				"    BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT,\r\n" + 
				"    BOARD_LIKE,\r\n" + 
				"    BOARD_VIEWS,\r\n" + 
				"    RPAD(MEM_SELLER,10,' ') MEM_SELLER,\r\n" + 
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
	
	public List<UserVo> boardSeller(List<Object>param) {
		String sql = "SELECT\n" + 
				"    MEM_NICK,\n" + 
				"    MEM_TEM,\n" + 
				"    AREA_NO\n" + 
				"FROM MEMBER\n" + 
				"WHERE MEM_ID = ?";
		return jdbc.selectList(sql,param, UserVo.class);
	}
	
	public List<BoardVo> boardSellerItem(String seller) {
		String sql ="SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT,0,20) BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" + 
				"    BOARD_LIKE\r\n" +
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
	
	public List<BoardVo> boardSearch(int cate,int ano) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" + 
				"    BOARD_LIKE\r\n" + 
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND B.CATE_ID = "+cate+"\r\n" + 
				"AND M.AREA_NO = "+ano;
		return jdbc.selectList(sql, BoardVo.class);
	}
	public List<BoardVo> boardSearch(String title,int ano) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" +
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND BOARD_TITLE LIKE '%"+title+"%'\r\n" + 
				"AND M.AREA_NO = "+ano;
		return jdbc.selectList(sql, BoardVo.class);
	}
	public List<BoardVo> boardSearch2(String content,int ano) {
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" +
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND BOARD_CONTENT LIKE '%"+content+"%'\r\n" + 
				"AND M.AREA_NO = "+ano;
		return jdbc.selectList(sql, BoardVo.class);
	}
	
	public List<BoardVo> boardPrice(int ano){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" +
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND M.AREA_NO = "+ano+"\r\n" + 
				"ORDER BY BOARD_PRICE ASC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardTem(int ano){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" +
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND M.AREA_NO = "+ano+"\r\n" + 
				"ORDER BY M.MEM_TEM DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardLikeSort(int ano){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" +
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND M.AREA_NO = +"+ano+"\r\n" + 
				"ORDER BY BOARD_LIKE DESC";
		return jdbc.selectList(sql,BoardVo.class);
	}
	public List<BoardVo> boardNew(int ano){
		String sql = "SELECT \r\n" + 
				"    BOARD_NO, \r\n" + 
				"    RPAD(BOARD_TITLE, 40, ' ') AS BOARD_TITLE, \r\n" + 
				"    SUBSTR(BOARD_CONTENT, 1, 20) AS BOARD_CONTENT, \r\n" + 
				"    BOARD_PRICE, \r\n" + 
				"    TO_CHAR(BOARD_DATE,'YYYY/MM/DD') BOARD_DATE, \r\n" + 
				"    BOARD_STAT, \r\n" + 
				"    BOARD_LIKE\r\n" +
				"FROM BOARD B\r\n" + 
				"INNER JOIN MEMBER M ON B.MEM_SELLER = M.MEM_ID\r\n" + 
				"WHERE B.DELYN = 'N'\r\n" + 
				"AND M.AREA_NO = "+ano+"\r\n" + 
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
				"    BOARD_STAT, \r\n" + 
				"    BOARD_LIKE\r\n" +
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
				"    BOARD_STAT, \r\n" + 
				"    BOARD_LIKE\r\n" +
				"FROM BOARD\r\n" + 
				"WHERE MEM_BUYER = '"+id+"'\r\n" +
				"AND NOT MEM_BUYER = MEM_SELLER";
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
	
	// 회원 - 공지사항
	public List<Map<String, Object>> noticeList() {
	    String sql = " SELECT NOTICE_NO, SUBSTR(NOTICE_TITLE,0,10) NOTICE_TITLE, SUBSTR(NOTICE_MES,0,20) NOTICE_MES, TO_CHAR(NOTICE_DATE, 'YYYY/MM/DD') NOTICE_DATE\n" + 
	                 " FROM NOTICE\n" + 
	                 " WHERE DELYN = 'N'";
	    return jdbc.selectList(sql);
	}
	
	// 회원 - 공지사항 상세보기
	/* 추후 content 부분 개행 필요 */
	public Map<String, Object> noticeDetail(int no) {
		String sql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_MES, TO_CHAR(NOTICE_DATE, 'YYYY/MM/DD') NOTICE_DATE\r\n" + 
					" FROM NOTICE\r\n" + 
					" WHERE DELYN = 'N'\r\n" + 
					" AND NOTICE_NO = " +no;
		return jdbc.selectOne(sql);
	}
	
	public void makeChatRoom(List<Object>param, int sel) {
		String sql = "INSERT INTO CHATROOM\r\n" + 
				"VALUES(CHAT_NO_SEQ.NEXTVAL,"+sel+",?,?,DEFAULT)";
		jdbc.update(sql,param);
	}
	
	public void sendMessage(List<Object>param,int no) {
		String sql = "INSERT INTO MESSAGE\r\n" + 
				"VALUES(MES_NO_SEQ.NEXTVAL,?,DEFAULT,"+no+",?,?)";
		jdbc.update(sql,param);
	}
	
	public Map<String, Object> maxChatRoomNum() {
		String sql = "SELECT MAX(CHAT_NO)\r\n" + 
				"FROM CHATROOM";
		return jdbc.selectOne(sql);
	}
	
	public List<Map<String, Object>> chatList(List<Object>param) {
		String sql = "SELECT\r\n" + 
				"    C.CHAT_NO,\r\n" + 
				"    C.BOARD_NO,\r\n" + 
				"    C.MEM_ID,\r\n" + 
				"    C.MEM_ID2,\r\n" + 
				"    B.BOARD_TITLE\r\n" + 
				"FROM CHATROOM C, BOARD B\r\n" + 
				"WHERE C.BOARD_NO = B.BOARD_NO \r\n" +
				"AND C.DELYN = 'N'\r\n" +
				"AND C.MEM_ID = ?\r\n" + 
				"OR  C.MEM_ID2 = ?";
		return jdbc.selectList(sql, param);
	}
	
	public void delChatRoom(int no) {
		String sql = "UPDATE CHATROOM\r\n" + 
				"SET DELYN = 'Y'\r\n" + 
				"WHERE CHAT_NO = "+no;
		jdbc.update(sql);
	}
	
	public void finishSell(int bno) {
		String sql ="UPDATE BOARD\r\n" + 
				"SET BOARD_STAT = 'Y'\r\n" + 
				"WHERE BOARD_NO = "+bno;
		jdbc.update(sql);
	}
	public void finishSeller(String seller, int bno) {
		String sql = "UPDATE BOARD\n" + 
				"SET MEM_BUYER = '"+seller+"'\n" + 
				"WHERE BOARD_NO = "+bno;
		jdbc.update(sql);
	}
	public List<MessageVo> pastMessage(int no) {
		String sql = "SELECT\r\n" + 
				"    MESSAGE_ID,\r\n" + 
				"    MESSAGE_CONTENT,\r\n" + 
				"    TO_CHAR(MESSAGE_DATE, 'MM/DD') MESSAGE_DATE,\r\n" + 
				"    MEM_ID,\r\n" + 
				"    MEM_ID2\r\n" + 
				"FROM MESSAGE\r\n" +
				"WHERE CHAT_NO ="+no; 
		return jdbc.selectList(sql, MessageVo.class);
	}
	
	public Map<String, Object> readBno(int con) {
		String sql = "SELECT\r\n" + 
				"    BOARD_NO\r\n"+ 
				"FROM CHATROOM\r\n" + 
				"WHERE CHAT_NO ="+con;
		
		return jdbc.selectOne(sql);
	}
	public Map<String, Object> readSeller(int con) {
		String sql = "SELECT\r\n" + 
				"    MEM_ID2\r\n"+ 
				"FROM CHATROOM\r\n" + 
				"WHERE CHAT_NO ="+con;
		
		return jdbc.selectOne(sql);
	}
	
	public List<Map<String, Object>> checkChatRoom() {
		String sql = "SELECT *\r\n" + 
				"FROM CHATROOM";
		return jdbc.selectList(sql);
	}
	
	public void temUpdate(String s) {
		String sql ="UPDATE MEMBER\n" + 
				"SET MEM_TEM = MEM_TEM+1\n" + 
				"WHERE MEM_ID = '"+s+"'";
		jdbc.update(sql);
	}
}
