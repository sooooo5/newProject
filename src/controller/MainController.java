package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noti.ThreadNoti;
import service.AdminService;
import service.BoardService;
import service.NotiService;
import service.UserService;
import util.Print;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.BoardVo;
import vo.MessageVo;
import vo.UserVo;

public class MainController extends Print {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	UserService userService = UserService.getInstance();
	AdminService adminService = AdminService.getInstance();
	BoardService boardService = BoardService.getInstance();
	NotiService nService = NotiService.getInstance();
	public static void main(String[] args) {
		ThreadNoti tn = new ThreadNoti();
		tn.start();
		sessionStorage.put("noti", tn);
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
			case ADMIN_BOARD_LIST:
				view = adminBoardList();
				break;
			case ADMIN_DEL:
				view = adminBoardDelete();
				break;
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
			case ADMIN_NOTICE_DETAIL:
				view = adminNoticeDetail();
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
			case NOTICE:
				view = notice();
				break;
			case NOTICE_DETAIL:
				view = noticeDetail();
				break;
			case CHAT_MESSAGE:
				view = chatMessage();
				break;
			case CHAT_LOG:
				view = chatLog();
				break;
			default:
				break;
			}
		}
	}
	
	private View chatLog() {
		UserVo user = (UserVo) MainController.sessionStorage.get("user");
		String id = user.getMem_id();
		String seller = (String) sessionStorage.get("seller");
		int no = (int) sessionStorage.get("chatno");
		int bno = (int) sessionStorage.get("bno");
		boolean flag =true;
		
		while(flag) {
			String message = ScanUtil.nextLine("메세지입력>>  (exit 입력시 종료)\n");
			if(message.equals("exit")) {
				ThreadNoti tn = (ThreadNoti) sessionStorage.get("noti");
				tn.setStop(true);
				flag = false;
				return View.CHAT_MESSAGE;
			}
			List<Object> param = new ArrayList();
			param.add(message);
			param.add(id);
			param.add(seller);
			boardService.sendMessage(param, no);
		}
		return View.CHAT_LOG;
	}

	private View chatMessage() {
		UserVo user = (UserVo) MainController.sessionStorage.get("user");
		String id = user.getMem_id();
		String seller = (String) sessionStorage.get("seller");
		int no = (int) sessionStorage.get("chatno");
		int bno = (int) sessionStorage.get("bno");
		boolean flag =true;
		
				
		System.out.println("1.채팅방 목록");
		System.out.println("2.거래 확정");   //채팅방도 사라진다.
		System.out.println("3.전체 게시판");
		System.out.println("4.채팅방 삭제");
		
		int sel = ScanUtil.nextInt(" ");
		if(sel == 1) {
			return View.CHAT_LIST;
		}
		if(sel == 2) {
			boardService.delChatRoom(no);
			boardService.finishSell(bno);
			System.out.println("거래 완료!");
			return View.CHAT_LIST;
		}
		if(sel == 3) {
			return View.BOARD_LIST;
		}
		if(sel == 4) {
			System.out.println("채팅방 삭제");
			boardService.delChatRoom(no);
			return View.CHAT_LIST;
		}
		return View.CHAT_LIST;
	}
	//끝
	private View myBuy() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		List<BoardVo> list = boardService.myBuy(id);
		for (BoardVo boardVo : list) {
			int no =boardVo.getBoard_no();
			String title = boardVo.getBoard_title();
			String content = boardVo.getBoard_content();
			int price = boardVo.getBoard_price();
			String date = boardVo.getBoard_date();
			String stat = boardVo.getBoard_stat();
			int like = boardVo.getBoard_like();
			System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);;
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
	//끝
	private View mySell() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
		List<BoardVo> list = boardService.mySell(id);
		for (BoardVo boardVo : list) {
			int no =boardVo.getBoard_no();
			String title = boardVo.getBoard_title();
			String content = boardVo.getBoard_content();
			int price = boardVo.getBoard_price();
			String date = boardVo.getBoard_date();
			String stat = boardVo.getBoard_stat();
			int like = boardVo.getBoard_like();
			System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
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
	//끝
	private View boardSellerItem() {
		String seller = (String) sessionStorage.get("seller");
		System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
		List<BoardVo> sellerI = boardService.boardSellerItem(seller);
		for (BoardVo boardVo : sellerI) {
			int no =boardVo.getBoard_no();
			String title = boardVo.getBoard_title();
			String content = boardVo.getBoard_content();
			int price = boardVo.getBoard_price();
			String date = boardVo.getBoard_date();
			String stat = boardVo.getBoard_stat();
			int like = boardVo.getBoard_like();
			System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
		}
		System.out.println("1. 판매글 상세보기");
		System.out.println("2. 돌아가기");
		int sel = ScanUtil.menu();
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
	//끝
	private View boardSeller() {
		String seller = (String) sessionStorage.get("seller");
		List<UserVo> sellerP = boardService.boardSeller(seller);
		System.out.println("닉네임\t매너온도\t지역번호");
		for (UserVo userVo : sellerP) {
			String nick = userVo.getMem_nick();
			int tem = userVo.getMem_tem();
			int area = userVo.getArea_no();
			System.out.println(nick+"\t"+tem+"\t"+area);
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
		String yn =ScanUtil.nextLine("삭제 하시겠습니까? (y/n)");
		if(yn.equalsIgnoreCase("y")) {
			boardService.boardDel(con);
			System.out.println("삭제 되었습니다.");
			return View.BOARD_LIST;
		}else {
			return View.BOARD_DETAIL;
		}
		
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
	
	
	// 관리자 - 거래글 리스트 
	private View adminBoardList() {
		List<BoardVo> list =  adminService.adminBoardList();
		printAdminBoardList(list);
		printAdminBoardListMenu();	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			int boardNo = ScanUtil.nextInt("게시글 번호 입력 : ");
			sessionStorage.put("boardNo", boardNo);
			return View.ADMIN_DEL;		// 거래글 삭제
		case 2:
			return View.ADMIN;			// 관리자 홈
		default:
			return View.ADMIN;			// 예외 : 관리자 홈
		}
	}
	
	
	// 관리자 - 거래글 삭제
	private View adminBoardDelete() {
		int boardNo = (int) sessionStorage.get("boardNo");

	    String confirmDelete = null;
	    while (confirmDelete == null || !confirmDelete.equalsIgnoreCase("Y") && !confirmDelete.equalsIgnoreCase("N")) {
	        // 게시글 삭제 확인
	        confirmDelete = ScanUtil.nextLine("게시글을 삭제하시겠습니까? (Y/N) : ");
	        if (confirmDelete == null || !confirmDelete.equalsIgnoreCase("Y") && !confirmDelete.equalsIgnoreCase("N")) {
	            System.out.println("Y 또는 N을 입력해주세요.");
	        }
	    }
	    printLn(1);
	    if (confirmDelete.equalsIgnoreCase("N")) {
	        System.out.println("삭제가 취소되었습니다.");
	        return View.ADMIN_BOARD_LIST;
	    } else if (confirmDelete.equalsIgnoreCase("Y")) {
	        // 게시글 삭제 서비스 호출
	        boardService.boardDel(boardNo);
	        System.out.println("성공적으로 삭제되었습니다.");
	        return View.ADMIN_BOARD_LIST;
	    } else {
	        System.out.println("잘못된 입력입니다. 삭제를 취소합니다.");
	        return View.ADMIN_BOARD_LIST;
	    }
	}
	

	// 관리자 - 회원목록 조회
	private View memberList() {
		
		List<Map<String, Object>> list = adminService.memberList();
		printMemberList(list);
		printMemberListMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				return View.ADMIN;			// 관리자 홈
			default:
				return View.ADMIN;			// 예외 : 관리자 홈
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
	        printLn(1);
	        System.out.println("공지사항이 성공적으로 등록되었습니다.");
	        return View.ADMIN_NOTICE;
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
	    if (sel == 4) {
	    	printLn(1);
	    	System.out.println("수정이 취소되었습니다.");
	    	return View.ADMIN_NOTICE_DETAIL;
	    }

	    // 수정할 내용을 담을 리스트
	    List<Object> param = new ArrayList<>();
	    String name = null;
	    String content = null;

	    if (sel == 1 || sel == 3) {
	        while (name == null || name.isEmpty()) {
	            name = ScanUtil.nextLine("제목 >> ");
	            if (name.isEmpty()) {
	                System.out.println("제목을 입력해주세요.");
	            } else if (name.getBytes().length > 40) {
	                System.out.println("입력된 제목의 길이: " + name.getBytes().length);
	                System.out.println("제목은 40byte 이하로 입력해주세요.");
	                name = null; // 제목 길이가 초과된 경우 다시 입력 받기
	            }
	        }
	        param.add(name);
	    }

	    if (sel == 2 || sel == 3) {
	        while (content == null || content.isEmpty()) {
	            content = ScanUtil.nextLine("내용 >> ");
	            if (content.isEmpty()) {
	                System.out.println("내용을 입력해주세요.");
	            }
	        }
	        param.add(content);
	    }

	    int noticeNo = (int) sessionStorage.get("noticeNo");
	    param.add(noticeNo);

	    // 수정 내용 반영 전에 사용자에게 확인
	    String confirmUpdate = null;

	    while (confirmUpdate == null || !confirmUpdate.equalsIgnoreCase("Y") && !confirmUpdate.equalsIgnoreCase("N")) {
	        confirmUpdate = ScanUtil.nextLine("게시글을 수정하시겠습니까?(Y/N) : ");

	        if (confirmUpdate == null || !confirmUpdate.equalsIgnoreCase("Y") && !confirmUpdate.equalsIgnoreCase("N")) {
	            System.out.println("Y 또는 N을 입력해주세요.");
	        }
	    }
	    printLn(1);
	    if (!confirmUpdate.equalsIgnoreCase("Y")) {
	        System.out.println("수정이 취소되었습니다.");
	        return View.ADMIN_NOTICE_DETAIL;
	    }

	    // 게시글 수정 서비스 호출
	    adminService.noticeUpdate(param, sel);
	    System.out.println("성공적으로 수정되었습니다.");

	    return View.ADMIN_NOTICE_DETAIL;
	}

	
	// 관리자 - 공지사항 삭제
	private View noticeDelete() {
	    String confirmDelete = null;
	    while (confirmDelete == null || !confirmDelete.equalsIgnoreCase("Y") && !confirmDelete.equalsIgnoreCase("N")) {
	        // 게시글 삭제 확인
	        confirmDelete = ScanUtil.nextLine("게시글을 삭제하시겠습니까? (Y/N) : ");

	        if (confirmDelete == null || !confirmDelete.equalsIgnoreCase("Y") && !confirmDelete.equalsIgnoreCase("N")) {
	            System.out.println("Y 또는 N을 입력해주세요.");
	        }
	    }

	    printLn(1);
	    if (confirmDelete.equalsIgnoreCase("N")) {
	        System.out.println("삭제가 취소되었습니다.");
	        return View.ADMIN_NOTICE_DETAIL;
	    } else if (confirmDelete.equalsIgnoreCase("Y")) {
	        int noticeNo = (int) sessionStorage.get("noticeNo");
	        List<Object> param = new ArrayList<>();
	        param.add(noticeNo);
	        // 게시글 삭제 서비스 호출
	        adminService.noticeUpdate(param, 0);
	        System.out.println("성공적으로 삭제되었습니다.");
	        return View.ADMIN_NOTICE;
	    } else {
	        System.out.println("잘못된 입력입니다. 삭제를 취소합니다.");
	        return View.ADMIN_NOTICE_DETAIL;
	    }
	}

	
	// 관리자 - 공지사항 상세보기
	private View adminNoticeDetail() {
		int noticeNo = (int) sessionStorage.get("noticeNo");
		// 공지사항 상세보기 출력
		Map<String, Object> detail = adminService.noticeDetail(noticeNo);
		printNoticeDetail(detail);
		printAdminNoticeDetailMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.NOTICE_UPDATE;			// 공지사항 수정
		case 2:
			return View.NOTICE_DEL;				// 공지사항 삭제
		case 3:
			return View.ADMIN_NOTICE;			// 공지사항 리스트
		case 4:
			return View.ADMIN;					// 관리자 홈
		default:
			return View.ADMIN_NOTICE_DETAIL;	// 예외 : 공지사항 상세보기
		}
	}

	
	// 관리자 - 공지사항
	private View adminNotice() {
		// 공지사항 목록 출력
		List<Map<String, Object>> list = adminService.noticeList();
		printNoticeList(list);
		printAdminNoticeListMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				int noticeNo;
			 	Map<String, Object> detail;
				do {
				    noticeNo = ScanUtil.nextInt("게시글 번호 입력 : ");
				    detail = adminService.noticeDetail(noticeNo);
				    if (detail == null) {
				        System.out.println("잘못된 게시글 번호입니다. 다시 입력해주세요.");
				    }
				} while (detail == null);
				
				sessionStorage.put("noticeNo", noticeNo);
				return View.ADMIN_NOTICE_DETAIL; 	 // 공지사항 상세보기
			case 2:
				return View.NOTICE_ADD;				// 공지사항 등록
			case 3:
				return View.ADMIN;					// 관리자 홈
			default:
				return View.NOTICE;					// 예외 : 공지사항 리스트
		}
	}
	
	
	// 회원 - 공지사항 상세보기
	private View noticeDetail() {
		int noticeNo = (int) sessionStorage.get("noticeNo");
		// 공지사항 상세보기 출력
		Map<String, Object> detail = boardService.noticeDetail(noticeNo);
		printNoticeDetail(detail);
		printNoticeDetailMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1:
			return View.NOTICE;				// 공지사항 리스트
		case 2:
			return View.BOARD_LIST;			// 게시물 리스트
		default:
			return View.NOTICE_DETAIL;		// 예외 : 공지사항 상세보기
		}
	}
	
	
	// 회원 - 공지사항
	private View notice() {
		// 공지사항 목록 출력
		List<Map<String, Object>> list = boardService.noticeList();
		printNoticeList(list);
		printNoticeListMenu(); 	//메뉴 출력
		
		int sel = ScanUtil.menu();
		switch (sel) {
			case 1:
				int noticeNo;
			 	Map<String, Object> detail;
				do {
				    noticeNo = ScanUtil.nextInt("게시글 번호 입력 : ");
				    detail = adminService.noticeDetail(noticeNo);
				    if (detail == null) {
				        System.out.println("잘못된 게시글 번호입니다. 다시 입력해주세요.");
				    }
				} while (detail == null);
				
				sessionStorage.put("noticeNo", noticeNo);
				return View.NOTICE_DETAIL;  // 공지사항 상세보기
			case 2:
				return View.BOARD_LIST;		// 게시물 리스트
			default:
				return View.NOTICE;			// 예외 : 공지사항 리스트
		}
	}
	
	
	
	//끝
	private View boardMyProfile() {
		UserVo user = (UserVo) sessionStorage.get("user");
		String id = user.getMem_id();
		System.out.println("닉네임\t온도\t지역번호");
		List<UserVo> list = boardService.printMyProfile(id);
		for (UserVo userVo : list) {
			String nick = userVo.getMem_nick();
			int tem = userVo.getMem_tem();
			int area = userVo.getArea_no();
			System.out.println(nick+"\t"+tem+"\t"+area);
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
	//끝
	private View chatList() {
		UserVo user = (UserVo) MainController.sessionStorage.get("user");
		String id = user.getMem_id();
		List<Object>param = new ArrayList();
		param.add(id);
		param.add(id);
		System.out.println("채팅방번호\t게시물번호\t게시물제목");
		List<Map<String, Object>> list = boardService.chatList(param);
		for (Map<String, Object> map : list) {
			BigDecimal cno =(BigDecimal) map.get("CHAT_NO");
			BigDecimal bno =(BigDecimal) map.get("BOARD_NO");
			String bt =(String) map.get("BOARD_TITLE");
			System.out.println(cno+"\t"+bno+"\t"+bt);
		}
		
		System.out.println("1.채팅방 선택");
		System.out.println("2.나가기");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			int con = ScanUtil.nextInt("채팅방 번호 입력>> ");
			sessionStorage.put("chatno", con);
			ThreadNoti tn = (ThreadNoti) sessionStorage.get("noti");
			Map<String, Object> number = boardService.readBno(con);
			int bno = Integer.valueOf(String.valueOf(number.get("BOARD_NO")));
			Map<String, Object> number2 = boardService.readSeller(con);
			String seller = (String) number2.get("MEM_ID2");
			sessionStorage.put("bno", bno);
			sessionStorage.put("seller", seller);
			tn.setStop(false);
			return View.CHAT_LOG;
		}if(sel == 2) {
			return View.BOARD_LIST;
		}
		return View.BOARD_LIST;
	}
	//끝
	private View boardSort() {
		UserVo user = (UserVo) sessionStorage.get("user");
		int ano = user.getArea_no();
		System.out.println("1.가격순 정렬");
		System.out.println("2.매너온도순 정렬");
		System.out.println("3.좋아요순 정렬");
		System.out.println("4.작성순 정렬");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo>list = boardService.boardPrice(ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
		}
		if(sel == 2) {
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo>list = boardService.boardTem(ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
		}
		if(sel == 3) {
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo>list = boardService.boardLikeSort(ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
		}
		if(sel == 4) {
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo>list = boardService.boardNew(ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
		}
		return View.BOARD_RESULT;
	}
	//끝
	private View boardSearch() {
		UserVo user = (UserVo) sessionStorage.get("user");
		int ano = user.getArea_no();
		System.out.println("1. 카테고리 검색");
		System.out.println("2. 제목 검색");
		System.out.println("3. 내용 검색");
		int sel = ScanUtil.menu();
		if(sel == 1) {
			System.out.println("1.전자기기 2.가구 3.주방 4.도서 5.의류 6.스포츠 7.게임 8.식품 9.기타");
			int cate = ScanUtil.nextInt("카테고리>>");
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo> list = boardService.boardSearch(cate,ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
			return View.BOARD_RESULT;
		}
		if(sel == 2) {
			String title = ScanUtil.nextLine("제목>>");
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo> list = boardService.boardSearch(title,ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title1 = boardVo.getBoard_title();
				String content = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title1+"\t"+content+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
			}
			return View.BOARD_RESULT;
		}
		if(sel == 3) {
			String content = ScanUtil.nextLine("내용>>");
			System.out.println("번호\t제목\t내용\t가격\t등록일\t거래상태\t좋아요");
			List<BoardVo> list = boardService.boardSearch2(content,ano);
			for (BoardVo boardVo : list) {
				int no =boardVo.getBoard_no();
				String title1 = boardVo.getBoard_title();
				String content1 = boardVo.getBoard_content();
				int price = boardVo.getBoard_price();
				String date = boardVo.getBoard_date();
				String stat = boardVo.getBoard_stat();
				int like = boardVo.getBoard_like();
				System.out.println(no+"\t"+title1+"\t"+content1+"\t"+price+"\t"+date+"\t"+stat+"\t"+like);
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
	
	//끝
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
		
		int con = ScanUtil.menu();
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
		UserVo user = (UserVo) MainController.sessionStorage.get("user");
		String id = user.getMem_id();
		String seller = (String) sessionStorage.get("seller");
		List<Object>param = new ArrayList();
		param.add(id);
		param.add(seller);
		
		boolean flag = false;
		List<Map<String, Object>> check = boardService.checkChatRoom();
		for (Map<String, Object> map : check) {
			int a =Integer.valueOf(String.valueOf(map.get("CHAT_NO")));
			int b =Integer.valueOf(String.valueOf(map.get("BOARD_NO")));
			String checkId = (String) map.get("MEM_ID");
			String checkSeller = (String) map.get("MEM_ID2");
			String checkDel =(String) map.get("DELYN");
			if(b==board_no&&checkId.equals(id)&&checkSeller.equals(seller)&&checkDel.equals("N")) {
				flag = true;
				sessionStorage.put("chatno", a);
				break;
			}
		}
		
		
		
		switch (con) {
		case 1: 
			if(id.equals(mem_seller)) {
				System.out.println("자신과 대화할 수 없습니다.");
				return View.BOARD_DETAIL;
			}if(flag) {
				System.out.println("이전 채팅방으로 입장합니다.");
				sessionStorage.put("bno", board_no);
				ThreadNoti tn = (ThreadNoti) sessionStorage.get("noti");
				tn.setStop(false);
			}
			else {
			boardService.makeChatRoom(param,sel);
			Map<String, Object> number = boardService.maxChatRoomNum();
			int no = Integer.valueOf(String.valueOf(number.get("MAX(CHAT_NO)")));
			sessionStorage.put("chatno", no);
			sessionStorage.put("bno", board_no);
			
			ThreadNoti tn = (ThreadNoti) sessionStorage.get("noti");
			tn.setStop(false);
			}
			
			return View.CHAT_LOG;
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
	//끝
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
		UserVo user = (UserVo) sessionStorage.get("user");
		int ano = user.getArea_no();
		
		List<BoardVo> list =  boardService.printBoard(param,ano);
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
		}
		
		System.out.println("1. 거래글 상세보기");
		System.out.println("2. 거래글 작성");
		System.out.println("3. 거래글 검색");
		System.out.println("4. 거래글 정렬");
		System.out.println("5. 채팅방 보기");
		System.out.println("6. 내 프로필 보기");
		System.out.println("7. 공지사항");
		System.out.println("이전페이지 = <, 다음페이지= >");
		String sel = ScanUtil.nextLine("메뉴 선택 : ");
		if(sel.equals("1")) {
			int con = ScanUtil.nextInt("게시글 번호 입력>> ");
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
		case "7":
			return View.NOTICE;
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
		
		int sel = ScanUtil.menu();
		switch (sel) {
		case 1: 
			return View.ADMIN_BOARD_LIST;	// 거래글 리스트
		case 2:
			return View.ADMIN_MEM_LIST;		// 회원목록 조회
		case 3:
			return View.ADMIN_NOTICE;		// 공지사항
		case 4:
			adminLogout();
			return View.MAIN;				// 로그아웃 후 메인으로 이동
		default:
			return View.ADMIN;				// 예외 : 관리자 홈
		}
	}
	
	
	// 관리자 - 로그인
	private View adminLogin() {
		printadminLogin();
		printLn(1);
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
		printLn(1);
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
	    printLn(1);
	    System.out.println("로그아웃되었습니다.");
	    printLn(2);
	    return View.MAIN;
	}
	

	private View home() {
		printHome();
		int sel = ScanUtil.menu();
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
