package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.BoardService;
import service.UserService;
import util.Print;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.UserVo;

public class MainController extends Print {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	AdminService adminService = AdminService.getInstance();
	BoardService boardService = BoardService.getInstance();
	
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
			case MAIN:
				view = home();
				break;
			case ADMIN:
				view = admin();
				break;
			case MEMBER:
				view = member();
				break;
			case MEMBER_LOGIN:
				view = memberLogin();
				break;
			case MEMBER_JOIN:
				view = memberJoin();
				break;
			case BOARD_LIST:
				view = boardList();
				break;
			default:
				break;
			}
		}
	}
	private View boardList() {
		System.out.println("전체 게시판 출력");
		List<Map<String, Object>> list =  boardService.printBoard();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		System.out.println("1. 거래글 상세보기");
		System.out.println("2. 거래글 작성");
		System.out.println("3. 거래글 검색");
		System.out.println("4. 거래글 정렬");
		System.out.println("5. 채팅방 보기");
		System.out.println("6. 내 프로필 보기");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1: 
			return View.MAIN;
		case 2:
			return View.ADMIN;
		case 3:
			return View.ADMIN;
		case 4:
			return View.ADMIN;
		case 5:
			return View.ADMIN;
		case 6:
			return View.ADMIN;
		default:
			return View.MAIN;
		}
	}

	private View memberJoin() {
		List<Object> param = new ArrayList();
		String id = ScanUtil.nextLine("ID>>");
		String pass = ScanUtil.nextLine("PASS>>");
		String name = ScanUtil.nextLine("이름>>");
		String nick = ScanUtil.nextLine("별명>>");
		String address = ScanUtil.nextLine("주소>>");
		String tel = ScanUtil.nextLine("전화번호>>");
		int area = ScanUtil.nextInt("지역번호>>");
		param.add(id);
		param.add(pass);
		param.add(name);
		param.add(nick);
		param.add(address);
		param.add(tel);
		param.add(area);
		
		userService.memberJoin(param);
		
		
		return View.MEMBER;
	}

	private View member() {
		System.out.println("1. 회원 로그인");
		System.out.println("2. 회원 가입");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1: 
			return View.MEMBER_LOGIN;
		case 2:
			return View.MEMBER_JOIN;
		default:
			return View.MEMBER;
		}
	}

	private View memberLogin() {
		String id = ScanUtil.nextLine("ID>>");
		String pass = ScanUtil.nextLine("PASS>>");
		
		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pass);
		
		boolean loginPass = userService.login(param);
		if(loginPass) {
			UserVo user = (UserVo) sessionStorage.get("user");
			System.out.println(user.getMem_name()+"님 환영합니다.");
		}else {
			System.out.println("1.다시 로그인 하시겠습니까?");
			System.out.println("2.회원가입");
			System.out.println("3.홈");
			
			int sel = ScanUtil.menu();
			if(sel==1) return View.MEMBER_LOGIN;
			if(sel==2) return View.MEMBER_JOIN;
			if(sel==3) return View.MAIN;
			
		}
		return View.BOARD_LIST;
	}
	
	private View admin() {
		String id = ScanUtil.nextLine("ID>>");
		String pass = ScanUtil.nextLine("PASS>>");
		
		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pass);
		
		boolean loginPass = adminService.login(param);
		if(loginPass) {
			AdminVo admin = (AdminVo)sessionStorage.get("admin");
			System.out.println(admin.getAdmin_name()+"님 환영합니다.");
		}else {
			System.out.println("1.다시 로그인 하시겠습니까?");
			System.out.println("2.홈");
			
			int sel = ScanUtil.menu();
			if(sel==1) return View.ADMIN;
			if(sel==2) return View.MAIN;
			
		}
		
		return View.MAIN;
	}

	private View home() {
		System.out.println("1. 회원");
		System.out.println("2. 관리자");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1: 
			return View.MEMBER;
		case 2:
			return View.ADMIN;
		default:
			return View.MAIN;
		}
	}
}
