package service;

import java.util.List;

import dao.NotiDao;
import vo.Fboard;

public class NotiService {
	private static NotiService singleTon = null;

	private NotiService() {
	};

	public static NotiService getInstance() {
		if (singleTon == null) {
			singleTon = new NotiService();
		}
		return singleTon;
	}
	NotiDao ndao = NotiDao.getInstance();
	
	public List<Fboard> getNoti() {
		return ndao.getNoti();
	}
	
	public List<Fboard> getList() {
		return ndao.getList();
	}
	
	public void setNoti(int no) {
		ndao.setNoti(no);
	}
}
