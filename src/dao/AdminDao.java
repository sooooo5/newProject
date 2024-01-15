package dao;

import java.util.List;
import java.util.Map;

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
	
	// 관리자 - 로그인
	public AdminVo login(List<Object> param) {
		String sql = " SELECT *\r\n" + 
				" FROM ADMIN\r\n" + 
				" WHERE ADMIN_ID = ? \r\n" + 
				" AND ADMIN_PASS = ? ";
		return jdbc.selectOne(sql,param,AdminVo.class);
	}
	
	// 관리자 - 공지사항
	public List<Map<String, Object>> noticeList() {
	    String sql = " SELECT NOTICE_NO, SUBSTR(NOTICE_TITLE,0,10) NOTICE_TITLE, SUBSTR(NOTICE_MES,0,20) NOTICE_MES, TO_CHAR(NOTICE_DATE, 'YYYY/MM/DD') NOTICE_DATE\n" + 
	                 " FROM NOTICE\n" + 
	                 " WHERE DELYN = 'N'";
	    return jdbc.selectList(sql);
	}
	
	// 관리자 - 공지사항 상세보기
	/* 추후 content 부분 개행 필요 */
	public Map<String, Object> noticeDetail(int no) {
		String sql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_MES, TO_CHAR(NOTICE_DATE, 'YYYY/MM/DD') NOTICE_DATE\r\n" + 
					" FROM NOTICE\r\n" + 
					" WHERE DELYN = 'N'\r\n" + 
					" AND NOTICE_NO = " +no;
		return jdbc.selectOne(sql);
	}

	// 관리자 - 공지사항 등록
	public void noticeInsert(List<Object> param) {
	    String sql = " INSERT INTO NOTICE "+
	                 " VALUES (notice_seq.NEXTVAL, ?, ?, SYSDATE, 'N')";

	    jdbc.update(sql, param);
	}
	
	// 관리자 - 공지사항 수정, 삭제
	public void noticeUpdate(List<Object> list, int sel) {
		
		String sql_front = " UPDATE NOTICE "
						+  " SET ";
		String format = "%s = ?";	//%s에 구멍을 뚫어놓으면 format값이 들어감
		String sql = sql_front;
		if(sel == 0) {
			sql += " DELYN = 'Y'";
		}
		if(sel == 1 || sel == 3) {
			sql += String.format(format, " NOTICE_TITLE");
		}
		if(sel == 2 || sel == 3) {
			if(sel == 3) sql+=", ";
			sql += String.format(format, " NOTICE_MES");
		}
		sql += " WHERE NOTICE_NO = ?";
		jdbc.update(sql, list);
	}
	
	// 관리자 - 회원목록 조회
	public List<Map<String, Object>> memberList() {
	    String sql = " SELECT MEM_ID, MEM_NAME, MEM_NICK, MEM_ADDRESS, MEM_TEL, MEM_TEM, AREA_NO\n" + 
	                 " FROM MEMBER\n" + 
	                 " WHERE DELYN = 'N'";
	    return jdbc.selectList(sql);
	}
	
}
