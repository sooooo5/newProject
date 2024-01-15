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
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case NOTICE:
				view = notice();
				break;
			case NOTICE_DETAIL:
				view = noticeDetail();
				break;
			case NOTICE_ADD:
				view = noticeInsert();
				break;
			case NOTICE_UPDATE:
				view = noticeUpdate();
				break;
			case NOTICE_DEL:
				view = noticeDelete();
				break;
			case ADMIN_MEM_LIST:
				view = memberList();
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
			case BOARD_UPDATE:
				view = boardUpdate();
				break;
			case BOARD_DEL:
				view = boardDel();
				break;
			case BOARD_SELLER:
				view = boardSeller();
				break;
			case BOARD_SELLER_ITEM:
				view = boardSellerItem();
				break;
			case BOARD_RESULT:
				view = boardResult();
				break;
			case MY_SELL:
				view = mySell();
				break;
			case MY_PROFILE_UPDATE:
				view = myProfileUpdate();
				break;
			case MY_BUY:
				view = myBuy();
				break;
			default:
				break;
			}
		}
	}
	
	private View myBuy() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		List<BoardVo> list = boardService.myBuy(id);
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
		}
		System.out.println("1.게시글 상세보기");
		System.out.println("2.돌아가기");
		
		int con = ScanUtil.nextInt("게시글 번호 선택>>");
		sessionStorage.put("bno", con);
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOARD_DETAIL;
		case 2:
			return View.BOARD_MY_PROFILE;
		default:
			return View.MY_BUY;
		}
	}

	private View myProfileUpdate() {
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 닉네임 변경");
		System.out.println("3. 지역번호 변경");
		System.out.println("4. 주소 변경");
		System.out.println("5. 전화번호 변경");
		System.out.println("6. 회원 탈퇴");
		int sel = ScanUtil.nextInt("수정할 항목을 고르시오.");
		
		List<Object> param = new ArrayList();
		if(sel ==1) {
			String pass = null;
			while (pass == null || pass.isEmpty()) {
				 pass = ScanUtil.nextLine("PASS >>");
		        if (pass.isEmpty()) {
		            System.out.println("비밀번호를 입력해주세요.");
		        } else if (pass.getBytes().length > 20) {
		            System.out.println("입력된 비밀번호의 길이: " + pass.getBytes().length);
		            System.out.println("비밀번호는 20byte 이하로 입력해주세요.");
		            pass = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(pass);
		}
		if(sel ==2) {
			String nick = null;
			while (nick == null || nick.isEmpty()) {
				 nick = ScanUtil.nextLine("닉네임 >>");
		        if (nick.isEmpty()) {
		            System.out.println("닉네임을 입력해주세요.");
		        } else if (nick.getBytes().length > 40) {
		            System.out.println("입력된 닉네임의 길이: " + nick.getBytes().length);
		            System.out.println("닉네임은 40byte 이하로 입력해주세요.");
		            nick = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(nick);
		}
		if(sel ==3) {
			int area = ScanUtil.nextInt("지역번호 >>");
			param.add(area);
		}
		if(sel ==4) {
			String adress = null;
			while (adress == null || adress.isEmpty()) {
				 adress = ScanUtil.nextLine("주소 >>");
		        if (adress.isEmpty()) {
		            System.out.println("주소를 입력해주세요.");
		        } else if (adress.getBytes().length > 100) {
		            System.out.println("입력된 주소의 길이: " + adress.getBytes().length);
		            System.out.println("주소는 100byte 이하로 입력해주세요.");
		            adress = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(adress);
		}
		if(sel ==5) {
			String tel = null;
			while (tel == null || tel.isEmpty()) {
				 tel = ScanUtil.nextLine("전화번호 >>");
		        if (tel.isEmpty()) {
		            System.out.println("전화번호를 입력해주세요.");
		        } else if (tel.getBytes().length > 13) {
		            System.out.println("입력된 번호의 길이: " + tel.getBytes().length);
		            System.out.println("번호는 13byte 이하로 입력해주세요.");
		            tel = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(tel);
		}
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		param.add(id);
		
		if(sel ==6) {
			String yn = ScanUtil.nextLine("회원 탈퇴 하시겠습니까?(y/n): ");
			if(yn.equalsIgnoreCase("y")) {
				boardService.memDel(id);
				return View.MAIN;
			}else if(yn.equalsIgnoreCase("n")) {
				return View.MY_PROFILE_UPDATE;
			}
		}
		
		boardService.myProfileUpdate(param, sel);
		return View.BOARD_MY_PROFILE;
	}

	private View mySell() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		List<BoardVo> list = boardService.mySell(id);
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
		}
		System.out.println("1.게시글 상세보기");
		System.out.println("2.돌아가기");
		
		int con = ScanUtil.nextInt("게시글 번호 선택>>");
		sessionStorage.put("bno", con);
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOARD_DETAIL;
		case 2:
			return View.BOARD_MY_PROFILE;
		default:
			return View.MY_SELL;
		}
	}

	private View boardResult() {
		System.out.println("1.거래글 상세");
		System.out.println("2.거래글 검색으로 돌아가기");
		System.out.println("3.거래글 정렬로 돌아가기");
		System.out.println("4.전체 리스트");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			int con = ScanUtil.nextInt("게시물 번호 입력");
			sessionStorage.put("bno", con);
			return View.BOARD_DETAIL;
		}
		if(sel == 2) {
			return View.BOARD_SEARCH;
		}
		if(sel == 3) {
			return View.BOARD_SORT;
		}
		if(sel == 4) {
			return View.BOARD_LIST;
		}
		return View.BOARD_RESULT;
	}

	private View boardSellerItem() {
		String seller = (String) sessionStorage.get("seller");
		List<BoardVo> sellerI = boardService.boardSellerItem(seller);
		for (BoardVo boardVo : sellerI) {
			System.out.println(boardVo);
		}
		System.out.println("1. 판매글 상세보기");
		System.out.println("2. 돌아가기");
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		if(sel == 1) {
			int con = ScanUtil.nextInt("게시글 번호 입력>>");
			sessionStorage.put("bno", con);
			boardService.boardViews(con);
		}
		switch (sel) {
		case 1: 
			return View.BOARD_DETAIL;
		case 2: 
			return View.BOARD_SELLER;
		default :
			return View.BOARD_SELLER_ITEM;
		}
	}

	private View boardSeller() {
		String seller = (String) sessionStorage.get("seller");
		List<UserVo> sellerP = boardService.boardSeller(seller);
		for (UserVo userVo : sellerP) {
			System.out.println(userVo);
		}
		System.out.println("1.판매 목록 보기");
		System.out.println("2.돌아가기");
		
		int sel =ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.BOARD_SELLER_ITEM;
		case 2:
			return View.BOARD_DETAIL;
		default:
			return View.BOARD_SELLER;
		}
	}

	private View boardDel() {
		int con = (int) sessionStorage.get("bno");
		boardService.boardDel(con);
		System.out.println("삭제 되었습니다.");
		return View.BOARD_LIST;
	}

	private View boardUpdate() {
		System.out.println("1. 제목");
		System.out.println("2. 내용");
		System.out.println("3. 가격");
		System.out.println("4. 전체");
		System.out.println("5. 취소");
		int sel = ScanUtil.nextInt("수정할 항목을 고르시오.");
		
		List<Object> param = new ArrayList();
		if(sel ==1 || sel ==4) {
			String title = null;
			while (title == null || title.isEmpty()) {
				 title = ScanUtil.nextLine("제목 >>");
		        if (title.isEmpty()) {
		            System.out.println("제목을 입력해주세요.");
		        } else if (title.getBytes().length > 40) {
		            System.out.println("입력된 제목의 길이: " + title.getBytes().length);
		            System.out.println("제목은 40byte 이하로 입력해주세요.");
		            title = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(title);
		}
		if(sel ==2|| sel ==4) {
			String content = null;
			while (content == null || content.isEmpty()) {
				 content = ScanUtil.nextLine("내용 >>");
		        if (content.isEmpty()) {
		            System.out.println("제목을 입력해주세요.");
		        } else if (content.getBytes().length > 1000) {
		            System.out.println("입력된 제목의 길이: " + content.getBytes().length);
		            System.out.println("내용은 1000byte 이하로 입력해주세요.");
		            content = null; // 제목 길이가 초과된 경우 다시 입력 받기
		        }
		    }
			param.add(content);
		}
		if(sel ==3|| sel ==4) {
			int price = ScanUtil.nextInt("가격 >>");
			param.add(price);
		}
		if(sel ==5) {
			return View.BOARD_DETAIL;
		}
		int bno = (int) sessionStorage.get("bno");
		param.add(bno);
		
		boardService.boardUpdate(param,sel);
		
		return View.BOARD_DETAIL;
	}

	// 관리자 - 회원목록 조회
	private View memberList() {
		
		List<Map<String, Object>> list = adminService.memberList();
		printMemberList(list);
		printMemberListMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
			case 1:
				return View.ADMIN;			// 관리자 홈
			default:
				return View.ADMIN;			// 예외 : 공지사항 리스트
		}
	}

	// 관리자 - 공지사항 등록
	private View noticeInsert() {
	    printNoticeInsert();

	    String subject = null;
	    String content = null;

	    // 제목 입력
	    while (subject == null || subject.isEmpty()) {
	        subject = ScanUtil.nextLine("제목 >> ");
	        if (subject.isEmpty()) {
	            System.out.println("제목을 입력해주세요.");
	        } else if (subject.getBytes().length > 40) {
	            System.out.println("입력된 제목의 길이: " + subject.getBytes().length);
	            System.out.println("제목은 40byte 이하로 입력해주세요.");
	            subject = null; // 제목 길이가 초과된 경우 다시 입력 받기
	        }
	    }

	    // 내용 입력
	    while (content == null || content.isEmpty()) {
	        content = ScanUtil.nextLine("내용 >> ");
	        if (content.isEmpty()) {
	            System.out.println("내용을 입력해주세요.");
	        } else if (content.getBytes().length > 600) {
	            System.out.println("입력된 내용의 길이: " + content.getBytes().length);
	            System.out.println("내용은 600byte 이하로 입력해주세요.");
	            content = null; // 내용 길이가 초과된 경우 다시 입력 받기
	        }
	    }

	    // 등록할 내용을 담을 리스트
	    List<Object> param = new ArrayList<>();
	    param.add(subject);
	    param.add(content);

	    try {
	        // 서비스로 전달하기 전에 길이 체크 및 예외 발생
	        adminService.noticeInsert(param);

	        System.out.println("공지사항이 성공적으로 등록되었습니다.");
	        return View.NOTICE;
	    } catch (IllegalArgumentException e) {
	        // 예외 처리: 길이 초과 등의 문제 발생 시
	        System.out.println("오류 발생: " + e.getMessage());
	        System.out.println("다시 입력해주세요.");
	        return noticeInsert(); // 오류 발생 시 다시 입력 받기
	    }
	}

	
	// 관리자 - 공지사항 수정
	private View noticeUpdate() {
	    printNoticeUpdate();

	    int sel = ScanUtil.nextInt("수정할 항목 : ");
	    if (sel == 4) return View.NOTICE_DETAIL;

	    // 수정할 내용을 담을 리스트
	    List<Object> param = new ArrayList<>();
	    if (sel == 1 || sel == 3) {
	        String name = ScanUtil.nextLine("제목 >> ");
	        param.add(name);
	    }
	    if (sel == 2 || sel == 3) {
	        String content = ScanUtil.nextLine("내용 >> ");
	        param.add(content);
	    }
	    int noticeNo = (int) sessionStorage.get("noticeNo");
	    param.add(noticeNo);

	    // 수정 내용 반영 전에 사용자에게 확인
	    String confirmUpdate = ScanUtil.nextLine("게시글을 수정하시겠습니까?(Y/N) : ");
	    if (!confirmUpdate.equalsIgnoreCase("Y")) {
	        System.out.println("수정이 취소되었습니다.");
	        return View.NOTICE_DETAIL;
	    }

	    // 게시글 수정 서비스 호출
	    adminService.noticeUpdate(param, sel);
	    System.out.println("성공적으로 수정되었습니다.");

	    return View.NOTICE_DETAIL;
	}

	
	// 관리자 - 공지사항 삭제
	private View noticeDelete() {
		String confirmDelete = ScanUtil.nextLine("게시글을 삭제하시겠습니까?(Y/N) : ");
		
		if(confirmDelete.equalsIgnoreCase("N")) {
			System.out.println("삭제가 취소되었습니다.");
			return View.NOTICE_DETAIL;
		}
		if(confirmDelete.equalsIgnoreCase("Y")) {
			int noticeNo = (int) sessionStorage.get("noticeNo");
			List<Object> param = new ArrayList();
			param.add(noticeNo);
			
			// 게시글 삭제 서비스 호출
			adminService.noticeUpdate(param, 0);
			System.out.println("성공적으로 삭제되었습니다.");
		}
		return View.NOTICE;
	}

	
	// 관리자 - 공지사항 상세보기
	private View noticeDetail() {
		int noticeNo = (int) sessionStorage.get("noticeNo");
		// 공지사항 상세보기 출력
		Map<String, Object> detail = adminService.noticeDetail(noticeNo);
		printNoticeDetail(detail);
		printNoticeDetailMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.NOTICE_UPDATE;		// 공지사항 수정
		case 2:
			return View.NOTICE_DEL;			// 공지사항 삭제
		case 3:
			return View.NOTICE;				// 공지사항 리스트
		case 4:
			return View.ADMIN;				// 관리자 홈
		default:
			return View.NOTICE_DETAIL;		// 예외 : 공지사항 상세보기
		}
	}

	// 관리자 - 공지사항
	private View notice() {
		// 공지사항 목록 출력
		List<Map<String, Object>> list = adminService.noticeList();
		printNoticeList(list);
		printNoticeListMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
			case 1:
				int noticeNo = ScanUtil.nextInt("게시글 번호 입력 : ");
				sessionStorage.put("noticeNo", noticeNo);
				return View.NOTICE_DETAIL;	// 공지사항 상세보기
			case 2:
				return View.NOTICE_ADD;		// 공지사항 등록
			case 3:
				return View.ADMIN;			// 관리자 홈
			default:
				return View.NOTICE;			// 예외 : 공지사항 리스트
		}
	}
	
	private View boardMyProfile() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		List<UserVo> list = boardService.printMyProfile(id);
		for (UserVo userVo : list) {
			System.out.println(userVo);
		}
		
		System.out.println("1. 내 판매목록");
		System.out.println("2. 내 구매목록");
		System.out.println("3. 개인정보 수정");
		System.out.println("4. 돌아가기");
		System.out.println("5. 로그아웃");
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.MY_SELL;
		case 2:
			return View.MY_BUY;
		case 3:
			return View.MY_PROFILE_UPDATE;
		case 4:
			return View.BOARD_LIST;
		case 5:
			sessionStorage.remove("user");
			return View.MAIN;
		default:
			return View.BOARD_MY_PROFILE;
		}
	}

	private View chatList() {
		// TODO Auto-generated method stub
		return null;
	}

	private View boardSort() {
		System.out.println("1.가격순 정렬");
		System.out.println("2.매너온도순 정렬");
		System.out.println("3.좋아요순 정렬");
		System.out.println("4.작성순 정렬");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			List<BoardVo>list = boardService.boardPrice();
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
		}
		if(sel == 2) {
			List<BoardVo>list = boardService.boardTem();
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
		}
		if(sel == 3) {
			List<BoardVo>list = boardService.boardLike();
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
		}
		if(sel == 4) {
			List<BoardVo>list = boardService.boardNew();
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
		}
		return View.BOARD_RESULT;
	}

	private View boardSearch() {
		System.out.println("1. 카테고리 검색");
		System.out.println("2. 제목 검색");
		System.out.println("3. 내용 검색");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			System.out.println("1.전자기기 2.가구 3.주방 4.도서 5.의류 6.스포츠 7.게임 8.식품 9.기타");
			int cate = ScanUtil.nextInt("카테고리>>");
			List<BoardVo> list = boardService.boardSearch(cate);
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
			return View.BOARD_RESULT;
		}
		if(sel == 2) {
			String title = ScanUtil.nextLine("제목>>");
			List<BoardVo> list = boardService.boardSearch(title);
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
			return View.BOARD_RESULT;
		}
		if(sel == 3) {
			String content = ScanUtil.nextLine("내용>>");
			List<BoardVo> list = boardService.boardSearch2(content);
			for (BoardVo boardVo : list) {
				System.out.println(boardVo);
			}
			return View.BOARD_RESULT;
		}
		return View.BOARD_SEARCH;
	}

	private View boardWrite() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		List<Object>param = new ArrayList();
		String title = null;
		while (title == null || title.isEmpty()) {
			 title = ScanUtil.nextLine("제목 >>");
	        if (title.isEmpty()) {
	            System.out.println("제목을 입력해주세요.");
	        } else if (title.getBytes().length > 40) {
	            System.out.println("입력된 제목의 길이: " + title.getBytes().length);
	            System.out.println("제목은 40byte 이하로 입력해주세요.");
	            title = null; // 제목 길이가 초과된 경우 다시 입력 받기
	        }
	    }
		param.add(title);
		String content = null;
		while (content == null || content.isEmpty()) {
			 content = ScanUtil.nextLine("내용 >>");
	        if (content.isEmpty()) {
	            System.out.println("제목을 입력해주세요.");
	        } else if (content.getBytes().length > 1000) {
	            System.out.println("입력된 제목의 길이: " + content.getBytes().length);
	            System.out.println("내용은 1000byte 이하로 입력해주세요.");
	            content = null; // 제목 길이가 초과된 경우 다시 입력 받기
	        }
	    }
		param.add(content);
		int price = ScanUtil.nextInt("가격 >>");
		param.add(price);
		System.out.println("1.전자기기 2.가구 3.주방 4.도서 5.의류 6.스포츠 7.게임 8.식품 9.기타");
		int cate = ScanUtil.nextInt("카테고리 >>");
		param.add(cate);
		
		boardService.boardWrite(param, id);
		return View.BOARD_LIST;
	}

	private View boardDtail() {
		int sel = (int) sessionStorage.get("bno");
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
		System.out.println(list); //나중에 위에 겟으로 꺼내온걸로 디자인
		
		sessionStorage.put("seller", mem_seller);  //판매자 이름 저장;
		
		System.out.println("1. 채팅하기");
		System.out.println("2. 수정하기");
		System.out.println("3. 삭제하기");
		System.out.println("4. 좋아요 누르기");
		System.out.println("5. 판매자 프로필 보기");
		System.out.println("6. 게시글 리스트");
		int con = ScanUtil.nextInt("메뉴 선택 : ");
		if(con == 4) {
			boardService.boardLike(sel);
			System.out.println("좋아요!");
		}else if(con ==2||con == 3) {
			UserVo user = (UserVo) MainController.sessionStorage.get("user");
			String mem_buyer = user.getMem_id();
			String seller = (String) MainController.sessionStorage.get("seller");
			if(mem_buyer.equals(seller)) {
				if(con==2) {
					return View.BOARD_UPDATE;
				}else if(con == 3) {
					return View.BOARD_DEL;
				}
			}else {
				System.out.println("작성자가 아닙니다.");
				return View.BOARD_DETAIL;
			}
		}
		switch (con) {
		case 1: 
			return View.BOARD_DETAIL;
		case 2:
			return View.BOARD_UPDATE;
		case 3:
			return View.BOARD_DEL;
		case 4:
			return View.BOARD_DETAIL;
		case 5:
			return View.BOARD_SELLER;
		case 6:
			return View.BOARD_LIST;
		default:
			return View.MAIN;
		}
		
	}

	private View boardList() {
		sessionStorage.remove("seller");
		sessionStorage.remove("bno");
		
		Map<String, Object> maxBoard = boardService.maxBoard();
		int boardMax = Integer.valueOf(String.valueOf(maxBoard.get("COUNT(*)")));
		
		int page =1;
		if(sessionStorage.containsKey("page")) page = (int) sessionStorage.get("page");
		int start =1+(page-1)*5;
		int end = page*5;
		
		
		List<Object> param = new ArrayList();
		param.add(start);
		param.add(end);
		
		List<BoardVo> list =  boardService.printBoard(param);
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
		}
		
		System.out.println("1. 거래글 상세보기");
		System.out.println("2. 거래글 작성");
		System.out.println("3. 거래글 검색");
		System.out.println("4. 거래글 정렬");
		System.out.println("5. 채팅방 보기");
		System.out.println("6. 내 프로필 보기");
		System.out.println("이전페이지 = <, 다음페이지= >");
		String sel = ScanUtil.nextLine("메뉴 선택 : ");
		if(sel.equals("1")) {
			int con = ScanUtil.nextInt("게시글 번호 입력>>");
			sessionStorage.put("bno", con);
			boardService.boardViews(con);
		}
		if(sel.equals("<")) {
			page = page -1;
			if(page<1) page =1;
			sessionStorage.put("page", page);
			return View.BOARD_LIST;
		}
		if(sel.equals(">")) {
			if(end <boardMax) page = page+1;
			sessionStorage.put("page", page);
			return View.BOARD_LIST;
		}
		switch (sel) {
		case "1": 
			return View.BOARD_DETAIL;
		case "2":
			return View.BOARD_WRITE;
		case "3":
			return View.BOARD_SEARCH;
		case "4":
			return View.BOARD_SORT;
		case "5":
			return View.CHAT_LIST;
		case "6":
			return View.BOARD_MY_PROFILE;
		default:
			return View.BOARD_LIST;
		}
	}

	private View memberJoin() {
		List<Object> param = new ArrayList();
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
		String name = ScanUtil.nextLine("이름 >> ");
		String nick = ScanUtil.nextLine("별명 >> ");
		String address = ScanUtil.nextLine("주소 >> ");
		String tel = ScanUtil.nextLine("전화번호 >> ");
		int area = ScanUtil.nextInt("지역번호 >> ");
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
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
		
		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pass);
		
		boolean loginPass = userService.login(param);
		if(loginPass) {
			UserVo user = (UserVo) sessionStorage.get("user");
			System.out.println(user.getMem_name()+"님 환영합니다!");
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
	
	
	// 관리자 - 홈
	private View admin() {
		printAdminHome();	// 메뉴 출력
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1: 
			return View.ADMIN_BOARD_LIST;	// 거래글 리스트
		case 2:
			return View.ADMIN_MEM_LIST;		// 회원목록 조회
		case 3:
			return View.NOTICE;				// 공지사항
		case 4:
			return View.ADMIN;				// 관리자 홈
		case 5:
			adminLogout();
			return View.MAIN;				// 로그아웃 후 메인으로 이동
		default:
			return View.ADMIN;				// 예외 : 관리자 홈
		}
	}
	
	
	// 관리자 - 로그인
	private View adminLogin() {
		printadminLogin();
		
		String id = ScanUtil.nextLine("ID>>");
		String pass = ScanUtil.nextLine("PASS>>");
		
		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pass);
		
		boolean loginPass = adminService.login(param);
		if(loginPass) {
			AdminVo admin = (AdminVo)sessionStorage.get("admin");
			printVar();
			System.out.println(admin.getAdmin_name()+"님 환영합니다!");
		}else {
			System.out.println("1.다시 로그인 하시겠습니까?");
			System.out.println("2.홈");
			
			int sel = ScanUtil.menu();
			if(sel==1) return View.ADMIN_LOGIN;
			if(sel==2) return View.MAIN;
			
		}
		
		return View.ADMIN;
	}
	
	// 관리자 - 로그아웃
	private View adminLogout() {
	    // 현재 세션에서 로그인 정보 삭제
	    sessionStorage.remove("admin");

	    System.out.println("로그아웃되었습니다.");
	    return View.MAIN;  // 로그아웃 후 이동할 화면을 지정하세요.
	}
	

	private View home() {
		printHome();
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1: 
			return View.MEMBER;
		case 2:
			return View.ADMIN_LOGIN;
		case 3:
			return View.ADMIN;	//관리자 테스트용 (반영시삭제)
		default:
			return View.MAIN;
		}
	}
}
