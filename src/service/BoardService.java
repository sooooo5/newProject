package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.BoardDao;
import util.View;
import vo.BoardVo;
import vo.MessageVo;
import vo.UserVo;

public class BoardService {
	private static BoardService instance = null;

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardService();
		}
		return instance;
	}
	BoardDao dao = BoardDao.getInstance();
	
	public List<BoardVo> printBoard(List<Object>param,int ano){
		return dao.printBoard(param,ano);
	}
	
	public BoardVo boardDetail(int sel) {
		return dao.boardDetail(sel);
	}
	
	public void boardLike(int sel) {
		dao.boardLike(sel);
	}
	
	public void boardViews(int con) {
		dao.boardViews(con);
	}
	
	public void boardDel(int con) {
			dao.boardDel(con);
	}
	
	public void boardUpdate(List<Object> list,int sel) {
		dao.boardUpdate(list, sel);
	}
	
	public List<UserVo> boardSeller(String seller){
		return dao.boardSeller(seller);
	}
	
	public List<BoardVo> boardSellerItem(String seller){
		return dao.boardSellerItem(seller);
	}
	
	public Map<String, Object> maxBoard(){
		return dao.maxBoard();
	}
	
	public void boardWrite(List<Object>param,String id) {
		dao.boardWrite(param, id);
	}
	
	public List<BoardVo> boardSearch(int cate, int ano){
		return dao.boardSearch(cate,ano);
	}
	public List<BoardVo> boardSearch(String title, int ano){
		return dao.boardSearch(title,ano);
	}
	public List<BoardVo> boardSearch2(String content, int ano){
		return dao.boardSearch2(content,ano);
	}
	
	public List<BoardVo> boardPrice(int ano){
		return dao.boardPrice(ano);
	}
	
	public List<BoardVo> boardTem(int ano){
		return dao.boardTem(ano);
	}
	public List<BoardVo> boardLikeSort(int ano){
		return dao.boardLikeSort(ano);
	}
	public List<BoardVo> boardNew(int ano){
		return dao.boardNew(ano);
	}
	
	public List<BoardVo> mySell(String id){
		return dao.mySell(id);
	}
	public List<BoardVo> myBuy(String id){
		return dao.mySell(id);
	}
	
	public List<UserVo> printMyProfile(String id){
		return dao.printMyProfile(id);
	}
	
	public void myProfileUpdate(List<Object> list,int sel) {
		dao.myProfileUpdate(list, sel);
	}
	
	public void memDel(String id) {
		dao.memDel(id);
	}
	
	// ȸ�� - �������� ����Ʈ
	public List<Map<String, Object>> noticeList() {
		List<Map<String, Object>> list = dao.noticeList();
		
		for (Map<String, Object> map : list) {
			String title   = (String) map.get("NOTICE_TITLE");
			String content = (String) map.get("NOTICE_MES");
			// �� ��� ���� ����
			if(title.length() > 5) {
				title = title.substring(0, 5)+"...";
			}
			if(content.length() > 10) {
				content = content.substring(0, 10)+"...";
			}
			// ���๮�� ����
			content = content.replace("\n", "");
			content = content.replace("\r", "");
			content = content.replace("\r\n", "");
			
			map.put("NOTICE_TITLE", title);
			map.put("NOTICE_MES", content);
		}
		return list;
	}

	// ȸ�� - �������� �󼼺���
	public Map<String, Object> noticeDetail(int no) {
		return dao.noticeDetail(no);
	}
	
	public void makeChatRoom(List<Object>param, int sel) {
		dao.makeChatRoom(param,sel);
	}
	
	public void sendMessage(List<Object>param,int no) {
		dao.sendMessage(param, no);
	}
	
	public Map<String, Object> maxChatRoomNum(){
		return dao.maxChatRoomNum();
	}
	public List<Map<String, Object>> chatList(List<Object>param){
		return dao.chatList(param);
	}
	public void delChatRoom(int no) {
		dao.delChatRoom(no);
	}
	
	public Map<String, Object> loadBno(int con){
		return dao.loadBno(con);
	}
	
	public void finishSell(int bno) {
		dao.finishSell(bno);
	}
	public List<MessageVo> pastMessage(int no){
		return dao.pastMessage(no);
	}
	public Map<String, Object> readBno(int con){
		return dao.readBno(con);
	}
	public Map<String, Object> readSeller(int con) {
		return dao.readSeller(con);
	}
}
