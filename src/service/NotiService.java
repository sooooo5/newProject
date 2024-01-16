package service;

import java.util.List;

import dao.NotiDao;
import vo.MessageVo;

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
	
	public List<MessageVo> getNoti(int no) {
		return ndao.getNoti(no);
	}
}
