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
	
	public List<BoardVo> boardSearch(int cate){
		return dao.boardSearch(cate);
	}
	public List<BoardVo> boardSearch(String title){
		return dao.boardSearch(title);
	}
	public List<BoardVo> boardSearch2(String content){
		return dao.boardSearch2(content);
	}
	
	public List<BoardVo> boardPrice(){
		return dao.boardPrice();
	}
	
	public List<BoardVo> boardTem(){
		return dao.boardTem();
	}
	public List<BoardVo> boardLike(){
		return dao.boardLike();
	}
	public List<BoardVo> boardNew(){
		return dao.boardNew();
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
}
