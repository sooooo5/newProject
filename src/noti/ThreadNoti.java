package noti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.MainController;
import service.NotiService;
import vo.MessageVo;

public class ThreadNoti extends Thread {

	Map<Integer, String> messge = new HashMap();
	NotiService ns = NotiService.getInstance();
	boolean stop = true;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (true) {
			while (!stop) {
				timer();
				printMessege();
			}
			timer();
		}		
	}
	
	public void printMessege() {
		int no = (int) MainController.sessionStorage.get("chatno");
		List<MessageVo> l = ns.getNoti(no);
		
		for (MessageVo f : l) {
			int cno = f.getMessage_id();
			if (messge.containsKey(cno)) {
				continue;
			}
			int rn = f.getRn();
			String send = f.getMem_nick();
			String content = f.getMessage_content();
			String date = f.getMessage_date();
			System.out.println("번호:"+rn+"보낸사람:"+send+"내용"+content+"날짜"+date);
			messge.put(cno, "");
		}
	}
	
	private void timer() {
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
