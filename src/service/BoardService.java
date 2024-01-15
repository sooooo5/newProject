package service;

import java.util.List;
import java.util.Map;

import dao.BoardDao;
import vo.BoardVo;

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
	
	public List<BoardVo> printBoard(){
		return dao.printBoard();
	}
	
	public BoardVo boardDetail(int sel) {
		return dao.boardDetail(sel);
	}
}
