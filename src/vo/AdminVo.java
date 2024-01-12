package vo;

import lombok.Data;


public class AdminVo {
	 private int admin_no;
	 private String admin_id;
	 private String admin_pass;
	 private String admin_name;
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	@Override
	public String toString() {
		return "AdminVo [admin_no=" + admin_no + ", admin_id=" + admin_id + ", admin_pass=" + admin_pass
				+ ", admin_name=" + admin_name + "]";
	}
}
