package util;

public enum View { 			
	MAIN,					// 기본화면
	MEMBER,					// 멤버 로그인
	MEMBER_JOIN,			// 회원가입
	MEMBER_LOGIN,			// 멤버 로그인 로직
	
	ADMIN,					// 관리자 로그인
	ADMIN_LOGIN,			// 관리자 로그인 로직
	
	ADMIN_BOARD_LIST,		// 관리자 거래글 리스트
	ADMIN_DEL,				// 관리자 거래글 삭제
	ADMIN_MEM_LIST,			// 관리자 회원목록 조회
	
	ADMIN_NOTICE,			// 관리자 공지사항 리스트
	ADMIN_NOTICE_DETAIL,	// 관리자 공지사항 상세보기
	NOTICE_ADD,				// 관리자 공지사항 등록
	NOTICE_UPDATE,			// 관리자 공지사항 수정
	NOTICE_DEL,				// 관리자 공지사항 삭제
	
	
	BOARD_LIST,				// 게시판 리스트
	BOARD_DETAIL,			// 게시글 상세보기
	BOARD_WRITE,			// 게시글 작성
	BOARD_SEARCH,			// 게시글 검색
	BOARD_SORT,				// 게시글 정렬
	BOARD_MY_PROFILE,		// 내 프로필 보기
	
	BOARD_UPDATE,			// 게시글 수정
	BOARD_DEL,				// 게시글 삭제
	BOARD_LIKE,				// 게시글 좋아요
	BOARD_SELLER,			// 판매자 프로필 보기
	
	BOARD_SELLER_ITEM,		// 판매자 프로필 에서 판매자 판매목록 보기
	BOARD_RESULT,			// 검색/정렬 후
	
	CHAT_LIST,				// 채팅방 목록
	CHAT_MESSAGE,			// 채팅창
	CHAT_LOG,				// 채팅진행창
	
	MY_SELL,				// 내 판매 목록
	MY_BUY,					// 내 구매 목록
	MY_PROFILE_UPDATE,		// 내 프로필 변경
	
	NOTICE,					// 공지사항 리스트
	NOTICE_DETAIL			// 공지사항 상세보기
}
