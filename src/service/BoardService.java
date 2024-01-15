package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.BoardDao;
import util.View;
import vo.BoardVo;
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
	
	public List<BoardVo> printBoard(List<Object>param){
		return dao.printBoard(param);
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
}
