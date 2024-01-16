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
			System.out.println(f);
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
