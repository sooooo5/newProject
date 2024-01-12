package noti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.NotiService;
import vo.Fboard;

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
		List<Fboard> l = ns.getNoti();
		
		
		for (Fboard f : l) {
			int no = f.getBoard_no();
			if (messge.containsKey(no)) {
				continue;
			}
			System.out.println(f);
			messge.put(no, "");
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
