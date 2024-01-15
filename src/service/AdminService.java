package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.AdminDao;
import vo.AdminVo;
import vo.UserVo;

public class AdminService {
	private static AdminService instance = null;

	private AdminService() {
	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	AdminDao dao = AdminDao.getInstance();
	
	// 관리자 - 로그인
	public boolean login(List<Object> param) {
		AdminVo admin =  dao.login(param);
		if(admin != null) {
			MainController.sessionStorage.put("admin", admin);
			return true;
		}
		return false;
	}
	
	// 관리자 - 공지사항 리스트
	public List<Map<String, Object>> noticeList() {
		List<Map<String, Object>> list = dao.noticeList();
		
		for (Map<String, Object> map : list) {
			String title   = (String) map.get("NOTICE_TITLE");
			String content = (String) map.get("NOTICE_MES");
			// 글 출력 길이 제한
			if(title.length() > 5) {
				title = title.substring(0, 5)+"...";
			}
			if(content.length() > 10) {
				content = content.substring(0, 10)+"...";
			}
			// 개행문자 제거
			content = content.replace("\n", "");
			content = content.replace("\r", "");
			content = content.replace("\r\n", "");
			
			map.put("NOTICE_TITLE", title);
			map.put("NOTICE_MES", content);
		}
		return list;
	}

	// 관리자 - 공지사항 상세보기
	public Map<String, Object> noticeDetail(int no) {
		return dao.noticeDetail(no);
	}

	// 관리자 - 공지사항 등록
	public void noticeInsert(List<Object> param) {
		dao.noticeInsert(param);
	}
	
	// 관리자 - 공지사항 수정, 삭제
	public void noticeUpdate(List<Object> param, int sel) {
		dao.noticeUpdate(param, sel);
	}

	// 관리자 - 회원목록 조회
	public List<Map<String, Object>> memberList() {
		List<Map<String, Object>> list = dao.memberList();
		return list;
	}

	
	
}
