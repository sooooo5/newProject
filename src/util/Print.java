package util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import vo.BoardVo;

public class Print {
	public static final String customOrange = "\u001B[38;2;255;111;15m";
	public static final String exit = "\u001B[0m";
	
	
	// 화면 출력 요소
	public void printVarVar() {
		System.out.println(customOrange + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + exit);

	}
	public void printVar() {
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)System.out.println();
	}
	
	
	// 관리자 - 공지사항 : 리스트 출력
	public void printNoticeList(List<Map<String, Object>> list) {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤㅤ 공지사항");
		printVar();
		System.out.println("번호\t제목\t\t\t내용\t\t\t\t작성일");
		printVar();
		
		for (Map<String, Object> map : list) {
			BigDecimal no		= (BigDecimal) map.get("NOTICE_NO");
			String title 		= (String) map.get("NOTICE_TITLE");
			String content 		= (String) map.get("NOTICE_MES");
			String date			= (String) map.get("NOTICE_DATE");
			
			System.out.println(no.intValue()+"\t"+title+"\t\t"+content+"\t"+date);
		}
	}
	
	// 관리자 - 공지사항 상세보기 : 리스트 출력
	public void printNoticeDetail(Map<String, Object> detail) {
		printLn(2);
		printVarVar();
		System.out.println("  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤㅤ 공지사항 상세보기");
		printVar();
		printLn(1);
		
		BigDecimal no		= (BigDecimal) detail.get("NOTICE_NO");
		String title 		= (String) detail.get("NOTICE_TITLE");
		String content 		= (String) detail.get("NOTICE_MES");
		String date			= (String) detail.get("NOTICE_DATE");
		
		System.out.println("제목 : "+title);
		System.out.println("번호 : "+no+ "\t\t\t 작성일 : "+date);
		printVar();
	    for (int i = 0; i < content.length(); i += 55) {
	        int endIndex = Math.min(i + 55, content.length());
	        System.out.println(content.substring(i, endIndex));
	    }
	}
	
	// 관리자 - 공지사항 : 메뉴 출력
	public void printAdminNoticeListMenu() {
		printLn(1);
		printVar();
		printLn(1);
//		System.out.println("1. 공지사항 상세보기");
//		System.out.println("2. 공지사항 등록");
//		System.out.println("3. 관리자 홈");
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ 1. 공지사항 상세보기 ㅤ ㅤ ㅤ 2. 공지사항 등록 ㅤ ㅤ ㅤ 3. 관리자 홈");
		printLn(1);
		printVar();
	}
	
	// 관리자 - 공지사항 상세보기 : 메뉴 출력
	public void printAdminNoticeDetailMenu() {
		printLn(1);
		printVar();
		printLn(1);
//		System.out.println("1. 공지사항 수정");
//		System.out.println("2. 공지사항 삭제");
//		System.out.println("3. 공지사항 리스트");
//		System.out.println("4. 관리자 홈");
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ1. 공지사항 수정 ㅤ ㅤ ㅤ 2. 공지사항 삭제 ㅤ ㅤ ㅤ 3. 공지사항 리스트 ㅤ ㅤ ㅤ 4. 관리자 홈");
		printLn(1);
		printVar();
	}
	
	// 회원 - 공지사항 : 메뉴 출력
	public void printNoticeListMenu() {
		printLn(1);
		printVar();
		System.out.println("1. 공지사항 상세보기");
		System.out.println("2. 게시글 리스트");
		printLn(1);
		printVar();
	}
	
	// 회원 - 공지사항 상세보기 : 메뉴 출력
	public void printNoticeDetailMenu() {
		printLn(1);
		printVar();
		System.out.println("1. 공지사항 리스트");
		System.out.println("2. 게시글 리스트");
		printLn(1);
		printVar();
	}
	
	// 관리자 - 공지사항 등록 : 메뉴 출력
	public void printNoticeInsert() {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤㅤ 공지사항 등록");
		printVar();
	}
	
	// 관리자 - 공지사항 수정 : 메뉴 출력
	public void printNoticeUpdate() {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤㅤ 공지사항 수정");
		printVar();
		printLn(1);
//		System.out.println("1. 제목");
//		System.out.println("2. 내용");
//		System.out.println("3. 전체");
//		System.out.println("4. 취소");
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ   ㅤ ㅤ ㅤㅤ ㅤ 1. 제목 ㅤ ㅤ ㅤ 2. 내용 ㅤ ㅤ ㅤ 3. 전체 ㅤ ㅤ ㅤ 4. 취소");
		printLn(1);
		printVar();
	}
	
	
	// 관리자 - 회원목록 조회 : 리스트 출력
	public void printMemberList(List<Map<String, Object>> list) {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤ 회원목록 조회");
		printVar();
		System.out.println("ID\t\t이름\t닉네임\t주소\t전화번호\t\t매너온도\t지역번호");
		printVar();
		
		for (Map<String, Object> map : list) {
			String id 			= (String) map.get("MEM_ID");
			String name 		= (String) map.get("MEM_NAME");
			String nick			= (String) map.get("MEM_NICK");
			String add			= (String) map.get("MEM_ADDRESS");
			String Tel			= (String) map.get("MEM_TEL");
			BigDecimal Tem		= (BigDecimal) map.get("MEM_TEM");
			BigDecimal area		= (BigDecimal) map.get("AREA_NO");
			
			System.out.println(id+"\t"+name+"\t"+nick+"\t"+add+"\t"+Tel+"\t"+Tem+"\t"+area);
		}
	}
	
	// 관리자 - 회원목록 조회 : 메뉴 출력
	public void printMemberListMenu() {
		printLn(1);
		printVar();
		printLn(1);
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤ 1. 관리자 홈");
		printLn(1);
		printVar();
	}
	
	
	// 관리자 - 거래글 리스트 : 리스트 출력
	public void printAdminBoardList(List<BoardVo> boardList) {
	    printLn(2);
	    printVarVar();
	    System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤ 거래글 리스트");
	    printVar();
	    System.out.println("번호\t제목\t\t\t\t\t가격\t등록일\t\t거래상태\t판매자");
	    printVar();
	    
	    for (BoardVo boardVo : boardList) {
	    	String saleStatus = boardVo.getBoard_stat().equals("Y") ? "판매완료" : "판매중";
	        System.out.println(boardVo.getBoard_no() + "\t" + 
	        				   boardVo.getBoard_title() + "\t" +
	        				   boardVo.getBoard_price() + "\t" +
	                           boardVo.getBoard_date() + "\t" + 
	                           saleStatus  + "\t" + 
	        				   boardVo.getMem_seller());
	    }
	}

	
	// 관리자 - 거래글 리스트 : 메뉴 출력
	public void printAdminBoardListMenu() {
		printLn(1);
		printVar();
		printLn(1);
//		System.out.println("1. 거래글 삭제");
//		System.out.println("2. 관리자 홈");
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ ㅤ ㅤ ㅤ1. 거래글 삭제 ㅤ ㅤ ㅤ 2. 관리자 홈");
		printLn(1);
		printVar();
	}
	
	
	// 관리자 - 홈 : 메뉴 출력
	public void printAdminHome() {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤㅤ 관리자 홈");
		printVar();
		printLn(1);
//		System.out.println("1. 거래글 리스트");
//		System.out.println("2. 회원목록 조회");
//		System.out.println("3. 공지사항");
//		System.out.println("4. 로그아웃");
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ 1. 거래글 리스트 ㅤ ㅤ ㅤ 2. 회원목록 조회 ㅤ ㅤ ㅤ 3. 공지사항 ㅤ ㅤ ㅤ 4. 로그아웃");
		printLn(1);
		printVar();
	}
	
	
	// 관리자 - 로그인 : 메뉴 출력
	public void printadminLogin() {
		printLn(2);
		printVarVar();
		System.out.println(" ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ ㅤ  ㅤ ㅤ  ㅤ  ㅤ  ㅤ  ㅤ 관리자 로그인");
		printVar();
	}
	
	
	// 메인 홈 : 메뉴 출력
	public void printHome() {
		printVarVar();
		printLn(2);
		System.out.println(
				"⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⣶⣶⣶⣄⠀⠀⠀⠀⢠⣾⣿⣷⣶⣶⣦⡀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⠿⠀⠀⣿⣿⡆⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀\n" + 
				"⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡏⠙⠛⢿⣿⣷⡀⠀⠀⢸⣿⣿⠉⠛⠻⣿⣿⣆⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⣷⣶⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" + 
				"⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠈⣿⣿⣧⠀⠀⢸⣿⣿⠀⠀⠀⢸⣿⣿⡆⠀⠀⢻⣿⣿⣶⣶⣶⣶⡆⠀⣿⣿⡏⠉⠀⠀⢀⣀⣀⣀⣀⣀⣀⣀⣀⣙⣛⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" + 
				"⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠀⣿⣿⡟⠀⠀⢸⣿⣿⠀⠀⠀⢸⣿⣿⡇⠀⠀⠈⠉⠉⣉⣉⣉⣉⣀⣀⣛⠛⠀⠀⠀⠀⠻⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠇⠀⠀⠀⠀⠀⠀⠀\n" + 
				"⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡇⠀⣀⣴⣿⣿⠃⠀⠀⢸⣿⣿⠀⢀⣠⣾⣿⡿⠀⠀⠀⢀⣶⣿⡿⠿⠿⠿⠿⠿⣿⣿⡆⠀⠀⠀⠀⣴⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" + 
				"⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⢸⣿⣿⣿⣿⣿⡿⠟⠀⠀⠀⠀⠘⢿⣿⣶⣦⣤⣤⣤⣶⣾⣿⠇⠀⠀⠀⠀⣿⣿⣶⣶⣶⣶⣶⣶⣶⣶⡆⠀⠀⠀");
		printLn(2);
		printVar();
		
//		System.out.println("1. 회원");
//		System.out.println("2. 관리자");
		printLn(1);
		System.out.println(" ㅤ ㅤ ㅤ ㅤ ㅤ ㅤ ㅤ ㅤ ㅤㅤ ㅤ ㅤ ㅤㅤㅤㅤㅤㅤㅤ1. 회원 ㅤ ㅤ ㅤ 2. 관리자");
		printLn(1);
		printVar();
	}
	
}
