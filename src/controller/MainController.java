package controller;

import java.util.ArrayList;
import java.util.Date;
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
import vo.BoardVo;
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
			case BOARD_DETAIL:
				view = boardDtail();
				break;
			case BOARD_WRITE:
				view = boardWrite();
				break;
			case BOARD_SEARCH:
				view = boardSearch();
				break;
			case BOARD_SORT:
				view = boardSort();
				break;
			case CHAT_LIST:
				view = chatList();
				break;
			case BOARD_MY_PROFILE:
				view = boardMyProfile();
				break;
			default:
				break;
			}
		}
	}
	private View boardMyProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	private View chatList() {
		// TODO Auto-generated method stub
		return null;
	}

	private View boardSort() {
		// TODO Auto-generated method stub
		return null;
	}

	private View boardSearch() {
		// TODO Auto-generated method stub
		return null;
	}

	private View boardWrite() {
		// TODO Auto-generated method stub
		return null;
	}

	private View boardDtail() {
		int sel = ScanUtil.nextInt("게시글 선택>>");
		boardService.boardDetail(sel);
		BoardVo list = boardService.boardDetail(sel);
		int board_no = list.getBoard_no();
		String board_title = list.getBoard_title();
		String board_content = list.getBoard_content();
		int board_price = list.getBoard_price();
		String board_date = list.getBoard_date();
		String board_stat = list.getBoard_stat();
		int board_like = list.getBoard_like();
		String mem_seller = list.getMem_seller();
		int cate_id = list.getCate_id();
		
		
		return null;
	}

	private View boardList() {
		System.out.println("전체 게시판 출력");
		List<BoardVo> list =  boardService.printBoard();
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
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
			return View.BOARD_DETAIL;
		case 2:
			return View.BOARD_WRITE;
		case 3:
			return View.BOARD_SEARCH;
		case 4:
			return View.BOARD_SORT;
		case 5:
			return View.CHAT_LIST;
		case 6:
			return View.BOARD_MY_PROFILE;
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
